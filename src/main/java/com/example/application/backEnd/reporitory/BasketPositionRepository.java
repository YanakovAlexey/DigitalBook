package com.example.application.backEnd.reporitory;

import com.example.application.backEnd.domain.Basket;
import com.example.application.backEnd.domain.BasketPosition;
import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.viewModel.BasketPositionViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BasketPositionRepository extends JpaRepository<BasketPosition, Long> {
    List<BasketPosition> findAllByIdBasket(Long id);
//
//    @Query(
//            "SELECT Book.id FROM BasketPosition basketPosition " +
//                    "WHERE basketPosition.is_paid = TRUE"
//    )
    List<BasketPosition> findAllByIsPaidTrue();


}
