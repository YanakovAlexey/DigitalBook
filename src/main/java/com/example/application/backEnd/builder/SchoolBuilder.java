package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.School;
import com.example.application.backEnd.viewModel.SchoolViewModel;
import com.example.application.backEnd.viewModel.UserViewModel;

public interface SchoolBuilder {
    SchoolViewModel createSchool(School school);

    SchoolViewModel build(School item);

    void updateSchool(School updateSchool, SchoolViewModel request);
}
