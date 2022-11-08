package com.example.application.backEnd.service;

import com.example.application.backEnd.domain.Publisher;
import com.example.application.backEnd.viewModel.PublisherViewModel;

import java.util.List;

public interface PublisherService {

    void create(Publisher request);

    List<Publisher> getAll();

    PublisherViewModel getById(Long id);


    void deleteById(Long id);
}
