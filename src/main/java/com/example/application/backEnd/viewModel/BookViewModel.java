package com.example.application.backEnd.viewModel;

import com.example.application.backEnd.domain.Users;
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
    String file;
    Long idUsers;
    Long idDiscipline;
    String extension;
    String bookImg;
    Integer priceSubscription;
    Integer type;
    Integer pages;
    Date dataInfo;
}
