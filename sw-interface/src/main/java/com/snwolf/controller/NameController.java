package com.snwolf.controller;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.snwolf.po.User;
import com.snwolf.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/name")
@Slf4j
public class NameController {

    private final String TEST_ACCESS_KEY = "testAccessKey";

    private final String TEST_SECRET_KEY = "testSecretKey";

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

    private boolean chechAuth(String accessKey, String secretKey) {
        log.info("请求中的accessKey: {}, secretKey: {}", accessKey, secretKey);
        return TEST_ACCESS_KEY.equals(accessKey) && SignUtil.getSignWithSecretKey(TEST_SECRET_KEY).equals(secretKey);
    }
}
