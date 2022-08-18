package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.viewModel.UserViewModel;

public interface UsersBuilder {
    UserViewModel createUsers(Users users);

    void updateUsers(Users users, UserViewModel request);

    UserViewModel build(Users item);
}
