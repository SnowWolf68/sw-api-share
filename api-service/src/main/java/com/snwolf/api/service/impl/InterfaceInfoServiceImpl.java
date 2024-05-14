package com.snwolf.api.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snwolf.api.domain.dto.IdDTO;
import com.snwolf.api.domain.entity.InterfaceInfo;
import com.snwolf.api.exception.InterfaceStatusException;
import com.snwolf.api.exception.ParamErrorException;
import com.snwolf.api.mapper.InterfaceInfoMapper;
import com.snwolf.api.service.IInterfaceInfoService;
import com.snwolf.client.TestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo> implements IInterfaceInfoService {

    private final TestClient testClient;

    @Override
    public boolean online(IdDTO idDTO) throws ParamErrorException, InterfaceStatusException {
        // 判断接口是否存在
        if(idDTO == null || idDTO.getId() == null || idDTO.getId() < 0){
            throw new ParamErrorException("参数错误");
        }
        // 判断数据库中接口状态是否为关闭
        InterfaceInfo oldInterfaceInfo = getById(idDTO.getId());
        if(oldInterfaceInfo == null){
            throw new InterfaceStatusException("接口不存在");
        }
        if(oldInterfaceInfo.getStatus() != 0){
            throw new InterfaceStatusException("不能上线已经上线的接口");
        }
        // 判断接口是否正常
        if(!checkInterfaceStatus()){
            throw new InterfaceStatusException("接口异常");
        }
        InterfaceInfo interfaceInfo = InterfaceInfo.builder()
                .id(idDTO.getId())
                .status(1)
                .build();
        return updateById(interfaceInfo);
    }

    private boolean checkInterfaceStatus() throws InterfaceStatusException {
        String name = testClient.getNameByPostWithBody("jack");
        if(StrUtil.isBlank(name)){
            return false;
        }else{
            return true;
        }
    }
}
