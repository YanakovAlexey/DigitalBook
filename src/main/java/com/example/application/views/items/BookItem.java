package com.example.application.views.items;

import com.example.application.backEnd.viewModel.BookViewModel;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class BookItem extends Div {

    private Image image;
    private Anchor titleLabel, authorLabel;

    public BookItem(BookViewModel bookViewModel) {

        this.image = new Image(bookViewModel.getBookImg(), "");
        this.image.addClickListener(button -> System.out.println("Работает"));
        this.image.addClassNames("book-item-image");
        this.titleLabel = new Anchor("bookContent/" + bookViewModel.getId(), bookViewModel.getTitle());
        this.authorLabel = new Anchor("bookContent/" + bookViewModel.getId(), bookViewModel.getAuthor());
        var verticalLayout = new VerticalLayout();
        verticalLayout.addClassNames("book-item");
        verticalLayout.add(this.image, this.titleLabel, this.authorLabel);
        add(verticalLayout);
    }


}
