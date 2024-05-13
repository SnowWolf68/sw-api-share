package com.snwolf.util;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public class SignUtil {
    public static String getSignWithSecretKey(String secretKey){
        Digester digester = new Digester(DigestAlgorithm.SHA256);
        return digester.digestHex(secretKey);
    }
}
