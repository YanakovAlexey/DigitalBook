package com.example.application.backEnd.builder.impl;

import com.example.application.backEnd.builder.SchoolBuilder;
import com.example.application.backEnd.domain.School;
import com.example.application.backEnd.viewModel.SchoolViewModel;

public class SchoolBuilderImpl implements SchoolBuilder {
    @Override
    public SchoolViewModel createSchool(School school) {
        return SchoolViewModel.builder()
                .id(school.getId())
                .id_city(school.getId_city())
                .title(school.getTitle())
                .build();
    }

    public void updateSchool(School school, SchoolViewModel request){
        school.setTitle(request.getTitle());
    }
}
