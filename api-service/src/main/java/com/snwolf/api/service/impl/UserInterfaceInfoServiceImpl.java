package com.snwolf.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snwolf.api.domain.entity.UserInterfaceInfo;
import com.snwolf.api.mapper.UserInterfaceInfoMapper;
import com.snwolf.api.service.IUserInterfaceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo> implements IUserInterfaceInfoService {

}
