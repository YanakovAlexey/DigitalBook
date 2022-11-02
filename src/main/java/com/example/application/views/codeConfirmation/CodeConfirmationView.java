package com.example.application.views.codeConfirmation;

import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.service.impl.security.AuthenticatedUser;
import com.example.application.translation.TranslationProvider;
import com.example.application.views.ContentView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "success", layout = ContentView.class)
@AnonymousAllowed
public class CodeConfirmationView extends Div {

    private Label successfully;
    private Icon performed;

    UsersService usersService;
    private final AuthenticatedUser authenticatedUser;
    private final TranslationProvider translationProvider = new TranslationProvider();

    public CodeConfirmationView(UsersService usersService, AuthenticatedUser authenticatedUser) {
        this.usersService = usersService;
        this.authenticatedUser = authenticatedUser;
        codeConfirmation();
    }

    public void codeConfirmation() {

        performed = new Icon(VaadinIcon.CHECK);
        performed.setColor("#0cb829");
        successfully = new Label("Письмо на почту успешно отправлено");
        Div container = new Div();
        container.addClassNames("code-confirmation-container");

        performed.addClassNames("performed-icon");
        addClassNames("code-confirmation-view");

        Div line = new Div();
        line.addClassNames("horizontal-line");
        add(container);
        this.setWidth(String.valueOf(false));
        container.add(performed, line, successfully);
    }
}
