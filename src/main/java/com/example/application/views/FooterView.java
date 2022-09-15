package com.example.application.views;


import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import org.springframework.beans.factory.annotation.Autowired;

public class FooterView extends Div {

    @Autowired
    public FooterView() {
        this.addClassNames("app-nav-footer");

        Anchor aboutUsLink = new Anchor("aboutUs", "О нас");
        aboutUsLink.addClassNames("app-nav-aboutUs");
        this.add(aboutUsLink);
    }
}