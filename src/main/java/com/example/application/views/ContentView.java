package com.example.application.views;

import com.example.application.backEnd.service.BookService;
import com.example.application.views.content.BookShapeContent;
import com.vaadin.flow.component.html.Div;

public class ContentView extends Div {


    public ContentView() {
        this.addClassNames("view-content");
//        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
//        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
//        radioGroup.setLabel("Travel class");
//        radioGroup.setItems("Economy", "Business", "First Class", "Economy", "Business", "First Class", "Economy", "Business", "First Class");
    }

}
