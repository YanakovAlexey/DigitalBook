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

    private final TranslationProvider translationProvider;

    private final HeaderView headerView = new HeaderView();
    private final FooterView footerView = new FooterView();
    private final ContentView contentView = new ContentView();

    private final Button langButtonGE = new Button("GE");
    private final Button langButtonEN = new Button("EN");

    @Autowired
    public MainLayout(TranslationProvider translationProvider) {
        this.translationProvider = translationProvider;
        this.setPadding(false);
        this.setHeight("100%");
        add(headerView);
        add(contentView);
        add(footerView);
        final UI ui = UI.getCurrent();
        ui.setLocale(new Locale("ge", "GE"));
        add(new Paragraph(translationProvider.getTranslation("searchTitle", ui.getLocale())));
        if (ui.getLocale().getLanguage() == "ar") {
            ui.setDirection(Direction.RIGHT_TO_LEFT);
        }
        langButtonEN.addClickListener(buttonClickEvent -> {
            UI.getCurrent().setLocale(translationProvider.LOCALE_EN);
            VaadinSession.getCurrent().setLocale(translationProvider.LOCALE_EN);
            System.out.println("Current locale is = " + UI.getCurrent().getLocale());
            System.out.println("Current vaadin session locale = " + VaadinSession.getCurrent().getLocale());
            UI.getCurrent().getPage().reload();
        });

        langButtonGE.addClickListener(buttonClickEvent -> {
            UI.getCurrent().setLocale(translationProvider.LOCALE_GE);
            VaadinSession.getCurrent().setLocale(translationProvider.LOCALE_GE);
            System.out.println("Current locale is = " + UI.getCurrent().getLocale());
            System.out.println("Current vaadin session locale = " + VaadinSession.getCurrent().getLocale());
            UI.getCurrent().getPage().reload();
        });

        add(langButtonEN, langButtonGE);
    }

}



