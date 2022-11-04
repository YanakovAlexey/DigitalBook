package com.example.application.views.Basket;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.domain.Basket;
import com.example.application.backEnd.domain.BasketPosition;
import com.example.application.backEnd.reporitory.BasketPositionRepository;

import com.example.application.backEnd.reporitory.BasketRepository;
import com.example.application.backEnd.service.BasketService;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.service.impl.security.AuthenticatedUser;
import com.example.application.views.ContentView;
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
    private final BookService bookService;
    private final BookBuilder bookBuilder;
    private final AuthenticatedUser authenticatedUser;
    private final BasketService basketService;


    public BasketView(BasketPositionRepository basketPositionRepository,
                      BasketRepository basketRepository,
                      BookService bookService,
                      BookBuilder bookBuilder,
                      AuthenticatedUser authenticatedUser,
                      BasketService basketService) {
        this.basketPositionRepository = basketPositionRepository;
        this.basketRepository = basketRepository;
        this.bookService = bookService;
        this.bookBuilder = bookBuilder;
        this.authenticatedUser = authenticatedUser;
        this.basketService = basketService;
        this.title.addClassNames("basket-title");

    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        this.idUser = parameter;
        var layout = new FlexLayout();
        var basket = basketRepository.findFirstByIdUser(idUser);

        if (basket == null) {
            basket = new Basket();
            basket.setIdUser(authenticatedUser.get().get().getId());
            basketService.create(basket);
        }

        var basketPositionList = basketPositionRepository.findAllByIdBasket(basket.getId());

        for (BasketPosition element : basketPositionList) {
            var book = bookService.getById(element.getIdBook());

            layout.add(new BookItemBasket(book));
            layout.addClassName("basket-book-item");
        }

        this.buyAllButton.addClassNames("basket-button-buy-all");
        div.add(title, layout, buyAllButton);
        this.addClassNames("book-content-background");
        add(div);
    }
}

