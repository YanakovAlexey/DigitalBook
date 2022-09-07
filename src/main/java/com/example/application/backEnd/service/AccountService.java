package com.example.application.backEnd.service;

import com.example.application.backEnd.viewModel.account.AuthViewModel;
import com.example.application.backEnd.viewModel.account.TokenViewModel;

public interface AccountService {
    TokenViewModel auth(AuthViewModel authViewModel);
}
