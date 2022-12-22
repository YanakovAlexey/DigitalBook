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
            image.getUI().ifPresent(ui -> ui.navigate("book-content/" + bookViewModel.getId()));

        });

        this.image.addClassNames("book-item-image");

        this.titleLabel = new Anchor("book-content/" + bookViewModel.getId(), bookViewModel.getTitle());
        this.titleLabel.addClassNames("view-color-title");
        this.titleLabel.setWidth("155px");

        this.authorLabel = new Anchor("book-content/" + bookViewModel.getId(), bookViewModel.getAuthor());
        this.authorLabel.addClassNames("view-color-title");
        this.authorLabel.setWidth("155px");

        var verticalLayout = new VerticalLayout();

        verticalLayout.addClassNames("book-item");
        verticalLayout.add(this.image, this.titleLabel, this.authorLabel);

        add(verticalLayout);
    }

}
