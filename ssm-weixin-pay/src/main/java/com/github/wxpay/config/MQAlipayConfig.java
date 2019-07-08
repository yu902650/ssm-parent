package com.github.wxpay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述：米泉支付宝支付相关信息
 * <p>
 * #
 * </p>
 * User: WYL Date: 2017/10/14 ProjectName:mq-parent Version: 1.0
 */
@Component
public class MQAlipayConfig {

    @Value("${alipay.URL}")
    private String URL;

    @Value("${alipay.MQ.APP_ID}")
    private String APP_ID;

    @Value("${alipay.MQ.APP_PRIVATE_KEY}")
    private String APP_PRIVATE_KEY;

    @Value("${alipay.FORMAT}")
    private String FORMAT;

    @Value("${alipay.CHARSET}")
    private String CHARSET;

    @Value("${alipay.MQ.ALIPAY_PUBLIC_KEY}")
    private String ALIPAY_PUBLIC_KEY;

    @Value("${alipay.MQ.NOTIFY_URL}")
    private String NOTIFY_URL;

    @Value("${alipay.SIGN_TYPE}")
    private String SIGN_TYPE;

    @Value("${alipay.MQ.PARTNER}")
    private String PARTNER;

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getAPP_ID() {
        return APP_ID;
    }

    public void setAPP_ID(String APP_ID) {
        this.APP_ID = APP_ID;
    }

    public String getAPP_PRIVATE_KEY() {
        return APP_PRIVATE_KEY;
    }

    public void setAPP_PRIVATE_KEY(String APP_PRIVATE_KEY) {
        this.APP_PRIVATE_KEY = APP_PRIVATE_KEY;
    }

    public String getFORMAT() {
        return FORMAT;
    }

    public void setFORMAT(String FORMAT) {
        this.FORMAT = FORMAT;
    }

    public String getCHARSET() {
        return CHARSET;
    }

    public void setCHARSET(String CHARSET) {
        this.CHARSET = CHARSET;
    }

    public String getALIPAY_PUBLIC_KEY() {
        return ALIPAY_PUBLIC_KEY;
    }

    public void setALIPAY_PUBLIC_KEY(String ALIPAY_PUBLIC_KEY) {
        this.ALIPAY_PUBLIC_KEY = ALIPAY_PUBLIC_KEY;
    }

    public String getSIGN_TYPE() {
        return SIGN_TYPE;
    }

    public void setSIGN_TYPE(String SIGN_TYPE) {
        this.SIGN_TYPE = SIGN_TYPE;
    }

    public String getNOTIFY_URL() {
        return NOTIFY_URL;
    }

    public void setNOTIFY_URL(String NOTIFY_URL) {
        this.NOTIFY_URL = NOTIFY_URL;
    }

    public String getPARTNER() {
        return PARTNER;
    }

    public void setPARTNER(String PARTNER) {
        this.PARTNER = PARTNER;
    }
}
