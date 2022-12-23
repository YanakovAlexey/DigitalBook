package com.example.application.ui;

import com.example.application.models.NotificationType;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

public class NotificationComponent extends Div {

    H1 heading = new H1();
    Text message = new Text("");
    NotificationType type = NotificationType.ERROR;

    public NotificationComponent(String title, String description, NotificationType type) {

        this.heading.setText(title);
        this.message.setText(description);
        this.type = type;

        Button exit = new Button(new Icon(VaadinIcon.CLOSE));

        Div container = new Div();
        container.add(heading, exit, message);

        exit.addClassNames("exit-message");

        exit.addClickListener(event -> {
            container.setVisible(false);
        });

        container.addClassNames("notification-container");
        add(container);
        addClassNames("notification-view");


    }

}
