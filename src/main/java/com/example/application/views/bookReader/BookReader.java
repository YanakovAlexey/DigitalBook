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


    public BookReader(String file) {
        this.pagination = new Pagination(10);
        this.pagination.setDelegate(this);
        this.textSettings = new TextSettings();
        this.textSettings.addDelegate(this);

        this.pageReader = new PageReader(file);
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
    public void onFontWillChange(TextSettings.TextSettingsState.ThemeFont newFont,
                                 TextSettings.TextSettingsState.ThemeFont oldFont) {
        switch (newFont) {
            case GIGI -> {
                this.getStyle().set("font-family", "Gigi");
            }
            case ROBOTO -> {
                this.getStyle().set("font-family", "Roboto");
            }
            case VERDANA -> {
                this.getStyle().set("font-family", "Verdana");
            }
            case ARIAL -> {
                this.getStyle().set("font-family", "ARIAL");
            }
        }
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
