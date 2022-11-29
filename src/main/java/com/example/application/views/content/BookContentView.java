package com.example.application.views.content;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.reporitory.BasketRepository;
import com.example.application.backEnd.reporitory.DisciplineRepository;
import com.example.application.backEnd.reporitory.UserRepository;
import com.example.application.backEnd.service.*;
import com.example.application.backEnd.service.impl.security.AuthenticatedUser;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.backEnd.viewModel.DisciplineViewModel;
import com.example.application.views.ContentView;
import com.example.application.views.items.BookDetailsView;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Route(value = "BookContent", layout = ContentView.class)
@AnonymousAllowed
public class BookContentView extends VerticalLayout implements HasUrlParameter<Long> {
    private long bookId;
    private final BookService bookService;
    private final BasketService basketService;
    private final UsersService usersService;
    private final DisciplineService disciplineService;
    private final BookBuilder bookBuilder;
    private final BasketPositionService basketPositionService;
    private final UserRepository userRepository;
    private final AuthenticatedUser authenticatedUser;
    private final BasketRepository basketRepository;
    private final DisciplineRepository disciplineRepository;
    private Div div = new Div();

    @Autowired
    public BookContentView(BookService bookService,
                           BasketService basketService,
                           UsersService usersService,
                           DisciplineService disciplineService,
                           BookBuilder bookBuilder,
                           BasketPositionService basketPositionService,
                           AuthenticatedUser authenticatedUser,
                           UserRepository userRepository, BasketRepository basketRepository,
                           DisciplineRepository disciplineRepository) {

        this.bookService = bookService;
        this.basketService = basketService;
        this.usersService = usersService;
        this.disciplineService = disciplineService;
        this.bookBuilder = bookBuilder;
        this.basketPositionService = basketPositionService;
        this.authenticatedUser = authenticatedUser;
        this.userRepository = userRepository;
        this.basketRepository = basketRepository;
        this.disciplineRepository = disciplineRepository;
        addClassName("book-content-background");
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        this.bookId = parameter;
        var book = bookService.getById(bookId);
        div.add(new BookDetailsView(book,
                usersService,
                basketService,
                disciplineService,
                bookService,
                bookBuilder,
                basketPositionService,
                authenticatedUser,
                basketRepository, disciplineRepository, userRepository));

        add(div);
    }
}

@Route(value = "GetAllAuthors", layout = ContentView.class)
@AnonymousAllowed
class GetAllAuthors extends VerticalLayout implements HasUrlParameter<Long> {
    private Label title;
    private final BookService bookService;
    private final BookBuilder bookBuilder;

    GetAllAuthors(BookService bookService,
                  BookBuilder bookBuilder) {
        this.bookService = bookService;
        this.bookBuilder = bookBuilder;
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        var oldBook = bookService.getById(parameter);
        this.title = new Label("Авторские книги  '" + oldBook.getAuthor() + "'");
        this.title.addClassNames("book-label");
        var bookList = bookService.findAllByAuthor(oldBook.getAuthor());
        var layout = new FlexLayout();
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        bookList.forEach(bookViewModel -> {
            layout.add(new BookItem(bookBuilder.createBook(bookViewModel)));
        });
        add(title, layout);
    }
}

@Route(value = "GetAllPublisher", layout = ContentView.class)
@AnonymousAllowed
class GetAllPublisher extends VerticalLayout implements HasUrlParameter<Long> {
    private Long idUser;
    private final BookService bookService;
    private final UsersService usersService;
    private final PublisherService publisherService;
    private Label title;
    private final BookBuilder bookBuilder;

    GetAllPublisher(BookService bookService,
                    UsersService usersService,
                    PublisherService publisherService,
                    BookBuilder bookBuilder) {
        this.bookService = bookService;
        this.usersService = usersService;
        this.publisherService = publisherService;
        this.bookBuilder = bookBuilder;
    }
    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        this.idUser = parameter;
        this.title = new Label("Книги издательства  '" + getAPublisher(idUser) + "'");
        this.title.addClassNames("book-label");

        var user = usersService.getById(idUser);
        var publisherList = bookService.findAllByUserId(user.get().getId());
        var layout = new FlexLayout();
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        publisherList.forEach(publisher -> {
            layout.add(new BookItem(bookBuilder.createBook(publisher)));
        });
        add(title, layout);
    }

    private String getAPublisher(Long id) {
        var user = usersService.getById(id).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return user.getUsername();
    }
}

@Route(value = "GetAllGenre", layout = ContentView.class)
@AnonymousAllowed
class GetAllGenre extends VerticalLayout implements HasUrlParameter<Long> {
    private Long idGenre;
    private final BookService bookService;
    private Label title;
    private final DisciplineService disciplineService;
    private final BookBuilder bookBuilder;
    GetAllGenre(BookService bookService,
                DisciplineService disciplineService,
                BookBuilder bookBuilder) {
        this.bookService = bookService;
        this.disciplineService = disciplineService;
        this.bookBuilder = bookBuilder;
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        this.idGenre = parameter;
        this.title = new Label("В том же жанре '" + getAGenre(idGenre) + "'");
        this.title.addClassNames("book-label");

        var genreList = bookService.getAllByIdGenre(idGenre);

        var layout = new FlexLayout();
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        genreList.forEach(book -> {
            layout.add(new BookItem(bookBuilder.createBook(book)));
        });
        add(title, layout);
    }

    private String getAGenre(Long idGenre) {
        var listGenre = disciplineService.getAll();
        for (DisciplineViewModel disciplineViewModel : listGenre) {
            if (disciplineViewModel.getId().equals(idGenre)) {
                return disciplineViewModel.getTitle();
            }

        }
        return null;
    }
}
