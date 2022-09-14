package com.example.application.views.content;

import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Select;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.List;
import java.util.stream.Stream;

@Route("shapes")
public class BookShapeContent extends VerticalLayout {




    @Autowired
    public BookShapeContent() {
        FormLayout layout = new FormLayout();
        Book book = new Book();
        book.setAuthor("Илья Янаков");
        book.setTitle("Личная биография");
        Button button = new Button();
        button.addClickListener(bookButton -> System.out.println("Работает"));
        button.setIcon(getThumbnail(book));
        layout.add(button);

    }

    private Image getThumbnail(Book book) {
        var image = new Image(book.getBookImg(), book.getTitle() + " cover");
        image.setHeight("70px");
        image.addClickListener(event -> System.out.println("Прописать поведение кнопки здесь"));
        return image;
    }
}
