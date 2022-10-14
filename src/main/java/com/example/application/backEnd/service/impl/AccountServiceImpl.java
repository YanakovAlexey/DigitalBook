package com.example.application.backEnd.service.impl;

import com.example.application.backEnd.reporitory.UserRepository;
import com.example.application.backEnd.service.AccountService;
import com.example.application.backEnd.service.ResponseException;
import com.example.application.backEnd.viewModel.account.AuthViewModel;
import com.example.application.backEnd.viewModel.account.TokenViewModel;
import lombok.SneakyThrows;
import org.springframework.util.DigestUtils;

public class AccountServiceImpl implements AccountService {

    UserRepository userRepository;


    @SneakyThrows
    @Override
    public TokenViewModel auth(AuthViewModel authViewModel) {

//        var users = userRepository.findFirstByUsernameAndPassword(authViewModel.getUsername(),
//                authViewModel.setPassword(DigestUtils.md5DigestAsHex()));
//
//        if (users.isEmpty()) {
//            throw new ResponseException("error", "error", 504);
//        }
//
//        TokenHelper.buildToken(users.get().getId());

        return TokenViewModel.builder().build();
    }
}
