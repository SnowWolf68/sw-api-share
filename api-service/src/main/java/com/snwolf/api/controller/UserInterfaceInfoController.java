package com.snwolf.api.controller;

import cn.hutool.core.bean.BeanUtil;
import com.snwolf.api.annotation.CheckRole;
import com.snwolf.api.domain.dto.UserInterfaceInfoDTO;
import com.snwolf.api.domain.entity.UserInterfaceInfo;
import com.snwolf.api.service.IUserInterfaceInfoService;
import com.snwolf.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userInterfaceInfo")
@RequiredArgsConstructor
public class UserInterfaceInfoController {

    private final IUserInterfaceInfoService userInterfaceInfoService;

    @PostMapping("/add")
    @CheckRole(role = "admin")
    public Result add(@RequestBody UserInterfaceInfoDTO userInterfaceInfoDTO){
        UserInterfaceInfo userInterfaceInfo = BeanUtil.copyProperties(userInterfaceInfoDTO, UserInterfaceInfo.class);
        userInterfaceInfoService.save(userInterfaceInfo);
        return Result.success();
    }
}
