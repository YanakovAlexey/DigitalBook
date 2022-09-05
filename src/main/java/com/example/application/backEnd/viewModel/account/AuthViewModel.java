package com.example.application.backEnd.viewModel.account;

import com.vaadin.flow.component.textfield.TextField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthViewModel {
 String login;
 String password;
}
