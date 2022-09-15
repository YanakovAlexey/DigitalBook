package com.example.application.views.content;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.service.BookService;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;


@Route("shapes")
@RolesAllowed("USER")
public class BookShapeContent extends HorizontalLayout {

    private final BookService bookService;
    private final BookBuilder bookBuilder;
    private Button button;

    public BookShapeContent(BookService bookService, BookBuilder bookBuilder) {

        this.addClassNames("view-content");
        this.bookService = bookService;
        this.bookBuilder = bookBuilder;
        var books = bookService.getAll().stream().map(book -> bookBuilder.createBook(book));
        var layout = new FlexLayout();
       layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        books.forEach(bookViewModel -> {
            layout.add(new BookItem(bookViewModel));
        });
        add(layout);
    }



    private Image getThumbnail(Book book) {
        var image = new Image(book.getBookImg(), book.getTitle() + " cover");
        image.setHeight("210x");
        image.setWidth("154px");
        image.addClickListener(bookButton -> System.out.println("Работает"));
        return image;
    }


    //        https://digitalbooks.app/books_img/2021/09/cover_227.jpg
//        https://digitalbooks.app/books_img/2022/06/cover_228.png
//        https://digitalbooks.app/books_img/2022/06/cover_231.png
}
