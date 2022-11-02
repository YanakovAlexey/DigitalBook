package com.example.application.views.Basket;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.domain.BasketPosition;
import com.example.application.backEnd.reporitory.BasketPositionRepository;

import com.example.application.backEnd.reporitory.BasketRepository;
import com.example.application.backEnd.reporitory.BookRepository;
import com.example.application.views.ContentView;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "Basket", layout = ContentView.class)
@AnonymousAllowed
public class BasketView extends HorizontalLayout implements HasUrlParameter<Long> {
    Long idUser;
    Div div = new Div();
    Label title = new Label("КОРЗИНА");
    Button buyAllButton = new Button("КУПИТЬ ВСЕ");
    private final BasketPositionRepository basketPositionRepository;
    private final BasketRepository basketRepository;
    private final BookRepository bookRepository;
    private final BookBuilder bookBuilder;

    public BasketView(BasketPositionRepository basketPositionRepository, BasketRepository basketRepository, BookRepository bookRepository, BookBuilder bookBuilder) {
        this.basketPositionRepository = basketPositionRepository;
        this.basketRepository = basketRepository;
        this.bookRepository = bookRepository;
        this.bookBuilder = bookBuilder;
        this.title.addClassNames("basket-title");

    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        this.idUser = parameter;
        var layout = new FlexLayout();
        var basket = basketRepository.findFirstByIdUser(idUser);
        var basketPositionList = basketPositionRepository.findAllByIdBasket(basket.getId());
        for (BasketPosition element : basketPositionList) {
            var book = bookRepository.findById(element.getIdBook());
            layout.add(new BookItemBasket(bookBuilder.createBook(book.get())));
        }

        add(layout);
    }
}

