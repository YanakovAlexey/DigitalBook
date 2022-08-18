package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.domain.BookType;
import com.example.application.backEnd.viewModel.BookTypeViewModel;

public interface BookTypeBuilder {
    BookTypeViewModel create(Book book);

    void update (BookType book, BookTypeViewModel request);
}
