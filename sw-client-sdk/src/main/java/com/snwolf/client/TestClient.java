package com.snwolf.client;


import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.snwolf.po.User;
import com.snwolf.util.SignUtil;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
@NoArgsConstructor
public class TestClient {

    private String urlPrifix = "http://localhost:8081/api/name";

    private String accessKey;

    private String secretKey;

    public TestClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    private HashMap<String, String> setHeaders(){
        HashMap<String, String> headers = new HashMap<>();
        headers.put("accessKey", accessKey);
        // headers.put("secretKey", secretKey);
        // secretKey不能放在请求头中明文传输, 需要加密生成签名
        headers.put("sign", SignUtil.getSignWithSecretKey(secretKey));
        return headers;
    }

//    private String getSign(String secretKey) {
//        Digester digester = new Digester(DigestAlgorithm.SHA256);
//        return digester.digestHex(secretKey);
//    }


    public String getNameByGet(String name){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", "jack");
        HttpResponse response = HttpRequest.get(urlPrifix)
                .addHeaders(setHeaders())
                .form(paramMap)
                .execute();
        return response.body();
    }

    public String getNameByPost(String name){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", "jack");
        HttpResponse response = HttpRequest.post(urlPrifix)
                .addHeaders(setHeaders())
                .form(paramMap)
                .execute();
        return response.body();
    }

    public String getNameByPostWithBody(String name){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String jsonStr = JSONUtil.toJsonStr(paramMap);
        HttpResponse response = HttpRequest.post(urlPrifix + "/body")
                .addHeaders(setHeaders())
                .body(jsonStr)
                .execute();
        log.info("response code: {}", response.getStatus());
        if(response.isOk()){
            return response.body();
        }else {
            log.error("状态码不正常, 状态码: {}", response.getStatus());
            return "";
        }
    }

    public String getNameByPostWithBody(User user){
        String jsonStr = JSONUtil.toJsonStr(user);
        HttpResponse response = HttpRequest.post(urlPrifix + "/body")
                .addHeaders(setHeaders())
                .body(jsonStr)
                .execute();
        log.info("response code: {}", response.getStatus());
        if(response.isOk()){
            return response.body();
        }else {
            log.error("状态码不正常, 状态码: {}", response.getStatus());
            return "";
        }
    }
}
