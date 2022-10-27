package com.example.application.backEnd.viewModel.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeConfirmationViewModel {

    String email;
    String code;
    boolean confirmation;
    Date dateOfCreation;
}
