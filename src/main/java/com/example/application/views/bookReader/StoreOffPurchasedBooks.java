package com.example.application.views.bookReader;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.domain.BasketPosition;
import com.example.application.backEnd.reporitory.BasketPositionRepository;
import com.example.application.backEnd.reporitory.BookRepository;
import com.example.application.backEnd.service.BasketPositionService;
import com.example.application.backEnd.service.BookService;
import com.example.application.views.ContentView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "get-all-purchased-books", layout = ContentView.class)
@AnonymousAllowed
public class StoreOffPurchasedBooks extends FlexLayout {
    private final BookService bookService;
    private final BasketPositionService basketPositionService;
    private final BookBuilder bookBuilder;
    BookItemCustom bookItemCustom;

    public StoreOffPurchasedBooks(BookService bookService,
                                  BasketPositionService basketPositionService, BookBuilder bookBuilder) {
        this.basketPositionService = basketPositionService;
        this.bookBuilder = bookBuilder;
        this.bookService = bookService;

        var div = new Div();
        Label label = new Label("Мои книги");
        var layout = new FlexLayout();
        var basketPositionList = basketPositionService.findAllByIsPaid();

        for (int i = 0; i < basketPositionList.size(); i++) {
            var book = bookService.getById(basketPositionList.get(i).getIdBook());

            bookItemCustom = new BookItemCustom(book);
            bookItemCustom.readButton.addClickListener(event ->
                    getUI().ifPresent(ui -> ui.navigate("reader/" + book.getId())));

            bookItemCustom.deleteButton.addClickListener(event ->
                    bookService.deleteById(book.getId()));
            layout.add(bookItemCustom);
        }


        div.add(label, layout);
        add(div);
    }
}
