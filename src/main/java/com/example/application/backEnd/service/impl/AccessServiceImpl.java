package com.example.application.backEnd.service.impl;

import com.example.application.backEnd.builder.AccessBuilder;
import com.example.application.backEnd.domain.Access;
import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.reporitory.AccessRepository;
import com.example.application.backEnd.service.AccessService;
import com.example.application.backEnd.viewModel.AccessViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class AccessServiceImpl implements AccessService {
    private final AccessRepository accessRepository;

    private final AccessBuilder accessBuilder;

    @Autowired
    public AccessServiceImpl(AccessRepository accessRepository, AccessBuilder accessBuilder) {
        this.accessRepository = accessRepository;
        this.accessBuilder = accessBuilder;
    }

    @Override
    public Access create(Access access) {
        accessBuilder.createAccess(access);
        accessRepository.save(access);
        return access;
    }

    @Override
    public void update(Access access, AccessViewModel viewModel) {
        Optional<Access> accessOpt = accessRepository.findById(access.getId());
        if (accessOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (accessOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        accessBuilder.update(access, viewModel);
    }
}
