package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.School;
import com.example.application.backEnd.viewModel.SchoolViewModel;

public interface SchoolBuilder {
    SchoolViewModel create(School school);

    SchoolViewModel build(School item);

    void update(School updateSchool, SchoolViewModel request);
}
