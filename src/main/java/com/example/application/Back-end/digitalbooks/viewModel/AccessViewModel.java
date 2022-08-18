package com.example.digitalbooks.viewModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessViewModel {
    Long id;
    Integer id_user;
    Integer id_role;
    String string;
    Date date_out;
}
