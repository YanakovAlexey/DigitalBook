package com.example.application.views.search;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.service.BookService;
import com.example.application.translation.TranslationProvider;
import com.example.application.views.content.BookShapeContent;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SearchView extends Div {

    private final TranslationProvider translationProvider = new TranslationProvider();
    private BookService bookService;
    private BookBuilder bookBuilder;
    private final TextField textField;


    @Autowired
    public SearchView(BookService bookService) {
        this.bookService = bookService;
        this.textField = new TextField();
        setLog();
    }

    public void setLog() {
//        Div list = new Div();
        final var enter = Key.ENTER;

        textField.setPlaceholder(this.translationProvider.getTranslation("searchTitle",
                UI.getCurrent().getLocale()));
        textField.setClearButtonVisible(true);
        textField.setPrefixComponent(VaadinIcon.SEARCH.create());
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.addClassNames("view-search-input");
//        textField.addValueChangeListener(event -> {
//
//            bookShapeContent.
//            enter.getKeys();
//        });

        add(textField);
    }

    public void setTextChangeListener(HasValue.ValueChangeListener<?
            super AbstractField.ComponentValueChangeEvent<TextField, String>> listener) {
        if (listener != null)
            this.textField.addValueChangeListener(listener);
    }
}
