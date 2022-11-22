package com.example.application.views;

import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.service.DisciplineService;
import com.example.application.backEnd.service.PublisherService;
import com.example.application.backEnd.service.impl.security.AuthenticatedUser;
import com.example.application.backEnd.viewModel.DisciplineViewModel;
import com.example.application.backEnd.viewModel.UploadBookModel;
import com.example.application.translation.TranslationProvider;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Route(value = "upload", layout = ContentView.class)
@RolesAllowed("USER") //publisher
public class BookUploadForm extends Div {
    private final TranslationProvider translationProvider = new TranslationProvider();
    private final Binder<UploadBookModel> binder =
            new BeanValidationBinder<>(UploadBookModel.class);

    private Upload bookFileUpload;
    private Upload bookThumbnailUpload;
    private Select<DisciplineViewModel> genresSelect;
    private TextField titleTF, authorTF;
    private TextArea descriptionTF;
    private List<DisciplineViewModel> disciplineList;
    private DisciplineViewModel selectedGenre;
    private Button download;
    private MemoryBuffer fileBuffer = new MemoryBuffer();
    private MemoryBuffer imageBuffer = new MemoryBuffer();


    private final BookService bookService;
    private final DisciplineService disciplineService;
    private final PublisherService publisherService;

    private UploadBookModel bookModel;
    private final AuthenticatedUser authenticatedUser;


    public BookUploadForm(BookService bookService, DisciplineService disciplineService, PublisherService publisherService, AuthenticatedUser authenticatedUser) {
        this.bookService = bookService;
        this.disciplineService = disciplineService;
        this.publisherService = publisherService;
        this.authenticatedUser = authenticatedUser;
        setupForm();
        bookModel = new UploadBookModel();
    }

    private void setupForm() {
        var listGenre = disciplineService.getAll();
        if (bookFileUpload == null) {
            bookFileUpload = new Upload(fileBuffer);
        }
        if (bookThumbnailUpload == null) {
            bookThumbnailUpload = new Upload(imageBuffer);
        }
        if (genresSelect == null) {
            genresSelect = new Select<>();
            genresSelect.setItems(listGenre);
            genresSelect.setTextRenderer(
                    (ItemLabelGenerator<DisciplineViewModel>) DisciplineViewModel::getTitle
            );
            genresSelect.addValueChangeListener((HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<Select<DisciplineViewModel>, DisciplineViewModel>>) event -> {
                selectedGenre = event.getValue();
                System.out.println("New Genre = " + event.getValue());
                System.out.println("Old Genre = " + event.getOldValue());
            });
        }
        if (titleTF == null) {
            titleTF = new TextField();
        }
        if (descriptionTF == null) {
            descriptionTF = new TextArea();
        }
        if (authorTF == null) {
            authorTF = new TextField();
        }
        if (download == null) {
            download = new Button(this.translationProvider.getTranslation("download",
                    UI.getCurrent().getLocale()));
            download.addClickListener(event -> uploadButtonClicked(event));
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

    private void uploadButtonClicked(ClickEvent<Button> buttonClickEvent) {
        authenticatedUser.get().ifPresent(user -> {
            Book book = new Book();
            book.setTitle(titleTF.getValue());
            book.setDescription(descriptionTF.getValue());
            book.setAuthor(authorTF.getValue());
            book.setIdDiscipline(selectedGenre.getId());
            book.setUsers(user);
            book.setType(1);


            bookService.create(book, fileBuffer, imageBuffer);
        });
    }

}
