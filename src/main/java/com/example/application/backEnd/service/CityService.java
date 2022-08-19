package com.example.application.backEnd.service;

import com.example.application.backEnd.domain.City;
import com.example.application.backEnd.viewModel.CityViewModel;

import java.util.List;

public interface CityService {
    void create(City request);
    List<CityViewModel> getAll();
    CityViewModel getById(Long id);
    void update(Long id, CityViewModel request);
    void deleteById(Long id);
}
