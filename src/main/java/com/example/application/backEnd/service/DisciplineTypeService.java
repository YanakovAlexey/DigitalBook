package com.example.application.backEnd.service;


import com.example.application.backEnd.domain.DisciplineType;
import com.example.application.backEnd.viewModel.DisciplineViewModel;

import java.util.List;

public interface DisciplineTypeService {
    DisciplineTypeService create(DisciplineType request);

    List<DisciplineViewModel> getAll();

    DisciplineViewModel getById(Long id);

    void update(Long id, DisciplineType disciplineType, DisciplineViewModel request);

    void deleteById(Long id);
}
