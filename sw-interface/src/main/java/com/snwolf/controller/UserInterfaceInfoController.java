package com.snwolf.controller;

import com.snwolf.common.domain.dto.URLKeyDTO;
import com.snwolf.common.result.Result;
import com.snwolf.service.IUserInterfaceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userInterfaceInfo")
@RequiredArgsConstructor
public class UserInterfaceInfoController {

    private final IUserInterfaceInfoService userInterfaceInfoService;

    @PostMapping("/addCnt")
    public Result addCnt(@RequestBody URLKeyDTO urlKeyDTO) throws Exception {
        userInterfaceInfoService.addCnt(urlKeyDTO);
        return Result.success();
    }

}
