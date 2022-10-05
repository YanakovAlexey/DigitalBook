package com.example.application.views.cart;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;



//@Route(value = "cart", layout = MainLayout.class)
public class CartView extends Div {

    public CartView() {

        H1 heading = new H1("Cart view");
        add(heading);

    }
}
