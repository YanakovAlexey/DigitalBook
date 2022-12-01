package com.example.application.views.content;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.views.HeaderView;
import com.example.application.views.MainLayout;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "/", layout = MainLayout.class)
@RouteAlias(value = "/", layout = MainLayout.class)
@AnonymousAllowed
public class BookShapeContent extends HorizontalLayout implements HeaderView.Delegate {

    private VerticalLayout verticalLayout = new VerticalLayout();
    private FlexLayout allBooksLayout = new FlexLayout();

    private final BookService bookService;
    private final BookBuilder bookBuilder;

    @Autowired
    public BookShapeContent(BookService bookService, BookBuilder bookBuilder) {
        this.addClassNames("view-content");
        this.bookService = bookService;
        this.bookBuilder = bookBuilder;

        verticalLayout.add(allBooks(), youMayLike(), bestsellers(), mainBooks());
        add(verticalLayout);
    }

    private Div allBooks() {
        Label label = new Label("Все");
        label.addClassNames("book-label");
        Div div = new Div();
        var books = bookService
                .getAll()
                .stream()
                .map(bookBuilder::createBook)
                .toList();


        var layout = new HorizontalLayout();

        books.forEach(bookViewModel -> {

            layout.add(new BookItem(bookViewModel));
        });
        Scroller scroller = new Scroller(
                new Div(layout));
        scroller.setScrollDirection(Scroller.ScrollDirection.HORIZONTAL);
        scroller.setWidth("70%");
        div.add(scroller);
        scroller.setScrollDirection(Scroller.ScrollDirection.HORIZONTAL);
        HorizontalLayout booksScroll = new HorizontalLayout();
        allBooksLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        books.forEach(bookViewModel -> {

            allBooksLayout.add(new BookItem(bookViewModel));
        });


        booksScroll.setPadding(true);
        booksScroll.getStyle().set("display", "inline-flex");

        scroller.setContent(allBooksLayout);
        verticalLayout.setHeight(String.valueOf(false));
        div.add(label, allBooksLayout);
        add(div);

        return div;
    }

    protected void refreshView(String header, List<BookViewModel> books) {
        Div div = new Div();
        Label titleAll = new Label(header);
        titleAll.addClassNames("book-label");

        var layout = new FlexLayout();
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        books.forEach(bookViewModel -> {
            layout.add(new BookItem(bookViewModel));
        });

        div.add(layout);
        allBooksLayout.removeAll();
        allBooksLayout.add(div);
    }


    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        getParent().ifPresent((parent) -> {
            if (parent instanceof MainLayout) {
                var headerView = ((MainLayout) parent).getHeaderView();
                headerView.subscribe(this);
            }
        });
    }


    private Div youMayLike() {
        Div div = new Div();
        Label titleMayLike = new Label("Вам может понравиться");
        titleMayLike.addClassNames("book-label");

        var books = bookService
                .getAll()
                .stream()
                .map(bookBuilder::createBook)
                .toList();

        var layout = new FlexLayout();
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);

        books.forEach(bookViewModel -> {
            layout.add(new BookItem(bookViewModel));
        });

        div.add(titleMayLike, layout);
        return div;
    }


    private Div bestsellers() {
//        Button allButton = new Button("Все");
//        allButton.setHeight("20px");
        Div div = new Div();
        Label titleBestSellers = new Label("Бестселлеры");
        titleBestSellers.addClassNames("book-label");
        var books = bookService
                .getAll()
                .stream()
                .map(bookBuilder::createBook)
                .toList();

        var layout = new FlexLayout();
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);

        books.forEach(bookViewModel -> {
            layout.add(new BookItem(bookViewModel));
        });

        div.add(titleBestSellers, layout);
        return div;
    }

    private Div mainBooks() {
        Div div = new Div();
        Label titleMain = new Label("Главные книги 2020 года");
        titleMain.addClassNames("book-label");
        var books = bookService
                .getAll()
                .stream()
                .map(bookBuilder::createBook)
                .toList();

        var layout = new FlexLayout();
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);

        books.forEach(bookViewModel -> {
            layout.add(new BookItem(bookViewModel));
        });

        div.add(titleMain, layout);
        return div;
    }

    @Override
    public void onGenreChange(long id, long oldId) {
        var books = bookService
                .getAllByIdGenre(id)
                .stream()
                .map(bookBuilder::createBook)
                .toList();
        refreshView("По жанрам", books);
    }

    @Override
    public void onSearchChange(String text, String oldText) {
        var books = bookService
                .getBySearch(text)
                .stream()
                .map(bookBuilder::createBook)
                .toList();
        refreshView("Все", books);
    }

    @Override
    public void onPublisherChange(long id, long oldId) {
        var books = bookService
                .findAllByUserId(id)
                .stream()
                .map(bookBuilder::createBook)
                .toList();
        refreshView("От издательства ", books);
    }

    @Override
    public void onAuthorChange(long id, long oldId) {
        var book = bookService.getById(id);
        var books = bookService
                .findAllByAuthor(book.getAuthor())
                .stream()
                .map(bookBuilder::createBook)
                .toList();
        refreshView("От автора ", books);
    }
}
