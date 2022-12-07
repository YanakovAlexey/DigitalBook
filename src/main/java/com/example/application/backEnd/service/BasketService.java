package com.example.application.backEnd.service;

import com.example.application.backEnd.domain.Basket;
import com.example.application.backEnd.viewModel.BasketViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BasketService {

    void create(Basket request);

    List<BasketViewModel> getAll();

    BasketViewModel getById(Long id);

    void update(Long id, BasketViewModel request);

    void deleteById(Long id);

}
