package com.example.application.backEnd.service.impl;

import com.example.application.backEnd.builder.DisciplineTypeBuilder;
import com.example.application.backEnd.domain.Discipline;
import com.example.application.backEnd.domain.DisciplineType;
import com.example.application.backEnd.reporitory.DisciplineTypeRepository;
import com.example.application.backEnd.service.DisciplineTypeService;
import com.example.application.backEnd.viewModel.DisciplineTypeViewModel;
import com.example.application.backEnd.viewModel.DisciplineViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DisciplineTypeServiceImpl implements DisciplineTypeService {

    final DisciplineTypeRepository disciplineTypeRepository;
    final DisciplineTypeBuilder disciplineTypeBuilder;

    public DisciplineTypeServiceImpl(DisciplineTypeRepository disciplineRepository, DisciplineTypeBuilder disciplineBuilder) {
        this.disciplineTypeRepository = disciplineRepository;
        this.disciplineTypeBuilder = disciplineBuilder;
    }

    @Override
    public DisciplineType create(DisciplineType request) {
        disciplineTypeBuilder.create(request);
        disciplineTypeRepository.save(request);
        return request;
    }

    @Override
    public List<DisciplineTypeViewModel> getAll() {
        List<DisciplineType> discipline = disciplineTypeRepository.findAll();
        List<DisciplineTypeViewModel> disciplineTypeViewModels = new ArrayList<>();
        for (DisciplineType item : discipline) {
            disciplineTypeViewModels.add(disciplineTypeBuilder.build(item));
        }
        return disciplineTypeViewModels;    }

    @Override
    public DisciplineTypeViewModel getById(Long id) {
        Optional<DisciplineType> discOpt = disciplineTypeRepository.findById(id);
        if (discOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (discOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return disciplineTypeBuilder.build(discOpt.get());
    }

    @Override
    public void update(Long id, DisciplineType disciplineType, DisciplineTypeViewModel request) {
        Optional<DisciplineType> discOpt = disciplineTypeRepository.findById(id);
        if (discOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (discOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        disciplineTypeBuilder.update(disciplineType, request);
    }

    @Override
    public void deleteById(Long id) {
        Optional<DisciplineType> discOpt = disciplineTypeRepository.findById(id);
        if (discOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (discOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        disciplineTypeRepository.delete(discOpt.get());
    }
    
}
