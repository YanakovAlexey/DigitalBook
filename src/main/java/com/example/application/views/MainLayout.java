package com.example.application.views;

import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.service.impl.security.AuthenticatedUser;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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
    private final BookService bookService;


    @Autowired
    public MainLayout(AuthenticatedUser authenticatedUser, BookService bookService) {
        this.authenticatedUser = authenticatedUser;
        this.bookService = bookService;
        this.setPadding(false);
        this.addClassNames("main-background");
        this.headerView = new HeaderView(authenticatedUser, bookService);
        add(headerView);
    }

    public HeaderView getHeaderView() {
        return headerView;
    }
}



