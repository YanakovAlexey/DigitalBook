//package com.example.application.views;
//
//import com.vaadin.flow.component.ClickEvent;
//import com.vaadin.flow.component.UI;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.textfield.PasswordField;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.router.Route;
//import com.vaadin.flow.router.RouteConfiguration;
//import com.vaadin.flow.server.VaadinRequest;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Route("")
//public class LoginView extends VerticalLayout {
//    private TextField login;
//    private PasswordField password;
//
//
//    public LoginView(){
//
//        login = new TextField("Login");
//        login.setId("login-field");
//        password = new PasswordField("Password");
//        password.setId("password-field");
//
//
//        Button submit = new Button("Submit", this::handeLogin);
//        submit.setId("submit");
//
//
//    }
//
//
//
//    private void handeLogin(ClickEvent<Button> buttonClickEvent) {
//
//
//
//        // Change session id a security measure
//        ((HttpServletRequest) VaadinRequest.getCurrent()).changeSessionId();
//
//        RouteConfiguration session = RouteConfiguration.forSessionScope();
//
//        // Set route should override global route, but throw if session contains same route.
//        if ("admin".equals(login.getValue())) {
//            session.setAnnotatedRoute(AdminView.class);
//        } else if ("user".equals(login.getValue())) {
//            session.setAnnotatedRoute(UserView.class);
//        }
//
//        // Add the version view to the route for path "version" with the MainLayout as its parent.
//        // Note that the parent routes shouldn't be as a list as we can collect parents using
//        // RouterUtil.getParentLayoutsForNonRouteTarget(MainLayout.class), though this
//        // depends on how dynamic do we want to support. We should anyway be able to request
//        // registry for the parts that we need for navigation.
//        session.setParentAnnotatedRoute("version", VersionView.class);
////
////        // Add a view using manually populated parent chain
//        session.setRoute("time", TimeView.class, LooseCenterLayout.class,
//                MainLayout.class);
//
//        // Reload to target url that was navigated to as it may now be registered.
//        UI.getCurrent().getPage().reload();
//    }
//}
