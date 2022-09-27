package com.example.application.views;

import com.example.application.backEnd.viewModel.UploadBookModel;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "upload", layout = MainLayout.class)
@AnonymousAllowed
public class BookUploadForm extends VerticalLayout {

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
        this.setWidth(String.valueOf(false));

        addClassNames("upload-view");
        add(container);

        container.addFormItem(bookFileUpload, "Файл книги");
        container.addFormItem(bookThumbnailUpload, "Файл обложки");
        container.addFormItem(genresSelect, "Жанр");
        container.addFormItem(titleTF, "Название");
        container.addFormItem(descriptionTF, "Описание ");
        container.addFormItem(authorTF, "Автор");
    }
}
