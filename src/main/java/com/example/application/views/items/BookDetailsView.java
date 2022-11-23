package com.example.application.views.items;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.domain.Basket;
import com.example.application.backEnd.domain.BasketPosition;
import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.reporitory.BasketRepository;
import com.example.application.backEnd.reporitory.DisciplineRepository;
import com.example.application.backEnd.reporitory.UserRepository;
import com.example.application.backEnd.service.*;
import com.example.application.backEnd.service.impl.security.AuthenticatedUser;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDetailsView extends Div {
    private Image image;
    private Label title;
    private Label author;
    private Label publishingHouse;
    private Label description;
    private Label genre;
    private Label printedPages;
    private Button basketButton;
    private final UsersService usersService;
    private final BasketService basketService;
    private final DisciplineService disciplineService;
    private final BookService bookService;
    private final BookBuilder bookBuilder;
    private final BasketPositionService basketPositionService;
    private final AuthenticatedUser authenticatedUser;
    private final BasketRepository basketRepository;
    private Basket basket = new Basket();

    private final DisciplineRepository disciplineRepository;
    private final UserRepository userRepository;
    private Basket localBasket = null;
    Div div = new Div();
    private final String BASE_PATH = "http://localhost:7070/images/";

    public BookDetailsView(BookViewModel bookViewModel, UsersService usersService,
                           BasketService basketService, DisciplineService disciplineService,
                           BookService bookService, BookBuilder bookBuilder,
                           BasketPositionService basketPositionService,
                           AuthenticatedUser authenticatedUser,
                           BasketRepository basketRepository,
                           DisciplineRepository disciplineRepository,
                           UserRepository userRepository) {

        this.usersService = usersService;
        this.basketService = basketService;
        this.disciplineService = disciplineService;
        this.bookService = bookService;
        this.bookBuilder = bookBuilder;
        this.basketPositionService = basketPositionService;
        this.authenticatedUser = authenticatedUser;
        this.basketRepository = basketRepository;
        this.disciplineRepository = disciplineRepository;
        this.userRepository = userRepository;

        add(content(bookViewModel), searchByAuthor(bookViewModel),
                searchByPublishingHouse(bookViewModel), searchByGenre(bookViewModel));
        removeAll();


    }

    private HorizontalLayout content(BookViewModel bookViewModel) {
        var genrePagesButton = new VerticalLayout();
        var verticalLayout = new VerticalLayout();
        var horizontalLayout = new HorizontalLayout();

        this.image = new Image(BASE_PATH + bookViewModel.getBookImg(), "OPs, not image)");

        this.image.addClassNames("book-content-item-image");

        this.title = new Label(bookViewModel.getTitle());
        this.title.addClassNames("book-label");

        this.author = new Label("Автор:  " + bookViewModel.getAuthor());

        this.publishingHouse = new Label("Издательство:   " + getAPublisher(bookViewModel));

        this.description = new Label("Описание:   " + bookViewModel.getDescription());
        this.description.setWidth("500px");

        this.printedPages = new Label("Печатных страниц: " + bookViewModel.getPages());

        this.genre = new Label("Жанр: " + getAGenre(bookViewModel));

        this.basketButton = new Button("В корзину");
        this.basketButton.addClickListener(event ->
                addToBasket(bookViewModel)
        );

        genrePagesButton.add(printedPages, genre, basketButton);
        genrePagesButton.addClassName("book-content-item-button");

        verticalLayout.add(title, author, publishingHouse, description, genrePagesButton);

        horizontalLayout.add(image, verticalLayout);

        return horizontalLayout;
    }

    private Div searchByAuthor(BookViewModel bookViewModel) {
        Div div = new Div();
        Label label = new Label("Еще от автора '" + bookViewModel.getAuthor() + "'");
        label.addClassNames("book-label");
        Button getAllAuthorButton = new Button("  все");
        getAllAuthorButton.addClickListener(event ->
                getAllAuthorButton.getUI().ifPresent(ui ->
                        ui.navigate("GetAllAuthors/" + bookViewModel.getAuthor())));

        getAllAuthorButton.addClassName("book-content-anchor-getAll");

        div.add(label, getAllAuthorButton);
        var horizontalLayout = new HorizontalLayout();

        var authorList = bookService.findAllByAuthor(bookViewModel.getAuthor());
        int o = 0;
        for (Book a : authorList) {
            if (o < 8){
                horizontalLayout.add(new BookItem(bookBuilder.createBook(a)));
            }
            else {
                break;
            }
            o++;
        }

        div.add(horizontalLayout);
        div.addClassName("book-content-item-column");


        return div;
    }

    private Div searchByPublishingHouse(BookViewModel bookViewModel) {
        Book[] books = new Book[8];
        Button getAllPublisherButton = new Button("  все");
        getAllPublisherButton.addClickListener(event ->
                getAllPublisherButton.getUI().ifPresent(ui ->
                        ui.navigate("GetAllPublisher/" + bookViewModel.getIdUsers())));

        getAllPublisherButton.addClassName("book-content-anchor-getAll");
        var flexLayout = new FlexLayout();
        var user = usersService.getById(bookViewModel.getIdUsers());
        var bookList = bookService.findAllByIdIdUser(user.get());
        int o = 0;

        for (Book b : bookList) {
            if (o < 8) {
            flexLayout.add(new BookItem(bookBuilder.createBook(b)));
            }
            else {
                break;
            }
            o++;
        }

        Label label = new Label("Еще от издательства '" + getAPublisher(bookViewModel) + "'");
        label.addClassNames("book-label");
        div.add(label, getAllPublisherButton, flexLayout);

        div.addClassName("book-content-item-column-author");

        return div;
    }

    private Div searchByGenre(BookViewModel bookViewModel) {
        Div div = new Div();
        Label label = new Label("В том же жанре " + getAGenre(bookViewModel));
        label.addClassNames("book-label");
        Button genreAllGenreButton = new Button("все");
        genreAllGenreButton.addClickListener(event ->
                genreAllGenreButton.getUI().ifPresent(ui ->
                        ui.navigate("GetAllGenre/" + bookViewModel.getIdDiscipline())));
        genreAllGenreButton.addClassName("book-content-anchor-getAll");
        var horizontalLayout = new HorizontalLayout();
        var genreList = bookService.findAllByIdDiscipline(bookViewModel.getIdDiscipline());
        int o = 0;
        for (Book a : genreList) {
            if(o < 8){
                horizontalLayout.add(new BookItem(bookBuilder.createBook(a)));
            }
            else {
                break;
            }
            o++;
        }
        div.add(label, genreAllGenreButton, horizontalLayout);
        div.addClassName("book-content-item-column-genre");

        return div;
    }

    private String getAPublisher(BookViewModel bookViewModel) {
        var user = usersService.getById(bookViewModel.getIdUsers());
        return user.get().getUsername();
    }

    private String getAGenre(BookViewModel bookViewModel) {
        var genre = disciplineRepository.findById(bookViewModel.getIdDiscipline()).orElse(null);
        if (genre == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return genre.getTitle();
    }

    private void addToBasket(BookViewModel bookViewModel) {
        var basketPos = basketRepository.findFirstByIdUser(authenticatedUser.get().get().getId());

        if (basketPos == null) {
            basket.setIdUser(authenticatedUser.get().get().getId());
            basketService.create(basket);
            localBasket = basket;
        }

        var basketPosition = new BasketPosition();
        basketPosition.setIdBook(bookViewModel.getId());
        basketPosition.setIdBasket(basketPos.getId());
//        localBasket == null ? basket.getId() :
        basketPosition.setPrice(bookViewModel.getPricePay());
        basketPosition.setNumberOfBooks(2);

        basketPositionService.create(basketPosition);

        authenticatedUser.get().ifPresent(u ->
                getUI().ifPresent(ui -> ui.navigate("Basket/" + u.getId()))

        );
    }

    private void updatePage() {
        getUI().ifPresent(ui -> ui.getPage().reload());

    }
}



