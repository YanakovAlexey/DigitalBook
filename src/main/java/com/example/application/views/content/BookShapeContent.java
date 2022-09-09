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
import com.vaadin.flow.component.html.Image;
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

  public BookShapeContent() {
    Book book = new Book();
    var grid = new Grid<Book>();
    book.setTitle("Грива");
    book.setDescription("Описание гривы льва");
    book.setBookImg("https://avatarko.ru/img/kartinka/33/multfilm_lyagushka_32117.jpg");


    grid.setSizeFull();
    grid.addComponentColumn(this::getThumbnail);
    grid.addColumn(Book::getTitle).setHeader("Название");
    grid.addColumn(Book::getDescription).setHeader("Описание");

    grid.setItems(book);
    setHeight("30%");
    setWidth("100%");


    add(grid);
  }



  private Image getThumbnail(Book book) {
    var image = new Image(book.getBookImg(), book.getTitle() + " cover");
    image.setHeight("70px");
    image.addClickListener(event -> System.out.println("Должна быть ссылка на книгу"));
    return image;
  }
}
