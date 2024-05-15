package com.snwolf.controller;

import com.snwolf.domain.dto.AuthDTO;
import com.snwolf.po.User;
import com.snwolf.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/name")
@Slf4j
@RequiredArgsConstructor
public class NameController {

    private final IUserService userService;

    @GetMapping()
    public String getNameByGet(String name, HttpServletRequest request){
        String accessKey = request.getHeader("accessKey");
        String secretKey = request.getHeader("sign");
        if(!chechAuth(accessKey, secretKey)){
            log.error("鉴权失败");
            return "";
        }
        return name;
    }

    @PostMapping
    public String getNameByPost(@RequestParam String name, HttpServletRequest request){
        String accessKey = request.getHeader("accessKey");
        String secretKey = request.getHeader("sign");
        if(!chechAuth(accessKey, secretKey)){
            log.error("鉴权失败");
            return "";
        }
        return name;
    }

    @PostMapping("/body")
    public String getNameByPostWithBody(@RequestBody User user, HttpServletRequest request){
        String accessKey = request.getHeader("accessKey");
        String secretKey = request.getHeader("sign");
        if(!chechAuth(accessKey, secretKey)){
            log.error("鉴权失败");
            return "";
        }
        return user.getName();
    }

    @PostMapping("/checkAuth")
    private boolean chechAuth(@RequestBody AuthDTO authDTO) {
        String accessKey = authDTO.getAccessKey();
        String secretKey = authDTO.getSecretKey();
        return chechAuth(accessKey, secretKey);
    }

    private boolean chechAuth(String accessKey, String secretKey) {
        log.info("请求中的accessKey: {}, secretKey: {}", accessKey, secretKey);
        com.snwolf.domain.entity.User user = userService.lambdaQuery()
                .eq(com.snwolf.domain.entity.User::getAccessKey, accessKey)
                .eq(com.snwolf.domain.entity.User::getSecretKey, secretKey)
                .one();
        if(user == null){
            return false;
        }
        return true;
    }
}
