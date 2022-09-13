package com.example.application.views.registration;


import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.viewModel.account.RegistrationViewModel;
import com.example.application.translation.TranslationProvider;
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
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;


@Route("reg")
public class RegistrationView extends VerticalLayout {

    private TextField userNameTF;
    private TextField emailTF;
    private PasswordField passwordPF;
    private PasswordField repeatPasswordPF;

    private final UsersService usersService;
    private final TranslationProvider translationProvider = new TranslationProvider();

    @Autowired
    public RegistrationView(UsersService usersService) {
        this.usersService = usersService;

        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form i18nForm = i18n.getForm();
        System.out.println("Current locale is = " + UI.getCurrent().getLocale());
        i18nForm.setTitle(this.translationProvider.getTranslation("registration",
                UI.getCurrent().getLocale()));
        Avatar avatarName = new Avatar();
        i18n.setForm(i18nForm);
        LoginForm loginForm = new LoginForm();
        loginForm.setI18n(i18n);

        userNameTF = new TextField(this.translationProvider.getTranslation("username",
                UI.getCurrent().getLocale()));
        userNameTF.setPlaceholder(this.translationProvider.getTranslation("username",
                UI.getCurrent().getLocale()));
        userNameTF.setId("userName-field");
        userNameTF.setWidth("500px");
        emailTF = new TextField(this.translationProvider.getTranslation("email",
                UI.getCurrent().getLocale()));
        emailTF.setPlaceholder(this.translationProvider.getTranslation("email",
                UI.getCurrent().getLocale()));
        emailTF.setId("email-field");
        emailTF.setWidth("500px");
        passwordPF = new PasswordField(this.translationProvider.getTranslation("password",
                UI.getCurrent().getLocale()));
        passwordPF.setPlaceholder(this.translationProvider.getTranslation("password",
                UI.getCurrent().getLocale()));
        passwordPF.setId("password-field");
        passwordPF.setWidth("500px");
        repeatPasswordPF = new PasswordField(this.translationProvider.getTranslation("repeatPassword",
                UI.getCurrent().getLocale()));
        repeatPasswordPF.setPlaceholder(this.translationProvider.getTranslation("repeatPassword",
                UI.getCurrent().getLocale()));
        repeatPasswordPF.setWidth("500px");

        Button submit = new Button(this.translationProvider.getTranslation("send",
                UI.getCurrent().getLocale()), this::registrationButtonClicked);
        submit.addClickListener((e) -> submit.getUI().ifPresent(ui -> ui.navigate("/auth")));
        submit.setId("submit");
        submit.setWidth("200px");

        Anchor regLink = new Anchor("auth", this.translationProvider.getTranslation("login",
                UI.getCurrent().getLocale()));

        Div container = new Div();
        container.addClassNames("registration-container");

        addClassNames("registration-view");
        add(container);
        container.add(avatarName, userNameTF, emailTF, passwordPF, repeatPasswordPF, submit, regLink);
    }

    private void registrationButtonClicked(ClickEvent<Button> buttonClickEvent) {
        usersService.registration(new RegistrationViewModel(
                userNameTF.getValue(),
                emailTF.getValue(),
                passwordPF.getValue()

        ));
    }

    public static void logout() {
        // close session to clear all registered routes.
        // also available as sessionRegistry.clear()
        VaadinSession.getCurrent().close();
        UI.getCurrent().getPage().reload();
    }
}

