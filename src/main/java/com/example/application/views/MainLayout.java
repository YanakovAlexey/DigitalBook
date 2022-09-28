package com.example.application.views;

import com.example.application.backEnd.service.impl.security.AuthenticatedUser;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * The main view is a top-level placeholder for other views.
 */

@AnonymousAllowed
public class MainLayout extends VerticalLayout implements RouterLayout {
    private final HeaderView headerView;
    private final AuthenticatedUser authenticatedUser;

    @Autowired
    public MainLayout(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        this.setPadding(false);
        this.setHeight("100%");
        this.headerView = new HeaderView(authenticatedUser);
        add(headerView);
    }
}



