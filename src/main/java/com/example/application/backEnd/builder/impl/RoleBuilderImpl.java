package com.example.application.backEnd.builder.impl;

import com.example.application.backEnd.builder.RoleBuilder;
import com.example.application.backEnd.domain.Role;
import com.example.application.backEnd.viewModel.RoleViewModel;
import org.springframework.stereotype.Component;

@Component
public class  RoleBuilderImpl implements RoleBuilder {
    @Override
    public RoleViewModel create(Role role) {
        return RoleViewModel.builder()
                .id(role.getId())
                .title(role.getTitle())
                .build();
    }

    @Override
    public RoleViewModel build(Role item) {
        return null;
    }

    public void update(Role role, RoleViewModel request){
        role.setTitle(request.getTitle());
    }
}
