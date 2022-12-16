package com.example.application.views.items;

import com.example.application.backEnd.viewModel.BookViewModel;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class BookItem extends Div {

    private final String BASE_PATH = "http://91.239.26.196:7070/images/";
    private Image image;
    private Anchor titleLabel, authorLabel;

    public BookItem(BookViewModel bookViewModel) {

        this.image = new Image(BASE_PATH + bookViewModel.getBookImg(), "");
        this.image.addClickListener(event -> {
            image.getUI().ifPresent(ui -> ui.navigate("BookContent/" + bookViewModel.getId()));

        });

        this.image.addClassNames("book-item-image");

        this.titleLabel = new Anchor("BookContent/" + bookViewModel.getId(), bookViewModel.getTitle());

        this.titleLabel.addClassNames("view-color-title");

        this.authorLabel = new Anchor("BookContent/" + bookViewModel.getId(), bookViewModel.getAuthor());
        this.authorLabel.addClassNames("view-color-title");

        var verticalLayout = new VerticalLayout();

        verticalLayout.addClassNames("book-item");
        verticalLayout.add(this.image, this.titleLabel, this.authorLabel);

        add(verticalLayout);
    }
}
