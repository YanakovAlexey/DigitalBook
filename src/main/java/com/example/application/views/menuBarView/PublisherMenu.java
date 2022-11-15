package com.example.application.views.menuBarView;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.service.UsersService;
import com.example.application.views.ContentView;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.ArrayList;

@Route(value = "getByPublisher", layout = ContentView.class)
@AnonymousAllowed
public class PublisherMenu extends FlexLayout implements HasUrlParameter<Long> {
    private final BookService bookService;
    private final BookBuilder bookBuilder;
    private final UsersService userService;
    private  Long userId;

    public PublisherMenu(BookService bookService, BookBuilder bookBuilder, UsersService userService) {
        this.bookService = bookService;
        this.bookBuilder = bookBuilder;
        this.userService = userService;
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
       Label label = new Label();
        userId = parameter;

        var layout = new FlexLayout();

        ArrayList<Book> bookList = new ArrayList<>();

        var list = bookService.getAll();

        for(Book b: list ){
            if (b.getUserId().equals(userId)){
                bookList.add(b);
            }
        }
        if (bookList.isEmpty()) {
            new EmptyView();
        }
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        bookList.forEach(bookViewModel -> {
            layout.add(new BookItem(bookBuilder.createBook(bookViewModel)));
        });
        var user = userService.getById(bookList.get(0).getId());


        label.add("От издательства " + user.getName() );
        add( layout);
    }
}
