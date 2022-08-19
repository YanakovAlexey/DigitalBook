package com.example.application.backEnd.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Table(name = "access")
@Entity
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Access {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "id_user")
    Integer id_user;

    @Column(name = "id_role")
    Integer id_role;

    @Column(name = "string")
    String string;

    @Column(name = "date_out")
    Date date_out;



}
