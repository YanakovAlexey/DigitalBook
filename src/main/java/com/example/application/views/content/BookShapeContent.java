package com.example.application.views.content;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.service.BookService;
import com.example.application.views.MainLayout;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "/", layout = MainLayout.class)
@RouteAlias(value = "/", layout = MainLayout.class)
@AnonymousAllowed
public class BookShapeContent extends HorizontalLayout {

    private final BookService bookService;
    private final BookBuilder bookBuilder;

    @Autowired
    public BookShapeContent(BookService bookService, BookBuilder bookBuilder) {
        this.addClassNames("view-content");
        this.bookService = bookService;
        this.bookBuilder = bookBuilder;

        var books = bookService
                .getAll()
                .stream()
                .map(bookBuilder::createBook)
                .toList();
        var layout = new FlexLayout();
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        books.forEach(bookViewModel -> {
            layout.add(new BookItem(bookViewModel));
        });

        add(layout);

        var prent = layout.getParent();

        System.out.println(prent);
    }

    public void refresh() {

    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        getParent().ifPresent((parent) -> {
            if (parent instanceof MainLayout) {
                var searchView = ((MainLayout) parent).getHeaderView().getSearchView();
                searchView.setTextChangeListener((HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<TextField, String>>) event -> {
                    System.out.printf("Old %s new %s%n", event.getOldValue(), event.getValue());
                });
            }
        });
    }
}
