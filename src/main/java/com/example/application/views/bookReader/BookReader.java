package com.example.application.views.bookReader;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@Route(value = "reader")
@RolesAllowed("USER")
public class BookReader extends VerticalLayout implements PaginationDelegate, TextSettingsDelegate {
    private final Pagination pagination;
    private final TextSettings textSettings;
    private final PageReader pageReader;

    public BookReader() {
        this.pagination = new Pagination(10);
        this.pagination.setDelegate(this);
        this.textSettings = new TextSettings();
        this.textSettings.addDelegate(this);

        this.pageReader = new PageReader();
        this.setPadding(false);
        this.addClassNames("main-reader");
        this.textSettings.addDelegate(pageReader);

        add(textSettings, pageReader, pagination);
    }

    @Override
    public void toTableOfContents() {

    }

    @Override
    public void onPageWillChange(int page, int previousPage) {
        if (this.pageReader != null)
            this.pageReader.changePage(page, previousPage);
    }

    @Override
    public void onFontWillChange(String newFont, String oldFont) {

    }

    @Override
    public void onFontSizeWillChange(int size, int oldSize) {

    }

    @Override
    public void onThemeWillChange(TextSettings.TextSettingsState.ThemeColor newColor,
                                  TextSettings.TextSettingsState.ThemeColor oldColor) {
        switch (newColor) {
            case LIGHT -> {
                this.getStyle().set("background", "white");
            }
            case GREY -> {
                this.getStyle().set("background", "grey");
            }
            case BEIGE -> {
                this.getStyle().set("background", "beige");
            }
            case NIGHT -> {
                this.getStyle().set("background", "black");
            }
        }
    }
}
