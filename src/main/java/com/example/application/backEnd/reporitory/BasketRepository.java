package com.example.application.backEnd.reporitory;


import com.example.application.backEnd.domain.Basket;
import com.example.application.backEnd.viewModel.BasketViewModel;
import com.example.application.backEnd.viewModel.BookViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    BasketViewModel findFirstById(Long id);
}
