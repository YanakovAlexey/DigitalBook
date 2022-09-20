package com.example.application.views.content;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.service.BookService;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;


@Route("shapes")
@RolesAllowed("USER")
public class BookShapeContent extends HorizontalLayout {

    private final BookService bookService;
    private final BookBuilder bookBuilder;
    private Div div = new Div();

    public BookShapeContent(BookService bookService, BookBuilder bookBuilder) {
        Book bo0ok = new Book();
        bo0ok.setTitle("письмо солнца");
        bo0ok.setAuthor("Малхаз Мачавариани");
        bo0ok.setBookImg("https://digitalbooks.app/books_img/2021/09/cover_227.jpg");


        for(long i = 1;i < 6; i++){

            bo0ok.setId(i);
            bookService.create(bo0ok);
        }

        this.addClassNames("view-content");
        this.bookService = bookService;
        this.bookBuilder = bookBuilder;
        var books = bookService.getAll().stream().map(book -> bookBuilder.createBook(book));
        var layout = new FlexLayout();
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        books.forEach(bookViewModel -> {
            layout.add(new BookItem(bookViewModel));
        });
        div.setText("ВСЕ");
        div.add(layout);

        add(div);
    }
}
