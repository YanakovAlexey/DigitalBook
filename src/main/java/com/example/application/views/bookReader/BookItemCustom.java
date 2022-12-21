package com.example.application.views.bookReader;

import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class BookItemCustom extends BookItem {
    Button readButton = new Button("Читать");
    Button deleteButton = new Button("Удалить");
    VerticalLayout layout = new VerticalLayout();

    public BookItemCustom(BookViewModel bookViewModel) {
        super(bookViewModel);
        readButton.setWidth("155px");
        deleteButton.setWidth("155px");
        readButton.addClassNames("basket-content-view");
        deleteButton.addClassNames("basket-delete-button");
        layout.add(readButton, deleteButton);
        add(layout);
    }
}
