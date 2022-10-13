package com.example.application.backEnd.builder.impl;

import com.example.application.backEnd.builder.DisciplineBuilder;
import com.example.application.backEnd.domain.Discipline;
import com.example.application.backEnd.domain.School;
import com.example.application.backEnd.viewModel.DisciplineTypeViewModel;
import com.example.application.backEnd.viewModel.DisciplineViewModel;
import org.springframework.stereotype.Component;

@Component
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
        DisciplineViewModel discipline = new DisciplineViewModel();
        discipline.setId(item.getId());
        discipline.setTitle(item.getTitle());
        discipline.setType(item.getType());
        return discipline;
    }

    @Override
    public void update(Discipline updateDiscipline, DisciplineViewModel request) {
        updateDiscipline.setTitle(request.getTitle());

    }
}
