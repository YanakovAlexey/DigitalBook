package com.example.application.views.footer;

import com.vaadin.flow.component.html.Footer;

public class footerView {
    private Footer createFooter() {
        Footer layout = new Footer();
        layout.addClassNames("app-nav-footer");

        return layout;
    }
}
