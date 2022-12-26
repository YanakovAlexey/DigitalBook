package com.example.application.views.basket;

import com.example.application.views.ContentView;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@Route(value = "book-payment", layout = ContentView.class)
@RolesAllowed("USER")
public class PayPageView extends FlexLayout {



}
