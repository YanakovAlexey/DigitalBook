package com.example.application.backEnd.service;

import com.example.application.backEnd.domain.Book;
import com.example.application.backEnd.domain.Discipline;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.backEnd.viewModel.DisciplineViewModel;

import java.util.List;

public interface DisciplineService {
    Discipline create(Discipline request);

    List<DisciplineViewModel> getAll();

    DisciplineViewModel getById(Long id);

    void update(Long id, Discipline discipline, DisciplineViewModel request);

    void deleteById(Long id);

}
