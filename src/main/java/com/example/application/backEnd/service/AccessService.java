package com.example.application.backEnd.service;

import com.example.application.backEnd.domain.Access;
import com.example.application.backEnd.viewModel.AccessViewModel;

public interface AccessService {

    Access create(Access access);

    void update(Access access, AccessViewModel viewModel);
}
