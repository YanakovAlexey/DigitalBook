package com.example.application.backEnd.reporitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.application.backEnd.domain.BookType;

@Repository
public interface BookTypeRepository extends JpaRepository<BookType, Long> {}
