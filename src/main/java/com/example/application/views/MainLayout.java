package com.example.application.views;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.service.BookService;
import com.example.application.views.authorization.AuthorizationView;
import com.example.application.views.content.BookShapeContent;
import com.example.application.views.registration.RegistrationView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import javax.annotation.security.RolesAllowed;


/**
 * The main view is a top-level placeholder for other views.
 */
@Route("/")
@AnonymousAllowed
public class MainLayout extends VerticalLayout implements RouterLayout {

    private final BookService bookService;
    private final BookBuilder bookBuilder;
    private final HeaderView headerView = new HeaderView();
    //    private final FooterView footerView = new FooterView();
    private Component contentView;

    public MainLayout(BookService bookService,
                      BookBuilder bookBuilder) {
        this.bookService = bookService;
        this.bookBuilder = bookBuilder;
        this.setPadding(false);
        this.setHeight("100%");
        contentView = new ContentView();
        add(headerView, contentView);
    }
}



