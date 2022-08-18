package com.example.application.backEnd.builder.impl;

import com.example.application.backEnd.builder.AccessBuilder;
import com.example.application.backEnd.domain.Access;
import com.example.application.backEnd.viewModel.AccessViewModel;

public class AccessBuilderImpl implements AccessBuilder {
    @Override
    public AccessViewModel createAccess(Access access) {
        return AccessViewModel.builder()
                .id(access.getId())
                .id_user(access.getId_user())
                .id_role(access.getId_role())
                .string(access.getString())
                .date_out(access.getDate_out())
                .build();
    }

    @Override
    public void update(Access access, AccessViewModel request) {
        access.setId_user(request.getId_user());
        access.setId_role(request.getId_role());
        access.setString(request.getString());
        access.setDate_out(request.getDate_out());
    }
}
