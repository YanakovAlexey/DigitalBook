package com.example.digitalbooks.reporitory;

import com.example.digitalbooks.domain.BookType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTypeRepository extends JpaRepository<BookType, Long> {

}
