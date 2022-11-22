package com.example.application.backEnd.reporitory;

import com.example.application.backEnd.domain.Publisher;
import com.example.application.backEnd.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    
}
