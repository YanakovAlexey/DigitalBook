package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.Basket;
import com.example.application.backEnd.viewModel.BasketViewModel;

public interface BasketBuilder {
    BasketViewModel createBook(Basket basket);

    BasketViewModel build(Basket request);

    void update(Basket book, BasketViewModel request);
}
