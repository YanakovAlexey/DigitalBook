package com.example.application.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLayout;

@ParentLayout(MainLayout.class)
public class ContentView extends Div implements RouterLayout {

    public ContentView() {
        setWidth("100%");
    }
}
