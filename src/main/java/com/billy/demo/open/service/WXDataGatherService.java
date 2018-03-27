package com.billy.demo.open.service;

/**
 * 微信接口
 *
 * @author liulei@bshf360.com
 * @since 2018-03-16 16:25
 */
public interface WXDataGatherService {
    /**
     * 获取一个新的预授权码
     */
    String getNewPreAuthCode();

}
