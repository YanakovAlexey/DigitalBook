package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.Author;
import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.viewModel.AuthorViewModel;
import com.example.application.backEnd.viewModel.UserViewModel;
import com.example.application.backEnd.viewModel.account.RegistrationViewModel;

public interface AuthorBuilder {
    AuthorViewModel create(Author author);

    void update(Author author, AuthorViewModel request);

    AuthorViewModel build(Author item);

}
