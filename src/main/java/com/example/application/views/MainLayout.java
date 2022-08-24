package com.example.application.views;


import com.example.application.components.appnav.AppNav;
import com.example.application.components.appnav.AppNavItem;
import com.example.application.views.about.AboutView;
import com.example.application.views.helloworld.DigitalBooksView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * The main view is a top-level placeholder for other views.
 */
@Route("button-basic")
public class MainLayout extends AppLayout {

    private H1 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
//        addToDrawer(createDrawerContent());


    }


    private Component createHeaderContent() {
        TextField name;
        viewTitle = new H1();
        viewTitle.addClassNames("view-title");


        Header header = new Header(viewTitle);
        header.addClassNames("view-header");
        Icon searchIcon = new Icon(VaadinIcon.SEARCH);
        Icon bookIcon = new Icon(VaadinIcon.OPEN_BOOK);
        ButtonBasic cartButton = new ButtonBasic(VaadinIcon.CART);
        Button userButton = new Button(String.valueOf(VaadinIcon.USER));
        name = new TextField();
        header.add(name, searchIcon, bookIcon, cartButton, userButton);



        return header;
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

    public class ButtonBasic extends Div {
    private int counter = 0;

    public ButtonBasic(VaadinIcon cart) {
        Button button = new Button(String.valueOf(cart));
        Paragraph info = new Paragraph(infoText());
        addClickListener(clickEvent -> {
            counter += 1;
            info.setText(infoText());
        });

        HorizontalLayout horizontalLayout = new HorizontalLayout(button, info);
        horizontalLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
        add(horizontalLayout);
    }

    private String infoText() {
        return String.format("Clicked %d times", counter);
    }

}

    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();
        nav.addClassNames("app-nav");

        nav.addItem(new AppNavItem("Hello World", DigitalBooksView.class, "la la-globe"));
        nav.addItem(new AppNavItem("About", AboutView.class, "la la-file"));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        layout.addClassNames("app-nav-footer");

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
