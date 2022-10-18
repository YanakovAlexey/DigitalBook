package com.example.application.views.Basket;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class BasketPosition {
    Long idBook;
    Integer price;
    Integer numberOfBooks;
    Integer status;
    Double discount;


}
