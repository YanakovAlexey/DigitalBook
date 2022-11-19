package com.example.application.views.content;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.service.BookService;
import com.example.application.views.ContentView;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "search", layout = ContentView.class)
@AnonymousAllowed
public class SearchContent extends HorizontalLayout {

    private final BookService bookService;
    private final BookBuilder bookBuilder;

    @Autowired
    public SearchContent(BookService bookService, BookBuilder bookBuilder) {

        this.bookService = bookService;
        this.bookBuilder = bookBuilder;
        this.addClassNames("view-content");

    }

    public void appealSearch(String text) {
        var books = bookService
                .getBySearch(text)
                .stream()
                .map(bookBuilder::createBook)
                .toList();
        var layout = new FlexLayout();
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        books.forEach(bookViewModel -> {
            layout.add(new BookItem(bookViewModel));
        });

        add(layout);
    }
}
