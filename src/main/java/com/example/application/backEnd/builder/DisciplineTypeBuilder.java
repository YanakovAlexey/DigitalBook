package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.City;
import com.example.application.backEnd.domain.DisciplineType;
import com.example.application.backEnd.viewModel.CityViewModel;
import com.example.application.backEnd.viewModel.DisciplineViewModel;

public interface DisciplineTypeBuilder {
    CityViewModel create(DisciplineType city);

    void update(City city, CityViewModel request);

    CityViewModel build(City item);

}
