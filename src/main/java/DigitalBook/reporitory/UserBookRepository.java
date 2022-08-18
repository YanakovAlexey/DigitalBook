package DigitalBook.reporitory;

import DigitalBook.domain.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBookRepository extends JpaRepository<UserBook, Long> {
}
