package com.example.application.backEnd.builder.impl;

import com.example.application.backEnd.builder.BasketBuilder;
import com.example.application.backEnd.domain.Basket;
import com.example.application.backEnd.viewModel.BasketViewModel;

public class BasketBuilderImpl implements BasketBuilder {

    @Override
    public BasketViewModel createBook(Basket basket) {
        return BasketViewModel.builder()
                .id(basket.getId())
                .id_user(basket.getId_user())
                .id_book(Math.toIntExact(basket.getId_book()))
                .priceType(basket.getPriceType())
                .price(basket.getPrice())
                .period(basket.getPeriod())
                .build();

    }

    @Override
    public BasketViewModel build(Basket request) {
        BasketViewModel basket = new BasketViewModel();
        basket.setId_user(request.getId_user());
        basket.setId_book(Math.toIntExact(request.getId_book()));
        basket.setPriceType(request.getPriceType());
        basket.setPrice(request.getPrice());
        basket.setPeriod(request.getPeriod());
        return basket;
    }

    @Override
    public void update(Basket basket, BasketViewModel request) {
        basket.setId_user(request.getId_user());
//        basket.setId_book(request.getId_book());


    }
}
