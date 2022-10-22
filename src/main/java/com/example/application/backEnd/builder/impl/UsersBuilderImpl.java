package com.example.application.backEnd.builder.impl;

import com.example.application.backEnd.builder.UsersBuilder;
import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.viewModel.UserViewModel;
import com.example.application.backEnd.viewModel.account.RegistrationViewModel;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UsersBuilderImpl implements UsersBuilder {
    @Override
    public UserViewModel create(Users users) {
        return UserViewModel.builder()
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
                .idBasket(users.getIdBasket())
                .build();
    }


    public void update(Users users, UserViewModel request) {
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
        users.setIdBasket(request.getIdBasket());
    }

    @Override
    public UserViewModel build(Users item) {
        return UserViewModel.builder()
                .id(item.getId())
                .name(item.getName())
                .surname(item.getSurname())
                .patronymic(item.getPatronymic())
                .inn(item.getInn())
                .email(item.getEmail())
                .phone(item.getPhone())
                .password(item.getPassword())
                .cityId(item.getCityId())
                .schoolId(item.getSchoolId())
                .classNumber(item.getClassNumber())
                .letterClass(item.getLetterClass())
                .rollId(item.getRollId())
                .strActive(item.getStrActive())
                .active(item.getActive())
                .authorizationKey(item.getAuthorizationKey())
                .address(item.getAddress())
                .ogrn(item.getOgrn())
                .idBasket(item.getIdBasket())
                .build();
    }

    @Override
    public Users regBuild(RegistrationViewModel request) {
        Users user = new Users();
        user.setPassword(DigestUtils.md5DigestAsHex(request.getPassword().getBytes()));
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        return user;
    }
}