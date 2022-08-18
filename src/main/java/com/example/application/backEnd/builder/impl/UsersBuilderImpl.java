package com.example.application.backEnd.builder.impl;

import com.example.application.backEnd.builder.UsersBuilder;
import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.viewModel.UsersViewModel;

public class UsersBuilderImpl implements UsersBuilder {
    @Override
    public UsersViewModel createUsers(Users users) {
        return UsersViewModel.builder()
                .id(users.getId())
                .name(users.getName())
                .surname(users.getSurname())
                .patronymic(users.getPatronymic())
                .inn(users.getInn())
                .email(users.getEmail())
                .phone(users.getPhone())
                .password(users.getPassword())
                .cityId(users.getCityId())
                .schoolId(users.getSchoolId())
                .classNumber(users.getClassNumber())
                .letterClass(users.getLetterClass())
                .rollId(users.getRollId())
                .strActive(users.getStrActive())
                .active(users.getActive())
                .authorizationKey(users.getAuthorizationKey())
                .address(users.getAddress())
                .ogrn(users.getOgrn())
                .build();
    }
    public void updateUsers(Users users, UsersViewModel request){
        users.setName(request.getName());
        users.setSurname(request.getSurname());
        users.setPatronymic(request.getPatronymic());
        users.setInn(request.getInn());
        users.setEmail(request.getEmail());
        users.setPhone(request.getPhone());
        users.setPassword(request.getPassword());
        users.setCityId(request.getCityId());
        users.setSchoolId(request.getSchoolId());
        users.setClassNumber(request.getClassNumber());
        users.setLetterClass(request.getLetterClass());
        users.setRollId(users.getRollId());
        users.setStrActive(request.getStrActive());
        users.setActive(request.getActive());
        users.setAuthorizationKey(request.getAuthorizationKey());
        users.setAddress(request.getAddress());
        users.setOgrn(request.getOgrn());
    }
}
