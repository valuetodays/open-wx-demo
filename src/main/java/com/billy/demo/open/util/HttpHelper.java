package com.billy.demo.open.util;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class HttpHelper {
    private HttpHelper() {
    }

    /**
     * 发送http请求
     *
     * @param xmlData XML格式的数据
     * @param url     请求地址
     */
    public static Element doPostString(String xmlData, String url) {
        try {
            String strmemberResult = HttpClientUtil.doPostString(url, xmlData);
            if (StringUtils.isEmpty(strmemberResult))
                return null;
            Document doc = DocumentHelper.parseText(strmemberResult);
            Element root = doc.getRootElement();
            return root;
        } catch (Exception e) {
            return null;
        }
    }


}
