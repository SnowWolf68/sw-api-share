package com.snwolf.gateway.filter;

import com.snwolf.gateway.client.InterfaceClient;
import com.snwolf.gateway.domain.dto.AuthDTO;
import com.snwolf.gateway.domain.dto.URLKeyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.net.URI;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    private final InterfaceClient interfaceClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String accessKey = headers.get("accessKey").get(0);
        String sign = headers.get("sign").get(0);
        AuthDTO authDTO = AuthDTO.builder()
                .accessKey(accessKey)
                .secretKey(sign)
                .build();
        boolean checkResult = interfaceClient.chechAuth(authDTO);
        if(!checkResult){
            log.error("鉴权失败");
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.setComplete();
        }
        log.info("鉴权成功");
        // TODO 调用接口的调用次数 + 1
        URI uri = exchange.getRequest().getURI();
        log.info("uri: {}", uri);
        try {
            log.info("url: {}", uri.toURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try {
            URLKeyDTO urlKeyDTO = URLKeyDTO.builder()
                    .url(uri.toURL().toString())
                    .accessKey(accessKey)
                    .secretKey(sign)
                    .build();
            interfaceClient.addCnt(urlKeyDTO);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // 其余所有filter都没有拦截这个请求, 此时才能将当前接口的调用次数 + 1
        // -1表示放在过滤器链的最后执行
        return -1;
    }
}
