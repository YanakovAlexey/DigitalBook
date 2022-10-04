package com.example.application.views;

import com.example.application.backEnd.viewModel.UploadBookModel;
import com.example.application.translation.TranslationProvider;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "upload", layout = ContentView.class)
@AnonymousAllowed
public class BookUploadForm extends Div {

    private final Binder<UploadBookModel> binder =
            new BeanValidationBinder<>(UploadBookModel.class);

    private Upload bookFileUpload;
    private Upload bookThumbnailUpload;
    private Select<String> genresSelect;
    private TextField titleTF, descriptionTF, authorTF;
    private final TranslationProvider translationProvider = new TranslationProvider();

    private UploadBookModel bookModel;

    public BookUploadForm() {
        setupForm();
        bookModel = new UploadBookModel();
    }

    private void setupForm() {

        if (bookFileUpload == null)
            bookFileUpload = new Upload();
        if (bookThumbnailUpload == null)
            bookThumbnailUpload = new Upload();
        if (genresSelect == null)
            genresSelect = new Select<>("Альбом", "Биография",
                    "Документальная проза", "Учебник", "Словарь",
                    "Комикс/графический роман", "Кулинарный", "Детская литература",
                    "Юношеская литература", "Грузинская проза", "Переведенная проза",
                    "Грузинская поэзия", "Переведенные стихи", "Научно-популярный",
                    "Переведенные стихи", "Специальная литература");
        if (titleTF == null) {
            titleTF = new TextField();
        }
        if (descriptionTF == null)
            descriptionTF = new TextField();
        if (authorTF == null)
            authorTF = new TextField();


        bookFileUpload.setWidth("200px");
        bookThumbnailUpload.setWidth("200px");


        FormLayout container = new FormLayout();
        container.addClassNames("upload-container");


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
    }
}
