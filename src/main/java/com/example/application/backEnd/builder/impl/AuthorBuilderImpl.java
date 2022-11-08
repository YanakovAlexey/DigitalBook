package com.example.application.backEnd.builder.impl;

import com.example.application.backEnd.builder.AuthorBuilder;
import com.example.application.backEnd.domain.Author;
import com.example.application.backEnd.viewModel.AuthorViewModel;


public class AuthorBuilderImpl implements AuthorBuilder {
    @Override
    public AuthorViewModel create(Author author) {
        return AuthorViewModel.builder()
                .name(author.getName())
               .build();
    }

    @Override
    public void update(Author author, AuthorViewModel request) {
        author.setName(request.getName());

    }

    @Override
    public AuthorViewModel build(Author item) {
        return AuthorViewModel.builder()
                .name(item.getName()).build();

    }
}
