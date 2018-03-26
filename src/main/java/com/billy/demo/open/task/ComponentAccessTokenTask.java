package com.billy.demo.open.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.billy.demo.open.service.AuthService;
import com.billy.demo.open.util.HttpClientUtil;
import com.billy.demo.open.util.ServerPropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("componentAccessTokenTask")
public class ComponentAccessTokenTask {
    private static Logger LOG = Logger.getLogger(ComponentAccessTokenTask.class);
    private static ComponentAccessTokenEntity ACCESS_TOKEN = new ComponentAccessTokenEntity(null);
    private static long OFFSET_TIME = 110*60*1000; // 110分钟

    @Autowired
    private AuthService authService;

    public String getComponentAccessToken() {
        String accessToken = ACCESS_TOKEN.getAccessToken();
        if (accessToken == null) {
            doComponentAccessTokenRefresh();
        }
        return ACCESS_TOKEN.getAccessToken();
    }

    /**
     * 定时更新component_access_token，策略是每2分钟刷新一下调试任务，
     * 当上个component_access_token的创建时间在110分钟之后时，就重新刷新
     */
    @Scheduled(cron = "0 0/2 * * * *") // 每隔2分钟开始工作
    public void refresh() {
        String accessToken = ACCESS_TOKEN.getAccessToken();
        long createAt = ACCESS_TOKEN.getCreateAt();

        if (accessToken == null || System.currentTimeMillis() - createAt > OFFSET_TIME) {
            doComponentAccessTokenRefresh();
        }
    }

    /**
     * 该方法置为public会有其它风险
     */
    public void doComponentAccessTokenRefresh() {
        LOG.info("component_access_token is going to expire, refreshing....");
        String url = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
        Map<String, String> params = new HashMap<>();
        params.put("component_appid", ServerPropertiesUtil.getAppId());
        params.put("component_appsecret", ServerPropertiesUtil.getSecret());
        params.put("component_verify_ticket", authService.getComponentVerifyTicket());
        String doGet = HttpClientUtil.doPostString(url, JSON.toJSONString(params));

        LOG.info("result: " + doGet);
        if (StringUtils.isNotBlank(doGet)) {
            JSONObject accessTokenObject = JSON.parseObject(doGet);
            Object object = accessTokenObject.get("component_access_token");
            String accessTokenStr = ((object == null) ? null : object.toString());
            put(accessTokenStr);
        }
    }


    private synchronized void put(String accessTokenStr) {
        if (accessTokenStr == null) {
            return ;
        }

        ACCESS_TOKEN = new ComponentAccessTokenEntity(accessTokenStr);
        LOG.info("component_access_token["+accessTokenStr+"] was refreshed.");
    }

    private static class ComponentAccessTokenEntity {
        final private String accessToken;
        final private long createAt;

        public ComponentAccessTokenEntity(String accessToken) {
            this.accessToken = accessToken;
            createAt = System.currentTimeMillis();
        }

        public String getAccessToken() {
            return accessToken;
        }
        public long getCreateAt() {
            return createAt;
        }
    }

}
