package com.example.digitalbooks.reporitory;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.digitalbooks.domain.Basket;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
}
