package com.example.application.views.bookReader;

import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.translation.TranslationProvider;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class BookItemCustom extends BookItem {

    private final TranslationProvider translationProvider = new TranslationProvider();

    Button readButton = new Button(this.translationProvider.getTranslation("toRead",
            UI.getCurrent().getLocale()));
    Button deleteButton = new Button(this.translationProvider.getTranslation("delete",
            UI.getCurrent().getLocale()));
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
