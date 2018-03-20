package com.billy.demo.open.util;

import java.util.ResourceBundle;

/**
 * read data from src/main/resources/mp.properties
 */
public class ServerPropertiesUtil {

    /**
     * 公众平台上，开发者设置的EncodingAESKey
     */
    private static String encodingAesKey;
    /**
     * 公众平台上，开发者设置的token
     */
    private static String token;
    /**
     * 公众平台appid
     */
    private static String appId;
    /**
     * secret
     */
    private static String secret;
    /**
     * baseUrl
     */
    private static String baseUrl;

    static {
        ResourceBundle rb = ResourceBundle.getBundle("server");
        token = rb.getString("token");
        appId = rb.getString("appId");
        secret = rb.getString("secret");
        encodingAesKey = rb.getString("encodingAesKey");
        baseUrl = rb.getString("base.url");
    }

    public static String getEncodingAesKey() {
        return encodingAesKey;
    }

    public static String getToken() {
        return token;
    }

    public static String getAppId() {
        return appId;
    }

    public static String getSecret() {
        return secret;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }


}
