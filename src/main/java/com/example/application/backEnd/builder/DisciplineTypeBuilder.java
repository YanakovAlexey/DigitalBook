package com.example.application.backEnd.builder;

import com.example.application.backEnd.builder.impl.DisciplineTypeBuilderImp;
import com.example.application.backEnd.domain.DisciplineType;
import com.example.application.backEnd.viewModel.DisciplineTypeViewModel;

public interface DisciplineTypeBuilder {
    DisciplineTypeViewModel createDisciplineType(DisciplineType disciplineType);
}
