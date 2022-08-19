package com.example.application.backEnd.service;

import com.example.application.backEnd.domain.City;
import com.example.application.backEnd.domain.Role;
import com.example.application.backEnd.viewModel.BookViewModel;
import com.example.application.backEnd.viewModel.CityViewModel;
import com.example.application.backEnd.viewModel.RoleViewModel;

import java.util.List;

public interface RoleService {
    Role create(Role request);

    List<RoleViewModel> getAll();

    RoleViewModel getById(Long id);

    void update(Long id, Role role, RoleViewModel request);

    void deleteById(Long id);
}
