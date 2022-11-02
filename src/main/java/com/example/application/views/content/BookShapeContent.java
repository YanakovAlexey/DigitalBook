package com.example.application.views.content;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.views.ContentView;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;

@Route(value = "/", layout = ContentView.class)
@AnonymousAllowed
public class BookShapeContent extends HorizontalLayout {
    private final BookService bookService;
    private final BookBuilder bookBuilder;

    @Autowired
    public BookShapeContent(BookService bookService, BookBuilder bookBuilder) {

        this.addClassNames("view-content");

        this.bookService = bookService;
        this.bookBuilder = bookBuilder;
        var books = bookService.getAll().stream().map(book -> bookBuilder.createBook(book));
        var layout = new FlexLayout();
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        books.forEach(bookViewModel -> {
            layout.add(new BookItem(bookViewModel));
        });

        add(layout);
    }
}
