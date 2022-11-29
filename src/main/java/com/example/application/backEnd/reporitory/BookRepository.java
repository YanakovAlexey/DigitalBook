package com.example.application.backEnd.reporitory;

import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.backEnd.viewModel.UserViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthor(String author);
    Book findFirstBookByAuthor(String author);
    List<Book> findAllByIdDiscipline(Long id);
    List<Book> findAllByUserId(Long id);
    List<Book> findByTitleLike(String title);

    @Query("SELECT book FROM Book book " +
            "WHERE book.title LIKE :title% ")
    List<Book> findByTitleContains(@Param("title") String title);

    @Query(value = """
            select DISTINCT book.author from Book book
            """
    )
    List<String> findAllAuthors();

    @Query("""
            select DISTINCT book.userId from Book book
            """
    )
    List<Long> findAllPublisher();
}
