package com.snwolf.gateway.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class URLKeyDTO {
    private String url;
    private String accessKey;
    private String secretKey;
}
