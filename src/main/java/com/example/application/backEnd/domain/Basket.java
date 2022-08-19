package com.example.application.backEnd.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Table(name = "basket")
@Entity
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "id_user")
    Integer id_user;

    @Column(name = "id_book")
    Long id_book;

    @Column(name = "price_type")
    Integer priceType;

    @Column(name = "price")
    Integer price;

    @Column(name = "period")
    Integer period;

}
