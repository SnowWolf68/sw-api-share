package com.snwolf.api.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String username;
    private String password;
    private String userAccount;
    private String userAvatar;
    private Integer gender;
    private String userRole;
    private String accessKey;
    private String secretKey;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean isDelete;
}
