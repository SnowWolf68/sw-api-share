package com.snwolf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snwolf.domain.dto.URLKeyDTO;
import com.snwolf.domain.entity.InterfaceInfo;
import com.snwolf.domain.entity.User;
import com.snwolf.domain.entity.UserInterfaceInfo;
import com.snwolf.mapper.UserInterfaceInfoMapper;
import com.snwolf.service.IInterfaceInfoService;
import com.snwolf.service.IUserInterfaceInfoService;
import com.snwolf.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo> implements IUserInterfaceInfoService {

    private final IInterfaceInfoService interfaceInfoService;

    private final UserInterfaceInfoMapper userInterfaceInfoMapper;

    private final IUserService userService;

    @Override
    public void addCnt(URLKeyDTO urlKeyDTO) {
        String url = urlKeyDTO.getUrl();
        String accessKey = urlKeyDTO.getAccessKey();
        String secretKey = urlKeyDTO.getSecretKey();
        InterfaceInfo interfaceInfo = interfaceInfoService.lambdaQuery()
                .eq(InterfaceInfo::getUrl, url)
                .one();
        Long interfaceId = interfaceInfo.getId();
        User user = userService.lambdaQuery()
                .eq(User::getAccessKey, accessKey)
                .eq(User::getSecretKey, secretKey)
                .one();
        Long userId = user.getId();
        userInterfaceInfoMapper.deduckCnt(userId, interfaceId);
    }
}