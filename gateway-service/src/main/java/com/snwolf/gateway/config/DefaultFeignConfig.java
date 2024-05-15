package com.snwolf.gateway.config;

import feign.Logger;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

@Configuration
public class DefaultFeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    @Bean
    public HttpMessageConverters httpMessageConverters(){
        HttpMessageConverter<?> formHttpMessageConverter = new FormHttpMessageConverter();
        return new HttpMessageConverters(formHttpMessageConverter);
    }
}
