package DigitalBook.viewModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookViewModel {
    Long id;
    String title;
    String description;
    String author;
    String link;
    Integer pricePay;
    Integer numClass;
    Integer idUsers;
    Integer idDiscipline;
    String extension;
    String bookImg;
    Integer priceSubscription;
    Integer type;
    Integer pages;
    Date dataInfo;
}
