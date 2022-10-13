package com.example.application.backEnd.builder.impl;

import com.example.application.backEnd.builder.DisciplineTypeBuilder;
import com.example.application.backEnd.domain.DisciplineType;
import com.example.application.backEnd.viewModel.DisciplineTypeViewModel;
import com.example.application.backEnd.viewModel.DisciplineViewModel;

public class DisciplineTypeBuilderImpl implements DisciplineTypeBuilder {
    @Override
    public DisciplineTypeViewModel create(DisciplineType disciplineType) {
        return DisciplineTypeViewModel.builder()
                .id(disciplineType.getId())
                .title(disciplineType.getTitle())
                .build();
    }

    @Override
    public void update(DisciplineType disciplineType, DisciplineTypeViewModel request) {
        disciplineType.setTitle(request.getTitle());

    }

    @Override
    public DisciplineTypeViewModel build(DisciplineType item) {
        return null;
    }
}
