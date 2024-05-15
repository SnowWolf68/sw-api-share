package com.snwolf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snwolf.domain.dto.URLKeyDTO;
import com.snwolf.domain.entity.UserInterfaceInfo;

public interface IUserInterfaceInfoService extends IService<UserInterfaceInfo> {
    void addCnt(URLKeyDTO urlKeyDTO);
}
