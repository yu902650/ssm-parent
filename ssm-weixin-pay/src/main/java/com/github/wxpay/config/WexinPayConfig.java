package com.github.wxpay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述：微信接口请求相关统一参数
 * <p>
 * #
 * </p>
 * User: WYL Date: 2017/10/14 ProjectName:mq-parent Version: 1.0
 */
@Component
public class WexinPayConfig {

    @Value("http://47.96.178.229:7777/mq-api/notify/wx/callback")
    private String NOTIFY_URL;


    @Value("http://47.96.178.229:7777/mq-api/notify/wx/callback")
    private String MQ_NOTIFY_URL;

    @Value("NATIVE")
    private String TRADE_TYPE;

    @Value("10000")
    private int HTTP_CONNECT_TIME_OUT;

    @Value("10000")
    private int HTTP_READ_TIME_OUT;

    public String getNOTIFY_URL() {
        return NOTIFY_URL;
    }

    public void setNOTIFY_URL(String NOTIFY_URL) {
        this.NOTIFY_URL = NOTIFY_URL;
    }

    public String getTRADE_TYPE() {
        return TRADE_TYPE;
    }

    public void setTRADE_TYPE(String TRADE_TYPE) {
        this.TRADE_TYPE = TRADE_TYPE;
    }

    public int getHTTP_CONNECT_TIME_OUT() {
        return HTTP_CONNECT_TIME_OUT;
    }

    public void setHTTP_CONNECT_TIME_OUT(int HTTP_CONNECT_TIME_OUT) {
        this.HTTP_CONNECT_TIME_OUT = HTTP_CONNECT_TIME_OUT;
    }

    public int getHTTP_READ_TIME_OUT() {
        return HTTP_READ_TIME_OUT;
    }

    public void setHTTP_READ_TIME_OUT(int HTTP_READ_TIME_OUT) {
        this.HTTP_READ_TIME_OUT = HTTP_READ_TIME_OUT;
    }

    public String getMQ_NOTIFY_URL() {
        return MQ_NOTIFY_URL;
    }

    public void setMQ_NOTIFY_URL(String MQ_NOTIFY_URL) {
        this.MQ_NOTIFY_URL = MQ_NOTIFY_URL;
    }
}
