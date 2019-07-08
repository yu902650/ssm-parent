package com.ssm.common.aliconfig;

/**
 * @author bo bo
 * @date 2019/6/25 10:13
 * @desc
 */

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;

/**
 * 阿里支付平台功能类
 *
 * @author gaojunming
 *
 */
public class Alipay {
    // 初始化alipayClient对象
    public static AlipayClient alipayClient = new DefaultAlipayClient(
            Config.URL, Config.APP_ID, Config.APP_PRIVATE_KEY, "json",
            Config.CHARSET, Config.ALIPAY_PUBLIC_KEY);

    /**
     * wap支付
     *
     * @return
     * @throws AlipayApiException
     */
    public static AlipayTradeWapPayResponse pay(String content) throws AlipayApiException {
        // 创建API对应的request
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
        // 在公共参数中设置回跳和通知地址(应用提供给支付宝的请求路径),沙箱模式中不起作用(不知道是不是这个原因,支付宝技术客服告诉我正式上线后就没问题)
        alipayRequest.setReturnUrl("http://xxx.com/xxx");
        alipayRequest.setNotifyUrl("http://xxx.com/xxx");
        // 填充业务参数
        alipayRequest.setBizContent(content);
        AlipayTradeWapPayResponse alipayResponse = alipayClient
                .pageExecute(alipayRequest);
        return alipayResponse;

    }
}