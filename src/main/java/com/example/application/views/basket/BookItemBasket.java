package com.example.application.views.basket;

import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class BookItemBasket extends BookItem {
    VerticalLayout layout = new VerticalLayout();

    Button buyButton = new Button("Купить");
    Button deleteButton = new Button("Удалить");

    public BookItemBasket(BookViewModel bookViewModel) {
        super(bookViewModel);
        layout.add(buyButton, deleteButton);
        add(layout);
    }
}
