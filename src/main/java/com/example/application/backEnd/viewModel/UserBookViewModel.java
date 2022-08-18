package com.example.application.backEnd.viewModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBookViewModel {


    Long id;
    Integer idUser;
    Integer idBook;
    Integer priceType;
    String datePay;
    Double cost;
    String stopSubscription;

}
