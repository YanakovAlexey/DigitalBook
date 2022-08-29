package com.example.application.views.content;

import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.model.Select;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Stream;

@Route("shapes")
public class BookShapeContent extends Div {

  private Grid<Book> grid = new Grid<>();

  private Grid.Column<Book> bookColumn;

  private OrderedList imageContainer;

  public BookShapeContent() {
    constructUI();

  }

  private void constructUI() {
    Book book = new Book();
    book.setBookImg("https://pngimg.com/uploads/anime_girl/anime_girl_PNG103.png");
    grid.addColumn(Book::getBookImg).setHeader("Обложка");
    addClassNames("image-list-view", "max-w-screen-lg", "mx-auto", "pb-l", "px-l");

    HorizontalLayout container = new HorizontalLayout();
    container.addClassNames("items-center", "justify-between");
    container.add(grid);

    VerticalLayout headerContainer = new VerticalLayout();
    H2 header = new H2("Books");
    header.add(grid);

    header.addClassNames("mb-0", "mt-xl", "text-3xl");
    Paragraph description = new Paragraph("Royalty free photos and pictures, courtesy of Unsplash");
    description.addClassNames("mb-xl", "mt-0", "text-secondary");
    headerContainer.add(header, description);


    imageContainer = new OrderedList();
    imageContainer.addClassNames("gap-m", "grid", "list-none", "m-0", "p-0");

    container.add(header);
    add(container, imageContainer);

  }

//  private Component createMainContent() {
//  }
}
