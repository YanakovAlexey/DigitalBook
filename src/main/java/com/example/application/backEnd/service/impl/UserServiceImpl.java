package com.example.application.backEnd.service.impl;

import com.example.application.backEnd.builder.UsersBuilder;
import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.reporitory.UserRepository;
import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.viewModel.UserViewModel;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UsersService {

    UsersBuilder usersBuilder;
    UserRepository userRepository;

    public List<UserViewModel> getAll() {
        List<Users> userList = userRepository.findAll();
        List<UserViewModel> userDtoList = new ArrayList<>();
        for (Users item : userList) {
            userDtoList.add(usersBuilder.build(item));
        }
        return userDtoList;
    }

    public void deleteById(Long id) {
        Optional<Users> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        userRepository.deleteById(userOpt.get().getId());
        userRepository.deleteById(id);
    }

    public void createUser(Users users) {
        usersBuilder.createUsers(users);
        userRepository.save(users);
    }

    public void updateUser(Long id,  ) {
        Optional<Users> usersOpt = userRepository.findById(id);
        if (usersOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Users updateUsers = usersOpt.get();
        usersBuilder.updateUsers(updateUsers);
        userRepository.save(updateUsers);
    }
}
