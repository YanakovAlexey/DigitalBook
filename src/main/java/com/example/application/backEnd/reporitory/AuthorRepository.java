package com.example.application.backEnd.reporitory;

import com.example.application.backEnd.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
