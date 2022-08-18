package com.example.application.backEnd.builder.impl;

import com.example.application.backEnd.builder.UserBookBuilder;
import com.example.application.backEnd.domain.UserBook;
import com.example.application.backEnd.viewModel.UserBookViewModel;

public class UserBookBuilderImpl implements UserBookBuilder {
    @Override
    public UserBookViewModel createUserBook(UserBook userBook) {
        return UserBookViewModel.builder()
                .id(userBook.getId())
                .idUser(userBook.getIdUser())
                .idBook(userBook.getIdBook())
                .priceType(userBook.getPriceType())
                .datePay(userBook.getDatePay())
                .cost(userBook.getCost())
                .stopSubscription(userBook.getStopSubscription())
                .build();
    }

    public void updateUserBook(UserBook userBook, UserBookViewModel request){
        userBook.setIdUser(request.getIdUser());
        userBook.setIdBook(request.getIdBook());
        userBook.setPriceType(request.getPriceType());
        userBook.setDatePay(request.getDatePay());
        userBook.setCost(request.getCost());
        userBook.setStopSubscription(request.getStopSubscription());
    }
}
