package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.viewModel.UsersViewModel;

public interface UsersBuilder {
    UsersViewModel createUsers(Users users);
}
