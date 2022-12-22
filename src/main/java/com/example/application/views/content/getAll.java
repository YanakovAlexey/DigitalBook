package com.example.application.views.content;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.service.BookService;
import com.example.application.translation.TranslationProvider;
import com.example.application.views.ContentView;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "get-all", layout = ContentView.class)
@AnonymousAllowed
public class getAll extends FlexLayout implements HasUrlParameter<Long> {

    private final BookBuilder bookBuilder;
    private final BookService bookService;

    private final TranslationProvider translationProvider = new TranslationProvider();

    public getAll(BookBuilder bookBuilder, BookService bookService) {
        this.setFlexWrap(FlexWrap.WRAP);
        this.bookBuilder = bookBuilder;
        this.bookService = bookService;
        this.addClassNames("book-content-background");
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        if (parameter == 0) {
            Label label = new Label(this.translationProvider.getTranslation("byRequestBestseller",
                    UI.getCurrent().getLocale()));
            label.setWidth("325px");
            label.setHeight("70px");
            label.addClassNames("get-all-books");
            removeAll();
            var bookList = bookService.getAll();
            var layout = new FlexLayout();
            layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
            bookList.forEach(book -> {
                layout.add(new BookItem(bookBuilder.createBook(book)));
            });
            layout.addClassNames("book-content-item-column-genre");
            this.add(label, layout);
        } else if (parameter == 1) {
            Label label = new Label(this.translationProvider.getTranslation("onRequestYouMayLike",
                    UI.getCurrent().getLocale()));
            label.setWidth("325px");
            label.setHeight("70px");
            label.addClassNames("get-all-books");

            removeAll();
            var bookList = bookService.getAll();
            var layout = new FlexLayout();
            layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
            bookList.forEach(book -> {
                layout.add(new BookItem(bookBuilder.createBook(book)));
            });
            layout.addClassNames("book-content-item-column-genre");
            this.add(label, layout);
        } else if (parameter == 2) {
            Label label = new Label(this.translationProvider.getTranslation("byRequestMajorBooksOf",
                    UI.getCurrent().getLocale()));
            label.setWidth("325px");
            label.setHeight("70px");

            removeAll();
            var bookList = bookService.getAll();
            var layout = new FlexLayout();
            layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
            bookList.forEach(book -> {
                layout.add(new BookItem(bookBuilder.createBook(book)));
            });
            label.addClassNames("get-all-books");
            layout.addClassNames("book-content-item-column-genre");

            this.add(label, layout);

        } else if (parameter == 3) {
            Label label = new Label(this.translationProvider.getTranslation("byRequestAll",
                    UI.getCurrent().getLocale()));
            label.setWidth("325px");
            label.setHeight("70px");

            removeAll();
            var bookList = bookService.getAll();
            var layout = new FlexLayout();
            layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
            bookList.forEach(book -> {
                layout.add(new BookItem(bookBuilder.createBook(book)));
            });
            label.addClassNames("get-all-books");
            layout.addClassNames("book-content-item-column-genre");

            this.add(label, layout);
        }
    }
}
