package com.example.application.views;

import com.example.application.backEnd.service.AuthorService;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.service.DisciplineService;
import com.example.application.backEnd.service.PublisherService;
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
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final BookService bookService;

    @Autowired
    public MainLayout(AuthenticatedUser authenticatedUser,
                      DisciplineService disciplineService,
                      AuthorService authorService,
                      PublisherService publisherService, BookService bookService) {
        this.authenticatedUser = authenticatedUser;
        this.disciplineService = disciplineService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.bookService = bookService;
        this.setPadding(false);
        this.setHeight("100%");
        this.headerView = new HeaderView(authenticatedUser,
                this.disciplineService, 
                this.publisherService, 
                this.authorService,
                this.bookService);
        


        this.setPadding(false);
        this.setHeight("100%");
//        this.headerView = new HeaderView(authenticatedUser, this.bookService);

        this.addClassNames("main-background");
        add(headerView);
    }

    public HeaderView getHeaderView() {
        return headerView;
    }
}



