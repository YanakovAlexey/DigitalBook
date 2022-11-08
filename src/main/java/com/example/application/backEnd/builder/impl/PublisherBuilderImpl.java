package com.example.application.backEnd.builder.impl;

import com.example.application.backEnd.builder.PublisherBuilder;
import com.example.application.backEnd.domain.Publisher;
import com.example.application.backEnd.viewModel.PublisherViewModel;
import org.springframework.stereotype.Component;

@Component
public class PublisherBuilderImpl implements PublisherBuilder {
    @Override
    public PublisherViewModel create(Publisher publisher) {
        return PublisherViewModel.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .build();
    }

    @Override
    public PublisherViewModel build(Publisher item) {
        PublisherViewModel publisher = new PublisherViewModel();
        publisher.setId(publisher.getId());
        publisher.setName(publisher.getName());
        return publisher;
    }
}
