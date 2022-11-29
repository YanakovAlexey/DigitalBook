package com.example.application.backEnd.service;

import com.example.application.backEnd.domain.BasketPosition;
import com.example.application.backEnd.viewModel.BasketPositionViewModel;
import com.example.application.backEnd.viewModel.BasketViewModel;

import java.util.List;

public interface BasketPositionService {

    void create(BasketPosition request);

    List<BasketPositionViewModel> getAll();

    BasketViewModel getById(Long id);

    void deleteById(Long id);

    List<BasketPosition> findAllByIdBasket(Long id);
}
