package com.snwolf;

import com.snwolf.client.TestClient;
import com.snwolf.properties.ClientProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class TestClientConfig {

    private final ClientProperties clientProperties;

    @Bean
    public TestClient testClient() {
        TestClient testClient = new TestClient(clientProperties.getAccessKey(), clientProperties.getSecretKey());
        log.info("testClient创建, testClient: {}", testClient);
        log.info("当前配置的accessKey: {}, secretKey: {}", clientProperties.getAccessKey(), clientProperties.getSecretKey());
        return testClient;
    }
}
