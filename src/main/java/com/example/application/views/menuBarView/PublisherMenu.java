package com.example.application.views.menuBarView;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.reporitory.BookRepository;
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

@Route(value = "getByPublisher", layout = ContentView.class)
@AnonymousAllowed
public class PublisherMenu extends FlexLayout implements HasUrlParameter<Long> {
    private final BookService bookService;
    private final BookBuilder bookBuilder;
    private final UsersService userService;
    private final BookRepository bookRepository;
    private  Long userId;

    public PublisherMenu(BookService bookService,
                         BookBuilder bookBuilder,
                         UsersService userService,
                         BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookBuilder = bookBuilder;
        this.userService = userService;
        this.bookRepository = bookRepository;
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
       Label label = new Label();
        userId = parameter;

        var layout = new FlexLayout();
        var userPol =  userService.getById(userId).orElse(null);
        if(userPol == null){
            throw new RuntimeException("userPol is null with id = " + userId);
        }

        var bookList = bookService.findAllByIdIdUser(userPol);
        if (bookList.isEmpty()) {
            new EmptyView();
        }
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        bookList.forEach(book -> {
            layout.add(new BookItem(bookBuilder.createBook(book)));
        });

        label.add("От издательства " + userPol.getUsername());
        add(label, layout);
    }
}
