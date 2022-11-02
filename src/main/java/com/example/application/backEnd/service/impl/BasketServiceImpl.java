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
import com.example.application.backEnd.viewModel.BasketPositionViewModel;
import com.example.application.backEnd.viewModel.BasketViewModel;
import com.example.application.backEnd.viewModel.BookViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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
    public void create(Basket request) {
        basketRepository.save(request);
    }



    @Override
    public List<BasketViewModel> getAll() {
        List<Basket> bookList = basketRepository.findAll();
        List<BasketViewModel> basketViewModels = new ArrayList<>();
        for(Basket item: bookList){
            basketViewModels.add(basketBuilder.build(item));
        }
        return basketViewModels;
    }

    @Override
    public BasketViewModel getById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return null;
    }

    @Override
    public void update(Long id, BasketViewModel request) {
        Optional<Basket> basketOpt = basketRepository.findById(id);
        if (basketOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (basketOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
//        basketBuilder.update(request);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Basket> basketOpt = basketRepository.findById(id);
        if (basketOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        basketRepository.deleteById(basketOpt.get().getId());
        basketRepository.deleteById(id);
    }
}
