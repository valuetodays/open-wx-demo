package com.billy.demo.open.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * jsticket的签名和预下单支付的签名
 *
 * @author liulei@bshf360.com
 * @since 2017-10-20 13:36
 */
public class WxSignUtil {
    private WxSignUtil() { }

    /**
     * jsticket的签名
     *
     * @param jsapi_ticket jsapi_ticket
     * @param nonce_str nonce_str
     * @param timestamp timestamp
     * @param url url
     */
    public static String getJsSignature(String jsapi_ticket, String nonce_str, String timestamp, String url) {
        //注意这里参数名必须全部小写，且必须有序
        String string1 = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            return byteToHex(crypt.digest());
        }  catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取支付的sign[js使用]
     *
     * @param appid appid
     * @param paykey paykey
     * @param prepay_id prepay_id
     * @param timestamp timestamp
     * @param noncestr noncestr
     */
    public static String getPaySign(String appid, String paykey, String prepay_id,
                              String timestamp, String noncestr) {
        //对所有需要传入的参数加上appkey作一次key＝value字典序的排序
        String keyvaluestring = "appId=" + appid + "&nonceStr=" + noncestr
                + "&package=prepay_id=" + prepay_id
                + "&signType=MD5&timeStamp=" + timestamp + "&key=" + paykey;
        String sign = MD5Util.MD5Encode(keyvaluestring, "utf-8");
        return sign.toUpperCase();
    }

    /**
     * 二维码支付时接口中使用的sign
     *
     * @param packageParams params
     * @param paykey paykey
     * @return sign
     */
    public static String getPaykeySign(SortedMap<String, String> packageParams, String paykey) {
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, String>> es = packageParams.entrySet();
        Iterator<Map.Entry<String, String>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            String k = entry.getKey();
            String v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k)
                    && !"paykey".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + paykey);
        String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
        return sign;
    }

    /**
     * map转化成xml格式
     */
    public static String mapToXML(SortedMap<String, String> map) {
        String xmlstr = "<xml>";

        /* 开始对map进行解析 */
        if (map == null)
            throw new NullPointerException("map 数据为空,不能解析!");
        Set<Map.Entry<String, String>> set = map.entrySet();
        Iterator<Map.Entry<String, String>> records = set.iterator();

        while (records.hasNext()) {
            Map.Entry<String, String> entry = records.next();
            if (entry != null && entry.getKey() != null && entry.getValue() != null)
                xmlstr += "<" + entry.getKey() + ">" + entry.getValue() + "</" + entry.getKey() + ">";
        }
        xmlstr += "</xml>";
        return xmlstr;
    }

    /**
     * 创建 nonce_str
     */
    public static String createNonceStr() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 创建 timestamp
     */
    public static String createTimestamp() {
        return Long.toString(System.currentTimeMillis());
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }


}
