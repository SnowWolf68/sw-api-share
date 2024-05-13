package com.snwolf.client;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
//@RequiredArgsConstructor
class TestClientTest {

    @Autowired
    private TestClient testClient;

    @Test
    void testAutowire(){
        log.info("testClient: {}", testClient);
    }

    @Test
    void testInterface(){
        TestClient testClient = new TestClient();
        log.info(testClient.getNameByGet("jack"));
        log.info(testClient.getNameByPost("jack"));
        log.info(testClient.getNameByPostWithBody("jack"));
    }

    @Test
    void testInterfaceWithAuth(){
        TestClient testClient = new TestClient("testAccessKey", "testSecretKey");
        String respStr = testClient.getNameByPostWithBody("jack");
        if(StrUtil.isBlank(respStr)){
            log.error("出错了");
        }else{
            log.info(respStr);
        }
    }

    @Test
    void testInterfaceWithSDK(){
        String respStr = testClient.getNameByPostWithBody("jack");
        if(StrUtil.isBlank(respStr)){
            log.error("出错了");
        }else{
            log.info(respStr);
        }
        respStr = testClient.getNameByGet("jack");
        if(StrUtil.isBlank(respStr)){
            log.error("出错了");
        }else{
            log.info(respStr);
        }
        respStr = testClient.getNameByPost("jack");
        if(StrUtil.isBlank(respStr)){
            log.error("出错了");
        }else{
            log.info(respStr);
        }
    }
}