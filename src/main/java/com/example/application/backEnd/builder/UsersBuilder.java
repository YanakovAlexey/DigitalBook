package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.viewModel.UserViewModel;
import com.example.application.backEnd.viewModel.account.RegistrationViewModel;

public interface UsersBuilder {
    UserViewModel create(Users users);

    void update(Users users, UserViewModel request);

    UserViewModel build(Users item);

    Users regBuild(RegistrationViewModel request);


}
