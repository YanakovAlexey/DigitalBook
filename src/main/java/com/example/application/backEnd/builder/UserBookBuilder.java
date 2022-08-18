package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.UserBook;
import com.example.application.backEnd.viewModel.UserBookViewModel;

public interface UserBookBuilder {
    UserBookViewModel createUserBook(UserBook userBook);
}
