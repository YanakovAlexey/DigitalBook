package com.example.application.backEnd.reporitory;

import com.example.application.backEnd.domain.CodeConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeConfirmationRepository extends JpaRepository<CodeConfirmation, Long> {


    Optional<CodeConfirmation> findByEmailAndCode(String email, String code);
}
