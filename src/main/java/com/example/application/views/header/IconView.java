package com.example.application.views.header;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;

public class IconView {
    public static class SearchIconDialogBasic extends Div {

        public SearchIconDialogBasic(Icon icon) {
            add(icon);

            getStyle().set("position", "fixed").set("top", "0").set("right", "0")
                    .set("bottom", "0").set("left", "180px").set("display", "flex")
                    .set("align-items", "center").set("justify-content", "center");

        }
    }
}
