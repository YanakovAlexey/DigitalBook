package com.example.application.backEnd.service.impl;

import com.example.application.backEnd.builder.PublisherBuilder;
import com.example.application.backEnd.domain.Discipline;
import com.example.application.backEnd.domain.Publisher;
import com.example.application.backEnd.reporitory.PublisherRepository;
import com.example.application.backEnd.service.PublisherService;
import com.example.application.backEnd.viewModel.PublisherViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherBuilder publisherBuilder;

    public PublisherServiceImpl(PublisherRepository publisherRepository, PublisherBuilder publisherBuilder) {

        this.publisherRepository = publisherRepository;
        this.publisherBuilder = publisherBuilder;
    }

    @Override
    public void create(Publisher request) {
        publisherBuilder.create(request);
        publisherRepository.save(request);
    }

    @Override
    public List<Publisher> getAll() {
        List<Publisher> publishers = publisherRepository.findAll();
        List<PublisherViewModel> publishesViewModels = new ArrayList<>();
        for (Publisher item : publishers ) {
            publishesViewModels.add(publisherBuilder.build(item));
        }
        return publishers;
    }

    @Override
    public PublisherViewModel getById(Long id) {
        Optional<Publisher> publisherOpt = publisherRepository.findById(id);
        if (publisherOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (publisherOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return publisherBuilder.build(publisherOpt.get());
    }



    @Override
    public void deleteById(Long id) {
        Optional<Publisher> publisherOpt = publisherRepository.findById(id);
        if (publisherOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (publisherOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        publisherRepository.delete(publisherOpt.get());

    }
}
