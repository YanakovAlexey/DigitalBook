package com.example.application.views;

import com.example.application.views.content.BookShapeContent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.router.RouterLayout;

@ParentLayout(MainLayout.class)
public class ContentView extends Div implements RouterLayout {

    public ContentView() {
        setWidth("100%");
    }
}
