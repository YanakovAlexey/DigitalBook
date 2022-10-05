package com.example.application.views.content;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.views.ContentView;
import com.example.application.views.items.BookContentItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route(value = "bookContent", layout = ContentView.class)
@AnonymousAllowed
public class BookContentView extends VerticalLayout implements HasUrlParameter<Long> {
    private long bookId;
    private final BookService bookService;
    private final UsersService usersService;
    private final BookBuilder bookBuilder;
    List<BookViewModel> bookViewModelList = new ArrayList<>();
    private Div div = new Div();
    @Autowired
    public BookContentView(BookService bookService, UsersService usersService, BookBuilder bookBuilder) {
        this.bookService = bookService;
        this.usersService = usersService;
        this.bookBuilder = bookBuilder;
    }



    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        this.bookId = parameter;

        var bookList = bookService.getAll();

        for (Book book : bookList) {
            bookViewModelList.add(bookBuilder.createBook(book));
        }

        div.add(new BookContentItem(getIdBook(bookId),
                usersService,
                bookService,
                bookBuilder));

        add(div);
    }

    private BookViewModel getIdBook(Long id){
        for (BookViewModel bookViewModel : bookViewModelList) {
            if (id.equals(bookViewModel.getId())) {
                return bookViewModel;
            }
        }
        return bookViewModelList.get(1);
    }
}
