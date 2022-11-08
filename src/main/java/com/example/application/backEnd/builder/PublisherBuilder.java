package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.Publisher;
import com.example.application.backEnd.viewModel.PublisherViewModel;

public interface PublisherBuilder {
    PublisherViewModel create(Publisher publisher);

    PublisherViewModel build(Publisher item);

}
