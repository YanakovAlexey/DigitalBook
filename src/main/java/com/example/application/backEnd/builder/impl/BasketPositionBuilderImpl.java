package com.example.application.backEnd.builder.impl;

import com.example.application.backEnd.builder.BasketPositionBuilder;
import com.example.application.backEnd.domain.Basket;
import com.example.application.backEnd.domain.BasketPosition;
import com.example.application.backEnd.viewModel.BasketPositionViewModel;
import org.springframework.stereotype.Component;

@Component
public class BasketPositionBuilderImpl implements BasketPositionBuilder {
    @Override
    public BasketPositionViewModel createBook(BasketPosition basket) {
        return BasketPositionViewModel.builder()
                .id(basket.getId())
                .idBasket(basket.getIdBasket())
                .idBook(basket.getIdBook())
                .price(basket.getPrice())
                .numberOfBooks(basket.getNumberOfBooks())
                .build();
    }

    @Override
    public BasketPositionViewModel build(BasketPosition request) {
        BasketPositionViewModel basket = new BasketPositionViewModel();
        basket.setIdBasket(request.getIdBasket());
        basket.setIdBook(request.getIdBook());
        basket.setPrice(request.getPrice());
        basket.setNumberOfBooks(request.getNumberOfBooks());
        return basket;
    }
}
