package com.snwolf.api.controller;

import com.snwolf.api.annotation.CheckRole;
import com.snwolf.api.domain.dto.IdDTO;
import com.snwolf.api.domain.dto.InterfaceInfoDTO;
import com.snwolf.api.domain.dto.InterfaceInvokeDTO;
import com.snwolf.api.exception.InterfaceStatusException;
import com.snwolf.api.exception.ParamErrorException;
import com.snwolf.api.result.Result;
import com.snwolf.api.service.IInterfaceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interfaceInfo")
@RequiredArgsConstructor
public class InterfaceInfoController {

    private final IInterfaceInfoService interfaceInfoService;

    @CheckRole(role = "admin")
    @PostMapping("/online")
    public Result<Boolean> onlineInterface(@RequestBody IdDTO idDTO) throws ParamErrorException, InterfaceStatusException {
        boolean result = interfaceInfoService.online(idDTO);
        return Result.success(result);
    }

    @CheckRole(role = "admin")
    @PostMapping("/offline")
    public Result<Boolean> offlineInterface(@RequestBody IdDTO idDTO) throws ParamErrorException, InterfaceStatusException {
        boolean result = interfaceInfoService.offline(idDTO);
        return Result.success(result);
    }

    @PostMapping("/invoke")
    public Result<Object> invokeInterface(@RequestBody InterfaceInvokeDTO interfaceInvokeDTO) throws ParamErrorException, InterfaceStatusException {
        Object result = interfaceInfoService.invokeInterface(interfaceInvokeDTO);
        return Result.success(result);
    }

    @CheckRole(role = "admin")
    @PostMapping("/add")
    public Result<Long> addInterface(@RequestBody InterfaceInfoDTO interfaceInfoDTO) throws ParamErrorException {
        Long id = interfaceInfoService.addInterface(interfaceInfoDTO);
        return Result.success(id);
    }
}
