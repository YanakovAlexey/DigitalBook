package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.City;
import com.example.application.backEnd.viewModel.CityViewModel;

public interface CityBuilder {
    CityViewModel createBook(City city);

    void updateBook(City city, CityViewModel request);
}
