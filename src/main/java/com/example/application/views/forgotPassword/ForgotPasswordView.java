package com.example.application.views.forgotPassword;

import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.service.impl.MailSenderService;
import com.example.application.backEnd.service.impl.security.AuthenticatedUser;
import com.example.application.models.EnumType;
import com.example.application.translation.TranslationProvider;
import com.example.application.views.ContentView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "forgot-password", layout = ContentView.class)
@AnonymousAllowed
public class ForgotPasswordView extends Div {

    private TextField email;
    private Button send;
    private final UsersService usersService;
    AuthenticatedUser authenticatedUser;

    private final TranslationProvider translationProvider = new TranslationProvider();


    @Autowired
    public ForgotPasswordView(UsersService usersService, MailSenderService mailSenderService,
                              AuthenticatedUser authenticatedUser) {
        this.usersService = usersService;
        this.authenticatedUser = authenticatedUser;
        forgotPassword();
    }

    public void forgotPassword() {

        email = new TextField(this.translationProvider.getTranslation("enterYourEmail",
                UI.getCurrent().getLocale()));
        email.setPlaceholder(this.translationProvider.getTranslation("email",
                UI.getCurrent().getLocale()));
        email.setWidth("230px");
        send = new Button(this.translationProvider.getTranslation("send",
                UI.getCurrent().getLocale()));
        send.setWidth("170px");
        send.addClickListener((ComponentEventListener<ClickEvent<Button>>) event -> {
            usersService.emailVerification(email.getValue(), EnumType.EDIT);
            send.getUI().ifPresent(ui -> ui.navigate("/success"));
        });


        Div container = new Div();
        container.addClassNames("forgot-password-container");

        addClassNames("forgot-password-view");
        add(container);
        this.setWidth(String.valueOf(false));
        container.add(email, send);
    }

    public static void logout() {
        // close session to clear all registered routes.
        // also available as sessionRegistry.clear()
        VaadinSession.getCurrent().close();
        UI.getCurrent().getPage().reload();
    }
}
