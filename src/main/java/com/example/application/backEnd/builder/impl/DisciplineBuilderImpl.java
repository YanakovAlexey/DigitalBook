package com.example.application.backEnd.builder.impl;

import com.example.application.backEnd.builder.DisciplineBuilder;
import com.example.application.backEnd.domain.Discipline;
import com.example.application.backEnd.domain.School;
import com.example.application.backEnd.viewModel.DisciplineTypeViewModel;
import com.example.application.backEnd.viewModel.DisciplineViewModel;

public class DisciplineBuilderImpl implements DisciplineBuilder {
    @Override
    public DisciplineViewModel create(Discipline discipline) {
        return DisciplineViewModel.builder()
                .id(discipline.getId())
                .title(discipline.getTitle())
                .build();
    }

    @Override
    public DisciplineViewModel build(Discipline item) {
        return null;
    }

    @Override
    public void update(Discipline updateDiscipline, DisciplineViewModel request) {
        updateDiscipline.setTitle(request.getTitle());

    }
}
