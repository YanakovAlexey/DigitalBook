package com.example.application.views.content;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.service.BookService;
import com.example.application.views.MainLayout;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
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
    private FlexLayout allBooksLayout = new FlexLayout();

    private final BookService bookService;
    private final BookBuilder bookBuilder;
    private final Scroller scroller = new Scroller();

    @Autowired
    public BookShapeContent(BookService bookService, BookBuilder bookBuilder) {
        this.addClassNames("view-content");
        this.bookService = bookService;
        this.bookBuilder = bookBuilder;
        verticalLayout.add(allBooks(), youMayLike(), bestsellers(), mainBooks());
        add(verticalLayout);
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

        scroller.setScrollDirection(Scroller.ScrollDirection.HORIZONTAL);
        HorizontalLayout booksScroll = new HorizontalLayout();
        allBooksLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        books.forEach(bookViewModel -> {

            allBooksLayout.add(new BookItem(bookViewModel));
        });


        booksScroll.setPadding(true);
        booksScroll.getStyle().set("display", "inline-flex");

        scroller.setContent(allBooksLayout);
        verticalLayout.setHeight(String.valueOf(false));
        div.add(titleAll, allBooksLayout);
        add(div);
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

                    Div div = new Div();
                    Label titleAll = new Label("Все");
                    titleAll.addClassNames("book-label");
                    var books = bookService
                            .getBySearch(event.getValue())
                            .stream()
                            .map(bookBuilder::createBook)
                            .toList();

                    var layout = new FlexLayout();
                    layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
                    books.forEach(bookViewModel -> {
                        layout.add(new BookItem(bookViewModel));
                    });

                    div.add(layout);
                    allBooksLayout.removeAll();
                    allBooksLayout.add(div);
                });
            }
        });
    }

    private Div youMayLike() {
        Div div = new Div();
        Label titleMayLike = new Label("Вам может понравиться");
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
