package com.example.application.backEnd.service.impl;

import com.example.application.backEnd.builder.BasketBuilder;
import com.example.application.backEnd.domain.Basket;
import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.reporitory.BasketRepository;
import com.example.application.backEnd.reporitory.BookRepository;
import com.example.application.backEnd.reporitory.UserRepository;
import com.example.application.backEnd.service.BasketService;
import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.viewModel.BasketViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public class BasketServiceImpl implements BasketService {
    final UsersService usersService;
    final UserRepository userRepository;
    final BookRepository bookRepository;
    final BasketRepository basketRepository;
    final BasketBuilder basketBuilder;

    public BasketServiceImpl(UsersService usersService, UserRepository userRepository, BookRepository bookRepository, BasketRepository basketRepository, BasketBuilder basketBuilder) {
        this.usersService = usersService;
        this.userRepository = userRepository;

        this.bookRepository = bookRepository;
        this.basketRepository = basketRepository;
        this.basketBuilder = basketBuilder;
    }


//    @Override
//    public void create(BasketViewModel request) {
//        Optional<Users> userOpt = userRepository.findById(request.getId_user());
//        if (userOpt.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//        Optional<Book> bookOpt = bookRepository.findById(request.getId_book());
//        if (bookOpt.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//        Basket bookBasket = BasketBuilder.build(bookOpt.get(), userOpt.get());
//        BasketRepository.save(groceryBasket);
//    }

    @Override
    public void create(BasketViewModel request) {

    }

    @Override
    public List<BasketViewModel> getAll() {
        return null;
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
