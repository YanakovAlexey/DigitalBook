package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.City;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.backEnd.viewModel.CityViewModel;

public interface CityBuilder {
    CityViewModel createBook(City city);

    void updateBook(City city, CityViewModel request);

    void createCity(City request);

    CityViewModel build(City item);

    void update(City book, BookViewModel request);
}
