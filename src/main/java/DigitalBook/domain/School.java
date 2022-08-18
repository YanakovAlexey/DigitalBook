package DigitalBook.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "school")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
     Long id;

    @Column(name = "title")
    String title;

    @Column(name = "id_city")
    Integer id_city;

}
