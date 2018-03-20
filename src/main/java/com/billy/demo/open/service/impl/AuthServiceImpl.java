package com.billy.demo.open.service.impl;

import com.alibaba.fastjson.JSON;
import com.billy.demo.open.constants.OpenConstants;
import com.billy.demo.open.entity.wx.WxVerifyMsg;
import com.billy.demo.open.service.AuthService;
import com.billy.demo.open.util.XmlStringUtil;
import com.billy.demo.open.util.wx.WXBizMsgCrypt;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author liulei@bshf360.com
 * @since 2018-03-15 17:43
 */
@Service
public class AuthServiceImpl implements AuthService {
    private static Logger LOG = Logger.getLogger(AuthServiceImpl.class);

    private String componentVerifyTicket = null;

    @Override
    public void handleAuthEvent(String stringFromRequest, WxVerifyMsg wxVerifyMsg) {
        LOG.info("stringFromRequest: " + stringFromRequest);
        LOG.info("wxVerifyMsg: " + JSON.toJSONString(wxVerifyMsg));
        String msgSignature = wxVerifyMsg.getMsg_signature();
        String timeStamp = wxVerifyMsg.getTimestamp();
        String nonce = wxVerifyMsg.getNonce();
        String decryptedPostData = WXBizMsgCrypt.decryptMsg(msgSignature, timeStamp, nonce, stringFromRequest);
        LOG.info("msg after decryption: " + decryptedPostData);
        Map<String, String> xmlMap = XmlStringUtil.xmlString2Map(decryptedPostData);
        LOG.info("map-data from wx: " + xmlMap);
        if (xmlMap == null) {
            return;
        }
        String infoType = xmlMap.get(OpenConstants.AUTH_INFOTYPE);

        // 授权与取消授权， ticket推送
        if (StringUtils.isNotEmpty(infoType)) {
            if (OpenConstants.INFOTYPE_COMPONENT_VERIFY_TICKET.equals(infoType)) {
                componentVerifyTicket = xmlMap.get("componentVerifyTicket");
                LOG.debug("componentVerifyTicket was flushed into redis.");
            }
        }

        LOG.debug("newest componentVerifyTicket: " + componentVerifyTicket);
    }


    @Override
    public String getComponentVerifyTicket() {
        return componentVerifyTicket;
    }



}

