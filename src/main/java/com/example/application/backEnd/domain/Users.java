package com.example.application.backEnd.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Table(name = "users")
@Setter
@Getter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username")
    String username;

    @Column(name = "name")
    String name;

    @Column(name = "surname")
    String surname;

    @Column(name = "patronymic")
    String patronymic;

    @Column(name = "inn")
    Integer inn;

    @Column(name = "email")
    String email;

    @Column(name = "phone")
    String phone;

    @Column(name = "password")
    String password;

    @Column(name = "id_city")
    Integer cityId;

    @Column(name = "id_school")
    Integer schoolId;

    @Column(name = "num_class")
    Integer classNumber;

    @Column(name = "letter_class")
    String letterClass;

    @Column(name = "id_rol")
    Integer rollId;

    @Column(name = "active_str")
    String strActive;

    @Column(name = "active")
    Integer active;

    @Column(name = "auth_key")
    String authorizationKey;

    @Column(name = "address")
    String address;

    @Column(name = "ogrn")
    String ogrn;
}
