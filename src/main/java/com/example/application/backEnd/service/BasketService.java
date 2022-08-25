package com.example.application.backEnd.service;

import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.viewModel.BasketViewModel;
import com.example.application.backEnd.viewModel.BookViewModel;

import java.util.List;

public interface BasketService {
    void create(BasketViewModel request);

    List<BookViewModel> getAll();

    BasketViewModel getById(Long id);

    void update(Long id, BasketViewModel request);

    void deleteById(Long id);
}
