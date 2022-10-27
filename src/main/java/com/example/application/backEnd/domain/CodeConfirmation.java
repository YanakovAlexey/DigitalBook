package com.example.application.backEnd.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Table(name = "code_confirmation")
@Entity
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor()
@AllArgsConstructor()
@Builder
public class CodeConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "email")
    String email;

    @Column(name = "code")
    String code;

    @Column(name = "confirmation")
    boolean confirmation;

    @Column(name = "dateOfCreation")
    Date dateOfCreation;
}
