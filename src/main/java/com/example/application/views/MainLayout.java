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
//        addToDrawer(createDrawerContent());
    }


//    private Component createDrawerContent() {
//        H2 appName = new H2("DigitalBookBackEnd");
//        appName.addClassNames("app-name");
//
//        com.vaadin.flow.component.html.Section section = new com.vaadin.flow.component.html.Section(appName,
//                createNavigation(), createFooter());
//        section.addClassNames("drawer-section");
//        return section;
//    }

    public static class MainButton extends Div {
        public MainButton() {
            Button refresh = new Button(new H1("DigitalBooks.app"));
            add(refresh);

            getStyle().set("position", "static").set("top", "0").set("right", "0")
                    .set("bottom", "200px").set("left", "0").set("margin", "15px");
        }
    }



    @Route("dialog-basic")
    public class UserDialogBasic extends Div {

        public UserDialogBasic(Icon icon) {

            LoginOverlay loginOverlay = new LoginOverlay();

            Button login = new Button(icon);
            login.addClickListener(event -> loginOverlay.setOpened(true));
            login.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            add(login);

            getStyle().set("position", "absolute").set("top", "0").set("right", "0")
                    .set("bottom", "0").set("left", "85%").set("margin", "15px");


        }
    }

    @Route("dialog-basic")
    public class CartDialogBasic extends Div {

        public CartDialogBasic(Icon icon) {
            LoginOverlay loginOverlay = new LoginOverlay();


            Button secondaryButton = new Button(icon);
            secondaryButton.addClickListener(event -> loginOverlay.setOpened(true));
            secondaryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            HorizontalLayout horizontalLayout = new HorizontalLayout(secondaryButton);

            add(horizontalLayout);

            getStyle().set("position", "absolute").set("top", "0").set("right", "0")
                    .set("bottom", "0").set("left", "80%").set("margin", "15px");

        }
    }

    @Route("dialog-basic")
    public class BookDialogBasic extends Div {

        public BookDialogBasic(Icon icon) {
            LoginOverlay loginOverlay = new LoginOverlay();

            Button login = new Button(icon);
            login.addClickListener(event -> loginOverlay.setOpened(true));
            login.addThemeVariants(ButtonVariant.LUMO_PRIMARY);


            add(login);

            getStyle().set("position", "absolute").set("top", "0").set("right", "0")
                    .set("bottom", "0").set("left", "75%").set("margin", "15px");

        }
    }


//    @Override
//    protected void afterNavigation() {
//        super.afterNavigation();
//        viewTitle.setText(getCurrentPageTitle());
//    }

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



