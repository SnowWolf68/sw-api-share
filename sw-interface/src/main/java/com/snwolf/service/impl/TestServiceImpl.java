package com.snwolf.service.impl;

import com.snwolf.service.TestService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class TestServiceImpl implements TestService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
