package com.example.application.ui;

import com.example.application.models.NotificationType;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.textfield.TextField;

public class NotificationComponent extends Div {

    H1 heading = new H1();
    Text message = new Text("");
    NotificationType type = NotificationType.Error;

    public NotificationComponent(String title, String description, NotificationType type) {

        this.heading.setText(title);
        this.message.setText(description);
        this.type = type;

        this.addClassNames("notification-container");
        this.add(heading, message);
    }

}
