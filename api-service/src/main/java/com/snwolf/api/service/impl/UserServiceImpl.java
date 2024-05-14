package com.snwolf.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snwolf.api.constants.JwtClaimsConstant;
import com.snwolf.api.domain.dto.UserRegisterDTO;
import com.snwolf.api.domain.dto.UserLoginDTO;
import com.snwolf.api.domain.entity.User;
import com.snwolf.api.domain.vo.UserLoginVO;
import com.snwolf.api.exception.CheckPasswordException;
import com.snwolf.api.exception.UserLoginException;
import com.snwolf.api.mapper.UserMapper;
import com.snwolf.api.properties.JwtProperties;
import com.snwolf.api.service.IUserService;
import com.snwolf.api.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final JwtProperties jwtProperties;

    @Override
    public UserLoginVO login(UserLoginDTO userLoginDTO) {
        String password = userLoginDTO.getPassword();
        String encryptPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = lambdaQuery()
                .eq(User::getUserAccount, userLoginDTO.getUserAccount())
                .eq(User::getPassword, encryptPassword)
                .one();
        if(user == null){
            throw new UserLoginException("用户名或密码错误");
        }
        UserLoginVO userLoginVO = new UserLoginVO();
        BeanUtils.copyProperties(user, userLoginVO);
        Long userId = user.getId();
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, userId);
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
        userLoginVO.setToken(token);
        return userLoginVO;
    }

    @Override
    public Long register(UserRegisterDTO userRegisterDTO) {
        if(!userRegisterDTO.getPassword().equals(userRegisterDTO.getCheckPassword())){
            throw new CheckPasswordException("两次数日的密码不一致");
        }
        String encryptPassword = DigestUtils.md5DigestAsHex(userRegisterDTO.getPassword().getBytes());
        User user = User.builder()
                .userAccount(userRegisterDTO.getUserAccount())
                .password(encryptPassword)
                .build();
        save(user);
        return user.getId();
    }
}
