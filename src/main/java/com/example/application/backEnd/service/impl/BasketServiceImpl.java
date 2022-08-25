package com.example.application.backEnd.service.impl;

import com.example.application.backEnd.builder.BasketBuilder;
import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.domain.Basket;
import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.reporitory.BasketRepository;
import com.example.application.backEnd.reporitory.BookRepository;
import com.example.application.backEnd.reporitory.UserRepository;
import com.example.application.backEnd.service.BasketService;
import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.viewModel.BasketViewModel;
import com.example.application.backEnd.viewModel.BookViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BasketServiceImpl implements BasketService {
    final UsersService usersService;
    final UserRepository userRepository;
    final BookRepository bookRepository;
    final BasketRepository basketRepository;
    final BookBuilder bookBuilder;
    final BasketBuilder basketBuilder;

    public BasketServiceImpl(UsersService usersService, UserRepository userRepository, BookRepository bookRepository, BasketRepository basketRepository, BookBuilder bookBuilder, BasketBuilder basketBuilder) {
        this.usersService = usersService;
        this.userRepository = userRepository;

        this.bookRepository = bookRepository;
        this.basketRepository = basketRepository;
        this.bookBuilder = bookBuilder;
        this.basketBuilder = basketBuilder;
    }


    @Override
    public void create(BasketViewModel request) {
        Optional<Users> userOpt = userRepository.findById(request.getId_user());
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Optional<Book> bookOpt = bookRepository.findById(request.getId_book());
        if (bookOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
//        Basket groceryBasket = basketBuilder.build(userOpt.get(), bookOpt.get());
//        BasketRepository.save(groceryBasket);
    }

    @Override
    public List<BookViewModel> getAll() {
        List<Book> bookList = bookRepository.findAll();
        List<BookViewModel> bookViewModels = new ArrayList<>();
        for(Book item: bookList){
            bookViewModels.add(bookBuilder.build(item));
        }
        return bookViewModels;
    }

    @Override
    public BasketViewModel getById(Long id) {
        return null;
    }

    @Override
    public void update(Long id, BasketViewModel request) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
