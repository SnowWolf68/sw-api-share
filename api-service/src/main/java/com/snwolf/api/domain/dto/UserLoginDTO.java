package com.snwolf.api.domain.dto;

import lombok.Data;

@Data
public class UserLoginDTO {

    private String password;

    private String userAccount;
}
