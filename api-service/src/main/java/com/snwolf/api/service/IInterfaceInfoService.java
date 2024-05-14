package com.snwolf.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snwolf.api.domain.dto.IdDTO;
import com.snwolf.api.domain.entity.InterfaceInfo;
import com.snwolf.api.exception.InterfaceStatusException;
import com.snwolf.api.exception.ParamErrorException;

public interface IInterfaceInfoService extends IService<InterfaceInfo> {
    boolean online(IdDTO idDTO) throws ParamErrorException, InterfaceStatusException;

    boolean offline(IdDTO idDTO) throws ParamErrorException, InterfaceStatusException;
}