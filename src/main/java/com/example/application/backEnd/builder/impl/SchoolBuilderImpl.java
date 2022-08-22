package com.example.application.backEnd.builder.impl;

import com.example.application.backEnd.builder.SchoolBuilder;
import com.example.application.backEnd.domain.School;
import com.example.application.backEnd.viewModel.SchoolViewModel;

public class SchoolBuilderImpl implements SchoolBuilder {
    @Override
    public SchoolViewModel create(School school) {
        return SchoolViewModel.builder()
                .id(school.getId())
                .id_city(school.getId_city())
                .title(school.getTitle())
                .build();
    }

    @Override
    public SchoolViewModel build(School item) {
        return null;
    }

    public void update(School school, SchoolViewModel request){
        school.setTitle(request.getTitle());
    }
}
