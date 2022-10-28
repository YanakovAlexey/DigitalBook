package com.example.application.backEnd.builder;

import com.example.application.backEnd.domain.Role;
import com.example.application.backEnd.viewModel.RoleViewModel;

public interface RoleBuilder {

    RoleViewModel create(Role role);

    RoleViewModel build(Role item);

    void update(Role role, RoleViewModel request);
}
