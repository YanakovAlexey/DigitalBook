package com.example.application.views.items;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.service.UsersService;

import com.example.application.backEnd.viewModel.BookViewModel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

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
    private List<Book> listBooks = new ArrayList<>();
    Div div = new Div();

    public BookContentItem(BookViewModel bookViewModel, UsersService usersService, BookService bookService, BookBuilder bookBuilder) {
        this.usersService = usersService;
        this.bookService = bookService;
        this.bookBuilder = bookBuilder;

        add(content(bookViewModel), searchByAuthor(bookViewModel), searchByAuthor(bookViewModel));
    }

    private HorizontalLayout content(BookViewModel bookViewModel){

        var genrePagesButton = new VerticalLayout();

        var verticalLayout = new VerticalLayout();

        var horizontalLayout = new HorizontalLayout();

        this.image = new Image(bookViewModel.getBookImg(), "");
        this.image.addClassNames("book-content-item-image");

        this.title = new Label(bookViewModel.getTitle());

        this.author = new Label("АВТОР:" + bookViewModel.getAuthor());

        this.publishingHouse = new Label("ИЗДАТЕЛЬСТВО: ");

        this.description = new Label("ОПИСАНИЕ: " + bookViewModel.getDescription());

        this.printedPages = new Label("ПЕЧАТНЫХ СТРАНИЦ: " + bookViewModel.getPages());

        this.genre = new Label("Жанр: " + bookViewModel.getType());

        this.button = new Button("В корзину");

        genrePagesButton.add(printedPages, genre, button);

        div.add(genrePagesButton);
        div.addClassName("book-content-item-button");

        verticalLayout.add(title, author, publishingHouse, description, div);

        horizontalLayout.add(image, verticalLayout);

        return horizontalLayout;
    }

    private Div searchByAuthor(BookViewModel bookViewModel){
        List<BookViewModel> bookViewModelList = new ArrayList<>();
        Div div = new Div();
        div.setText("Еще от автора \"" + bookViewModel.getAuthor() + "\"");

        var horizontalLayout = new HorizontalLayout();

//        var books = bookService.getAll().stream().map(book -> bookBuilder.createBook(book));

        var books = bookService.getAll();

        for (Book book : books) {
            bookViewModelList.add(bookBuilder.createBook(book));
        }

        for (Book book : books) {
            if (book.getAuthor().equals(bookViewModel.getAuthor())) {
                listBooks.add(book);
            }
        }

        for(int i = 0; i < listBooks.size(); i++){
            horizontalLayout.add(new BookItem(bookViewModelList.get(i)));
        }

        div.add(horizontalLayout);
        div.addClassName("book-content-item-column");


        return div;
    }

    private Div searchByPublishingHouse(BookViewModel bookViewModel){
        Div divPublishHouse = new Div();

        List<Book> publishHouse = new ArrayList<>();

        return divPublishHouse;
    }

    private Div searchByGenre(BookViewModel bookViewModel){
        List<BookViewModel> bookViewModelList = new ArrayList<>();

        Div div = new Div();
        div.setText("В том же жанре \"" + bookViewModel.getIdDiscipline() + "\"");

        var horizontalLayout = new HorizontalLayout();

        var books = bookService.getAll();

        for(int i = 0; i < books.size(); i++){
            bookViewModelList.add(bookBuilder.createBook(books.get(i)));
        }

        for (int i = 0; i < books.size(); i++){
            if(books.get(i).getType().equals(bookViewModel.getIdDiscipline())){
                listBooks.add(books.get(i));
            }
        }
        for(int i = 0; i < listBooks.size(); i++){
            horizontalLayout.add(new BookItem(bookViewModelList.get(i)));
        }
        div.add(horizontalLayout);

        return div;
    }
}
