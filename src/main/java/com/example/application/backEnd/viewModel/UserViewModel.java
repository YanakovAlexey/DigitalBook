package com.example.application.backEnd.viewModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserViewModel {

    Long id;
    String username;
    String name;
    String surname;
    String patronymic;
    Integer inn;
    String email;
    String phone;
    String password;
    Integer cityId;
    Integer schoolId;
    Integer classNumber;
    String letterClass;
    Integer rollId;
    String strActive;
    Integer active;
    String authorizationKey;
    String address;
    String ogrn;
}
