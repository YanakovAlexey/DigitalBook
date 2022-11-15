package com.example.application.views.content;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.service.BookService;
import com.example.application.views.ContentView;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "/", layout = ContentView.class)
@AnonymousAllowed
public class BookShapeContent extends HorizontalLayout {
    private VerticalLayout verticalLayout = new VerticalLayout();
    private final BookService bookService;
    private final BookBuilder bookBuilder;

    @Autowired
    public BookShapeContent(BookService bookService, BookBuilder bookBuilder) {

        this.addClassNames("view-content");

        this.bookService = bookService;
        this.bookBuilder = bookBuilder;
        verticalLayout.add(allBooks(), youMayLike(),bestsellers(), mainBooks());


        add(verticalLayout);
    }
    private Div allBooks(){
        Div div = new Div();
        Label titleAll = new Label("Все");
        titleAll.addClassNames("book-label");
        var books = bookService
                .getAll()
                .stream()
                .map(bookBuilder::createBook)
                .toList();

        var layout = new HorizontalLayout();

        layout.add(titleAll);
        books.forEach(bookViewModel -> {

            layout.add(new BookItem(bookViewModel));
        });
        Scroller scroller = new Scroller(
                new Div(layout));

        scroller.setScrollDirection(Scroller.ScrollDirection.HORIZONTAL);
        div.setWidth("100%");
        div.add(titleAll, scroller);
        return div;
    }
    private Div youMayLike(){
        Button allButton = new Button("Все");
        allButton.setHeight("20px");
        Div div = new Div();
        Label titleMayLike = new Label("Вам может поравиться");
        titleMayLike.addClassNames("book-label");
        var books = bookService
                .getAll()
                .stream()
                .map(bookBuilder::createBook)
                .toList();

        var layout = new HorizontalLayout();

        books.forEach(bookViewModel -> {
            layout.add(new BookItem(bookViewModel));
        });
        Scroller scroller = new Scroller(
                new Div(layout));
        scroller.setScrollDirection(Scroller.ScrollDirection.HORIZONTAL);
        div.setWidth("100%");
        div.add(titleMayLike,allButton, scroller);
        return div;
    }

    private Div bestsellers(){
        Button allButton = new Button("Все");
        allButton.setHeight("20px");
        Div div = new Div();
        Label titleBestSellers = new Label("Бестселлеры");
        titleBestSellers.addClassNames("book-label");
        var books = bookService
                .getAll()
                .stream()
                .map(bookBuilder::createBook)
                .toList();

        var layout = new HorizontalLayout();

        books.forEach(bookViewModel -> {
            layout.add(new BookItem(bookViewModel));
        });
        Scroller scroller = new Scroller(
                new Div(layout));
        scroller.setScrollDirection(Scroller.ScrollDirection.HORIZONTAL);
        scroller.setVisible(true);

        div.setWidth("100%");
        div.add(titleBestSellers,allButton, scroller);
        return div;

    }
    private Div mainBooks(){
        Button allButton = new Button("Все");
        allButton.setHeight("20px");
        Div div = new Div();
        Label titleMain = new Label("Главные книги 2020 года");
        titleMain.addClassNames("book-label");
        var books = bookService
                .getAll()
                .stream()
                .map(bookBuilder::createBook)
                .toList();

        var layout = new HorizontalLayout();

        books.forEach(bookViewModel -> {
            layout.add(new BookItem(bookViewModel));
        });
        Scroller scroller = new Scroller(
                new Div(layout));
        scroller.setScrollDirection(Scroller.ScrollDirection.HORIZONTAL);
        div.setWidth("100%");
        div.add(titleMain,allButton, scroller);
        return div;
    }

}
