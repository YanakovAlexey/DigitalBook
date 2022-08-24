package com.example.application.backEnd.service.impl;

import com.example.application.backEnd.builder.SchoolBuilder;
import com.example.application.backEnd.domain.School;
import com.example.application.backEnd.reporitory.SchoolRepository;
import com.example.application.backEnd.service.SchoolService;
import com.example.application.backEnd.viewModel.SchoolViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SchoolServiceImpl implements SchoolService {

    SchoolBuilder schoolBuilder;
    SchoolRepository schoolRepository;

    public List<SchoolViewModel> getAll() {
        List<School> userList = schoolRepository.findAll();
        List<SchoolViewModel> roleViewModels = new ArrayList<>();
        for (School item : userList) {
            roleViewModels.add(schoolBuilder.build(item));
        }
        return roleViewModels;
    }

    public void deleteById(Long id) {
        Optional<School> schoolOpt = schoolRepository.findById(id);
        if (schoolOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        schoolRepository.deleteById(schoolOpt.get().getId());
        schoolRepository.deleteById(id);
    }

    public void create(School school) {
        schoolBuilder.create(school);
        schoolRepository.save(school);
    }

    public void update(Long id, SchoolViewModel request) {
        Optional<School> schoolOpt = schoolRepository.findById(id);
        if (schoolOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (schoolOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        schoolBuilder.build(schoolOpt.get());
    }

    public SchoolViewModel getById(Long id) {
        Optional<School> schoolOpt = schoolRepository.findById(id);
        if (schoolOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (schoolOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return schoolBuilder.build(schoolOpt.get());
    }

}
