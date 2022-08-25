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
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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
        SearchIconDialogBasic searchIcon = new SearchIconDialogBasic(new Icon(SEARCH));
        BookDialogBasic bookIcon = new BookDialogBasic(new Icon(OPEN_BOOK));
        CartDialogBasic cartDialogBasic = new CartDialogBasic(new Icon(CART));
        header.add(searchDialogBasic, searchIcon, bookIcon, cartDialogBasic, userDialogBasic);


        return header;
    }

    public static class SearchIconDialogBasic extends Div {

        public SearchIconDialogBasic(Icon icon) {
            add(icon);

            getStyle().set("position", "fixed").set("top", "0").set("right", "0")
                    .set("bottom", "0").set("left", "180px").set("display", "flex")
                    .set("align-items", "center").set("justify-content", "center");

        }
    }


    public static class SearchDialogBasic extends Div {

        public SearchDialogBasic() {
            TextField search = new TextField();

            add(search);

            getStyle().set("position", "fixed").set("top", "0").set("right", "0")
                    .set("bottom", "0").set("left", "30px").set("display", "flex")
                    .set("align-items", "center").set("justify-content", "center");

        }
    }
    @Route("dialog-basic")
    public static class UserDialogBasic extends Div {

        public UserDialogBasic(Icon icon) {
            Dialog dialog = new Dialog();

            dialog.setHeaderTitle("Authorization");
            VerticalLayout dialogLayout = createDialogLayout();
            dialog.add(dialogLayout);

            com.vaadin.flow.component.button.Button saveButton = createSaveButton(dialog);
            com.vaadin.flow.component.button.Button cancelButton =
                    new com.vaadin.flow.component.button.Button("Cancel", e -> dialog.close());
            dialog.getFooter().add(cancelButton);
            dialog.getFooter().add(saveButton);

            com.vaadin.flow.component.button.Button button =
                    new com.vaadin.flow.component.button.Button(icon, e -> dialog.open());

            add(dialog, button);


            getStyle().set("position", "fixed").set("top", "0").set("right", "0")
                    .set("bottom", "0").set("left", "900px").set("display", "flex")
                    .set("align-items", "center").set("justify-content", "center");
        }

        private static VerticalLayout createDialogLayout() {

            TextField firstNameField = new TextField("First name");
            TextField lastNameField = new TextField("Last name");

            VerticalLayout dialogLayout = new VerticalLayout(firstNameField,
                    lastNameField);
            dialogLayout.setPadding(false);
            dialogLayout.setSpacing(false);
            dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
            dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

            return dialogLayout;
        }

        private static com.vaadin.flow.component.button.Button createSaveButton(Dialog dialog) {
            com.vaadin.flow.component.button.Button saveButton =
                    new com.vaadin.flow.component.button.Button("Add", e -> dialog.close());
            saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            return saveButton;
        }
    }

    @Route("dialog-basic")
    public static class CartDialogBasic extends Div {

        public CartDialogBasic(Icon icon) {
            Dialog dialog = new Dialog();

            dialog.setHeaderTitle("Authorization");
            VerticalLayout dialogLayout = createDialogLayout();
            dialog.add(dialogLayout);

            com.vaadin.flow.component.button.Button saveButton = createSaveButton(dialog);
            com.vaadin.flow.component.button.Button cancelButton =
                    new com.vaadin.flow.component.button.Button("Cancel", e -> dialog.close());
            dialog.getFooter().add(cancelButton);
            dialog.getFooter().add(saveButton);

            com.vaadin.flow.component.button.Button button =
                    new com.vaadin.flow.component.button.Button(icon, e -> dialog.open());

            add(dialog, button);


            getStyle().set("position", "fixed").set("top", "0").set("right", "0")
                    .set("bottom", "0").set("left", "750px").set("display", "flex")
                    .set("align-items", "center").set("justify-content", "center");
        }

        private static VerticalLayout createDialogLayout() {

            TextField firstNameField = new TextField("First name");
            TextField lastNameField = new TextField("Last name");

            VerticalLayout dialogLayout = new VerticalLayout(firstNameField,
                    lastNameField);
            dialogLayout.setPadding(false);
            dialogLayout.setSpacing(false);
            dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
            dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

            return dialogLayout;
        }

        private static com.vaadin.flow.component.button.Button createSaveButton(Dialog dialog) {
            com.vaadin.flow.component.button.Button saveButton =
                    new com.vaadin.flow.component.button.Button("Add", e -> dialog.close());
            saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            return saveButton;
        }
    }

    @Route("dialog-basic")
    public static class BookDialogBasic extends Div {

        public BookDialogBasic(Icon icon) {
            Dialog dialog = new Dialog();

            dialog.setHeaderTitle("Authorization");
            VerticalLayout dialogLayout = createDialogLayout();
            dialog.add(dialogLayout);

            com.vaadin.flow.component.button.Button saveButton = createSaveButton(dialog);
            com.vaadin.flow.component.button.Button cancelButton =
                    new com.vaadin.flow.component.button.Button("Cancel", e -> dialog.close());
            dialog.getFooter().add(cancelButton);
            dialog.getFooter().add(saveButton);

            com.vaadin.flow.component.button.Button button =
                    new com.vaadin.flow.component.button.Button(icon, e -> dialog.open());

            add(dialog, button);


            getStyle().set("position", "fixed").set("top", "0").set("right", "0")
                    .set("bottom", "0").set("left", "650px").set("display", "flex")
                    .set("align-items", "center").set("justify-content", "center");
        }

        private static VerticalLayout createDialogLayout() {

            TextField firstNameField = new TextField("First name");
            TextField lastNameField = new TextField("Last name");

            VerticalLayout dialogLayout = new VerticalLayout(firstNameField,
                    lastNameField);
            dialogLayout.setPadding(false);
            dialogLayout.setSpacing(false);
            dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
            dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

            return dialogLayout;
        }

        private static com.vaadin.flow.component.button.Button createSaveButton(Dialog dialog) {
            com.vaadin.flow.component.button.Button saveButton =
                    new com.vaadin.flow.component.button.Button("Add", e -> dialog.close());
            saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            return saveButton;
        }
    }


    @Route("login-basic")
    public class LoginBasic extends Div {

        public LoginBasic() {
            getStyle()
                    .set("background-color", "var(--lumo-contrast-5pct)")
                    .set("display", "flex")
                    .set("justify-content", "center")
                    .set("padding", "var(--lumo-space-l)");

            LoginForm loginForm = new LoginForm();
            add(loginForm);
            // Prevent the example from stealing focus when browsing the documentation
            loginForm.getElement().setAttribute("no-autofocus", "");
        }
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
