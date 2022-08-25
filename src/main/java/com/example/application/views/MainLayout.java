package com.example.application.views;


import com.example.application.components.appnav.AppNav;
import com.example.application.components.appnav.AppNavItem;
import com.example.application.views.about.AboutView;
import com.example.application.views.header.ButtonView;
import com.example.application.views.header.IconView;
import com.example.application.views.header.TextFileView;
import com.example.application.views.helloworld.RegistrationView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import static com.vaadin.flow.component.icon.VaadinIcon.*;

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


    public Component createHeaderContent() {

        viewTitle = new H1();

        viewTitle.addClassNames("view-title");


        Header header = new Header(viewTitle);
        header.addClassNames("view-header");
        IconView.SearchIconDialogBasic searchIcon = new IconView.SearchIconDialogBasic(new Icon(SEARCH));
        ButtonView.BookDialogBasic bookIcon = new ButtonView.BookDialogBasic(new Icon(OPEN_BOOK));
        ButtonView.CartDialogBasic cartDialogBasic = new ButtonView.CartDialogBasic(new Icon(CART));
        ButtonView.UserDialogBasic userDialogBasic = new ButtonView.UserDialogBasic(new Icon(USER));
        TextFileView.SearchDialogBasic searchDialogBasic = new TextFileView.SearchDialogBasic();
        header.add(searchDialogBasic, searchIcon, bookIcon, cartDialogBasic, userDialogBasic);


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

    //    public class CartButtonBasic extends Div {
//
//        public CartButtonBasic(Icon cart) {
//            ButtonView button = new ButtonView(cart);
//
//            button.addClickListener(clickEvent -> {
//            });
//
//            HorizontalLayout horizontalLayout = new HorizontalLayout(button);
//            horizontalLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
//            add(horizontalLayout);
//        }
//
//    }


    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();
        nav.addClassNames("app-nav");

        nav.addItem(new AppNavItem("Hello World", RegistrationView.class, "la la-globe"));
        nav.addItem(new AppNavItem("About", AboutView.class, "la la-file"));

        return nav;
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
