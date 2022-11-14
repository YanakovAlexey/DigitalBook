package com.example.application.views.content;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.service.BookService;
import com.example.application.views.MainLayout;
import com.example.application.views.items.BookItem;
import com.example.application.views.search.SearchView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "/", layout = MainLayout.class)
@RouteAlias(value = "/", layout = MainLayout.class)
@AnonymousAllowed
public class BookShapeContent extends HorizontalLayout {

    private VerticalLayout verticalLayout = new VerticalLayout();
    private final BookService bookService;
    private final BookBuilder bookBuilder;
    private final SearchView searchView;

    @Autowired
    public BookShapeContent(BookService bookService, BookBuilder bookBuilder) {
        this.addClassNames("view-content");
        this.bookService = bookService;
        this.bookBuilder = bookBuilder;
        verticalLayout.add(allBooks(), youMayLike(), bestsellers(), mainBooks());
        add(verticalLayout);
        searchView = new SearchView(bookService);
    }

    private Div allBooks() {
        Div div = new Div();
        Label titleAll = new Label("Все");
        titleAll.addClassNames("book-label");

        var books = bookService
                .getAll()
                .stream()
                .map(bookBuilder::createBook)
                .toList();

        var layout = new FlexLayout();
        layout.add(titleAll);
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        books.forEach(bookViewModel -> {
            layout.add(new BookItem(bookViewModel));
        });

        add(layout);

        div.add(titleAll, layout);
        return div;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        getParent().ifPresent((parent) -> {
            if (parent instanceof MainLayout) {
                var searchView = ((MainLayout) parent).getHeaderView().getSearchView();
                searchView.setTextChangeListener((HasValue.ValueChangeListener<AbstractField
                        .ComponentValueChangeEvent<TextField, String>>) event -> {

                    System.out.printf("Old %s new %s%n", event.getOldValue(), event.getValue());
                    bookService.getBySearch(event.getValue());

                });
            }
        });
    }

    private Div youMayLike() {
        Div div = new Div();
        Label titleMayLike = new Label("Вам может поравиться");
        titleMayLike.addClassNames("book-label");
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
        div.add(titleMayLike, layout);
        return div;
    }

    private Div bestsellers() {
        Div div = new Div();
        Label titleBestSellers = new Label("Бестселлеры");
        titleBestSellers.addClassNames("book-label");
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
        div.add(titleBestSellers, layout);
        return div;

    }

    private Div mainBooks() {
        Div div = new Div();
        Label titleMain = new Label("Главные книги 2020 года");
        titleMain.addClassNames("book-label");
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
        div.add(titleMain, layout);
        return div;
    }

}
