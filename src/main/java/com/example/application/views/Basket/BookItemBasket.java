package com.example.application.views.Basket;

import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;


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
