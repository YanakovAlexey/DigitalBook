package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.viewModel.BookViewModel;

public interface BookBuilder {
    BookViewModel createBook(Book book);

    BookViewModel build(Book request);

    void update(Book book, BookViewModel request);
}
