package com.example.application.views.forgotPassword;


import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.viewModel.account.ForgotPasswordViewModel;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;


@Tag("div")
@Route("forgot-password")
public class ForgotPasswordView extends Div {

    public TextField email;
    private final UsersService usersService;

    @Autowired
    public ForgotPasswordView(UsersService usersService) {
        this.usersService = usersService;

        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle("forgot-password");
        i18n.setForm(i18nForm);
        LoginForm loginForm = new LoginForm();
        loginForm.setI18n(i18n);

        email.setPlaceholder("email");
        email.setId("email-field");
        email.setWidth("500px");

        Button submit = new Button("Отправить", this::ForgotPasswordButtonClicked);
        submit.setId("submit");
        submit.setWidth("200px");

        Div container = new Div();
        container.addClassNames("forgot-password-container");

        addClassNames("RecoverPassword-view");
        add(container);
        container.add(email);
    }

    private void ForgotPasswordButtonClicked(ClickEvent<Button> buttonClickEvent) {
        usersService.updatePassword(new ForgotPasswordViewModel((
                email.getValue()
        )));
    }

    public static void logout() {
        // close session to clear all registered routes.
        // also available as sessionRegistry.clear()
        VaadinSession.getCurrent().close();
        UI.getCurrent().getPage().reload();
    }
}
