package com.example.application.backEnd.builder.impl;

import com.example.application.backEnd.builder.AuthorBuilder;
import com.example.application.backEnd.domain.Author;
import com.example.application.backEnd.viewModel.AuthorViewModel;
import org.springframework.stereotype.Component;


@Component
public class AuthorBuilderImpl implements AuthorBuilder {
    @Override
    public AuthorViewModel create(Author author) {
        return AuthorViewModel.builder()
                .id(author.getId())
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
                .id(item.getId())
                .name(item.getName()).build();

    }
}
