package com.example.application;


import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("/my")
@RoutePrefix(value = "my", absolute = true)
@ParentLayout(MainLayout.class)
@AnonymousAllowed
public class MyContainer extends HorizontalLayout implements RouterLayout {

    public MyContainer() {
        add(new Label("Hello"));
    }
}
