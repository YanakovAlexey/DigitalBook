package com.example.application.backEnd.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Table(name = "BasketPosition")
@Entity
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BasketPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "idBasket")
    Long idBasket;

    @Column(name = "idBook")
    Long idBook;

    @Column(name = "price")
    Integer price;

    @Column(name = "numbersOfBooks")
    Integer numberOfBooks;

}
