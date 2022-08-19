package com.example.application.backEnd.service;

import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.backEnd.viewModel.UserViewModel;

import java.util.List;

public interface UsersService {

    Users create(Users request);

    List<UserViewModel> getAll();

    UserViewModel getById(Long id);

    void update(Long id, Users users, UserViewModel request);

    void deleteById(Long id);

}
