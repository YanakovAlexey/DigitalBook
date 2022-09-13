package com.example.application.views.search;

import com.example.application.translation.TranslationProvider;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchView extends Div {

    private final TranslationProvider translationProvider = new TranslationProvider();

    @Autowired
    public SearchView() {

        TextField textField = new TextField();

        textField.setPlaceholder(this.translationProvider.getTranslation("searchTitle",
                UI.getCurrent().getLocale()));
        textField.setClearButtonVisible(true);
        textField.setPrefixComponent(VaadinIcon.SEARCH.create());
        addClassNames("view-search");
        textField.addClassNames("view-search-input");

        add(textField);
        addClickShortcut(Key.ENTER);
    }
}
