package com.example.application.backEnd.builder.impl;

import com.example.application.backEnd.builder.BookBuilder;
import com.example.application.backEnd.domain.Basket;
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
                .file(book.getFile())
                .numClass(book.getNumClass())
                .idUsers(book.getUserId())
                .idDiscipline(book.getIdDiscipline())
                .extension(book.getExtension())
                .bookImg(book.getBookImg())
                .priceSubscription(book.getPriceSubscription())
                .type(book.getType())
                .pages(book.getPages())
                .dataInfo(book.getDataInfo())
                .build();
    }

    @Override
    public BookViewModel build(Book request) {
        BookViewModel book = new BookViewModel();
        book.setId(request.getId());
        book.setTitle(request.getTitle());
        book.setDescription(request.getDescription());
        book.setAuthor(request.getAuthor());
        book.setLink(request.getLink());
        book.setFile(request.getFile());
        book.setPricePay(request.getPricePay());
        book.setNumClass(request.getNumClass());
        book.setIdUsers(request.getUserId());
        book.setIdDiscipline(request.getIdDiscipline());
        book.setExtension(request.getExtension());
        book.setBookImg(request.getBookImg());
        book.setPriceSubscription(request.getPriceSubscription());
        book.setType(request.getType());
        book.setPages(request.getPages());
        book.setDataInfo(request.getDataInfo());
        return book;
    }

    @Override
    public void update(Book book, BookViewModel request){
        book.setTitle(request.getTitle());
        book.setDescription(request.getDescription());
        book.setAuthor(request.getAuthor());
        book.setLink(request.getLink());
        book.setFile(request.getFile());
        book.setPricePay(request.getPricePay());
        book.setNumClass(request.getNumClass());
        book.setIdDiscipline(request.getIdDiscipline());
        book.setExtension(request.getExtension());
        book.setBookImg(request.getBookImg());
        book.setPriceSubscription(request.getPriceSubscription());
        book.setType(request.getType());
        book.setPages(request.getPages());
        book.setDataInfo(request.getDataInfo());
    }
}
