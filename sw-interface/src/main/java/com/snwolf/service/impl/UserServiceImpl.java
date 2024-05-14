package com.snwolf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snwolf.domain.entity.User;
import com.snwolf.mapper.UserMapper;
import com.snwolf.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
