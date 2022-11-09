package com.example.application.backEnd.service;

import com.example.application.backEnd.domain.Author;
import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.viewModel.AuthorViewModel;
import com.example.application.backEnd.viewModel.UserViewModel;

import java.util.List;

public interface AuthorService {

    Author create(Author request);

    List<AuthorViewModel> getAll();

    AuthorViewModel getById(Long id);

    void deleteById(Long id);
}
