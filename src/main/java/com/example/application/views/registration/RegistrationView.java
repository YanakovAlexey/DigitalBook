package com.example.application.views.registration;


import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.viewModel.account.RegistrationViewModel;
import com.example.application.views.MainLayout;
import com.example.application.views.authorization.AuthorizationView;
import com.vaadin.flow.component.ClickEvent;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.html.Anchor;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;


@Route("reg")
public class RegistrationView extends VerticalLayout {

    private TextField userName;
    private TextField email;
    private PasswordField password;
    private PasswordField repeatPassword;

    private final UsersService usersService;

    @Autowired
    public RegistrationView(UsersService usersService) {
        this.usersService = usersService;

        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle("Registration");
        Avatar avatarName = new Avatar();
        i18n.setForm(i18nForm);
        LoginForm loginForm = new LoginForm();
        loginForm.setI18n(i18n);

        userName = new TextField("Username");
        userName.setPlaceholder("username");
        userName.setId("userName-field");
        userName.setWidth("500px");
        email = new TextField("Email");
        email.setPlaceholder("email");
        email.setId("email-field");
        email.setWidth("500px");
        password = new PasswordField("Password");
        password.setPlaceholder("password");
        password.setId("password-field");
        password.setWidth("500px");
        repeatPassword = new PasswordField("Repeat password");
        repeatPassword.setPlaceholder("repeat password");
        repeatPassword.setWidth("500px");

        Button submit = new Button("Отправить", this::registrationButtonClicked);
        submit.addClickListener((e) -> submit.getUI().ifPresent(ui -> ui.navigate("/auth")));
        submit.setId("submit");
        submit.setWidth("200px");

        Anchor regLink = new Anchor("auth", "Авторизироваться");

        Div container = new Div();
        container.addClassNames("registration-container");

        addClassNames("registration-view");
        add(container);
        container.add(avatarName, userName, email, password, repeatPassword, submit, regLink);
    }

    private void registrationButtonClicked(ClickEvent<Button> buttonClickEvent) {
        usersService.registration(new RegistrationViewModel(
                userName.getValue(),
                email.getValue(),
                password.getValue()

        ));
    }

    public static void logout() {
        // close session to clear all registered routes.
        // also available as sessionRegistry.clear()
        VaadinSession.getCurrent().close();
        UI.getCurrent().getPage().reload();
    }
}

