package com.example.application.views.Basket;

import com.example.application.backEnd.domain.Basket;
import com.example.application.backEnd.reporitory.BasketRepository;
import com.example.application.backEnd.service.BasketService;
import com.example.application.views.ContentView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "Basket", layout = ContentView.class)
@AnonymousAllowed
public class BasketView extends HorizontalLayout {
    Div div = new Div();
    Label title = new Label("КОРЗИНА");
    Button buyAllButton = new Button("КУПИТЬ ВСЕ");
    private final BasketService basketService;
    private final BasketRepository basketRepository;
    public BasketView(BasketService basketService, BasketRepository basketRepository) {
        this.basketService = basketService;
        this.basketRepository = basketRepository;
        this.title.addClassNames("basket-title");
        this.buyAllButton.addClassNames("basket-button-buy-all");
        div.add(title, buyAllButton);
        add(div);

    }
}
