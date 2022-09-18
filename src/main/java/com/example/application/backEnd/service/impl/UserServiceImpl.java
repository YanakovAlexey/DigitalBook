package com.example.application.backEnd.service.impl;

import com.example.application.backEnd.builder.UsersBuilder;
import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.reporitory.UserRepository;
import com.example.application.backEnd.service.ResponseException;
import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.viewModel.UserViewModel;
import com.example.application.backEnd.viewModel.account.AuthViewModel;
import com.example.application.backEnd.viewModel.account.RegistrationViewModel;
import com.example.application.translation.TranslationProvider;
import com.vaadin.flow.component.UI;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final TranslationProvider translationProvider = new TranslationProvider();

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

        return userRepository.findById(id)
                .map(usersBuilder::build)
                .orElseThrow(()
                        -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @Override
    public void registration(RegistrationViewModel request) throws ResponseException {
        if (request.getPassword().length() < 6) {
            throw new ResponseException(this.translationProvider.getTranslation("shortPassword",
                    UI.getCurrent().getLocale()), this.
                    translationProvider.getTranslation("passwordLengthMustBeMoreThanFiveCharacters",
                            UI.getCurrent().getLocale()), 504);
        }

        var userLoginOpt = userRepository.findFirstByUsername(request.getUsername());
        if (userLoginOpt.isPresent()) {
            throw new ResponseException(this.translationProvider.getTranslation("thisCustomAlreadyExists",
                    UI.getCurrent().getLocale()), this.translationProvider.getTranslation("chooseAnotherName",
                    UI.getCurrent().getLocale()), 504);
        }

        var userEmailOpt = userRepository.findFirstByEmail(request.getEmail());
        if (userEmailOpt.isPresent()) {
            throw new ResponseException(this.translationProvider.getTranslation("userWithEmailAlreadyExists",
                    UI.getCurrent().getLocale()), this.translationProvider.getTranslation("chooseAnotherEmail",
                    UI.getCurrent().getLocale()), 504);
        }

        var entityUser = usersBuilder.regBuild(request);
        userRepository.save(entityUser);
    }

    @Override
    public void auth(AuthViewModel request) throws ResponseException {
        Optional<Users> userOpt = userRepository.findFirstByUsername(request.getLogin());
        var result = userOpt.map(user -> {
            var u = new User(
                    user.getUsername(),
                    user.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_USER"))
            );
            SecurityContext context = SecurityContextHolder.getContext();
            var authentication = new UsernamePasswordAuthenticationToken(
                    u.getUsername(),
                    u.getPassword(),
                    u.getAuthorities()
            );
            context.setAuthentication(authentication);
            return u;
        }).orElseThrow(() -> new ResponseException(this.translationProvider.getTranslation("wrongLoginOrPassword",
                UI.getCurrent().getLocale()), this.translationProvider.getTranslation("wrongLoginOrPassword",
                UI.getCurrent().getLocale()), 102));

        System.out.println(result);
    }

}

