package com.example.application;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "child", layout = MyContainer.class)
@AnonymousAllowed
public class MyChild extends HorizontalLayout {

    public MyChild() {
        add(new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/330px-Image_created_with_a_mobile_phone.png", "/"));
    }
}
