package com.example.application.backEnd.viewModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasketViewModel {
    Long id;
    Long id_user;
    Long id_book;
    Integer priceType;
    Integer price;
    Integer period;


}
