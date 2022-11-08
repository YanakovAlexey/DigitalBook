package com.example.application.views;

import com.example.application.backEnd.domain.Publisher;
import com.example.application.backEnd.service.DisciplineService;
import com.example.application.backEnd.service.PublisherService;
import com.example.application.backEnd.service.impl.security.AuthenticatedUser;
import com.example.application.backEnd.viewModel.DisciplineViewModel;
import com.example.application.backEnd.viewModel.PublisherViewModel;
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
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import static com.vaadin.flow.component.icon.VaadinIcon.*;

public class HeaderView extends VerticalLayout {

    private HorizontalLayout topLine = new HorizontalLayout();
    private HorizontalLayout bottomLine = new HorizontalLayout();
    private final TranslationProvider translationProvider = new TranslationProvider();
    private final Button langButtonGE = new Button("GE");
    private final Button langButtonRU = new Button("RU");
    private final AuthenticatedUser authenticatedUser;
    private final DisciplineService disciplineService;
    private final PublisherService publisherService;


    public HeaderView(AuthenticatedUser authenticatedUser, DisciplineService disciplineService, PublisherService publisherService) {
        this.authenticatedUser = authenticatedUser;
        this.disciplineService = disciplineService;
        this.publisherService = publisherService;

        screen();
    }

    private void screen() {

        Button aboutUs = new Button(new Icon(EXCLAMATION_CIRCLE_O));
        Button cartButton = new Button(new Icon(CART));

        LoginOverlay loginOverlay = new LoginOverlay();
        Button secondaryButton = new Button();
        secondaryButton.addClickListener(event -> loginOverlay.setOpened(true));
        secondaryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        cartButton.addClickListener(event -> {
            if (authenticatedUser.get().isPresent()) {
                cartButton.getUI().ifPresent(ui ->
                        ui.navigate("Basket/" + authenticatedUser.get().get().getId()));
            } else cartButton.getUI().ifPresent(ui ->
                    ui.navigate("/auth"));
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

        langButtonRU.addClickListener(buttonClickEvent -> {
            UI.getCurrent().setLocale(translationProvider.LOCALE_RU);
            VaadinSession.getCurrent().setLocale(translationProvider.LOCALE_RU);
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
        this.add(topLine, bottomLine);
        this.topLine.add(createTitle(), aboutUs, searchView, burgerButton, bookButton,
                cartButton, userButton, langButtonRU, langButtonGE);
        if (authenticatedUser.get().isPresent()) {
            this.bottomLine.add(createMenuBar(), createTabs());
            userButton.addClickListener(event -> {
                userButton.getUI().ifPresent(ui -> ui.navigate("profile"));
            });
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

    private Anchor createTabs() {

        Anchor addBook = new Anchor("upload", this.translationProvider.getTranslation("addABook",
                UI.getCurrent().getLocale()));

        addBook.addFocusListener(event -> {
            addBook.getUI().ifPresent(ui -> ui.navigate("upload"));
        });

        addBook.addClassNames("view-tabs");

        return addBook;
    }

    private MenuBar createMenuBar() {

        var menuBar = new MenuBar();
        menuBar.setOpenOnHover(true);

        var genresMenuItem = menuBar.addItem(this.translationProvider.getTranslation("genre",
                UI.getCurrent().getLocale()));
        var genresSubMenu = genresMenuItem.getSubMenu();

        genresSubMenu.add(getGenres());

        var publisherMenuItem = menuBar.addItem(this.translationProvider.getTranslation("publishingHouse",
                UI.getCurrent().getLocale()));

        var publisherSubMenu = publisherMenuItem.getSubMenu();
        publisherSubMenu.add(getAPublisher());


        var authorMenuItem = menuBar.addItem(this.translationProvider.getTranslation("author",
                UI.getCurrent().getLocale()));
        var authorSubMenu = authorMenuItem.getSubMenu();
        authorSubMenu.add();


        this.addClassNames("view-menu-bar");

        return menuBar;
    }

    private FlexLayout getGenres(){
         Anchor anchor;
         FlexLayout flexLayout = new FlexLayout();
        flexLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        flexLayout.setWidth("800px");
        flexLayout.setHeight("200px");

        var allGenresList = disciplineService.getAll();
        for(DisciplineViewModel discipline: allGenresList){
            flexLayout.add(anchor = new Anchor("getByGenre/" + discipline.getId(), discipline.getTitle() ));
            anchor.addClassName("tag-margin");
        }
        return flexLayout;
    }

    private FlexLayout getAPublisher(){
        Anchor anchor;
        FlexLayout flexLayout = new FlexLayout();
        flexLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        flexLayout.setWidth("800px");
        flexLayout.setHeight("200px");

        var publisherList = publisherService.getAll();
        for(Publisher publisher: publisherList){
            flexLayout.add(anchor = new Anchor("getByGenre/" + publisher.getId(), publisher.getName() ));
            anchor.addClassName("tag-margin");
        }
        return flexLayout;
    }
}
