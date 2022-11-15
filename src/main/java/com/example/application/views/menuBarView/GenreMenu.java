package com.example.application.views.menuBarView;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.reporitory.DisciplineRepository;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.service.DisciplineService;
import com.example.application.views.ContentView;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "getByGenre", layout = ContentView.class)
@AnonymousAllowed
public class GenreMenu extends FlexLayout implements HasUrlParameter<Long> {

    private final BookService bookService;
    private final BookBuilder bookBuilder;
    private final DisciplineService disciplineService;
    private final DisciplineRepository disciplineRepository;

    GenreMenu(BookService bookService,
              BookBuilder bookBuilder,
              DisciplineService disciplineService, DisciplineRepository disciplineRepository) {
        this.bookService = bookService;
        this.bookBuilder = bookBuilder;
        this.disciplineService = disciplineService;

        this.disciplineRepository = disciplineRepository;
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        Long idGenre = parameter;

        var d = disciplineService.getById(idGenre);

        Label label = new Label("По жанру  " + d.getTitle());


        var layout = new FlexLayout();

        var bookList = bookService.getAllByIdGenre(idGenre);

        if (bookList.isEmpty()) {
            new EmptyView();
        }

        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        bookList.forEach(bookViewModel -> {
            layout.add(new BookItem(bookBuilder.createBook(bookViewModel)));
        });
        label.addClassNames("content-name");
        add(label, layout);
    }
}



