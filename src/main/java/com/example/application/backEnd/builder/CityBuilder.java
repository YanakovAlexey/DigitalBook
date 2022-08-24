package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.City;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.backEnd.viewModel.CityViewModel;

public interface CityBuilder {
    CityViewModel create(City city);

    void update(City city, CityViewModel request);

    CityViewModel build(City item);

}
