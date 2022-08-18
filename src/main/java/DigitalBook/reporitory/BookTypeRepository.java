package DigitalBook.reporitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import DigitalBook.domain.BookType;

@Repository
public interface BookTypeRepository extends JpaRepository<BookType, Long> {

}
