package com.example.application.views.header;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;

public class TextFileView {
    public static class SearchDialogBasic extends Div {

        public SearchDialogBasic() {
            TextField search = new TextField();

            add(search);

            getStyle().set("position", "fixed").set("top", "0").set("right", "0")
                    .set("bottom", "0").set("left", "30px").set("display", "flex")
                    .set("align-items", "center").set("justify-content", "center");

        }
    }
}
