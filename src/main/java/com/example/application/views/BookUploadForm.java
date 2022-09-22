package com.example.application.views;

import com.example.application.backEnd.viewModel.UploadBookModel;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "upload")
@AnonymousAllowed
public class BookUploadForm extends FormLayout {

    private final Binder<UploadBookModel> binder =
            new BeanValidationBinder<>(UploadBookModel.class);

    private Upload bookFileUpload;
    private Upload bookThumbnailUpload;
    private Select<String> genresSelect;
    private TextField titleTF, descriptionTF, authorTF;

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
            genresSelect = new Select<>("Item 1", "Item 11", "Item 2", "Item 3");
        if (titleTF == null) {
            titleTF = new TextField();
        }
        if (descriptionTF == null)
            descriptionTF = new TextField();
        if (authorTF == null)
            authorTF = new TextField();

        addFormItem(bookFileUpload, "Файл книги");
        addFormItem(bookThumbnailUpload, "Файл обложки");
        addFormItem(genresSelect, "Файл обложки");
        addFormItem(titleTF, "Файл обложки");
        addFormItem(descriptionTF, "Файл обложки");
        addFormItem(authorTF, "Файл обложки");
    }
}
