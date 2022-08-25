package com.example.application.views.header;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

public class ButtonHeader {
    @Route("dialog-basic")
    public class UserDialogBasic extends Div {

        public UserDialogBasic(Icon icon) {
            Dialog dialog = new Dialog();

            dialog.setHeaderTitle("Authorization");
            VerticalLayout dialogLayout = createDialogLayout();
            dialog.add(dialogLayout);

            Button saveButton = createSaveButton(dialog);
            Button cancelButton = new Button("Cancel", e -> dialog.close());
            dialog.getFooter().add(cancelButton);
            dialog.getFooter().add(saveButton);

            Button button = new Button(icon, e -> dialog.open());

            add(dialog, button);


            getStyle().set("position", "fixed").set("top", "0").set("right", "0")
                    .set("bottom", "0").set("left", "900px").set("display", "flex")
                    .set("align-items", "center").set("justify-content", "center");
        }

        private static VerticalLayout createDialogLayout() {

            TextField firstNameField = new TextField("First name");
            TextField lastNameField = new TextField("Last name");

            VerticalLayout dialogLayout = new VerticalLayout(firstNameField,
                    lastNameField);
            dialogLayout.setPadding(false);
            dialogLayout.setSpacing(false);
            dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
            dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

            return dialogLayout;
        }

        private static Button createSaveButton(Dialog dialog) {
            Button saveButton = new Button("Add", e -> dialog.close());
            saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            return saveButton;
        }
    }

    @Route("dialog-basic")
    public class CartDialogBasic extends Div {

        public CartDialogBasic(Icon icon) {
            Dialog dialog = new Dialog();

            dialog.setHeaderTitle("Authorization");
            VerticalLayout dialogLayout = createDialogLayout();
            dialog.add(dialogLayout);

            Button saveButton = createSaveButton(dialog);
            Button cancelButton = new Button("Cancel", e -> dialog.close());
            dialog.getFooter().add(cancelButton);
            dialog.getFooter().add(saveButton);

            Button button = new Button(icon, e -> dialog.open());

            add(dialog, button);


            getStyle().set("position", "fixed").set("top", "0").set("right", "0")
                    .set("bottom", "0").set("left", "750px").set("display", "flex")
                    .set("align-items", "center").set("justify-content", "center");
        }

        private static VerticalLayout createDialogLayout() {

            TextField firstNameField = new TextField("First name");
            TextField lastNameField = new TextField("Last name");

            VerticalLayout dialogLayout = new VerticalLayout(firstNameField,
                    lastNameField);
            dialogLayout.setPadding(false);
            dialogLayout.setSpacing(false);
            dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
            dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

            return dialogLayout;
        }

        private static Button createSaveButton(Dialog dialog) {
            Button saveButton = new Button("Add", e -> dialog.close());
            saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            return saveButton;
        }
    }

    @Route("dialog-basic")
    public class BookDialogBasic extends Div {

        public BookDialogBasic(Icon icon) {
            Dialog dialog = new Dialog();

            dialog.setHeaderTitle("Authorization");
            VerticalLayout dialogLayout = createDialogLayout();
            dialog.add(dialogLayout);

            Button saveButton = createSaveButton(dialog);
            Button cancelButton = new Button("Cancel", e -> dialog.close());
            dialog.getFooter().add(cancelButton);
            dialog.getFooter().add(saveButton);

            Button button = new Button(icon, e -> dialog.open());

            add(dialog, button);


            getStyle().set("position", "fixed").set("top", "0").set("right", "0")
                    .set("bottom", "0").set("left", "650px").set("display", "flex")
                    .set("align-items", "center").set("justify-content", "center");
        }

        private static VerticalLayout createDialogLayout() {

            TextField firstNameField = new TextField("First name");
            TextField lastNameField = new TextField("Last name");

            VerticalLayout dialogLayout = new VerticalLayout(firstNameField,
                    lastNameField);
            dialogLayout.setPadding(false);
            dialogLayout.setSpacing(false);
            dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
            dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

            return dialogLayout;
        }

        private static Button createSaveButton(Dialog dialog) {
            Button saveButton = new Button("Add", e -> dialog.close());
            saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            return saveButton;
        }
    }
}
