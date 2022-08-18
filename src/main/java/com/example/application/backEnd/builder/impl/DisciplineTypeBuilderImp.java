package com.example.application.backEnd.builder.impl;


import com.example.application.backEnd.builder.DisciplineTypeBuilder;
import com.example.application.backEnd.domain.DisciplineType;
import com.example.application.backEnd.viewModel.DisciplineTypeViewModel;
import org.springframework.stereotype.Component;

@Component
public class DisciplineTypeBuilderImp implements DisciplineTypeBuilder {

    @Override
    public DisciplineTypeViewModel createDisciplineType(DisciplineType disciplineType){
        return DisciplineTypeViewModel.builder()
                .id(disciplineType.getId())
                .title(disciplineType.getTitle())
                .build();

    }
    public void updateDisciplineType(DisciplineType disciplineType,DisciplineTypeViewModel request){

        disciplineType.setTitle(request.getTitle());
    }
}
