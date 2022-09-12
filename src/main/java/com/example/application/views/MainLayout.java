package com.example.application.views;

import com.example.application.backEnd.domain.Book;
import com.example.application.components.appnav.AppNav;
import com.example.application.components.appnav.AppNavItem;
import com.example.application.views.about.AboutView;
import com.example.application.views.registration.RegistrationView;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


import static com.vaadin.flow.component.icon.VaadinIcon.*;

/**
 * The main view is a top-level placeholder for other views.
 */
@Route("/")
public class MainLayout extends AppLayout {

    private H2 viewContent;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, new HeaderView());
        addToNavbar(true, new FooterView());
    }

    public static class MainTitle extends Div {
        public MainTitle() {
            Anchor refresh = new Anchor("reg", "DigitalBooks.app");
            refresh.addClassNames("view-title");
            addClassNames("view-title-container");
            add(refresh);

        }
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }

//    private Component createMainContent() {
//
//        Main main = new Main();
//        main.addClassNames("view-main");
//        Grid<Book> grid = new Grid<>(Book.class, false);
//        grid.addColumn(Book::getDescription).setHeader("Описание книги");
//
////         List<Book> books = bookService.getAll();
//
//        grid.setItems();
//        main.add(grid);
//        return main;
//    }

}



