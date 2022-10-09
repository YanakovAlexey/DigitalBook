package com.example.application.backEnd.service.impl;

import com.example.application.backEnd.builder.DisciplineBuilder;
import com.example.application.backEnd.domain.Discipline;
import com.example.application.backEnd.reporitory.DisciplineRepository;
import com.example.application.backEnd.service.DisciplineService;
import com.example.application.backEnd.viewModel.DisciplineViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DisciplineServiceImpl implements DisciplineService {
    final DisciplineRepository disciplineRepository;
    final DisciplineBuilder disciplineBuilder;


    public DisciplineServiceImpl(DisciplineRepository disciplineRepository, DisciplineBuilder disciplineBuilder) {
        this.disciplineRepository = disciplineRepository;
        this.disciplineBuilder = disciplineBuilder;
    }

    @Override
    public Discipline create(Discipline request) {
        disciplineBuilder.create(request);
        disciplineRepository.save(request);
        return request;
    }

    @Override
    public List<DisciplineViewModel> getAll() {
        List<Discipline> discipline = disciplineRepository.findAll();
        List<DisciplineViewModel> disciplineViewModels = new ArrayList<>();
        for (Discipline item : discipline) {
            disciplineViewModels.add(disciplineBuilder.build(item));
        }
        return disciplineViewModels;
    }

    @Override
    public DisciplineViewModel getById(Long id) {
        Optional<Discipline> discOpt = disciplineRepository.findById(id);
        if (discOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (discOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return disciplineBuilder.build(discOpt.get());
    }

    @Override
    public void update(Long id, Discipline discipline, DisciplineViewModel request) {
        Optional<Discipline> discOpt = disciplineRepository.findById(id);
        if (discOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (discOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        disciplineBuilder.update(discipline, request);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Discipline> discOpt = disciplineRepository.findById(id);
        if (discOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (discOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        disciplineRepository.delete(discOpt.get());
    }
}
