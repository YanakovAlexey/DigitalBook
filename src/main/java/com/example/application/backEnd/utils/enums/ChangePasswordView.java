package com.example.application.backEnd.utils.enums;

import com.example.application.backEnd.service.ResponseException;
import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.service.impl.security.AuthenticatedUser;
import com.example.application.backEnd.viewModel.account.CodeConfirmationViewModel;
import com.example.application.models.ChangePasswordType;
import com.example.application.models.NotificationType;
import com.example.application.translation.TranslationProvider;
import com.example.application.ui.NotificationComponent;
import com.example.application.views.ContentView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.util.Objects;

@Route(value = "change-password", layout = ContentView.class)
@RolesAllowed("USER")
public class ChangePasswordView extends Div {

    private PasswordField oldPasswordPF;
    private PasswordField newPasswordPF;
    private PasswordField repeatPasswordPF;
    private Button saveButton;

    UsersService usersService;
    private final AuthenticatedUser authenticatedUser;
    private final CodeConfirmationViewModel codeConfirmationViewModel;
    private final TranslationProvider translationProvider = new TranslationProvider();

    public ChangePasswordView(UsersService usersService, AuthenticatedUser authenticatedUser,
                              CodeConfirmationViewModel codeConfirmationViewModel) {
        this.usersService = usersService;
        this.authenticatedUser = authenticatedUser;
        this.codeConfirmationViewModel = codeConfirmationViewModel;
        changePassword();
    }

    public void changePassword() {

        oldPasswordPF = new PasswordField(this.translationProvider.getTranslation("oldPassword",
                UI.getCurrent().getLocale()));
        newPasswordPF = new PasswordField(this.translationProvider.getTranslation("newPassword",
                UI.getCurrent().getLocale()));
        repeatPasswordPF = new PasswordField(this.translationProvider.getTranslation("repeatNewPassword",
                UI.getCurrent().getLocale()));

        saveButton = new Button(this.translationProvider.getTranslation("savePassword",
                UI.getCurrent().getLocale()), this::examinationPassword);

        saveButton.addClickListener(event -> {

            try {
                usersService.changePassword(authenticatedUser.get().get(),
                        oldPasswordPF.getValue(),
                        newPasswordPF.getValue(),
                        repeatPasswordPF.getValue(),
                        ChangePasswordType.EDIT
                );
                saveButton.getUI().ifPresent(ui -> ui.navigate("/"));
            } catch (ResponseException e) {
                e.printStackTrace();
                NotificationComponent notification =
                        new NotificationComponent(e.error, e.message, NotificationType.ERROR);
                this.add(notification);
            }
        });

        Div container = new Div();
        container.addClassNames("change-password-container");

        Div line = new Div();
        line.addClassNames("horizontal-line");

        addClassNames("change-password-view");
        add(container);
        this.setWidth(String.valueOf(false));
        container.add(oldPasswordPF, newPasswordPF, repeatPasswordPF, line, saveButton);
    }

    private void examinationPassword(ClickEvent<Button> buttonClickEvent) {

        if (oldPasswordPF.isInvalid()) {
            oldPasswordPF.setInvalid(true);
            oldPasswordPF.setErrorMessage(this.translationProvider.getTranslation("wrongOldPassword",
                    UI.getCurrent().getLocale()));
            return;
        }

        if (newPasswordPF.isInvalid()) {
            newPasswordPF.setInvalid(true);
            newPasswordPF.setErrorMessage(this.translationProvider.getTranslation("shortPassword",
                    UI.getCurrent().getLocale()));
            return;
        }

        if (!Objects.equals(newPasswordPF.getValue(), repeatPasswordPF.getValue())) {
            repeatPasswordPF.setInvalid(true);
            repeatPasswordPF.setErrorMessage(this.translationProvider.getTranslation("passwordsDoNotMatch",
                    UI.getCurrent().getLocale()));
            return;
        }
    }
}
