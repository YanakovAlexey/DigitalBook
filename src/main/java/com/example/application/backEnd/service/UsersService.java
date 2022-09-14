package com.example.application.backEnd.service;

import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.viewModel.UserViewModel;
import com.example.application.backEnd.viewModel.account.ForgotPasswordViewModel;
import com.example.application.backEnd.viewModel.account.RegistrationViewModel;


import java.util.List;

public interface UsersService {

    Users create(Users request);

    List<UserViewModel> getAll();

    UserViewModel getById(Long id);

    void update(Long id, Users users, UserViewModel request);

    void deleteById(Long id);

    void registration(RegistrationViewModel request) throws ResponseException;

    String auth(RegistrationViewModel request);
}
