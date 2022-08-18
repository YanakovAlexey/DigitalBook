package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.Access;
import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.viewModel.AccessViewModel;
import com.example.application.backEnd.viewModel.BookViewModel;

public interface AccessBuilder {

    AccessViewModel createAccess(Access access);

    void update(Access access, AccessViewModel request);
}
