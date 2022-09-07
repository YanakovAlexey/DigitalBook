package com.example.application.backEnd.viewModel.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationViewModel {
    String username;
    String email;
    String password;
}
