package DigitalBook.viewModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

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
