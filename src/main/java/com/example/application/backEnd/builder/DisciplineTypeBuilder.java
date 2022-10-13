package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.City;
import com.example.application.backEnd.domain.DisciplineType;
import com.example.application.backEnd.viewModel.CityViewModel;
import com.example.application.backEnd.viewModel.DisciplineTypeViewModel;
import com.example.application.backEnd.viewModel.DisciplineViewModel;

public interface DisciplineTypeBuilder {
    DisciplineTypeViewModel create(DisciplineType disciplineType);

    void update(DisciplineType disciplineType, DisciplineTypeViewModel request);

    DisciplineTypeViewModel build(DisciplineType item);

}
