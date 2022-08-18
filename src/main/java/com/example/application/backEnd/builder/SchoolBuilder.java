package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.School;
import com.example.application.backEnd.viewModel.SchoolViewModel;

public interface SchoolBuilder {
    SchoolViewModel createSchool(School school);
}
