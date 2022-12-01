package com.example.application.views.menuBarView;

import com.example.application.backEnd.service.BookService;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthorView extends Div {

    private final BookService bookService;

    @Autowired
    public AuthorView(BookService bookService) {
        this.bookService = bookService;
        getAllByAuthor();
    }

    private void getAllByAuthor(){
        Anchor button = new Anchor("getAllAuthors", "Все");
        button.addClassName("title-all-two");
        Anchor anchor;
        FlexLayout flexLayout = new FlexLayout();
        flexLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        flexLayout.setWidth("400px");
        flexLayout.setHeight("140px");
        var authorList = bookService.allAuthors();
        int i = 0;
        for (String a : authorList) {
            var book = bookService.findBookByAuthor(a);
            if (i < 9) {
                flexLayout.add(anchor = new Anchor("GetAllAuthors/" + book.getId(), book.getAuthor()));
            } else {
                break;
            }
            i++;
            anchor.addClassName("tag-margin");
        }
        flexLayout.add(button);

    }
}
