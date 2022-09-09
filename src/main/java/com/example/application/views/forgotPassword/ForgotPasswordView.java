package com.example.application.views.forgotPassword;


import com.example.application.views.MainLayout;
import com.example.application.views.authorization.AuthorizationView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinSession;

import javax.servlet.http.HttpServletRequest;


@Tag("div")
@Route("forgot-password")
public class ForgotPasswordView extends Div {
    public TextField email;


    public ForgotPasswordView(){

        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle(" forgot-password");
        i18n.setForm(i18nForm);
        LoginForm loginForm = new LoginForm();
        loginForm.setI18n(i18n);


        email.setPlaceholder("email");
        email.setId("email-field");

        Button submit = new Button("Отправить", this::handeLogin);
        submit.setId("submit");
        submit.setWidth("200px");


        Div container = new Div();
        container.addClassNames("forgot-password-container");

        addClassNames("RecoverPassword-view");
        add(container);
        container.add(email);


    }

    private void handeLogin(ClickEvent<Button> buttonClickEvent) {

        // Change session id a security measure
        ((HttpServletRequest) VaadinRequest.getCurrent()).changeSessionId();

        RouteConfiguration session = RouteConfiguration.forSessionScope();

        // Set route should override global route, but throw if session contains same route.

          if ("user".equals(email.getValue())) {
            session.setAnnotatedRoute(AuthorizationView.class);
        }

        // Add the version view to the route for path "version" with the MainLayout as its parent.
        // Note that the parent routes shouldn't be as a list as we can collect parents using
        // RouterUtil.getParentLayoutsForNonRouteTarget(MainLayout.class), though this
        // depends on how dynamic do we want to support. We should anyway be able to request
        // registry for the parts that we need for navigation.
        session.setParentAnnotatedRoute("version", MainLayout.class);
//
//        // Add a view using manually populated parent chain
        session.setRoute("time", MainLayout.class, MainLayout.class,
                MainLayout.class);

        // Reload to target url that was navigated to as it may now be registered.
        UI.getCurrent().getPage().reload();
    }
    public static void logout() {
        // close session to clear all registered routes.
        // also available as sessionRegistry.clear()
        VaadinSession.getCurrent().close();
        UI.getCurrent().getPage().reload();
    }
}
