package com.snwolf.gateway.client;

import com.snwolf.gateway.domain.dto.AuthDTO;
import com.snwolf.gateway.domain.dto.URLKeyDTO;
import com.snwolf.gateway.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "interface-client", url = "http://localhost:8082")
public interface InterfaceClient {

    @PostMapping("/api/name/checkAuth")
    boolean chechAuth(@RequestBody AuthDTO authDTO);

    @PostMapping("/api/userInterfaceInfo/addCnt")
    Result addCnt(@RequestBody URLKeyDTO urlKeyDTO);

}
