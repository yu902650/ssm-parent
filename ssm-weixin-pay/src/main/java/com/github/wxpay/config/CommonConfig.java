package com.github.wxpay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述：
 * <p>
 * 通用配置
 * </p>
 * User: WYL Date: 2017/10/23 ProjectName:mq-parent Version: 1.0
 */
@Component
public class CommonConfig {

//    @Value("${MQ.QR_CODE_PATH_PRI}")
//    private String qrCodePathPri;
//
//    @Value("${MQ.QR_CODE_URL_PRI}")
//    private String qrCodeUrlPri;

    @Value("${MQ.QR_CODE_FORMAT}")
    private String qrCodeFormat;

    @Value("${MQ.QR_CODE_WIDTH}")
    private int qrCodeWidth;

    @Value("${MQ.QR_CODE_HEIGHT}")
    private int qrCodeHeight;

//    public String getQrCodePathPri() {
//        return qrCodePathPri;
//    }
//
//    public void setQrCodePathPri(String qrCodePathPri) {
//        this.qrCodePathPri = qrCodePathPri;
//    }
//
//    public String getQrCodeUrlPri() {
//        return qrCodeUrlPri;
//    }
//
//    public void setQrCodeUrlPri(String qrCodeUrlPri) {
//        this.qrCodeUrlPri = qrCodeUrlPri;
//    }

    public String getQrCodeFormat() {
        return qrCodeFormat;
    }

    public void setQrCodeFormat(String qrCodeFormat) {
        this.qrCodeFormat = qrCodeFormat;
    }

    public int getQrCodeWidth() {
        return qrCodeWidth;
    }

    public void setQrCodeWidth(int qrCodeWidth) {
        this.qrCodeWidth = qrCodeWidth;
    }

    public int getQrCodeHeight() {
        return qrCodeHeight;
    }

    public void setQrCodeHeight(int qrCodeHeight) {
        this.qrCodeHeight = qrCodeHeight;
    }
}
