package com.example.application.views.content;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.reporitory.BasketRepository;
import com.example.application.backEnd.reporitory.BookRepository;
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
                            UserRepository userRepository, BasketRepository basketRepository, DisciplineRepository disciplineRepository) {

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
class GetAllAuthors extends VerticalLayout implements HasUrlParameter<String> {
    private String bookViewModel;
    private final BookService bookService;
    private Label title;
    private final BookBuilder bookBuilder;

    GetAllAuthors(BookService bookService, BookBuilder bookBuilder) {

        this.bookService = bookService;
        this.bookBuilder = bookBuilder;

    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        this.bookViewModel = parameter;

        this.title = new Label("Авторские книги " + bookViewModel);

       var bookList = bookService.findAllByAuthor(bookViewModel);
        System.out.println(bookList);

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

    private Long idPublisher;
    private final BookService bookService;
    private final UsersService usersService;
    private Label title;
    private final BookBuilder bookBuilder;

    GetAllPublisher(BookService bookService, UsersService usersService, BookBuilder bookBuilder) {
        this.bookService = bookService;
        this.usersService = usersService;
        this.bookBuilder = bookBuilder;
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        this.idPublisher = parameter;

        this.title = new Label("Книги издательства " + getAPublisher(idPublisher));

        var books = bookService.getAll();

        List<BookViewModel> bookViewModelList = new ArrayList<>();

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getUsers().equals(idPublisher)) {
                bookViewModelList.add(bookBuilder.createBook(books.get(i)));
            }
        }

        var layout = new FlexLayout();
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        bookViewModelList.forEach(bookViewModel -> {
            layout.add(new BookItem(bookViewModel));
        });

        add(title, layout);
    }

    private String getAPublisher(Long id) {
        var usersList = usersService.getAll();

        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getId().equals(id)) {
                return usersList.get(i).getName();
            }
        }
        return null;
    }
}

@Route(value = "GetAllGenre", layout = ContentView.class)
@AnonymousAllowed
class GetAllGenre extends VerticalLayout implements HasUrlParameter<Long> {

    private Long idGenre;
    private final BookService bookService;
    private final UsersService usersService;
    private Label title;
    private final DisciplineService disciplineService;
    private final BookBuilder bookBuilder;

    GetAllGenre(BookService bookService, UsersService usersService,
                DisciplineService disciplineService,
                BookBuilder bookBuilder) {
        this.bookService = bookService;
        this.usersService = usersService;
        this.disciplineService = disciplineService;
        this.bookBuilder = bookBuilder;
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        this.idGenre = parameter;

        this.title = new Label("В том же жанре " + getAGenre(idGenre));

        var books = bookService.getAll();

        List<BookViewModel> bookViewModelList = new ArrayList<>();

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getUsers().equals(idGenre)) {
                bookViewModelList.add(bookBuilder.createBook(books.get(i)));
            }
        }


        var layout = new FlexLayout();
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        bookViewModelList.forEach(bookViewModel -> {
            layout.add(new BookItem(bookViewModel));
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
