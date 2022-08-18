package com.example.application.backEnd.builder.impl;

import com.example.application.backEnd.builder.CityBuilder;
import com.example.application.backEnd.domain.City;
import com.example.application.backEnd.viewModel.CityViewModel;

public class CityBuilderImpl implements CityBuilder {
    @Override
    public CityViewModel createBook(City city) {
        return CityViewModel.builder()
                .id(city.getId())
                .title(city.getTitle())
                .build();
    }

    @Override
    public void updateBook(City city, CityViewModel request) {
        city.setTitle(request.getTitle());
    }
}
