package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.viewModel.BookViewModel;

public interface BookBuilder {
    BookViewModel createBook(Book book);
    void update(Book book, BookViewModel request);

    BookViewModel build(Book item);
}
