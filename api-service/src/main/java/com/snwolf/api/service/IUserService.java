package com.snwolf.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snwolf.api.domain.dto.UserRegisterDTO;
import com.snwolf.api.domain.dto.UserLoginDTO;
import com.snwolf.api.domain.entity.User;
import com.snwolf.api.domain.vo.UserLoginVO;
import com.snwolf.api.domain.vo.UserRegisterVO;

public interface IUserService extends IService<User> {
    UserLoginVO login(UserLoginDTO userLoginDTO);

    UserRegisterVO register(UserRegisterDTO userRegisterDTO);
}
