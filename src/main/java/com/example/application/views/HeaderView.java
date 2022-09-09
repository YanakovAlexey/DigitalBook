package com.example.application.views;

import com.example.application.views.registration.RegistrationView;
import com.example.application.views.search.SearchView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

import static com.vaadin.flow.component.icon.VaadinIcon.*;

public class HeaderView extends Header {
  

    public HeaderView() {
        super(new MainLayout.MainTitle());
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
        Button  bookButton = new Button(new Icon(OPEN_BOOK));
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

        bookButton.addClassNames("view-icons");
        cartButton.addClassNames("view-icons");
        userButton.addClassNames("view-icons");
        SearchView searchView = new SearchView();
        this.add(searchView, bookButton, cartButton, userButton);
    }
}
