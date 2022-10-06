package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.Discipline;
import com.example.application.backEnd.domain.School;
import com.example.application.backEnd.viewModel.DisciplineViewModel;

public interface DisciplineBuilder {
    DisciplineViewModel create(Discipline discipline);

    DisciplineViewModel build(Discipline item);

    void update(Discipline updateDiscipline, DisciplineViewModel request);
}
