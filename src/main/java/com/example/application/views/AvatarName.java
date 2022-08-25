package com.example.application.views;

import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.viewModel.UserViewModel;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("avatar-name")
public class AvatarName extends Div {


    UserViewModel person;

    public AvatarName() {
        String name = person.getName() + " " + person.getSurname();

        Avatar avatarName = new Avatar(name);

        add(avatarName);
    }

}
