package com.example.application.views.content;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.translation.TranslationProvider;
import com.example.application.views.HeaderView;
import com.example.application.views.MainLayout;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "/", layout = MainLayout.class)
@RouteAlias(value = "/", layout = MainLayout.class)
@AnonymousAllowed

public class BookShapeContent extends FlexLayout implements HeaderView.Delegate {

//    private VerticalLayout verticalLayout = new VerticalLayout();
//    private FlexLayout allBooksLayout = new FlexLayout();
//    private final TranslationProvider translationProvider = new TranslationProvider();
//    private final BookService bookService;
//    private final BookBuilder bookBuilder;
//
//    @Autowired
//    public BookShapeContent(BookService bookService, BookBuilder bookBuilder) {
//        this.addClassNames("view-content");
//        this.bookService = bookService;
//        this.bookBuilder = bookBuilder;
//        this.setFlexWrap(FlexWrap.WRAP);
//
//        verticalLayout.add(allBooks(), youMayLike(), bestsellers(), mainBooks());
//        add(verticalLayout);
//    }
//
//    private Div allBooks() {
//        Button allButton = new Button(this.translationProvider.getTranslation("all",
//                UI.getCurrent().getLocale()));
//        Label label = new Label(this.translationProvider.getTranslation("all",
//                UI.getCurrent().getLocale()));
//        label.addClassNames("book-label");
//        Div div = new Div();
//        var books = bookService
//                .getAll()
//                .stream()
//                .map(bookBuilder::createBook)
//                .toList();
//
//
//        var layout = new HorizontalLayout();
//
//        int i = 0;
//        for (BookViewModel bookViewModel : books) {
//            if (i < 13) {
//                layout.add(new BookItem(bookViewModel));
//                i++;
//            } else {
//                break;
//            }
//
//        }
//        Scroller scroller = new Scroller(
//                new Div(layout));
//        scroller.setScrollDirection(Scroller.ScrollDirection.HORIZONTAL);
//        scroller.setWidth("70%");
//        div.add(scroller);
//        scroller.setScrollDirection(Scroller.ScrollDirection.HORIZONTAL);
//        HorizontalLayout booksScroll = new HorizontalLayout();
//        allBooksLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
//        books.forEach(bookViewModel -> {
//            allBooksLayout.add(new BookItem(bookViewModel));
//        });
//
//        allButton.addClickListener(event ->
//                getUI().ifPresent(ui -> ui.navigate("get-all/" + 3))
//        );
//
//        allBooksLayout.add(allButton);
//        allButton.addClassNames("button-padding");
//        allButton.setHeight("215px");
//        allButton.setWidth("150px");
//
//        booksScroll.setPadding(true);
//        booksScroll.getStyle().set("display", "inline-flex");
//
//        scroller.setContent(allBooksLayout);
//        verticalLayout.setHeight(String.valueOf(false));
//        div.add(label, allBooksLayout);
//        add(div);
//
//        return div;
//    }
//
//    protected void refreshView(String header, List<BookViewModel> books) {
//        Div div = new Div();
//        Label titleAll = new Label(header);
//        titleAll.addClassNames("book-label");
//
//        var layout = new FlexLayout();
//        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
//        books.forEach(bookViewModel -> {
//            layout.add(new BookItem(bookViewModel));
//
//        });
//
//        div.add(layout);
//        allBooksLayout.removeAll();
//        allBooksLayout.add(div);
//    }
//
//
//    @Override
//    protected void onAttach(AttachEvent attachEvent) {
//        super.onAttach(attachEvent);
//        getParent().ifPresent((parent) -> {
//            if (parent instanceof MainLayout) {
//                var headerView = ((MainLayout) parent).getHeaderView();
//                headerView.subscribe(this);
//            }
//        });
//    }
//
//    private Div youMayLike() {
//        Button allButton = new Button(this.translationProvider.getTranslation("all",
//                UI.getCurrent().getLocale()));
//        Div div = new Div();
//        Label titleMayLike = new Label(this.translationProvider.getTranslation("youMayLike",
//                UI.getCurrent().getLocale()));
//        titleMayLike.addClassNames("book-label");
//
//        var books = bookService
//                .getAll()
//                .stream()
//                .map(bookBuilder::createBook)
//                .toList();
//
//        var layout = new FlexLayout();
//        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
//        int i = 0;
//        for (BookViewModel b : books) {
//            if (i < 13) {
//                layout.add(new BookItem(b));
//                i++;
//            } else {
//                break;
//            }
//        }
//
//        allButton.addClickListener(event ->
//                getUI().ifPresent(ui -> ui.navigate("get-all/" + 1))
//        );
//        allButton.addClassNames("button-padding");
//        allButton.setHeight("215px");
//        allButton.setWidth("150px");
//        layout.add(allButton);
//
//        div.add(titleMayLike, layout);
//        return div;
//    }
//
//
//    private Div bestsellers() {
//        Button allButton = new Button(this.translationProvider.getTranslation("all",
//                UI.getCurrent().getLocale()));
//        Div div = new Div();
//        Label titleBestSellers = new Label(this.translationProvider.getTranslation("bestsellers",
//                UI.getCurrent().getLocale()));
//        titleBestSellers.addClassNames("book-label");
//        var books = bookService
//                .getAll()
//                .stream()
//                .map(bookBuilder::createBook)
//                .toList();
//
//        var layout = new FlexLayout();
//        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
//
//        int i = 0;
//        for (BookViewModel b : books) {
//            if (i < 13) {
//                layout.add(new BookItem(b));
//                i++;
//            } else {
//                break;
//            }
//        }
//        allButton.addClickListener(event ->
//                getUI().ifPresent(ui -> ui.navigate("get-all/" + 0))
//        );
//
//        allButton.addClassNames("button-padding");
//        allButton.setHeight("215px");
//        allButton.setWidth("150px");
//
//        layout.add(allButton);
//
//        div.add(titleBestSellers, layout);
//        return div;
//    }
//
//    private Div mainBooks() {
//        Button allButton = new Button(this.translationProvider.getTranslation("all",
//                UI.getCurrent().getLocale()));
//        Div div = new Div();
//        Label titleMain = new Label(this.translationProvider.getTranslation("topBooksOf",
//                UI.getCurrent().getLocale()));
//        titleMain.addClassNames("book-label");
//        var books = bookService
//                .getAll()
//                .stream()
//                .map(bookBuilder::createBook)
//                .toList();
//
//        var layout = new FlexLayout();
//        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
//
//        int i = 0;
//        for (BookViewModel b : books) {
//            if (i < 13) {
//                layout.add(new BookItem(b));
//                i++;
//            } else {
//                break;
//            }
//        }
//        allButton.addClickListener(event ->
//                getUI().ifPresent(ui -> ui.navigate("get-all/" + 2))
//        );
//        allButton.addClassNames("button-padding");
//        allButton.setHeight("215px");
//        allButton.setWidth("150px");
//
//        layout.add(allButton);
//
//        div.add(titleMain, layout);
//        return div;
//    }
//
//    @Override
//    public void onGenreChange(long id, long oldId) {
//        var books = bookService
//                .getAllByIdGenre(id)
//                .stream()
//                .map(bookBuilder::createBook)
//                .toList();
//        refreshView(this.translationProvider.getTranslation("byGenre",
//                UI.getCurrent().getLocale()), books);
//    }
//
//    @Override
//    public void onSearchChange(String text, String oldText) {
//        var books = bookService
//                .getBySearch(text)
//                .stream()
//                .map(bookBuilder::createBook)
//                .toList();
//        refreshView(this.translationProvider.getTranslation("all",
//                UI.getCurrent().getLocale()), books);
//    }
//
//    @Override
//    public void onPublisherChange(long id, long oldId) {
//        var books = bookService
//                .findAllByUserId(id)
//                .stream()
//                .map(bookBuilder::createBook)
//                .toList();
//        refreshView(this.translationProvider.getTranslation("fromThePublisher",
//                UI.getCurrent().getLocale()), books);
//    }
//
//    @Override
//    public void onAuthorChange(long id, long oldId) {
//        var book = bookService.getById(id);
//        var books = bookService
//                .findAllByAuthor(book.getAuthor())
//                .stream()
//                .map(bookBuilder::createBook)
//                .toList();
//        refreshView(this.translationProvider.getTranslation("fromTheAuthor",
//                UI.getCurrent().getLocale()), books);
//    }

//    public void onShowAllBestsellers() {
//
//    }
//
//    public void onShowAllYouMayLike() {
//
//    }
//
//    public void onShowAllMainBooks() {
//    }
}
