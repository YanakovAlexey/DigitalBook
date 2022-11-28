package com.example.application.views.bookReader;

import com.example.application.Test;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.shared.Registration;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PageReader extends HorizontalLayout implements TextSettingsDelegate {
    private final Paragraph leftPage;
    private final Paragraph rightPage;

    private Registration windowResizeRegistration;

    private int currentLeftPage = 0;

    public PageReader() {
        this.addClassNames("page-container");

        this.leftPage = new Paragraph();
        this.leftPage.setId("LEFT_PAGE");
        this.leftPage.addClassNames("page-text");
        this.leftPage.setText(Test.TEST_TEXT);
        this.leftPage.setEnabled(false);

        this.rightPage = new Paragraph();
        this.rightPage.setId("RIGHT_PAGE");
        this.rightPage.addClassNames("page-text");
        this.rightPage.setText(Test.TEST_TEXT);
        this.rightPage.setEnabled(false);

        leftPage.addClassNames("style-page");
        rightPage.addClassNames("style-page");
        addClassNames("page-view");

        this.add(leftPage, rightPage);
    }

    private int countCharsInRect(Rectangle2D.Double rect) {
        Font font = new Font("Arial", Font.PLAIN, 7);
        var charSize = font.getMaxCharBounds(
                new FontRenderContext(null, false, false));
        var square = rect.width * rect.height;
        var charSquare = charSize.getWidth() * charSize.getHeight();
        int count = (int) (square / charSquare);
        return count;
    }


    @Override
    protected void onAttach(AttachEvent attachEvent) {
        if (windowResizeRegistration == null) {
            var page = attachEvent.getUI().getPage();
            windowResizeRegistration = page.addBrowserWindowResizeListener((event) -> {
                if (event.getWidth() > 0 && event.getHeight() > 0) {
                    this.getElement().executeJs(
                            "$0.$server.sizedLeftPage(" +
                                    "document.getElementById(\"LEFT_PAGE\").clientWidth, " +
                                    "document.getElementById(\"LEFT_PAGE\").clientHeight" +
                                    ");",
                            getElement());

                    this.getElement().executeJs(
                            "$0.$server.sizedRightPage(" +
                                    "document.getElementById(\"RIGHT_PAGE\").clientWidth, " +
                                    "document.getElementById(\"RIGHT_PAGE\").clientHeight" +
                                    ");",
                            getElement());
                }
            });
            update();
        }
    }

    @ClientCallable
    public void sizedLeftPage(int width, int height) {
        var countChars = countCharsInRect(new Rectangle2D.Double(0, 0, width, height));
        this.leftPage.setText(getPageText(this.currentLeftPage, countChars));
    }

    @ClientCallable
    public void sizedRightPage(int width, int height) {
        var countChars = countCharsInRect(new Rectangle2D.Double(0, 0, width, height));
        this.rightPage.setText(getPageText(this.currentLeftPage + 1, countChars));
    }

    private String getPageText(int pageNumber, int countCharsOnPage) {
        StringBuilder textBuilder = new StringBuilder();
        int skipCharsCount = pageNumber * countCharsOnPage;
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\zherd\\Documents\\test.txt"))) {
            String line;
            while ((line = br.readLine()) != null && textBuilder.length() <= countCharsOnPage) {
                if (skipCharsCount > 0) {
                    skipCharsCount -= line.length();
                    continue;
                }
                textBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textBuilder.toString();
    }

    public void changePage(int currentPage, int oldPage) {
        System.out.println("From " + oldPage + " to " + currentPage + " page");
        if (currentPage >= 0) {
            this.currentLeftPage = currentPage;
            update();
        }
    }

    private void update() {
        getUI().ifPresent((ui) -> {
            ui.getPage().executeJs("window.dispatchEvent(new Event('resize'));");
        });
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
            case LIGHT, BEIGE -> {
                this.rightPage.getStyle().set("color", "black");
                this.leftPage.getStyle().set("color", "black");
            }
            case GREY -> {
                this.rightPage.getStyle().set("color", "brown");
                this.leftPage.getStyle().set("color", "brown");
            }
            case NIGHT -> {
                this.rightPage.getStyle().set("color", "white");
                this.leftPage.getStyle().set("color", "white");
            }
        }
    }
}
