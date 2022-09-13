package com.example.application.views.authorization;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinSession;

import javax.servlet.http.HttpServletRequest;

@Route("/auth")
public class AuthorizationView extends Div {
    private TextField login;
    private PasswordField password;


    public AuthorizationView() {

        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle("Авторизация");
        i18n.setForm(i18nForm);
        LoginForm loginForm = new LoginForm();
        loginForm.setI18n(i18n);

        loginForm.addForgotPasswordListener((e) -> loginForm.getUI().ifPresent(ui
                -> ui.navigate("forgot-password")));
        loginForm.addLoginListener((e) -> loginForm.getUI().ifPresent(ui -> ui.navigate("/")));
        Anchor regLink = new Anchor("reg", "Registration");
        Div container = new Div();
        container.addClassNames("authorization-container");

        container.add(loginForm, regLink);
        addClassNames("authorization-view");
        add(container);

    }
}
