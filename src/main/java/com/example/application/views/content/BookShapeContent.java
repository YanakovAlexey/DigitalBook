package com.example.application.views.content;

import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.charts.model.Select;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.List;
import java.util.stream.Stream;

@Route("shapes")
public class BookShapeContent extends Div {

  @Autowired
  public BookShapeContent() {
    add(constructUI());
  }

  private Component constructUI() {
    Avatar avatarBasic = new Avatar();

    Avatar avatarName = new Avatar("https://pngicon.ru/file/uploads/dota-2.png");

    Avatar avatarImage = new Avatar("https://pngicon.ru/file/uploads/dota-2.png");

    VerticalLayout layout = new VerticalLayout();

    Book book = new Book();
    book.setTitle("Герой");
    book.setDescription("Книга про одинокого героя");
    Grid<Book> grid = new Grid<>(Book.class, false);
    grid.addColumn(Book::getTitle).setHeader("Название");
    grid.addColumn(Book::getDescription).setHeader("Описание");

    avatarImage.setImage("https://pngicon.ru/file/uploads/dota-2.png");
    avatarImage.setHeight("140px");
    avatarImage.setWidth("140px");

//    List<Book> people = DataService.getPeople();
    grid.setItems(book);
    layout.add(grid, avatarImage);
    return layout;
  }
}
