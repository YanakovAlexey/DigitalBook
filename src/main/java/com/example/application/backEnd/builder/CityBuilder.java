package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.City;
import com.example.application.backEnd.viewModel.CityViewModel;

public interface CityBuilder {
    CityViewModel createCity(City city);

    void updateCity(City city, CityViewModel request);

    CityViewModel build (City request );
}
