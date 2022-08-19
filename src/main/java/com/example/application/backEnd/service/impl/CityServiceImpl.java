package com.example.application.backEnd.service.impl;

import com.example.application.backEnd.builder.CityBuilder;
import com.example.application.backEnd.domain.City;
import com.example.application.backEnd.reporitory.CityRepository;
import com.example.application.backEnd.service.CityService;
import com.example.application.backEnd.viewModel.CityViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CityServiceImpl implements CityService {
   final CityBuilder cityBuilder ;
   final CityRepository cityRepository;

    public CityServiceImpl(CityBuilder cityBuilder, CityRepository cityRepository) {
        this.cityBuilder = cityBuilder;
        this.cityRepository = cityRepository;
    }

    @Override
    public void create(City request) {
       cityBuilder.createCity(request);
       cityRepository.save(request);
    }

    @Override
    public List<CityViewModel> getAll() {

        List<City> cityList = cityRepository.findAll();
        List<CityViewModel> cityDtoList = new ArrayList<>();
        for (City item : cityList) {
            cityDtoList.add(cityBuilder.build(item));
        }
        return cityDtoList;
    }

    @Override
    public CityViewModel getById(Long id) {
        Optional<City> cityOpt = cityRepository.findById(id);
        if (cityOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return cityBuilder.build(cityOpt.get());    }

    @Override
    public void update(Long id, CityViewModel request) {
        Optional<City> cityOpt = cityRepository.findById(id);
        if (cityOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        City updateCity = cityOpt.get();
        cityBuilder.updateCity(updateCity, request);

    }

    @Override
    public void deleteById(Long id) {
        Optional<City> cityOpt = cityRepository.findById(id);
        if (cityOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        cityRepository.deleteById(id);

    }
}
