package com.example.application.backEnd.service.impl;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.reporitory.BookRepository;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.viewModel.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    final BookRepository bookRepository;
    final BookBuilder bookBuilder;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookBuilder bookBuilder) {
        this.bookRepository = bookRepository;
        this.bookBuilder = bookBuilder;
    }

    @Override
    public Book create(Book request) {
        bookBuilder.createBook(request);
        bookRepository.save(request);
        return request;
    }

    @Override
    public List<Book> getAll() {

        List<Book> book = bookRepository.findAll();
        List<BookViewModel> bookList = new ArrayList<>();
        for (Book item : book) {
            bookList.add(bookBuilder.build(item));
        }
        return book;
    }

    @Override
    public BookViewModel getById(Long id) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (bookOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return bookBuilder.build(bookOpt.get());
    }

    @Override
    public void update(Long id, Book book, BookViewModel request) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (bookOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        bookBuilder.update(book, request);
    }


    @Override
    public void deleteById(Long id) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (bookOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        bookRepository.delete(bookOpt.get());
    }
}
