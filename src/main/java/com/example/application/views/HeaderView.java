package com.example.application.views;

import com.example.application.backEnd.service.impl.security.AuthenticatedUser;
import com.example.application.translation.TranslationProvider;
import com.example.application.views.search.SearchView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import java.util.Random;

import static com.vaadin.flow.component.icon.VaadinIcon.*;

public class HeaderView extends VerticalLayout {

    private HorizontalLayout topLine = new HorizontalLayout();
    private HorizontalLayout bottomLine = new HorizontalLayout();
    private final TranslationProvider translationProvider = new TranslationProvider();
    private final Button langButtonGE = new Button("GE");
    private final Button langButtonEN = new Button("RU");
    private final AuthenticatedUser authenticatedUser;


    public HeaderView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        screen();
    }

    private void screen() {
        Button aboutUs = new Button(new Icon(EXCLAMATION_CIRCLE_O));
        Button exit = new Button("Exit");
        Button cartButton = new Button(new Icon(CART));
        LoginOverlay loginOverlay = new LoginOverlay();
        Button secondaryButton = new Button();
        secondaryButton.addClickListener(event -> loginOverlay.setOpened(true));
        secondaryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        exit.addClickListener(event -> {
            exit.getUI().ifPresent(ui -> ui.navigate("logout"));
            this.authenticatedUser.logout();
        });
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

        Button burgerButton = new Button(new Icon(MENU));

        bookButton.addClassNames("view-icons");
        cartButton.addClassNames("view-icons");
        userButton.addClassNames("view-icons");
        burgerButton.addClassNames("view-burger");
        SearchView searchView = new SearchView();

        this.addClassNames("view-header");
//        this.topLine.addClassNames("view-header");
        this.add(topLine, bottomLine);
        this.topLine.add(createTitle(), aboutUs, searchView, burgerButton, bookButton,
                cartButton, userButton, langButtonEN, langButtonGE, exit);
        if (authenticatedUser.get().isPresent()) {
            this.bottomLine.add(createMenuBar());
        }
    }

    private static Div createTitle() {
        Div container = new Div();
        Anchor refresh = new Anchor("/", "DigitalBooks.app");
        refresh.addClassNames("view-title");
        container.addClassNames("view-title-container");
        container.add(refresh);
        return container;
    }

    private Div createTabs() {
        Anchor tab1 = new Anchor("/", "Жанр");
        Anchor tab2 = new Anchor("/", "Издательство");
        Anchor tab3 = new Anchor("/", "Автор");
        Anchor tab4 = new Anchor("upload", "Добавить книгу");

        tab4.addFocusListener(event -> {
            tab4.getUI().ifPresent(ui -> ui.navigate("upload"));
        });
        var result = new Div(tab1, tab2, tab3, tab4);
        tab1.addClassNames("view-tabs");
        tab2.addClassNames("view-tabs");
        tab3.addClassNames("view-tabs");
        tab4.addClassNames("view-tabs");

        return result;
    }

    private MenuBar createMenuBar() {
        var menuBar = new MenuBar();
        menuBar.setOpenOnHover(true);

        var genresMenuItem = menuBar.addItem("Жанр");
        var genresSubMenu = genresMenuItem.getSubMenu();
        for (int i = 0; i < 10; i++) {
            genresSubMenu.addItem("Item " + i, new ComponentEventListener<ClickEvent<MenuItem>>() {
                @Override
                public void onComponentEvent(ClickEvent<MenuItem> event) {
                    System.out.println("CLicked item is " + event);
                }
            });
        }

        var publisherMenuItem = menuBar.addItem("Издательство");
        var publisherSubMenu = publisherMenuItem.getSubMenu();
        for (int i = 0; i < 10; i++) {
            publisherSubMenu.addItem("Item " + i, new ComponentEventListener<ClickEvent<MenuItem>>() {
                @Override
                public void onComponentEvent(ClickEvent<MenuItem> event) {
                    System.out.println("CLicked item is " + event);
                }
            });
        }

        return menuBar;
    }
}
