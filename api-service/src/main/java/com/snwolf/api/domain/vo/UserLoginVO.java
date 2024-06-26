package com.snwolf.api.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO implements Serializable {

    private String token;
    private Long id;
    private String username;
    private String userAccount;
    private String userAvatar;
    private Integer gender;
    private String userRole;

}
