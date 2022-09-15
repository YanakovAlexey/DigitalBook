package com.example.application.views.authorization;

import com.example.application.backEnd.service.ResponseException;
import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.viewModel.account.AuthViewModel;
import com.example.application.translation.TranslationProvider;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("/auth")
public class AuthorizationView extends Div {
    private TextField login;
    private PasswordField password;
    LoginForm loginForm;
    LoginI18n i18n;
    Div container = new Div();
    private final TranslationProvider translationProvider = new TranslationProvider();
    private final UsersService usersService;

    public AuthorizationView(UsersService usersService) {
        this.usersService = usersService;

        i18n = LoginI18n.createDefault();
        LoginI18n.ErrorMessage i18nError = i18n.getErrorMessage();
        i18nError.setTitle("Incorrect username or password");
        i18nError.setMessage("Check that you have entered the correct username and password and try again");
        this.i18n.setErrorMessage(i18nError);

        LoginI18n.Form i18nForm = i18n.getForm();
        System.out.println("Current locale is = " + UI.getCurrent().getLocale());
        i18nForm.setTitle(this.translationProvider.getTranslation("authorization",
                UI.getCurrent().getLocale()));
        i18nForm.setUsername(this.translationProvider.getTranslation("username",
                UI.getCurrent().getLocale()));
        i18nForm.setPassword(this.translationProvider.getTranslation("password",
                UI.getCurrent().getLocale()));
        i18nForm.setForgotPassword(this.translationProvider.getTranslation("forgotPassword",
                UI.getCurrent().getLocale()));
        i18nForm.setSubmit(this.translationProvider.getTranslation("logIn",
                UI.getCurrent().getLocale()));
        i18n.setForm(i18nForm);
        this.loginForm = new LoginForm();
        loginForm.setI18n(i18n);
        loginForm.addForgotPasswordListener((e) -> loginForm.getUI().ifPresent(ui
                -> ui.navigate("forgot-password")));
        loginForm.addLoginListener(this::handleSubmit);
        Anchor regLink = new Anchor("reg", this.translationProvider.getTranslation("registration",
                UI.getCurrent().getLocale()));

        container.addClassNames("authorization-container");

        container.add(loginForm, regLink);
        addClassNames("authorization-view");
        add(container);
    }

    private void handleSubmit(AbstractLogin.LoginEvent event) {

        try {
            usersService.auth(new AuthViewModel(
                    event.getUsername(),
                    event.getPassword()
            ));

            this.getUI().ifPresent(ui -> ui.navigate("/"));

        } catch (ResponseException e) {
            e.printStackTrace();
            loginForm.setError(true);
        }
    }
}
