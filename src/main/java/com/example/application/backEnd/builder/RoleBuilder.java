package com.example.application.backEnd.builder;

import com.example.application.backEnd.builder.impl.RoleBuilderImpl;
import com.example.application.backEnd.domain.Role;
import com.example.application.backEnd.viewModel.RoleViewModel;

public interface RoleBuilder {

    RoleViewModel createRole(Role role);
}
