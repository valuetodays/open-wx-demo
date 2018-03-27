package com.billy.demo.open.controller;

import com.billy.demo.open.entity.wx.WxVerifyMsg;
import com.billy.demo.open.service.AuthService;
import com.billy.demo.open.service.WXDataGatherService;
import com.billy.demo.open.task.ComponentAccessTokenTask;
import com.billy.demo.open.util.ServerPropertiesUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URLEncoder;

/**
 * 处理微信发过来的认证事件推送
 *
 * @author liulei@bshf360.com
 * @since 2018-03-15 15:44
 */
@Controller
@RequestMapping("auth")
public class AuthController {
    private static Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;
    @Autowired
    private ComponentAccessTokenTask componentAccessTokenTask;
    @Autowired
    private WXDataGatherService wxDataGatherService;

    /**
     * 接收微信发过来的认证事件推送并处理
     * @param request request
     * @param wxVerifyMsg 微信发送过来的参数之一，另一部分数据在request.getInputStrame()中
     * @return 永远返回success
     */
    @RequestMapping("authEventReceived")
    @ResponseBody
    public String authEventReceived(HttpServletRequest request, WxVerifyMsg wxVerifyMsg) {
        LOG.debug("receive auth message from wx");
        try {
            StringWriter stringFromRequest = new StringWriter();
            IOUtils.copy(request.getInputStream(), stringFromRequest, "utf-8");
            authService.handleAuthEvent(stringFromRequest.toString(), wxVerifyMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }


    /**
     * 获取Component_Verify_Ticket
     */
    @RequestMapping("getComponentVerifyTicket")
    @ResponseBody
    public String getComponentVerifyTicket() {
        return authService.getComponentVerifyTicket();
    }

    /**
     * 获取Component_Access_Token
     */
    @RequestMapping("getComponentAccessToken")
    @ResponseBody
    public String getComponentAccessToken() {
        return componentAccessTokenTask.getComponentAccessToken();
    }

    /**
     * 刷新Component_Access_Token
     */
    @RequestMapping("refreshComponentAccessToken")
    @ResponseBody
    public String refreshComponentAccessToken() {
        componentAccessTokenTask.doComponentAccessTokenRefresh();
        return componentAccessTokenTask.getComponentAccessToken();
    }

    /**
     * 重定向到微信进行授权
     * @param response response
     */
    @RequestMapping("redirectToAuth")
    public void redirectToAuth(HttpServletResponse response) throws IOException {
        String redirectUri = ServerPropertiesUtil.getBaseUrl() + "/auth/authorized";
        String encodeRedirectUri = URLEncoder.encode(redirectUri, "utf-8");

        String url = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid="
                + ServerPropertiesUtil.getAppId() + "&pre_auth_code=" + wxDataGatherService.getNewPreAuthCode()
                + "&redirect_uri=" + encodeRedirectUri + "&auth_type=1";
        response.sendRedirect(url);
    }


    /**
     * 微信公众平台完成授权后的回调请求
     *
     */
    @RequestMapping("authorized")
    @ResponseBody
    public String authorized() {
        LOG.debug("wx notify");
        return "success";
    }

}
