package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.Basket;
import com.example.application.backEnd.domain.BasketPosition;
import com.example.application.backEnd.viewModel.BasketPositionViewModel;
import com.example.application.backEnd.viewModel.BasketViewModel;

public interface BasketPositionBuilder {

    BasketPositionViewModel createBook(BasketPosition basket);

    BasketPositionViewModel build(BasketPosition request);

}
