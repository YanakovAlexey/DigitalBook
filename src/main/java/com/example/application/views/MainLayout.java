package com.example.application.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.auth.AnonymousAllowed;


/**
 * The main view is a top-level placeholder for other views.
 */

@AnonymousAllowed
public class MainLayout extends VerticalLayout implements RouterLayout {
    private final HeaderView headerView = new HeaderView();
    private final ContentView contentView = new ContentView();

    public MainLayout() {
        this.setPadding(false);
        this.setHeight("100%");

        add(headerView);
        add(contentView);
    }
}



