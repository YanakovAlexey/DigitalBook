package com.example.application.views.Basket;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.reporitory.BasketRepository;
import com.example.application.backEnd.reporitory.BookRepository;
import com.example.application.backEnd.service.BasketPositionService;
import com.example.application.backEnd.service.BasketService;
import com.example.application.backEnd.service.BookService;
import com.example.application.backEnd.viewModel.BasketPositionViewModel;
import com.example.application.backEnd.viewModel.BasketViewModel;
import com.example.application.views.ContentView;
import com.example.application.views.items.BookItem;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "Basket", layout = ContentView.class)
@AnonymousAllowed
public class BasketView extends HorizontalLayout implements HasUrlParameter<Long> {
    Long idUser;
    Div div = new Div();
    Label title = new Label("КОРЗИНА");
    Button buyAllButton = new Button("КУПИТЬ ВСЕ");
    private final BasketService basketService;
    private final BasketPositionService basketPositionService;
    private final BookBuilder bookBuilder;
    private BasketViewModel basketPositionGetBook = new BasketViewModel();
    private final BookService bookService;
    private Button buyButton;
    private Button deleteButton;

    public BasketView(BasketService basketService,
                      BasketPositionService basketPositionService,
                      BasketRepository basketRepository,
                      BookRepository bookRepository,
                      BasketRepository basketRepository1,
                      BookBuilder bookBuilder,
                      BookService bookService) {

        this.basketService = basketService;
        this.basketPositionService = basketPositionService;
        this.bookBuilder = bookBuilder;
        this.bookService = bookService;
        this.title.addClassNames("basket-title");
        this.buyAllButton.addClassNames("basket-button-buy-all");
        this.buyAllButton.addClassNames("basket-content-view");

    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        var verticalLayout = new VerticalLayout();
        this.idUser = parameter;
        Long getIdBasket = null;

        var listBasket = basketService.getAll();
        var layout = new FlexLayout();
        var listBasketPositionList = basketPositionService.getAll();
        var bookList = bookService.getAll();


        for (BasketViewModel basketViewModel : listBasket) {
            if (basketViewModel.getId_user().equals(idUser)) {
                getIdBasket = basketViewModel.getId();
            }

            for (BasketPositionViewModel basketPositionViewModel : listBasketPositionList) {
                if (basketPositionViewModel.getIdBasket().equals(getIdBasket)) {
                    for (Book book : bookList) {
                        if (book.getId().equals(basketPositionViewModel.getIdBook())) {

                            this.buyButton = new Button("Купить");

                            this.deleteButton = new Button("Удалить");

                            this.buyButton.addClassNames("basket-content-view");

                            verticalLayout.add(buyButton, deleteButton);

                            layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
                            layout.add(new BookItem(bookBuilder.createBook(book)), verticalLayout);

                        }
                    }
                }
            }
        }

        layout.addClassName("basket-book-item");

        div.add(title, buyAllButton, layout);

        add(div);

    }
}
