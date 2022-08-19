package com.example.application.backEnd.service.impl;

import com.example.application.backEnd.builder.CityBuilder;
import com.example.application.backEnd.domain.City;
import com.example.application.backEnd.reporitory.CityRepository;
import com.example.application.backEnd.service.CityService;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.backEnd.viewModel.CityViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CityServiceImpl implements CityService {

    CityBuilder cityBuilder;
    CityRepository cityRepository;

    @Override
    public City create(City request) {
        cityBuilder.createCity(request);
        cityRepository.save(request);
        return request;
    }

    @Override
    public List<CityViewModel> getAll() {
        List<City> city = cityRepository.findAll();
        List<CityViewModel> bookList = new ArrayList<>();
        for (City item : city) {
            bookList.add(cityBuilder.build(item));
        }
        return bookList;
    }

    @Override
    public CityViewModel getById(Long id) {
        Optional<City> cityOpt = cityRepository.findById(id);
        if (cityOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (cityOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return cityBuilder.build(cityOpt.get());
    }

    @Override
    public void update(Long id, City book, BookViewModel request) {
        Optional<City> cityOpt = cityRepository.findById(id);
        if (cityOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (cityOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        cityBuilder.update(book, request);
    }

    @Override
    public void deleteById(Long id) {


        
        Optional<City> cityOpt = cityRepository.findById(id);
        if (cityOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (cityOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        cityRepository.delete(cityOpt.get());
    }
}
