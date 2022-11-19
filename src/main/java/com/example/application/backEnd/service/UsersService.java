package com.example.application.backEnd.service;

import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.viewModel.UserViewModel;
import com.example.application.backEnd.viewModel.account.AuthViewModel;
import com.example.application.backEnd.viewModel.account.RegistrationViewModel;
import com.example.application.models.EnumType;
import org.springframework.security.core.userdetails.User;


import java.util.List;
import java.util.Optional;

public interface UsersService {

    Users create(Users request);

    List<UserViewModel> getAll();

    Optional<Users> getById(Long id);

    void update(Long id, Users users, UserViewModel request);

    void deleteById(Long id);

    Users registration(RegistrationViewModel request) throws ResponseException;

    User auth(AuthViewModel request) throws ResponseException;

    void changePassword(Users users, String oldPassword, String newPassword, String repeatPassword,
                        EnumType type) throws ResponseException;

    void emailVerification(String email, EnumType type);

    boolean restorePassword(String email, String code, String password);
}
