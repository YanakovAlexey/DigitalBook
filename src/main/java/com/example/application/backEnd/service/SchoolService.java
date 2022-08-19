package com.example.application.backEnd.service;

import com.example.application.backEnd.domain.School;
import com.example.application.backEnd.viewModel.SchoolViewModel;

import java.util.List;

public interface SchoolService {

    void create(School request);

    List<SchoolViewModel> getAll();

    SchoolViewModel getById(Long id);

    void update(Long id, SchoolViewModel request);

    void deleteById(Long id);
}
