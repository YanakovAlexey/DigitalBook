package com.example.application.views;

import com.example.application.backEnd.domain.Discipline;
import com.example.application.backEnd.viewModel.UploadBookModel;
import com.example.application.translation.TranslationProvider;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.List;

@Route(value = "upload", layout = ContentView.class)
@AnonymousAllowed
public class BookUploadForm extends Div {

    private final TranslationProvider translationProvider = new TranslationProvider();
    private final Binder<UploadBookModel> binder =
            new BeanValidationBinder<>(UploadBookModel.class);

    private Upload bookFileUpload;
    private Upload bookThumbnailUpload;
    private Select<String> genresSelect;
    private TextField titleTF, descriptionTF, authorTF;
    private List<Discipline> disciplineList;
    private Button download;
    private UploadBookModel bookModel;

    public BookUploadForm() {
        setupForm();
        bookModel = new UploadBookModel();
    }

    private void setupForm() {

        if (bookFileUpload == null) {
            bookFileUpload = new Upload();
        }
        if (bookThumbnailUpload == null) {
            bookThumbnailUpload = new Upload();
        }
        if (genresSelect == null) {
            genresSelect = new Select<>();
        }
        if (titleTF == null) {
            titleTF = new TextField();
        }
        if (descriptionTF == null) {
            descriptionTF = new TextField();
        }
        if (authorTF == null) {
            authorTF = new TextField();
        }
        if (download == null) {
            download = new Button(this.translationProvider.getTranslation("download",
                    UI.getCurrent().getLocale()));
        }

        bookFileUpload.setWidth("200px");
        bookThumbnailUpload.setWidth("200px");

        Div line = new Div();
        line.addClassNames("horizontal-line");

        FormLayout container = new FormLayout();
        container.addClassNames("upload-container");

        download.addClassNames("button-download");
        addClassNames("upload-view");
        add(container);

        container.addFormItem(bookFileUpload, this.translationProvider.getTranslation("bookFile",
                UI.getCurrent().getLocale()));
        container.addFormItem(bookThumbnailUpload, this.translationProvider.getTranslation("coverFile",
                UI.getCurrent().getLocale()));
        container.addFormItem(genresSelect, this.translationProvider.getTranslation("genre",
                UI.getCurrent().getLocale()));
        container.addFormItem(titleTF, this.translationProvider.getTranslation("name",
                UI.getCurrent().getLocale()));
        container.addFormItem(descriptionTF, this.translationProvider.getTranslation("description",
                UI.getCurrent().getLocale()));
        container.addFormItem(authorTF, this.translationProvider.getTranslation("author",
                UI.getCurrent().getLocale()));

        container.add(line);
        container.addFormItem(download, download);
    }
}
