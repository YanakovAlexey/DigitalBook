package com.example.application.views.forgotPassword;


import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.viewModel.account.ForgotPasswordViewModel;
import com.example.application.views.ContentView;
import com.vaadin.flow.component.ClickEvent;
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

    @Autowired
    public ForgotPasswordView(UsersService usersService) {
        this.usersService = usersService;
        forgotPassword();

    }

    public void forgotPassword() {

        email = new TextField("Введите Ваш email");
        email.setPlaceholder("email");
        email.setWidth("250px");
        send = new Button("Отправить");
        send.setWidth("170px");

        Div container = new Div();
        container.addClassNames("forgot-password-container");

        addClassNames("forgot-password-view");
        add(container);
        this.setWidth(String.valueOf(false));
        container.add(email, send);
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
