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

    @Column(name = "idUser")
    Long idUser;

    @Column(name = "idBook")
    Long id_book;

    @Column(name = "priceType")
    Integer priceType;

    @Column(name = "price")
    Integer price;

    @Column(name = "period")
    Integer period;

}
