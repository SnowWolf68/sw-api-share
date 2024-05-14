package com.snwolf.api.controller;

import com.snwolf.api.annotation.CheckRole;
import com.snwolf.api.domain.dto.UserRegisterDTO;
import com.snwolf.api.domain.dto.UserLoginDTO;
import com.snwolf.api.domain.entity.User;
import com.snwolf.api.domain.vo.UserLoginVO;
import com.snwolf.api.result.Result;
import com.snwolf.api.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
        return Result.success(userService.login(userLoginDTO));
    }

    @PostMapping("/register")
    public Result<Long> register(@RequestBody UserRegisterDTO userRegisterDTO){
        Long userId = userService.register(userRegisterDTO);
        return Result.success(userId);
    }

    @GetMapping("/queryAllUser")
    @CheckRole(role = "admin")
    public Result<List<User>> queryAllUser(){
        List<User> userList = userService.query().list();
        return Result.success(userList);
    }


}
