package com.github.wxpay;

import com.github.wxpay.config.Detect;
import com.github.wxpay.config.WexinPayConfig;
import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.util.WXPayDomainSimpleImpl;
import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author bo bo
 * @date 2019/6/28 15:40
 * @desc
 */
public class MyConfig implements WXPayConfig {

    WexinPayConfig wexinPayConfig;

    public String getAppID() {
        return "wx1ea76dad79c8db28";
    }

    public String getMchID() {
        return "1490274322";
    }

    public String getKey() {
        return "shjy6461980905710576745175147065";
    }

    public InputStream getCertStream() {
        String cerContent = "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";
        if (Detect.notEmpty(cerContent)) {
            return new ByteArrayInputStream(Base64.decodeBase64(cerContent.getBytes()));
        }
        return null;
    }

    public int getHttpConnectTimeoutMs() {
        return 10000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    public IWXPayDomain getWXPayDomain() {
        return WXPayDomainSimpleImpl.instance();
    }

    public boolean shouldAutoReport() {
        return false;
    }

    public int getReportWorkerNum() {
        return 6;
    }

    public int getReportQueueMaxSize() {
        return 10000;
    }

    public int getReportBatchSize() {
        return 10;
    }
}
