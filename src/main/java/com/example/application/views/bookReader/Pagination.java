package com.example.application.views.bookReader;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyUpEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

interface PaginationDelegate {
    void toTableOfContents();

    void onPageWillChange(int page, int previousPage);
}

public class Pagination extends HorizontalLayout {
    private final Button toStart;
    private final Button previous;
    private final TextField pageNumber;
    private final Button next;
    private final Button tableOfContents;
    private final int maxPages;
    private int currentPage;

    private PaginationDelegate paginationDelegate;

    /**
     * создает экземпляр Pagination.
     *
     * @param maxPages - общее кол-во в книге
     */
    public Pagination(int maxPages) {
        this(maxPages, 1);
    }

    /**
     * создает экземпляр  Pagination.
     *
     * @param maxPages    - общее кол-во в книге.
     * @param currentPage - текущая страница в книге.
     */
    public Pagination(int maxPages, int currentPage) {
        this.maxPages = maxPages;
        this.currentPage = currentPage;

        this.toStart = new Button("К началу");
        toStart.addClassNames("to-start");
        this.previous = new Button(new Icon(VaadinIcon.ARROW_LEFT));
        if (currentPage == 1)
            this.previous.setEnabled(false);
        this.pageNumber = new TextField();
        pageNumber.addClassNames("page-number");
        this.pageNumber.setValue(String.valueOf(currentPage));
        this.next = new Button(new Icon(VaadinIcon.ARROW_RIGHT));
        this.tableOfContents = new Button("содержание");
        tableOfContents.addClassNames("table-of-contents");

        toStart.addClickListener(buttonClickEvent -> {
            final int previousPageNumber = this.currentPage;
            this.currentPage = 1;
            this.previous.setEnabled(false);
            this.next.setEnabled(true);
            if (paginationDelegate != null) {
                paginationDelegate.onPageWillChange(this.currentPage, previousPageNumber);
            }
            this.pageNumber.setValue(String.valueOf(this.currentPage));
        });
        previous.addClickListener(this::previousButtonClicked);
        next.addClickListener(this::nextButtonClicked);
        tableOfContents.addClickListener(this::tableOfContentsButtonClicked);

        pageNumber.addKeyUpListener(this::enterPageEvent);

        addClassNames("pagination");

        add(toStart, previous, pageNumber, next, tableOfContents);
    }

    /**
     * задает значение paginationDelegate.
     *
     * @param paginationDelegate - сылка на обект реализующая интерфейс PaginationDelegate.
     */
    public void setDelegate(PaginationDelegate paginationDelegate) {
        this.paginationDelegate = paginationDelegate;
    }

    /**
     * возвращает страницу на одну назад.
     *
     * @param buttonClickEvent - реализует клик.
     */
    private void previousButtonClicked(ClickEvent<Button> buttonClickEvent) {
        final int previousPage = this.currentPage;
        final int currentPage = this.currentPage - 1;
        if (currentPage < this.maxPages) {
            next.setEnabled(true);
        }
        if (currentPage <= 1) {
            previous.setEnabled(false);
        }
        this.currentPage = currentPage;

        pageNumber.setValue(String.valueOf(currentPage));
        if (paginationDelegate != null) {
            paginationDelegate.onPageWillChange(currentPage, previousPage);
        }

    }

    /**
     * листает страницу на одну вперед
     *
     * @param buttonClickEvent - реализует клик
     */
    private void nextButtonClicked(ClickEvent<Button> buttonClickEvent) {
        final int previousPage = this.currentPage;
        final int currentPage = this.currentPage + 1;
        if (currentPage > 1) {
            previous.setEnabled(true);
        }
        if (currentPage >= maxPages) {
            next.setEnabled(false);
        }
        this.currentPage = currentPage;

        pageNumber.setValue(String.valueOf(currentPage));
        if (paginationDelegate != null) {
            paginationDelegate.onPageWillChange(currentPage, previousPage);
        }
    }

    private void enterPageEvent(KeyUpEvent keyUpEvent) {
        if (keyUpEvent.getKey().equals(Key.ENTER)) {
            final int page = Integer.parseInt(pageNumber.getValue());
            if (page > maxPages || page < 1)
                return;
            if (page == 1) {
                previous.setEnabled(false);
                next.setEnabled(true);
            } else if (page == maxPages) {
                previous.setEnabled(true);
                next.setEnabled(false);
            } else {
                previous.setEnabled(true);
                next.setEnabled(true);
            }
            currentPage = page;
        }
    }

    /**
     * показывает содержание книги.
     *
     * @param buttonClickEvent реализует клик.
     */
    private void tableOfContentsButtonClicked(ClickEvent<Button> buttonClickEvent) {
        if (paginationDelegate != null) {
            paginationDelegate.toTableOfContents();
        }
    }
}
