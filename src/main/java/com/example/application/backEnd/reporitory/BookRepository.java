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

    BookViewModel findFirstById(Long id);
    List<Book> findByTitleContains(String title);
    List<Book> findAllByAuthor(String author);
    List<Book> findAllByIdDiscipline(Long id);
    List<Book> findAllByUsers(Users users);
    List<Book> findByTitleLike(String title);

}
