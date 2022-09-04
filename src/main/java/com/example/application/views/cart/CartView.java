package com.example.application.views.cart;

import com.example.application.views.HeaderView;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;



@Route(value = "cart", layout = MainLayout.class)
public class CartView extends Div {

    public CartView() {

        H1 heading = new H1("Cart view");
        add(heading);

    }
}
