package com.example.application.views.authorization;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.Route;

@Route("auth")
public class AuthorizationView extends Div {

    public AuthorizationView() {
        LoginForm loginForm = new LoginForm();

        Anchor regLink = new Anchor("reg", "Registration");

        add(loginForm, regLink);


    }
}
