package com.example.application.views.items;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.service.DisciplineService;
import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.views.ContentView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.ArrayList;
import java.util.List;

public class BookContentItem extends Div {
    private Image image;
    private Label title;
    private Label author;
    private Label publishingHouse;
    private Label description;
    private Label genre;
    private Label printedPages;
    private Button button;
    private final UsersService usersService;
    private final BookService bookService;
    private final BookBuilder bookBuilder;
    Div div = new Div();

    public BookContentItem(BookViewModel bookViewModel, UsersService usersService,
                           BookService bookService, BookBuilder bookBuilder) {
        this.usersService = usersService;
        this.bookService = bookService;
        this.bookBuilder = bookBuilder;

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
        this.title.addClassNames("view-color-title");

        this.author = new Label("Автор:" + bookViewModel.getAuthor());

        this.publishingHouse = new Label("Издательство: " + getAPublisher(bookViewModel));

        this.description = new Label("Описание: " + bookViewModel.getDescription());

        this.printedPages = new Label("Печатных страниц: " + bookViewModel.getPages());

        this.genre = new Label("Жанр: " + bookViewModel.getIdDiscipline());

        this.button = new Button("В корзину");

        genrePagesButton.add(printedPages, genre, button);
        genrePagesButton.addClassName("book-content-item-button");

        verticalLayout.add(title, author, publishingHouse, description, genrePagesButton);


        horizontalLayout.add(image, verticalLayout);

        return horizontalLayout;
    }

    private Div searchByAuthor(BookViewModel bookViewModel) {
//        Anchor anchor = new Anchor("bookGetAllAuthor", "Все");
//        anchor.addClassName("book-content-anchor-getAll");
        List<BookViewModel> bookViewModelList = new ArrayList<>();
        Div div = new Div();
        div.setText("Еще от автора \"" + bookViewModel.getAuthor() + "\"" );

        var horizontalLayout = new HorizontalLayout();

//        var books = bookService.getAll().stream().map(book -> bookBuilder.createBook(book));

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
        List<BookViewModel> bookViewModelList = new ArrayList<>();

        div.setText("Еще от издательства \"" + getAPublisher(bookViewModel) + "\"");

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

        div.add(horizontalLayout);
        div.addClassName("book-content-item-column-author");

        return div;
    }

    private Div searchByGenre(BookViewModel bookViewModel) {
        List<BookViewModel> bookViewModelList = new ArrayList<>();

        Div div = new Div();
        div.setText("В том же жанре \"" + bookViewModel.getIdDiscipline() + "\"");

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
        div.add(horizontalLayout);
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
//    private String getAGenre(BookViewModel bookViewModel){
//        var listGenre = disciplineService.getAll();
//        for (long i = 0L; i < listGenre.size(); i++){
//            if(listGenre.get((int) i).getId().equals(bookViewModel.getId())){
//                return listGenre.get((int) i).getTitle();
//            }
//
//        }
//        return null;
//    }
}



