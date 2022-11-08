package com.example.application.backEnd.service;

import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book create(Book request);

    Book create(Book request, MemoryBuffer FileBuffer, MemoryBuffer imageBuffer);

    List<Book> getAll();

    BookViewModel getById(Long id);

    List <Book> getAllByIdGenre(Long id);

    void update(Long id, Book book, BookViewModel request);

    void deleteById(Long id);

    List<Book> getBySearch(String title);

    List<Book> findAllByAuthor(String author);

    List<Book> findAllByIdDiscipline(Long idDiscipline);
}
