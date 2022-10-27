package com.example.application.backEnd.service.impl;


import com.example.application.backEnd.builder.RoleBuilder;
import com.example.application.backEnd.domain.Role;
import com.example.application.backEnd.reporitory.RoleRepository;
import com.example.application.backEnd.service.RoleService;
import com.example.application.backEnd.viewModel.RoleViewModel;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    RoleBuilder roleBuilder;
    RoleRepository roleRepository;


    public List<RoleViewModel> getAll() {
        List<Role> userList = roleRepository.findAll();
        List<RoleViewModel> roleViewModels = new ArrayList<>();
        for (Role item : userList) {
            roleViewModels.add(roleBuilder.build(item));
        }
        return roleViewModels;
    }

    @Override
    public RoleViewModel getById(Long id) {
        Optional<Role> roleOpt = roleRepository.findById(id);
        if (roleOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (roleOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return roleBuilder.build(roleOpt.get());
    }

    public void deleteById(Long id) {
        Optional<Role> roleOpt = roleRepository.findById(id);
        if (roleOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        roleRepository.deleteById(roleOpt.get().getId());
        roleRepository.deleteById(id);
    }

    public Role create(Role role) {
        roleBuilder.create(role);
        roleRepository.save(role);
        return role;
    }

    public void update(Long id, Role role, RoleViewModel request) {
        Optional<Role> roleOpt = roleRepository.findById(id);
        if (roleOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (roleOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        roleBuilder.update(role, request);
    }

}
