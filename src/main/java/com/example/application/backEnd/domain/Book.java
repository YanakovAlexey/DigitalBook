package com.example.application.backEnd.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "description", length = 1000)
    String description;

    @Column(name = "author")
    String author;

    @Column(name = "link")
    String link;

    @Column(name = "price_pay")
    Integer pricePay;

    @Column(name = "num_class")
    Integer numClass;

    Long idDiscipline;

    @Column(name = "extension")
    String extension;

    @Column(name = "book_img")
    String bookImg;

    @Column(name = "price_subscription")
    Integer priceSubscription;

    @Column(name = "type")
    Integer type;

    @Column(name = "pages")
    Integer pages;

    @Column(name = "data_info")
    Date dataInfo;

    @Column(name = "file")
    String file;

    Long userId;
}
