package com.example.application.views;

import com.example.application.translation.TranslationProvider;
import com.example.application.views.search.SearchView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

import static com.vaadin.flow.component.icon.VaadinIcon.*;

public class HeaderView extends Header {

    private final TranslationProvider translationProvider = new TranslationProvider();
    private final Button langButtonGE = new Button("GE");
    private final Button langButtonEN = new Button("EN");

    @Autowired
    public HeaderView() {

        super(createTitle());
//        Tab tab1 = new Tab("Жанр");
//        Tab tab2 = new Tab("Автор");
//        Tab tab3 = new Tab("Издательство");
//        Tab tab4 = new Tab("Поддержка");
//        Tabs tabs = new Tabs(tab1, tab2, tab3, tab4);

        this.addClassNames("view-header");

        Button cartButton = new Button(new Icon(CART));
        LoginOverlay loginOverlay = new LoginOverlay();
        Button secondaryButton = new Button();
        secondaryButton.addClickListener(event -> loginOverlay.setOpened(true));
        secondaryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cartButton.addClickListener(event -> {
            cartButton.getUI().ifPresent(ui -> ui.navigate("auth"));
        });
        Button bookButton = new Button(new Icon(OPEN_BOOK));
        Button login = new Button();
        login.addClickListener(event -> loginOverlay.setOpened(true));
        login.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        bookButton.addClickListener(event -> {
            bookButton.getUI().ifPresent(ui -> ui.navigate("auth"));
        });
        Button userButton = new Button(new Icon(USER));
        login.addClickListener(event -> loginOverlay.setOpened(true));
        login.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        userButton.addClickListener(event -> {
            userButton.getUI().ifPresent(ui -> ui.navigate("auth"));
        });


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

        bookButton.addClassNames("view-icons");
        cartButton.addClassNames("view-icons");
        userButton.addClassNames("view-icons");
        SearchView searchView = new SearchView();
        this.add(searchView, bookButton, cartButton, userButton, langButtonEN, langButtonGE);
    }

    private static Div createTitle() {
        Div container = new Div();
        Anchor refresh = new Anchor("/", "DigitalBooks.app");
        refresh.addClassNames("view-title");
        container.addClassNames("view-title-container");
        container.add(refresh);
        return container;
    }
}
