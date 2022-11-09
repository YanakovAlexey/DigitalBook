package com.example.application.backEnd.service.impl;

import com.example.application.backEnd.builder.AuthorBuilder;
import com.example.application.backEnd.domain.Author;
import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.reporitory.AuthorRepository;
import com.example.application.backEnd.service.AuthorService;
import com.example.application.backEnd.viewModel.AuthorViewModel;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    AuthorBuilder authorBuilder;

    AuthorRepository authorRepository;

    @Override
    public Author create(Author author) {
        authorBuilder.create(author);
        authorRepository.save(author);
        return author;
    }

    @Override
    public List<AuthorViewModel> getAll() {
        List<Author> authorList = authorRepository.findAll();
        List<AuthorViewModel> authorViewModels = new ArrayList<>();
        for (Author item : authorList) {
            authorViewModels.add(authorBuilder.build(item));
        }
        return  authorViewModels;
    }

    @Override
    public AuthorViewModel getById(Long id) {
        return authorRepository.findById(id)
                .map(authorBuilder::build)
                .orElseThrow(()
                        -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Author> authorOpt = authorRepository.findById(id);
        if (authorOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        authorRepository.deleteById(authorOpt.get().getId());
        authorRepository.deleteById(id);
    }
}