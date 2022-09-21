package com.example.application.views.registration;

import com.example.application.backEnd.service.ResponseException;
import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.viewModel.account.RegistrationViewModel;
import com.example.application.models.NotificationType;
import com.example.application.translation.TranslationProvider;
import com.example.application.ui.NotificationComponent;
import com.example.application.views.ContentView;
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
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Route(value = "reg", layout = ContentView.class)
@AnonymousAllowed
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
        emailTF.setPattern("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        emailTF.setPlaceholder(this.translationProvider.getTranslation("email",
                UI.getCurrent().getLocale()));
        emailTF.setId("email-field");
        emailTF.setWidth("500px");
        passwordPF = new PasswordField(this.translationProvider.getTranslation("password",
                UI.getCurrent().getLocale()));
        passwordPF.setMinLength(6);
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
        submit.setId("submit");
        submit.setWidth("200px");

        Anchor regLink = new Anchor("main/auth", this.translationProvider.getTranslation("login",
                UI.getCurrent().getLocale()));

        Div container = new Div();
        container.addClassNames("registration-container");

        addClassNames("registration-view");
        add(container);
        container.add(avatarName, userNameTF, emailTF, passwordPF, repeatPasswordPF, submit, regLink);
    }


    private void registrationButtonClicked(ClickEvent<Button> buttonClickEvent) {


        if (emailTF.isInvalid()) {
            emailTF.setErrorMessage(this.translationProvider.getTranslation("invalidMailFormat",
                    UI.getCurrent().getLocale()));
            return;
        }

        if (passwordPF.isInvalid()) {
            passwordPF.setErrorMessage(this.translationProvider.getTranslation("shortPassword",
                    UI.getCurrent().getLocale()));
            return;
        }

        if (!Objects.equals(passwordPF.getValue(), repeatPasswordPF.getValue())) {
            repeatPasswordPF.setInvalid(true);
            repeatPasswordPF.setErrorMessage(this.translationProvider.getTranslation("passwordsDoNotMatch",
                    UI.getCurrent().getLocale()));
            return;
        }

        try {
            usersService.registration(new RegistrationViewModel(
                    userNameTF.getValue(),
                    emailTF.getValue(),
                    passwordPF.getValue()
            ));

            this.getUI().ifPresent(ui -> ui.navigate("/main/auth"));

        } catch (ResponseException e) {
            e.printStackTrace();
            NotificationComponent notification = new NotificationComponent(e.error, e.message, NotificationType.Error);
            this.add(notification);
        }
    }

    public static void logout() {
        // close session to clear all registered routes.
        // also available as sessionRegistry.clear()
        VaadinSession.getCurrent().close();
        UI.getCurrent().getPage().reload();
    }
}


