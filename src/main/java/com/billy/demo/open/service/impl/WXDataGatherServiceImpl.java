package com.billy.demo.open.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.billy.demo.open.service.WXDataGatherService;
import com.billy.demo.open.task.ComponentAccessTokenTask;
import com.billy.demo.open.util.HttpClientUtil;
import com.billy.demo.open.util.ServerPropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liulei@bshf360.com
 * @since 2018-03-16 16:25
 */
@Service
public class WXDataGatherServiceImpl implements WXDataGatherService {
    private static Logger LOG = Logger.getLogger(WXDataGatherServiceImpl.class);

    @Autowired
    private ComponentAccessTokenTask componentAccessTokenTask;

    @Override
    public String getNewPreAuthCode() {
        String url = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token="
                + componentAccessTokenTask.getComponentAccessToken();
        Map<String, String> params = new HashMap<>();
        params.put("component_appid", ServerPropertiesUtil.getAppId());

        String result = HttpClientUtil.doPostString(url, JSON.toJSONString(params));
        LOG.info("result: " + result);
        if (StringUtils.isNotBlank(result)) {
            JSONObject accessTokenObject = JSON.parseObject(result);
            Object object = accessTokenObject.get("pre_auth_code");
            return ((object == null) ? null : object.toString());
        }
        return null;
    }


}
