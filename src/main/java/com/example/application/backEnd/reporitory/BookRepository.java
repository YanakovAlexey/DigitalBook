package com.example.application.backEnd.reporitory;

import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.viewModel.BookViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    BookViewModel findFirstById(Long id);

    @Query("SELECT book FROM Book book " +
            "WHERE book.title LIKE :title% ")
    List<Book> findByTitleContains(@Param("title") String title);

    List<Book> findAllByAuthor(String author);

    List<Book> findAllByIdDiscipline(Long id);
}
