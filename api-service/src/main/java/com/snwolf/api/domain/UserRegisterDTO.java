package com.snwolf.api.domain;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String userAccount;
    private String password;
    private String checkPassword;
}
