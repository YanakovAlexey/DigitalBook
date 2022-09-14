package com.example.application.views;

import com.example.application.backEnd.service.BookService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;

public class ContentView extends Div {

    public ContentView() {
        this.addClassNames("view-content");

        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        radioGroup.setLabel("Travel class");
        radioGroup.setItems("Economy", "Business", "First Class", "Economy", "Business", "First Class", "Economy", "Business", "First Class");
    }

}
