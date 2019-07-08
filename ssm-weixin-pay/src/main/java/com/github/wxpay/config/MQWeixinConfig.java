package com.github.wxpay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述：米泉微信支付相关信息
 * <p>
 * #
 * </p>
 * User: WYL Date: 2017/10/14 ProjectName:mq-parent Version: 1.0
 */
@Component
public class MQWeixinConfig {

    @Value("${weixin.MQ.WEIXIN_APP_ID}")
    private String APP_ID;

    @Value("${weixin.MQ.WEIXIN_MERCHANT_ID}")
    private String MERCHANT_ID;

    @Value("${weixin.MQ_WEIXIN_API_SERCRET}")
    private String API_SERCRET;

    @Value("${weixin.MQ_WEIXIN_APP_SERCRET}")
    private String APP_SERCRET;

    @Value("${weixin.MQ.WEIXIN_NOTIFY_URL}")
    private String NOTIFY_URL;

    public String getAPP_ID() {
        return APP_ID;
    }

    public void setAPP_ID(String APP_ID) {
        this.APP_ID = APP_ID;
    }

    public String getMERCHANT_ID() {
        return MERCHANT_ID;
    }

    public void setMERCHANT_ID(String MERCHANT_ID) {
        this.MERCHANT_ID = MERCHANT_ID;
    }

    public String getAPI_SERCRET() {
        return API_SERCRET;
    }

    public void setAPI_SERCRET(String API_SERCRET) {
        this.API_SERCRET = API_SERCRET;
    }

    public String getNOTIFY_URL() {
        return NOTIFY_URL;
    }

    public void setNOTIFY_URL(String NOTIFY_URL) {
        this.NOTIFY_URL = NOTIFY_URL;
    }

    public String getAPP_SERCRET() {
        return APP_SERCRET;
    }

    public void setAPP_SERCRET(String APP_SERCRET) {
        this.APP_SERCRET = APP_SERCRET;
    }
}
