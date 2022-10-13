package com.example.application.backEnd.service;


import com.example.application.backEnd.domain.DisciplineType;
import com.example.application.backEnd.viewModel.DisciplineTypeViewModel;


import java.util.List;

public interface DisciplineTypeService {
    DisciplineType create(DisciplineType request);

    List<DisciplineTypeViewModel> getAll();

    DisciplineTypeViewModel getById(Long id);

    void update(Long id, DisciplineType disciplineType, DisciplineTypeViewModel request);

    void deleteById(Long id);
}
