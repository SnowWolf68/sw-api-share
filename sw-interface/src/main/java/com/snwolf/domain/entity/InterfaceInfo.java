package com.snwolf.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sw_api")
public class InterfaceInfo {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private String description;
    private String url;
    private String requestHeader;
    private String responseHeader;
    private Integer status;
    private String method;
    private Long userId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer isDelete;
}
