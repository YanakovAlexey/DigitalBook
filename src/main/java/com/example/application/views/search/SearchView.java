package com.example.application.views.search;

import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.service.UsersService;
import com.example.application.translation.TranslationProvider;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchView extends Div {

    private final TranslationProvider translationProvider = new TranslationProvider();
    private BookService bookService;

    @Autowired
    public SearchView() {

        TextField textField = new TextField();

        textField.setPlaceholder(this.translationProvider.getTranslation("searchTitle",
                UI.getCurrent().getLocale()));
        textField.setClearButtonVisible(true);
        textField.setPrefixComponent(VaadinIcon.SEARCH.create());
//        bookService.getBySearch(textField.getValue());

        addClassNames("view-search");
        textField.addClassNames("view-search-input");

        add(textField);
        addClickShortcut(Key.ENTER);
    }
}
