package com.example.application.views.basket;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.domain.Basket;
import com.example.application.backEnd.domain.BasketPosition;
import com.example.application.backEnd.reporitory.BasketRepository;
import com.example.application.backEnd.service.BasketPositionService;
import com.example.application.backEnd.service.BasketService;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.service.impl.security.AuthenticatedUser;
import com.example.application.translation.TranslationProvider;
import com.example.application.views.ContentView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@Route(value = "Basket", layout = ContentView.class)
@RolesAllowed("USER")
public class BasketView extends HorizontalLayout implements HasUrlParameter<Long> {

    Long idUser;
    Div div = new Div();
    BookItemBasket bookItemBasket;

    private final TranslationProvider translationProvider = new TranslationProvider();

    Label title = new Label(this.translationProvider.getTranslation("basket",
            UI.getCurrent().getLocale()));
    Button buyAllButton = new Button(this.translationProvider.getTranslation("buyEverything",
            UI.getCurrent().getLocale()));

    private final BasketRepository basketRepository;

    private final BasketPositionService basketPositionService;
    private final BookService bookService;
    private final BasketService basketService;

    private final AuthenticatedUser authenticatedUser;

    private final BookBuilder bookBuilder;


    public BasketView(BasketPositionService basketPositionService,
                      BasketRepository basketRepository,
                      BookService bookService,
                      BookBuilder bookBuilder,
                      AuthenticatedUser authenticatedUser,
                      BasketService basketService) {
        this.basketPositionService = basketPositionService;
        this.basketRepository = basketRepository;
        this.bookService = bookService;
        this.bookBuilder = bookBuilder;
        this.authenticatedUser = authenticatedUser;
        this.basketService = basketService;
        this.title.addClassNames("basket-title");
        this.buyAllButton.addClassNames("basket-button-buy-all");
        this.buyAllButton.addClassNames("basket-content-view");

        buyAllButton.addClickListener(event ->
                getUI().ifPresent(ui -> ui.navigate("book-payment")));


    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        this.idUser = parameter;
        var layout = new FlexLayout();
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        var basket = basketRepository.findFirstByIdUser(idUser);

        if (basket == null) {
            basket = new Basket();
            basket.setIdUser(authenticatedUser.get().get().getId());
            basketService.create(basket);

        }

        var basketPositionList = basketPositionService.findAllByIdBasket(basket.getId());

        div.add(title, buyAllButton, layout);

        for (BasketPosition element : basketPositionList) {
            var book = bookService.getById(element.getIdBook());
            layout.add(bookItemBasket = new BookItemBasket(book, basketPositionService));
            bookItemBasket.deleteButton.addClickListener(even ->
                    basketPositionService.deleteById(element.getId()));
            layout.addClassName("basket-book-item");
        }

        this.buyAllButton.addClassNames("basket-button-buy-all");
        div.add(title, buyAllButton, layout);
        this.addClassNames("book-content-background");
        getUI().ifPresent(ui -> ui.getPage().reload());
        add(div);
    }
}


