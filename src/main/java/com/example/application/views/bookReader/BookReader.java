package com.example.application.views.bookReader;

import com.vaadin.flow.component.html.Div;

public class BookReader extends Div {
    private final Pagination pagination;

    public BookReader() {
        this.pagination = new Pagination(10);
        add(pagination);
    }
}
