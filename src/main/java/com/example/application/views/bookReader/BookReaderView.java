package com.example.application.views.bookReader;

import com.example.application.backEnd.service.BookService;
import com.example.application.views.ContentView;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@Route(value = "reader")
@RolesAllowed("USER")
public class BookReaderView extends Div implements HasUrlParameter<Long>, TextSettingsDelegate {

    private final BookService bookService;
    private final Label label = new Label();
    private final BookReader bookReader = new BookReader();
    Long idBook;

    public BookReaderView(BookService bookService) {
        this.bookService = bookService;
        add(label, bookReader);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long aLong) {
        idBook = aLong;
        var book = bookService.getById(idBook);
        label.setTitle("My Book");
    }

    @Override
    public void onThemeWillChange(TextSettings.TextSettingsState.ThemeColor newColor,
                                  TextSettings.TextSettingsState.ThemeColor oldColor) {

    }
}
