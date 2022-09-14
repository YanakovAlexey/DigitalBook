package com.example.application.backEnd.service.impl;

import com.example.application.backEnd.builder.UsersBuilder;
import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.reporitory.UserRepository;
import com.example.application.backEnd.service.ResponseException;
import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.viewModel.UserViewModel;
import com.example.application.backEnd.viewModel.account.AuthViewModel;
import com.example.application.backEnd.viewModel.account.RegistrationViewModel;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
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
        List<UserViewModel> userViewModels = new ArrayList<>();
        for (Users item : userList) {
            userViewModels.add(usersBuilder.build(item));
        }
        return userViewModels;
    }

    public void deleteById(Long id) {
        Optional<Users> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        userRepository.deleteById(userOpt.get().getId());
        userRepository.deleteById(id);
    }

    public Users create(Users users) {
        usersBuilder.create(users);
        userRepository.save(users);
        return users;
    }

    public void update(Long id, Users users, UserViewModel request) {
        Optional<Users> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (userOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        usersBuilder.update(users, request);
    }

    public UserViewModel getById(Long id) {
        Optional<Users> usersOpt = userRepository.findById(id);
        if (usersOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (usersOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return usersBuilder.build(usersOpt.get());
    }

    @Override
    public void registration(RegistrationViewModel request) throws ResponseException {
        if (request.getPassword().length() < 6) {
            throw new ResponseException("Short password", "Длина пароля должна быть больше 5 символов", 504);
        }

        var userLoginOpt = userRepository.findFirstByUsername(request.getUsername());
        if (userLoginOpt.isPresent()) {
            throw new ResponseException("Такой пользовательно уже существует", "Выберете другое имя", 504);
        }

        var userEmailOpt = userRepository.findFirstByEmail(request.getEmail());
        if (userEmailOpt.isPresent()) {
            throw new ResponseException("Пользователь с email уже существует", "Выберете другое email", 504);
        }

        var entityUser = usersBuilder.regBuild(request);
        userRepository.save(entityUser);
    }

    @Override
    public void auth(AuthViewModel request) throws ResponseException {

        Optional<Users> userOpt = userRepository.findFirstByEmail(request.getLogin());
//        System.out.println(!DigestUtils.md5DigestAsHex(request.getPassword().getBytes())
//                .equals(userOpt.get().getPassword()));
        if (!userOpt.isPresent()){
            throw new ResponseException("Неверный логин или пароль", "Неверный логин или пароль", 102);
        }
    }
}
