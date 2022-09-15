package com.example.application.views.items;

import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import javax.swing.*;

public class BookItem extends Div {

    private Image image;
    private Label titleLabel;
    private Label authorLabel;

    public BookItem(BookViewModel bookViewModel) {

        this.image = new Image(bookViewModel.getBookImg(), "");
        this.image.addClickListener(button -> System.out.println("Работает"));
        this.image.addClassNames("book-item-image");
        this.titleLabel = new Label(bookViewModel.getTitle());
        this.authorLabel = new Label(bookViewModel.getAuthor());
        var verticalLayout = new VerticalLayout();
        verticalLayout.addClassNames("book-item");
        verticalLayout.add(this.image, this.titleLabel, this.authorLabel);
        add(verticalLayout);
    }


}
