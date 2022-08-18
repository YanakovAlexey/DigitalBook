package com.example.application.backEnd.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Table(name = "user_book")
@Entity
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;


    @Column(name = "id_user")
    Integer idUser;

    @Column(name = "id_book")
    Integer idBook;

    @Column(name = "price_type")
    Integer priceType;

    @Column(name = "date_pay")
    String datePay;

    @Column(name = "cost")
    Double cost;

    @Column(name = "stop_subscription")
    String stopSubscription;

}
