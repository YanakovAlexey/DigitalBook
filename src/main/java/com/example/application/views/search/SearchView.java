package com.example.application.views.search;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import org.jfree.chart.plot.dial.DialValueIndicator;

public class SearchView extends Div {

    public SearchView() {
        TextField textField = new TextField();
        textField.getElement().setAttribute("aria-label", "search");
        textField.setPlaceholder("Search");
        textField.setClearButtonVisible(true);
        textField.setPrefixComponent(VaadinIcon.SEARCH.create());
        textField.setWidth("500px");

        add(textField);
        addClickShortcut(Key.ENTER);

        getStyle().set("position", "absolute").set("top", "0").set("right", "0")
                .set("bottom", "0").set("left", "500px").set("margin", "15px");

    }
}
