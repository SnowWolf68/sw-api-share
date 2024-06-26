package com.snwolf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snwolf.common.domain.dto.URLKeyDTO;
import com.snwolf.domain.entity.InterfaceInfo;
import com.snwolf.domain.entity.User;
import com.snwolf.domain.entity.UserInterfaceInfo;
import com.snwolf.exception.InterfaceInfoException;
import com.snwolf.exception.LeftCntNotEnoughException;
import com.snwolf.mapper.UserInterfaceInfoMapper;
import com.snwolf.service.IInterfaceInfoService;
import com.snwolf.service.IUserInterfaceInfoService;
import com.snwolf.service.IUserService;
import com.snwolf.service.UserInterfaceInfoService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@DubboService(interfaceClass = UserInterfaceInfoService.class)
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo> implements IUserInterfaceInfoService{

    private final IInterfaceInfoService interfaceInfoService;

    private final UserInterfaceInfoMapper userInterfaceInfoMapper;

    private final IUserService userService;

    @Override
    public void addCnt(URLKeyDTO urlKeyDTO) throws Exception{
        String url = urlKeyDTO.getUrl();
        String accessKey = urlKeyDTO.getAccessKey();
        String secretKey = urlKeyDTO.getSecretKey();
        InterfaceInfo interfaceInfo = interfaceInfoService.lambdaQuery()
                .eq(InterfaceInfo::getUrl, url)
                .one();
        if(interfaceInfo == null){
            throw new InterfaceInfoException("接口信息不存在");
        }
        Long interfaceId = interfaceInfo.getId();
        User user = userService.lambdaQuery()
                .eq(User::getAccessKey, accessKey)
                .eq(User::getSecretKey, secretKey)
                .one();
        Long userId = user.getId();
        int affectedRows = userInterfaceInfoMapper.deduckCnt(userId, interfaceId);
        if(affectedRows == 0){
            throw new LeftCntNotEnoughException("剩余调用次数不足");
        }
    }
}
