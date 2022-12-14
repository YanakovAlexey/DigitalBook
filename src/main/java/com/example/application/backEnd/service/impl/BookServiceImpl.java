package com.example.application.backEnd.service.impl;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.reporitory.BookRepository;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.backEnd.viewModel.UserViewModel;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookBuilder bookBuilder;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookBuilder bookBuilder) {
        this.bookRepository = bookRepository;
        this.bookBuilder = bookBuilder;
    }

    @Override
    public Book create(Book request) {
        if(request == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        bookRepository.save(request);
        return request;
    }

    @Override
    public Book create(Book book, MemoryBuffer fileBuffer, MemoryBuffer imageBuffer) {
        var file = new File("/var/Folder-Content/" + fileBuffer.getFileName());
        try (var fileWriter = new FileOutputStream(file)) {
            var stream = fileBuffer.getInputStream();
            fileWriter.write(stream.readAllBytes());
            book.setFile(fileBuffer.getFileName());
        } catch (IOException ignore) {
            return null; //обработать ошибку
        }
        var file2 = new File("/var/Folder-Image/" + imageBuffer.getFileName());
        try (var coverWriter = new FileOutputStream(file2)) {
            var stream = imageBuffer.getInputStream();
            coverWriter.write(stream.readAllBytes());
            book.setBookImg(imageBuffer.getFileName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bookRepository.save(book);

        return book;
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

        return bookBuilder.build(bookOpt.get());
    }

    @Override
    public List<Book> getAllByIdGenre(Long id) {
        var booksList = bookRepository.findAllByIdDiscipline(id);
        return booksList;
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

    @Override
    public List<Book> getBySearch(String title) {
        return bookRepository.findByTitleContains(title);
    }

    @Override
    public List<Book> findAllByUserId(Long users) {
        var bookList = bookRepository.findAllByUserId(users);
        if(bookList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return bookList;
    }

    @Override
    public List<Book> findAllByAuthor(String author) {
        var authorList = bookRepository.findAllByAuthor(author);
        if(authorList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return authorList;
    }

    @Override
    public List<Book> findAllByIdDiscipline(Long idDiscipline) {
        var genreList = bookRepository.findAllByIdDiscipline(idDiscipline);
        if(genreList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return genreList;
    }

    @Override
    public List<String> allAuthors() {
        var bookList = bookRepository.findAllAuthors();
        if (bookList.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return bookList;
    }

    @Override
    public List<Long> findPublishers() {
        var bookList = bookRepository.findAllPublisher();
        if(bookList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return bookList;
    }

    @Override
    public Book findBookByAuthor(String author) {
        var book = bookRepository.findFirstBookByAuthor(author);
        if (book == null){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return book;
    }

}
