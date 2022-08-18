package com.example.digitalbooks.reporitory;

import com.example.digitalbooks.domain.DisciplineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineTypeRepository extends JpaRepository<DisciplineType, Long> {
}
