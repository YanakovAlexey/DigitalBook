package com.example.application.views.recoveryPassword;

import com.example.application.backEnd.service.ResponseException;
import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.service.impl.security.AuthenticatedUser;
import com.example.application.models.NotificationType;
import com.example.application.translation.TranslationProvider;
import com.example.application.ui.NotificationComponent;
import com.example.application.views.ContentView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "recovery-password", layout = ContentView.class)
@AnonymousAllowed
public class RecoveryPasswordView extends Div implements HasUrlParameter<String> {

    private PasswordField oldPasswordPF;
    private PasswordField newPasswordPF;
    private PasswordField repeatPasswordPF;
    private Button saveButton;

    private String verificationCode;
    private String email;

    UsersService usersService;
    private final AuthenticatedUser authenticatedUser;
    private final TranslationProvider translationProvider = new TranslationProvider();

    public RecoveryPasswordView(UsersService usersService, AuthenticatedUser authenticatedUser) {
        this.usersService = usersService;
        this.authenticatedUser = authenticatedUser;
        recoveryPassword();
    }

    public void recoveryPassword() {

        oldPasswordPF = new PasswordField(this.translationProvider.getTranslation("oldPassword",
                UI.getCurrent().getLocale()));
        newPasswordPF = new PasswordField(this.translationProvider.getTranslation("newPassword",
                UI.getCurrent().getLocale()));
        repeatPasswordPF = new PasswordField(this.translationProvider.getTranslation("repeatNewPassword",
                UI.getCurrent().getLocale()));

        saveButton = new Button(this.translationProvider.getTranslation("savePassword",
                UI.getCurrent().getLocale()));

        saveButton.addClickListener(event -> {

            try {
                if (newPasswordPF.getValue().length() >= 6 &&
                        newPasswordPF.getValue().equals(repeatPasswordPF.getValue()) &&
                        usersService.restorePassword(email, verificationCode, newPasswordPF.getValue())
                ) {
                    getUI().ifPresent(ui -> ui.navigate("/"));
                } else {
                    throw new ResponseException("Ошибка", "Длина пароля меньше 6 символов", 500);
                }
            } catch (ResponseException e) {
                e.printStackTrace();
                NotificationComponent notification =
                        new NotificationComponent(e.error, e.message, NotificationType.ERROR);
                this.add(notification);
            }
        });

        Div container = new Div();
        container.addClassNames("recovery-password-container");

        Div line = new Div();
        line.addClassNames("horizontal-line");

        addClassNames("recovery-password-view");
        add(container);
        this.setWidth(String.valueOf(false));
        container.add(newPasswordPF, repeatPasswordPF, line, saveButton);
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        QueryParameters queryParameters = event.getLocation().getQueryParameters();
        var parameters = queryParameters.getParameters();
        if (parameters != null) {
            this.verificationCode = parameters.get("code").get(0);
            this.email = parameters.get("email").get(0);
        }
    }
}
