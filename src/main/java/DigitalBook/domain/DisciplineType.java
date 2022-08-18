package DigitalBook.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Table(name = "discipline_type")
@Entity
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DisciplineType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "title")
    String title;
}
