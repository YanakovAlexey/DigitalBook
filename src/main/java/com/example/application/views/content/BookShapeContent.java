package com.example.application.views.content;

import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Stream;

@Route("shapes")
public class BookShapeContent extends Div {
  final BookService bookService;
  @Autowired
  public BookShapeContent(BookService bookService) {


    
    this.bookService = bookService;
    add(createMainContent());

  }

  private Component createMainContent() {
    Grid<Book> grid = new Grid<>(Book.class, false);
    grid.addColumn(Book::getDescription).setHeader("Описание книги");
//    add(new Image("https://avatarko.ru/img/kartinka/33/multfilm_lyagushka_32117.jpg", "Cat"));

//    List<Book> books = bookService.getAll();

    grid.setItems();
    return grid;
  }
}
