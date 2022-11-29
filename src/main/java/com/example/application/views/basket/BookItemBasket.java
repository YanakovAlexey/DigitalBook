package com.example.application.views.basket;

import com.example.application.backEnd.service.BasketPositionService;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class BookItemBasket extends BookItem {
    VerticalLayout layout = new VerticalLayout();
    private final BasketPositionService basketPositionService;
    Button buyButton = new Button("Купить");
    Button deleteButton = new Button("Удалить");

    public BookItemBasket(BookViewModel bookViewModel, BasketPositionService basketPositionService) {

        super(bookViewModel);
        this.basketPositionService = basketPositionService;

        layout.add(buyButton, deleteButton);
        add(layout);
    }

}
