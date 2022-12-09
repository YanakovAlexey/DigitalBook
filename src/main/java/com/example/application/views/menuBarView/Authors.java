package com.example.application.views.menuBarView;

import com.example.application.backEnd.service.BookService;
import com.example.application.views.ContentView;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "getAllAuthors", layout = ContentView.class)
@AnonymousAllowed
public class Authors extends FlexLayout {
    private final BookService bookService;
    Anchor anchor;

    public Authors(BookService bookService) {
        this.bookService = bookService;
        add(getAll());
    }

    private FlexLayout getAll() {
        FlexLayout flexLayout = new FlexLayout();
        flexLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        var authorList = bookService.allAuthors();

        for (String a : authorList) {
            var book = bookService.findBookByAuthor(a);
            flexLayout.add(anchor = new Anchor("get-all-authors/" + book.getId(), a));
            anchor.addClassName("all-book");
        }

        return flexLayout;
    }

}
