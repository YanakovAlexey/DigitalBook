package com.example.application.views.menuBarView;

import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.service.UsersService;
import com.example.application.views.ContentView;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "getAllPublisher", layout = ContentView.class)
@AnonymousAllowed
public class Publishers extends FlexLayout {
    Anchor anchor;
    private final UsersService usersService;
    private final BookService bookService;

    public Publishers(UsersService usersService, BookService bookService) {
        this.usersService = usersService;
        this.bookService = bookService;
        add(getAll());


    }

    private FlexLayout getAll() {
        var flexLayout = new FlexLayout();
        flexLayout.setFlexWrap(FlexWrap.WRAP);
        var publisherList = this.bookService.findPublishers();
        for (Long aLong : publisherList) {
            var user = usersService.getById(aLong);
            flexLayout.add(anchor = new Anchor("get-all-publisher/"
                    + user.get().getId(), user.get().getUsername()));
            anchor.addClassName("all-book");
        }
        return flexLayout;
    }
}



