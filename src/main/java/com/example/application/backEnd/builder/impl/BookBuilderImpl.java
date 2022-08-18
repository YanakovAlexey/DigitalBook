package com.example.application.backEnd.builder.impl;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.viewModel.BookViewModel;
import org.springframework.stereotype.Component;

@Component
public class BookBuilderImpl implements BookBuilder {

    @Override
    public BookViewModel createBook(Book book) {
        return BookViewModel.builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .author(book.getAuthor())
                .link(book.getLink())
                .pricePay(book.getPricePay())
                .numClass(book.getNumClass())
                .idUsers(book.getIdUsers())
                .idDiscipline(book.getIdDiscipline())
                .extension(book.getExtension())
                .bookImg(book.getBookImg())
                .priceSubscription(book.getPriceSubscription())
                .type(book.getType())
                .pages(book.getPages())
                .dataInfo(book.getDataInfo())
                .build();
    }
}
