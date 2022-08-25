package com.example.application.views.helloworld;

import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.viewModel.UserViewModel;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("DigitalBooks.app")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class RegistrationView extends HorizontalLayout {

//    private TextField name;
//    private Button sayHello;

    public RegistrationView() {


        getStyle()
                .set("background-color", "var(--lumo-contrast-5pct)")
                .set("display", "flex")
                .set("justify-content", "center")
                .set("padding", "var(--lumo-space-l)");

        LoginForm loginForm = new LoginForm();
        Avatar avatarName = new Avatar();
        add(loginForm, avatarName);
        loginForm.getElement().setAttribute("no-autofocus", "");
    }
}
//    public DigitalBooksView() {
//        name = new TextField("Your name");
//        sayHello = new Button("Say hello");
//        sayHello.addClickListener(e -> {
//            Notification.show("Hello " + name.getValue());
//        });
//        sayHello.addClickShortcut(Key.ENTER);
//
//        setMargin(true);
//        setVerticalComponentAlignment(Alignment.END, name, sayHello);
//
//        add(name, sayHello);
//    }
