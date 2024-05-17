package com.snwolf.service;

import com.snwolf.domain.dto.URLKeyDTO;
import com.snwolf.exception.InterfaceInfoException;

public interface UserInterfaceInfoService {
    void addCnt(URLKeyDTO urlKeyDTO) throws InterfaceInfoException;
}
