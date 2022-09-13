package com.example.application.views;

import com.example.application.translation.TranslationProvider;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Direction;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;


/**
 * The main view is a top-level placeholder for other views.
 */
@Route("/")
public class MainLayout extends VerticalLayout {

    private final HeaderView headerView = new HeaderView();
    private final FooterView footerView = new FooterView();
    private final ContentView contentView = new ContentView();

    public MainLayout() {
        this.setPadding(false);
        this.setHeight("100%");
        add(headerView);
        add(contentView);
        add(footerView);
    }
}



