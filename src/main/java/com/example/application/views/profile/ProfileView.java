package com.example.application.views.profile;

import com.example.application.backEnd.service.UsersService;
import com.example.application.views.ContentView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "profile", layout = ContentView.class)
@AnonymousAllowed
public class ProfileView extends VerticalLayout {
    private TextField userNameTF;
    private TextField surNameTF;
    private TextField emailTF;
    private PasswordField passwordPF;

    private final UsersService usersService;

    @Autowired
    public ProfileView(UsersService usersService) {
        this.usersService = usersService;
        profileForm();
    }

    private void profileForm() {
        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form i18nForm = i18n.getForm();
        System.out.println("Current locale is = " + UI.getCurrent().getLocale());
        i18nForm.setTitle("profile");
        Avatar avatarName = new Avatar();
        i18n.setForm(i18nForm);
        LoginForm loginForm = new LoginForm();
        loginForm.setI18n(i18n);

        userNameTF = new TextField("Имя");
        userNameTF.setId("userName-field");
        userNameTF.setWidth("500px");
        surNameTF = new TextField("Фамилия");
        surNameTF.setId("surName-field");
        surNameTF.setWidth("500px");
        emailTF = new TextField("Электроная почта");
        emailTF.setId("email-field");
        emailTF.setWidth("500px");
        passwordPF = new PasswordField("Пароль");
        passwordPF.setId("password-field");
        passwordPF.setWidth("500px");


        Div container = new Div();
        container.addClassNames("profile-container");

        addClassNames("profile-view");
        add(container);
        this.setWidth(String.valueOf(false));
        container.add(avatarName, userNameTF, surNameTF, emailTF, passwordPF);
    }
}
