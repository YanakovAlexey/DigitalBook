package com.example.application.views.profile;

import com.example.application.backEnd.reporitory.UserRepository;
import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.service.impl.security.AuthenticatedUser;
import com.example.application.translation.TranslationProvider;
import com.example.application.views.ContentView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;

@Route(value = "profile", layout = ContentView.class)
@RolesAllowed("USER")
public class ProfileView extends Div {

    //    private Image avatarImage;
    private Icon avatarIcon;
    private TextField userNameTF;
    private TextField surNameTF;
    private TextField emailTF;
    private PasswordField passwordPF;
    private Button changePasswordButton;
    private Button exitButton;


    private final UsersService usersService;
    private final UserRepository userRepository;
    private final AuthenticatedUser authenticatedUser;
    private final TranslationProvider translationProvider = new TranslationProvider();

    @Autowired
    public ProfileView(UsersService usersService, AuthenticatedUser authenticatedUser, UserRepository userRepository) {

        this.authenticatedUser = authenticatedUser;
        addClassNames("profile-view");
        this.setWidth(String.valueOf(false));
        this.usersService = usersService;
        this.add(createForm());
        this.userRepository = userRepository;
    }

    private FormLayout createForm() {
        FormLayout formLayout = new FormLayout();
        formLayout.addClassNames("profile-container");

//        avatarImage = new Image(
//                "https://upload.wikimedia.org/wikipedia/commons/9/9a/Gull_portrait_ca_usa.jpg",
//                "/");
        avatarIcon = new Icon(VaadinIcon.USER);

        userNameTF = new TextField(this.translationProvider.getTranslation("username",
                UI.getCurrent().getLocale()));
        if (authenticatedUser.get().isPresent()) {
            userNameTF.setValue(authenticatedUser.get().get().getUsername());
        }
        userNameTF.setWidth("550px");

        surNameTF = new TextField(this.translationProvider.getTranslation("surname",
                UI.getCurrent().getLocale()));
        surNameTF.setId("surName-field");
        surNameTF.setWidth("550px");

        emailTF = new TextField(this.translationProvider.getTranslation("email",
                UI.getCurrent().getLocale()));
        if (authenticatedUser.get().isPresent()) {
            emailTF.setValue(authenticatedUser.get().get().getEmail());
        }
        emailTF.setId("email-field");
        emailTF.setWidth("550px");

        passwordPF = new PasswordField(this.translationProvider.getTranslation("password",
                UI.getCurrent().getLocale()));
        passwordPF.setId("password-field");
        passwordPF.setWidth("550px");

        changePasswordButton = new Button(this.translationProvider.getTranslation("changePassword",
                UI.getCurrent().getLocale()));
        changePasswordButton.addClickListener(event -> {
            changePasswordButton.getUI().ifPresent(ui -> ui.navigate("change-password"));
        });

        exitButton = new Button(this.translationProvider.getTranslation("exit",
                UI.getCurrent().getLocale()));
        exitButton.addClickListener(event -> {
            exitButton.getUI().ifPresent(ui -> ui.navigate("logout"));
            this.authenticatedUser.logout();
        });

        Div line = new Div();
        line.addClassNames("horizontal-line");


        exitButton.addClassNames("bottom-buttons");

        formLayout.add(
                avatarIcon,
//                avatarImage,
                userNameTF,
                surNameTF,
                emailTF,
                passwordPF,
                changePasswordButton,
                line,
                exitButton
        );

        return formLayout;
    }
}
