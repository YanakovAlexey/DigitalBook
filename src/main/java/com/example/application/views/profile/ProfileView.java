package com.example.application.views.profile;

import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.service.impl.security.AuthenticatedUser;
import com.example.application.views.ContentView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "profile", layout = ContentView.class)
@AnonymousAllowed
public class ProfileView extends Div {
    private Image avatarImage;
    private TextField userNameTF;
    private TextField surNameTF;
    private TextField emailTF;
    private PasswordField passwordPF;
    private Button changePasswordButton;
    private Button saveButton;
    private Button exitButton;

    private final UsersService usersService;
    private final AuthenticatedUser authenticatedUser;

    @Autowired
    public ProfileView(UsersService usersService, AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        addClassNames("profile-view");
        this.setWidth(String.valueOf(false));
        this.usersService = usersService;
        this.add(createForm());
    }

    private FormLayout createForm() {
        FormLayout formLayout = new FormLayout();
        formLayout.addClassNames("profile-container");

        avatarImage = new Image(
                "https://upload.wikimedia.org/wikipedia/commons/9/9a/Gull_portrait_ca_usa.jpg",
                "/");

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

        changePasswordButton = new Button("Сменить пароль");

        saveButton = new Button("Сохранить изменения");
        exitButton = new Button("Выход");
        exitButton.addClickListener(event -> {
            exitButton.getUI().ifPresent(ui -> ui.navigate("logout"));
            this.authenticatedUser.logout();
        });

        Div line = new Div();
        line.addClassNames("horizontal-line");

        formLayout.add(
                avatarImage,
                userNameTF,
                surNameTF,
                emailTF,
                passwordPF,
                changePasswordButton,
                line,
                new HorizontalLayout(saveButton, exitButton));

        return formLayout;
    }
}
