package com.snwolf.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "sw.client")
@Component
public class ClientProperties {
    private String accessKey;
    private String secretKey;
}
