package com.example.application.backEnd.builder.impl;

import com.example.application.backEnd.builder.BookTypeBuilder;
import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.domain.BookType;
import com.example.application.backEnd.viewModel.BookTypeViewModel;

public class BookTypeBuilderImpl implements BookTypeBuilder {
    @Override
    public BookTypeViewModel create(Book book) {
        return BookTypeViewModel.builder()
                .id(book.getId())
                .title(book.getTitle())
                .build();
    }

    @Override
    public void update(BookType book, BookTypeViewModel request) {
        book.setTitle(request.getTitle());
    }
}
