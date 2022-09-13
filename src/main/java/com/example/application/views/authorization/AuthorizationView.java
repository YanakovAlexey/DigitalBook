package com.example.application.views.authorization;

import com.example.application.translation.TranslationProvider;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("/auth")
public class AuthorizationView extends Div {
    private TextField login;
    private PasswordField password;
    private final TranslationProvider translationProvider = new TranslationProvider();

    public AuthorizationView() {

        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form i18nForm = i18n.getForm();
        System.out.println("Current locale is = " + UI.getCurrent().getLocale());
        i18nForm.setTitle(this.translationProvider.getTranslation("authorization",
                UI.getCurrent().getLocale()));
        i18nForm.setUsername( this.translationProvider.getTranslation("username",
                UI.getCurrent().getLocale()));
        i18nForm.setPassword( this.translationProvider.getTranslation("password",
                UI.getCurrent().getLocale()));
        i18nForm.setForgotPassword( this.translationProvider.getTranslation("forgotPassword",
                UI.getCurrent().getLocale()));
        i18nForm.setSubmit( this.translationProvider.getTranslation("logIn",
                UI.getCurrent().getLocale()));
        i18n.setForm(i18nForm);
        LoginForm loginForm = new LoginForm();
        loginForm.setI18n(i18n);

        loginForm.addForgotPasswordListener((e) -> loginForm.getUI().ifPresent(ui
                -> ui.navigate("forgot-password")));
        loginForm.addLoginListener((e) -> loginForm.getUI().ifPresent(ui -> ui.navigate("/")));
        Anchor regLink = new Anchor("reg", this.translationProvider.getTranslation("registration",
                UI.getCurrent().getLocale()));
        Div container = new Div();
        container.addClassNames("authorization-container");

        container.add(loginForm, regLink);
        addClassNames("authorization-view");
        add(container);
    }
}
