package com.example.application.views;

import com.example.application.backEnd.service.*;
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
    private final DisciplineService disciplineService;
    private final BookService bookService;
    private final UsersService usersService;


    @Autowired
    public MainLayout(AuthenticatedUser authenticatedUser,
                      DisciplineService disciplineService,
                      BookService bookService, UsersService usersService) {
        this.authenticatedUser = authenticatedUser;
        this.disciplineService = disciplineService;

        this.bookService = bookService;
        this.usersService = usersService;
        this.setPadding(false);
        this.setHeight("100%");
        this.headerView = new HeaderView(authenticatedUser,
                this.disciplineService,
                this.usersService,
                this.bookService);
        


        this.setPadding(false);
        this.setHeight("100%");
        this.addClassNames("main-background");
        add(headerView);
    }

    public HeaderView getHeaderView() {
        return headerView;
    }
}



