package com.example.application.views.content;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.views.items.BookContentItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@Route("bookContent")
@AnonymousAllowed
public class BookContentContent extends VerticalLayout {
    private final BookService bookService;
    private final BookBuilder bookBuilder;
    List<BookViewModel> bookViewModelList = new ArrayList<>();
    private Div div = new Div();
    @Autowired
    public BookContentContent(BookService bookService, BookBuilder bookBuilder) {
        this.bookService = bookService;
        this.bookBuilder = bookBuilder;

        var bookList = bookService.getAll();

        for (com.example.application.backEnd.domain.Book book : bookList) {
            bookViewModelList.add(bookBuilder.createBook(book));
        }

//        for (BookViewModel bookViewModel : bookViewModelList) {
//            div.add(new BookContentItem(bookViewModel, bookService, bookBuilder));
//        }

        div.add(new BookContentItem(bookViewModelList.get(1), bookService, bookBuilder));


        add(div);







    }
}
