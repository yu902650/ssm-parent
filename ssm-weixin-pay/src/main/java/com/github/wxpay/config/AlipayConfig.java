package com.github.wxpay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述：支付宝接口请求相关统一参数
 * <p>
 * #
 * </p>
 * User: WYL Date: 2017/10/14 ProjectName:mq-parent Version: 1.0
 */
@Component
public class AlipayConfig {

    @Value("${alipay.URL}")
    private String URL;

    @Value("${alipay.FORMAT}")
    private String FORMAT;

    @Value("${alipay.CHARSET}")
    private String CHARSET;

    @Value("${alipay.SIGN_TYPE}")
    private String SIGN_TYPE;

    @Value("${alipay.NOTIFY_URL}")
    private String NOTIFY_URL;

    @Value("${alipay.MQ.NOTIFY_URL}")
    private String MQ_NOTIFY_URL;


    @Value("${alipay.TIMEOUT_EXPRESS}")
    private String TIMEOUT_EXPRESS;

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
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

    public String getTIMEOUT_EXPRESS() {
        return TIMEOUT_EXPRESS;
    }

    public void setTIMEOUT_EXPRESS(String TIMEOUT_EXPRESS) {
        this.TIMEOUT_EXPRESS = TIMEOUT_EXPRESS;
    }

    public String getMQ_NOTIFY_URL() {
        return MQ_NOTIFY_URL;
    }

    public void setMQ_NOTIFY_URL(String MQ_NOTIFY_URL) {
        this.MQ_NOTIFY_URL = MQ_NOTIFY_URL;
    }
}
