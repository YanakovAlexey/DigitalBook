package com.example.application.views.search;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import org.jfree.chart.plot.dial.DialValueIndicator;

public class SearchView extends Div {

    public SearchView() {

        TextField textField = new TextField();
        textField.setPlaceholder("Search");
        textField.setClearButtonVisible(true);
        textField.setPrefixComponent(VaadinIcon.SEARCH.create());
        addClassNames("view-search");
        textField.addClassNames("view-search-input");

        add(textField);
        addClickShortcut(Key.ENTER);

    }
}
