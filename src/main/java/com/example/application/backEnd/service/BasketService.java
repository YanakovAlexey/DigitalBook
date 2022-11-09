package com.example.application.backEnd.service;

import com.example.application.backEnd.domain.Basket;
import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.viewModel.BasketPositionViewModel;
import com.example.application.backEnd.viewModel.BasketViewModel;
import com.example.application.backEnd.viewModel.BookViewModel;

import java.util.List;

public interface BasketService {

    void create(Basket request);

    void addToBasket(BasketPositionViewModel request);

    List<BasketViewModel> getAll();

    BasketViewModel getById(Long id);

    void update(Long id, BasketViewModel request);

    void deleteById(Long id);

}
