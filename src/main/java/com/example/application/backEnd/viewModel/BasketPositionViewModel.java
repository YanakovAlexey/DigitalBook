package com.example.application.backEnd.viewModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasketPositionViewModel {
    Long id;
    Long idBasket;
    Long idBook;
    Integer price;
    Integer numberOfBooks;
}
