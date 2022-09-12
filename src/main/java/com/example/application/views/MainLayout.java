package com.example.application.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


/**
 * The main view is a top-level placeholder for other views.
 */
@Route("/")
public class MainLayout extends VerticalLayout {

    private final HeaderView headerView = new HeaderView();
    private final FooterView footerView = new FooterView();
    private final ContentView contentView = new ContentView();

    public MainLayout() {
        this.setPadding(false);
        this.setHeight("100%");
        add(headerView);
        add(contentView);
        add(footerView);
    }
}



