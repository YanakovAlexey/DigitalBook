package com.example.application.views.items;

import com.example.application.backEnd.builder.BasketPositionBuilder;
import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.domain.Basket;
import com.example.application.backEnd.domain.BasketPosition;
import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.reporitory.BasketRepository;
import com.example.application.backEnd.service.*;
import com.example.application.backEnd.service.impl.security.AuthenticatedUser;
import com.example.application.backEnd.viewModel.BasketViewModel;
import com.example.application.backEnd.viewModel.DisciplineViewModel;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

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
    private BasketViewModel basket;
    Div div = new Div();

    public BookDetailsView(BookViewModel bookViewModel, UsersService usersService,
                           BasketService basketService, DisciplineService disciplineService,
                           BookService bookService, BookBuilder bookBuilder,
                           BasketPositionService basketPositionService, BasketPositionBuilder basketPositionBuilder, AuthenticatedUser authenticatedUser, BasketRepository basketRepository) {
        this.usersService = usersService;
        this.basketService = basketService;
        this.disciplineService = disciplineService;
        this.bookService = bookService;
        this.bookBuilder = bookBuilder;
        this.basketPositionService = basketPositionService;
        this.authenticatedUser = authenticatedUser;
        this.basketRepository = basketRepository;

        add(content(bookViewModel), searchByAuthor(bookViewModel),
                searchByPublishingHouse(bookViewModel), searchByGenre(bookViewModel));
    }

    private HorizontalLayout content(BookViewModel bookViewModel) {

        var genrePagesButton = new VerticalLayout();
        var verticalLayout = new VerticalLayout();
        var horizontalLayout = new HorizontalLayout();

        this.image = new Image(bookViewModel.getBookImg(), "");

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
        Label label = new Label("Еще от автора '" + bookViewModel.getAuthor() + "'");
        label.addClassNames("book-label");
        Button getAllAuthorButton = new Button("  все");
        getAllAuthorButton.addClickListener(event ->
                getAllAuthorButton.getUI().ifPresent(ui -> ui.navigate("GetAllAuthors/" + bookViewModel.getAuthor())));

        getAllAuthorButton.addClassName("book-content-anchor-getAll");

        List<BookViewModel> bookViewModelList = new ArrayList<>();
        Div div = new Div();
        div.add(label, getAllAuthorButton);
        var horizontalLayout = new HorizontalLayout();


        var books = bookService.getAll();

        for (Book book : books) {
            bookViewModelList.add(bookBuilder.createBook(book));
        }

        List<Book> listBooksAuthor = new ArrayList<>();

        for (Book book : books) {
            if (book.getAuthor().equals(bookViewModel.getAuthor())) {
                listBooksAuthor.add(book);
            }
        }

        for (int i = 0; i < listBooksAuthor.size(); i++) {
            horizontalLayout.add(new BookItem(bookViewModelList.get(i)));
        }

        div.add(horizontalLayout);
        div.addClassName("book-content-item-column");


        return div;
    }

    private Div searchByPublishingHouse(BookViewModel bookViewModel) {
        Label label = new Label("Еще от издательства '" + getAPublisher(bookViewModel) + "'");
        label.addClassNames("book-label");


        List<BookViewModel> bookViewModelList = new ArrayList<>();

        Button getAllPublisherButton = new Button("  все");
        getAllPublisherButton.addClickListener(event ->
                getAllPublisherButton.getUI().ifPresent(ui ->
                        ui.navigate("GetAllPublisher/" + bookViewModel.getIdUsers())));

        getAllPublisherButton.addClassName("book-content-anchor-getAll");


        var horizontalLayout = new HorizontalLayout();

        var books = bookService.getAll();

        for (Book book : books) {
            bookViewModelList.add(bookBuilder.createBook(book));
        }

        List<Book> listBooksAuthor = new ArrayList<>();

        for (Book book : books) {
            if (book.getIdUsers().equals(bookViewModel.getIdUsers())) {
                listBooksAuthor.add(book);
            }
        }

        for (int i = 0; i < listBooksAuthor.size(); i++) {
            horizontalLayout.add(new BookItem(bookViewModelList.get(i)));
        }

        div.add(label, getAllPublisherButton, horizontalLayout);
        div.addClassName("book-content-item-column-author");

        return div;
    }

    private Div searchByGenre(BookViewModel bookViewModel) {
        Div div = new Div();
        Label label = new Label("В том же жанре  " + getAGenre(bookViewModel));
        label.addClassNames("book-label");
        List<BookViewModel> bookViewModelList = new ArrayList<>();

        Button genreAllGenreButton = new Button("все");
        genreAllGenreButton.addClickListener(event ->
                genreAllGenreButton.getUI().ifPresent(ui ->
                        ui.navigate("GetAllGenre/" + bookViewModel.getIdDiscipline())));

        genreAllGenreButton.addClassName("book-content-anchor-getAll");


        var horizontalLayout = new HorizontalLayout();

        var books = bookService.getAll();

        for (Book book : books) {
            bookViewModelList.add(bookBuilder.createBook(book));
        }

        List<Book> listBooksByGenre = new ArrayList<>();

        for (Book book : books) {
            if (book.getType().equals(bookViewModel.getIdDiscipline())) {
                listBooksByGenre.add(book);
            }
        }
        for (int i = 0; i < listBooksByGenre.size(); i++) {
            horizontalLayout.add(new BookItem(bookViewModelList.get(i)));
        }
        div.add(label, genreAllGenreButton, horizontalLayout);
        div.addClassName("book-content-item-column-genre");

        return div;
    }

    private String getAPublisher(BookViewModel bookViewModel) {
        var usersList = usersService.getAll();

        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getId().equals(bookViewModel.getIdUsers())) {
                return usersList.get(i).getName();
            }
        }
        return null;
    }

    private String getAGenre(BookViewModel bookViewModel) {
        var listGenre = disciplineService.getAll();

        for (DisciplineViewModel disciplineViewModel : listGenre) {
            if (disciplineViewModel.getId().equals(bookViewModel.getId())) {
                return disciplineViewModel.getTitle();
            }

        }
        return null;
    }

    private void addToBasket(BookViewModel bookViewModel) {
        var basketRep = basketRepository.findFirstById(authenticatedUser.get().get().getIdBasket());

        Basket localBasket = null;
        if (basketRep == null) {
            var basket = new Basket();
            basket.setId_user(authenticatedUser.get().get().getId());
            basket.setId_book(bookViewModel.getId());
            basket.setPrice(bookViewModel.getPricePay());
            basketService.create(basket);
            localBasket = basket;
        }

        basket = basketRep;

        var basketPosition = new BasketPosition();
        basketPosition.setIdBook(bookViewModel.getId());
        basketPosition.setIdBasket(localBasket == null ? basket.getId() : localBasket.getId());

        basketPosition.setPrice(bookViewModel.getPricePay());
        basketPosition.setNumberOfBooks(2);

        basketPositionService.create(basketPosition);

        authenticatedUser.get().ifPresent(u ->
                getUI().ifPresent(ui -> ui.navigate("Basket/" + u.getId()))

        );
    }
}



