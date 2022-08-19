package com.example.application.backEnd.service;

import com.example.application.backEnd.viewModel.BasketViewModel;

import java.util.List;

public interface BasketService {
    void create(BasketViewModel request);
    List<BasketViewModel> getAll();
    BasketViewModel getById(Long id);
    void update(Long id, BasketViewModel request);
    void deleteById(Long id);
}
