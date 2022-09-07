package com.example.application.backEnd.reporitory;

import com.example.application.backEnd.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findFirstByUsername(String username);
}
