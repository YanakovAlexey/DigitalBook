package com.example.application.views.header;

import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

public class ButtonView {

    @Route("dialog-basic")
    public static class UserDialogBasic extends Div {

        public UserDialogBasic(Icon icon) {
            Dialog dialog = new Dialog();

            dialog.setHeaderTitle("Authorization");
            VerticalLayout dialogLayout = createDialogLayout();
            dialog.add(dialogLayout);

            com.vaadin.flow.component.button.Button saveButton = createSaveButton(dialog);
            com.vaadin.flow.component.button.Button cancelButton =
                    new com.vaadin.flow.component.button.Button("Cancel", e -> dialog.close());
            dialog.getFooter().add(cancelButton);
            dialog.getFooter().add(saveButton);

            com.vaadin.flow.component.button.Button button =
                    new com.vaadin.flow.component.button.Button(icon, e -> dialog.open());

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

        private static com.vaadin.flow.component.button.Button createSaveButton(Dialog dialog) {
            com.vaadin.flow.component.button.Button saveButton =
                    new com.vaadin.flow.component.button.Button("Add", e -> dialog.close());
            saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            return saveButton;
        }
    }

    @Route("dialog-basic")
    public static class CartDialogBasic extends Div {

        public CartDialogBasic(Icon icon) {
            Dialog dialog = new Dialog();

            dialog.setHeaderTitle("Authorization");
            VerticalLayout dialogLayout = createDialogLayout();
            dialog.add(dialogLayout);

            com.vaadin.flow.component.button.Button saveButton = createSaveButton(dialog);
            com.vaadin.flow.component.button.Button cancelButton =
                    new com.vaadin.flow.component.button.Button("Cancel", e -> dialog.close());
            dialog.getFooter().add(cancelButton);
            dialog.getFooter().add(saveButton);

            com.vaadin.flow.component.button.Button button =
                    new com.vaadin.flow.component.button.Button(icon, e -> dialog.open());

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

        private static com.vaadin.flow.component.button.Button createSaveButton(Dialog dialog) {
            com.vaadin.flow.component.button.Button saveButton =
                    new com.vaadin.flow.component.button.Button("Add", e -> dialog.close());
            saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            return saveButton;
        }
    }

    @Route("dialog-basic")
    public static class BookDialogBasic extends Div {

        public BookDialogBasic(Icon icon) {
            Dialog dialog = new Dialog();

            dialog.setHeaderTitle("Authorization");
            VerticalLayout dialogLayout = createDialogLayout();
            dialog.add(dialogLayout);

            com.vaadin.flow.component.button.Button saveButton = createSaveButton(dialog);
            com.vaadin.flow.component.button.Button cancelButton =
                    new com.vaadin.flow.component.button.Button("Cancel", e -> dialog.close());
            dialog.getFooter().add(cancelButton);
            dialog.getFooter().add(saveButton);

            com.vaadin.flow.component.button.Button button =
                    new com.vaadin.flow.component.button.Button(icon, e -> dialog.open());

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

        private static com.vaadin.flow.component.button.Button createSaveButton(Dialog dialog) {
            com.vaadin.flow.component.button.Button saveButton =
                    new com.vaadin.flow.component.button.Button("Add", e -> dialog.close());
            saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            return saveButton;
        }
    }


    @Route("login-basic")
    public class LoginBasic extends Div {

        public LoginBasic() {
            getStyle()
                    .set("background-color", "var(--lumo-contrast-5pct)")
                    .set("display", "flex")
                    .set("justify-content", "center")
                    .set("padding", "var(--lumo-space-l)");

            LoginForm loginForm = new LoginForm();
            add(loginForm);
            // Prevent the example from stealing focus when browsing the documentation
            loginForm.getElement().setAttribute("no-autofocus", "");
        }
    }
}
