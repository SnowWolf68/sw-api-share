package com.snwolf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.snwolf.mapper")
public class InterfaceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InterfaceApplication.class, args);
    }
}