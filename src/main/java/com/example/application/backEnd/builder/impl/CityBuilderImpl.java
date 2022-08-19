package com.example.application.backEnd.builder.impl;

import com.example.application.backEnd.builder.CityBuilder;
import com.example.application.backEnd.domain.City;
import com.example.application.backEnd.viewModel.CityViewModel;

public class CityBuilderImpl implements CityBuilder {
    @Override
    public CityViewModel createCity(City city) {
        return CityViewModel.builder()
                .id(city.getId())
                .title(city.getTitle())
                .build();
    }

    @Override
    public void updateCity(City city, CityViewModel request) {
        city.setTitle(request.getTitle());
    }

    @Override
    public CityViewModel build(City request) {
        City city = new City();
        city.setTitle(request.getTitle());
        return city;
    }


}

