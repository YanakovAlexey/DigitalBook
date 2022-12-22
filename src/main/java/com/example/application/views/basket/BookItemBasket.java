package com.example.application.views.basket;

import com.example.application.backEnd.service.BasketPositionService;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class BookItemBasket extends BookItem {
    private final BasketPositionService basketPositionService;
    VerticalLayout layout = new VerticalLayout();
    Button buyButton = new Button("Купить");
    Button deleteButton = new Button("Удалить");

    public BookItemBasket(BookViewModel bookViewModel,
                          BasketPositionService basketPositionService) {
        super(bookViewModel);
        this.basketPositionService = basketPositionService;
        buyButton.setWidth("155px");
        deleteButton.setWidth("155px");
        buyButton.addClassNames("basket-content-view");
        deleteButton.addClassNames("basket-delete-button");

        layout.add(buyButton, deleteButton);
        add(layout);
    }

}
