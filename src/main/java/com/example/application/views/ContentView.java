package com.example.application.views;

import com.example.application.views.content.BookShapeContent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.router.RouterLayout;

@ParentLayout(MainLayout.class)
@RoutePrefix("main")
public class ContentView extends HorizontalLayout implements RouterLayout {


//    RegistrationView registrationView;

    public ContentView() {

        this.addClassNames("view-content");
//        this.bookShapeContent = bookShapeContent;

    }
}
