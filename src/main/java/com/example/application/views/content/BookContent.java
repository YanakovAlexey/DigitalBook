package com.example.application.views.content;

import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/bookContent")
public class BookContent extends Div {
    private final BookService bookService;

    private Image image;
    private Label title;
    private Label author;
    private Label description;

    public BookContent(BookService bookService, BookViewModel bookViewModel) {
        var verticalLayout = new VerticalLayout();

        var book = bookService.getById(1L);

        this.bookService = bookService;
        var horizontalLayout = new HorizontalLayout();

        this.image = new Image(book.getBookImg(), "");
        this.title = new Label(book.getTitle());
        this.author = new Label(book.getAuthor());
        this.description = new Label(book.getDescription());

        horizontalLayout.add(image, title, author, description);

        verticalLayout.add(horizontalLayout);

        add(verticalLayout);




    }
}
