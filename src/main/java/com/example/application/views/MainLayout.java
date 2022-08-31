package com.example.application.views;

import com.example.application.backEnd.domain.Book;
import com.example.application.components.appnav.AppNav;
import com.example.application.components.appnav.AppNavItem;
import com.example.application.views.about.AboutView;
import com.example.application.views.content.BookShapeContent;
import com.example.application.views.helloworld.RegistrationView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.I18NProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.aspectj.weaver.Shadow;
import org.atmosphere.cpr.AtmosphereRequestImpl;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;
import org.gephi.ui.utils.UIUtils;
import org.slf4j.LoggerFactory;


import java.text.MessageFormat;
import java.util.*;

import static com.vaadin.flow.component.icon.VaadinIcon.*;

/**
 * The main view is a top-level placeholder for other views.
 */
@Route("button-basic")
public class MainLayout extends AppLayout {
    private H1 viewTitle;
    private H2 viewContent;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());

//        addToDrawer(createDrawerContent());

//        addToNavbar(true, createFooter());


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

        BookDialogBasic bookDialogBasic = new BookDialogBasic(new Icon(OPEN_BOOK));
        CartDialogBasic cartDialogBasic = new CartDialogBasic(new Icon(CART));
        UserDialogBasic userDialogBasic = new UserDialogBasic(new Icon(USER));
        TranslationProvider translationProvider = new TranslationProvider();

        InputFieldAriaLabel inputFieldAriaLabel = new InputFieldAriaLabel();
        header.add(inputFieldAriaLabel, bookDialogBasic, cartDialogBasic, userDialogBasic, tabs);

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
//            Button button = new Button(cart);
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

//    public class SearchDialogBasic extends Div {
//
//        public SearchDialogBasic() {
//            TextField search = new TextField();
//
//            add(search);
//
//            getStyle().set("position", "fixed").set("top", "0").set("right", "0")
//                    .set("bottom", "0").set("left", "30px").set("display", "flex")
//                    .set("align-items", "center").set("justify-content", "center");
//
//        }
//    }

    @Route("dialog-basic")
    public class UserDialogBasic extends Div {

        public UserDialogBasic(Icon icon) {

            LoginOverlay loginOverlay = new LoginOverlay();

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
            LoginOverlay loginOverlay = new LoginOverlay();

            Button login = new Button(icon);
            login.addClickListener(event -> loginOverlay.setOpened(true));
            login.addThemeVariants(ButtonVariant.LUMO_PRIMARY);


            add(login);

            getStyle().set("position", "fixed").set("top", "0").set("right", "0")
                    .set("bottom", "0").set("left", "750px").set("display", "flex")
                    .set("align-items", "center").set("justify-content", "center");
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


            getStyle().set("position", "fixed").set("top", "0").set("right", "0")
                    .set("bottom", "0").set("left", "650px").set("display", "flex")
                    .set("align-items", "center").set("justify-content", "center");
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

    private FooterView createFooter() {
        FooterView layout = new FooterView();
        layout.addClassNames("app-nav-footer");

//        Icon icon = UIUtils.createIcon(LumoUtility.IconSize.SMALL, LumoUtility.TextColor.SUCCESS, CHECK);
//        Label label = UIUtils.createLabel(LumoUtility.FontSize.XSMALL, LumoUtility.TextColor.BODY, "Online");
//
//        FlexLayout footer = new FlexLayout(icon, label);
//
//// Set the alignment
//        footer.setAlignItems(FlexComponent.Alignment.CENTER);

//// Add spacing and padding
//        footer.addClassNames(
//                LumoStyles.Spacing.Right.S,
//                LumoStyles.Padding.Wide.M
//        );
//
//// Set background color and shadow
//        UIUtils.s(LumoStyles.Color.BASE_COLOR, footer);
//        UIUtils.setShadow(Shadow.MAX_SHADOW_KIND, footer);
//        layout.add(footer);

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

    public class TranslationProvider implements I18NProvider {

        public static final String BUNDLE_PREFIX = "translate";

        public final Locale LOCALE_FI = new Locale("fi", "FI");
        public final Locale LOCALE_EN = new Locale("en", "GB");

        private List<Locale> locales = Collections
                .unmodifiableList(Arrays.asList(LOCALE_FI, LOCALE_EN));

        @Override
        public List<Locale> getProvidedLocales() {
            return locales;
        }

        @Override
        public String getTranslation(String key, Locale locale, Object... params) {
            if (key == null) {
                LoggerFactory.getLogger(TranslationProvider.class.getName())
                        .warn("Got lang request for key with null value!");
                return "";
            }

            final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_PREFIX, locale);

            String value;
            try {
                value = bundle.getString(key);
            } catch (final MissingResourceException e) {
                LoggerFactory.getLogger(TranslationProvider.class.getName())
                        .warn("Missing resource", e);
                return "!" + locale.getLanguage() + ": " + key;
            }
            if (params.length > 0) {
                value = MessageFormat.format(value, params);
            }
            return value;
        }
    }

    private Component createMainContent() {
        viewContent = new H2();

        Book book = new Book();
        Grid<Book> grid = new Grid<>(Book.class, false);
        grid.addColumn(Book::getBookImg);
        grid.addColumn(Book::getDescription).setHeader("Описание книги");
        // addToDrawer(new Image("https://avatarko.ru/img/kartinka/33/multfilm_lyagushka_32117.jpg", "Cat"));
//        List<Book> books;
        book.setBookImg("https://avatarko.ru/img/kartinka/33/multfilm_lyagushka_32117.jpg");
        book.setDescription("Книга про людей, Илья Янаков");

        grid.setItems(book);
        viewContent.add(grid);
        return grid;
    }
}


