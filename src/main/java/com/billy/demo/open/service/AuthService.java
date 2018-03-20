package com.billy.demo.open.service;

import com.billy.demo.open.entity.wx.WxVerifyMsg;

/**
 * @author liulei@bshf360.com
 * @since 2018-03-15 17:43
 */
public interface AuthService {
    /**
     * 处理微信发过来的请求
     * @param stringFromRequest
     * @param wxVerifyMsg
     */
    void handleAuthEvent(String stringFromRequest, WxVerifyMsg wxVerifyMsg);

    String getComponentVerifyTicket();

}
