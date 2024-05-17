package com.snwolf.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class URLKeyDTO implements Serializable {
    private String url;
    private String accessKey;
    private String secretKey;
}
