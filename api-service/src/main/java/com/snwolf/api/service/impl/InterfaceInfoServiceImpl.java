package com.snwolf.api.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snwolf.api.context.BaseContext;
import com.snwolf.api.domain.dto.IdDTO;
import com.snwolf.api.domain.dto.InterfaceInfoDTO;
import com.snwolf.api.domain.dto.InterfaceInvokeDTO;
import com.snwolf.api.domain.entity.InterfaceInfo;
import com.snwolf.api.domain.entity.User;
import com.snwolf.api.exception.InterfaceStatusException;
import com.snwolf.api.exception.ParamErrorException;
import com.snwolf.api.mapper.InterfaceInfoMapper;
import com.snwolf.api.service.IInterfaceInfoService;
import com.snwolf.api.service.IUserService;
import com.snwolf.client.TestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo> implements IInterfaceInfoService {

    private final TestClient testClient;

    private final IUserService userService;

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

    @Override
    public boolean offline(IdDTO idDTO) throws ParamErrorException, InterfaceStatusException {
        // 判断接口是否存在
        if(idDTO == null || idDTO.getId() == null || idDTO.getId() < 0){
            throw new ParamErrorException("参数错误");
        }
        // 判断数据库中接口状态是否为开启
        InterfaceInfo oldInterfaceInfo = getById(idDTO.getId());
        if(oldInterfaceInfo == null){
            throw new InterfaceStatusException("接口不存在");
        }
        if(oldInterfaceInfo.getStatus() != 1){
            throw new InterfaceStatusException("不能上线已经上线的接口");
        }
        InterfaceInfo interfaceInfo = InterfaceInfo.builder()
                .id(idDTO.getId())
                .status(0)
                .build();
        return updateById(interfaceInfo);
    }

    @Override
    public Object invokeInterface(InterfaceInvokeDTO interfaceInvokeDTO) throws ParamErrorException, InterfaceStatusException {
        Long id = interfaceInvokeDTO.getId();
        if(id == null){
            throw new ParamErrorException("接口id不能为空");
        }
        InterfaceInfo interfaceInfo = getById(id);
        if(interfaceInfo == null){
            throw new ParamErrorException("接口不存在");
        }
        if(interfaceInfo.getStatus() == 0){
            throw new InterfaceStatusException("接口已关闭");
        }
        Long userId = BaseContext.getCurrentId();
        User user = userService.getById(userId);
        String accessKey = user.getAccessKey();
        String secretKey = user.getSecretKey();
        // 这里的TestClient不是sdk中的, 因为sdk中的TestClient会对secretKey加密, 而这里我们本来从数据库中得到的secretKey就已经是加密后的了
        com.snwolf.api.client.TestClient testClient = new com.snwolf.api.client.TestClient(accessKey, secretKey);
        com.snwolf.po.User user1 = JSONUtil.toBean(interfaceInvokeDTO.getUserRequestParam(), com.snwolf.po.User.class);
        return testClient.getNameByPostWithBody(user1);
    }

    @Override
    public Long addInterface(InterfaceInfoDTO interfaceInfoDTO) throws ParamErrorException {
        if(interfaceInfoDTO == null){
            throw new ParamErrorException("接口信息不能为空");
        }
        InterfaceInfo interfaceInfo = BeanUtil.copyProperties(interfaceInfoDTO, InterfaceInfo.class);
        save(interfaceInfo);
        return interfaceInfo.getId();
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
