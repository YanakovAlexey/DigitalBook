package com.example.application.backEnd.service;

import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.viewModel.BookViewModel;

import java.util.List;

public interface BookService {

    Book create(Book request);

    List<Book> getAll();

    BookViewModel getById(Long id);

    void update(Long id, Book book, BookViewModel request);

    void deleteById(Long id);

    List<Book> getBySearch(String title);
}
