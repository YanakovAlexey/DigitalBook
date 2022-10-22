package com.example.application.backEnd.reporitory;

import com.example.application.backEnd.domain.BasketPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketPositionRepository extends JpaRepository<BasketPosition, Long> {
}
