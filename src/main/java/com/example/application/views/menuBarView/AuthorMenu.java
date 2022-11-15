package com.example.application.views.menuBarView;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.service.AuthorService;
import com.example.application.backEnd.service.BookService;
import com.example.application.views.ContentView;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "getByAuthor", layout = ContentView.class)
@AnonymousAllowed
public class AuthorMenu extends FlexLayout implements HasUrlParameter<Long> {

    private final BookService bookService;
    private final BookBuilder bookBuilder;
    private final AuthorService authorService;
    public AuthorMenu(BookService bookService, BookBuilder bookBuilder, AuthorService authorService) {
        this.bookService = bookService;
        this.bookBuilder = bookBuilder;
        this.authorService = authorService;
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        Label label = new Label();
        Long idAuthor = parameter;

        var layout = new FlexLayout();

        var author = authorService.getById(idAuthor);

        var bookList = bookService.findAllByAuthor(author.getName());

        if (bookList.isEmpty()) {
            new EmptyView();
        }
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        bookList.forEach(bookViewModel -> {
            layout.add(new BookItem(bookBuilder.createBook(bookViewModel)));
        });
        label.add("От автора " + author.getName());
        label.addClassNames("content-name");
        add(label, layout);
    }
}
