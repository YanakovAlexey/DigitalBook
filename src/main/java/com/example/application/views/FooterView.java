package com.example.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;

public class FooterView extends Div {

    public FooterView() {
        add(new Button("Helo"), new Button("world"));
    }
}