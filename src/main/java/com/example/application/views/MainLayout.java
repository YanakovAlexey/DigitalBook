package com.example.application.views;


import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.service.impl.UserServiceImpl;
import com.example.application.components.appnav.AppNav;
import com.example.application.components.appnav.AppNavItem;
import com.example.application.views.about.AboutView;
import com.example.application.views.helloworld.RegistrationView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.apache.catalina.User;
import org.aspectj.weaver.Shadow;

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
//        add(createFooter());


    }


    private Component createHeaderContent() {

        viewTitle = new H1();

        viewTitle.addClassNames("view-title");
        Tab tab1 = new Tab("Жанр");
        Tab tab2 = new Tab("Автор");
        Tab tab3 = new Tab("Издательство");
        Tab tab4 = new Tab("Поддержка");
        Tabs tabs = new Tabs(tab1, tab2, tab3, tab4);


        Header header = new Header(viewTitle);
        header.addClassNames("view-header");
//        SearchIconDialogBasic searchIcon = new SearchIconDialogBasic(new Icon(SEARCH));
        BookDialogBasic bookIcon = new BookDialogBasic(new Icon(OPEN_BOOK));
        CartDialogBasic cartDialogBasic = new CartDialogBasic(new Icon(CART));
        UserDialogBasic userDialogBasic = new UserDialogBasic(new Icon(USER));
//        SearchDialogBasic searchDialogBasic = new SearchDialogBasic();

        InputFieldAriaLabel inputFieldAriaLabel = new InputFieldAriaLabel();


        header.add(inputFieldAriaLabel, bookIcon, cartDialogBasic, userDialogBasic, tabs);


        return header;
    }

    @Route("input-field-aria-label")
    public class InputFieldAriaLabel extends Div {

        public InputFieldAriaLabel() {
            TextField textField = new TextField();
            textField.getElement().setAttribute("aria-label", "search");
            textField.setPlaceholder("Search");
            textField.setClearButtonVisible(true);
            textField.setPrefixComponent(VaadinIcon.SEARCH.create());
            textField.setWidth("500px");
            add(textField);

            getStyle().set("position", "fixed").set("top", "0").set("right", "0")
                    .set("bottom", "0").set("left", "10px").set("display", "flex")
                    .set("align-items", "center").set("justify-content", "center");
        }

    }


    @Route("dialog-basic")
    public class UserDialogBasic extends Div {

        public UserDialogBasic(Icon icon) {

            LoginOverlay loginOverlay = new LoginOverlay();
            add(loginOverlay);

            Button login = new Button(icon);
            login.addClickListener(event -> loginOverlay.setOpened(true));
            login.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            add(login);

            getStyle().set("position", "fixed").set("top", "0").set("right", "0")
                    .set("bottom", "0").set("left", "900px").set("display", "flex")
                    .set("align-items", "center").set("justify-content", "center");
        }

    }

    @Route("dialog-basic")
    public class CartDialogBasic extends Div {

        public CartDialogBasic(Icon icon) {
            Dialog dialog = new Dialog();

            dialog.setHeaderTitle("Authorization");
            VerticalLayout dialogLayout = createDialogLayout();
            dialog.add(dialogLayout);

            Button saveButton = createSaveButton(dialog);
            Button cancelButton = new Button("Cancel", e -> dialog.close());
            dialog.getFooter().add(cancelButton);
            dialog.getFooter().add(saveButton);

            Button button = new Button(icon, e -> dialog.open());

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

        private static Button createSaveButton(Dialog dialog) {
            Button saveButton = new Button("Add", e -> dialog.close());
            saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            return saveButton;
        }
    }

    @Route("dialog-basic")
    public class BookDialogBasic extends Div {

        public BookDialogBasic(Icon icon) {
            Dialog dialog = new Dialog();

            dialog.setHeaderTitle("Authorization");
            VerticalLayout dialogLayout = createDialogLayout();
            dialog.add(dialogLayout);

            Button saveButton = createSaveButton(dialog);
            Button cancelButton = new Button("Cancel", e -> dialog.close());
            dialog.getFooter().add(cancelButton);
            dialog.getFooter().add(saveButton);

            Button button = new Button(icon, e -> dialog.open());

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

        private static Button createSaveButton(Dialog dialog) {
            Button saveButton = new Button("Add", e -> dialog.close());
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

    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();
        nav.addClassNames("app-nav");

        nav.addItem(new AppNavItem("Hello World", RegistrationView.class, "la la-globe"));
        nav.addItem(new AppNavItem("About", AboutView.class, "la la-file"));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        layout.addClassNames("app-nav-footer");

//        Icon icon = UIUtils.createIcon(LumoUtility.IconSize.SMALL, LumoUtility.TextColor.SUCCESS, VaadinIcon.CHECK);
//        Label label = UIUtils.createLabel(LumoUtility.FontSize.XSMALL, LumoUtility.TextColor.BODY, "Online");
//
//        FlexLayout footer = new FlexLayout(icon, label);
//
//// Set the alignment
//        footer.setAlignItems(FlexComponent.Alignment.CENTER);
//
//// Add spacing and padding
//        footer.addClassNames(
//                LumoStyles.Spacing.Right.S,
//                LumoStyles.Padding.Wide.M
//        );
//
//// Set background color and shadow
//        UIUtils.setBackgroundColor(LumoStyles.Color.BASE_COLOR, footer);
//        UIUtils.setShadow(Shadow.MAX_SHADOW_KIND, footer);
//
//        setAppFooterOuter(footer);

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

