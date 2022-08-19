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
    Integer id_user;
    Integer id_book;
    Integer priceType;
    Integer price;
    Integer period;


}
