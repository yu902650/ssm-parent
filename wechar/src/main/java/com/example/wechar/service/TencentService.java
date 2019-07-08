package com.example.wechar.service;

import com.alibaba.fastjson.JSON;
import com.example.wechar.domain.common.Storage;
import com.example.wechar.domain.entity.wechar.NavigationButton;
import com.example.wechar.domain.entity.wechar.NavigationCondition;
import com.example.wechar.domain.entity.wechar.NavigationMenu;
import com.example.wechar.domain.entity.wechar.Ticket;
import com.example.wechar.util.*;
import org.bson.Document;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service("tencentService")
public class TencentService {

    public String verification(String signature, String timestamp, String nonce, String echostr) {
        String interactiveToken = PropertiesUtil.get("TOKEN");
        List<String> params = new ArrayList<>();
        params.add(interactiveToken);
        params.add(timestamp);
        params.add(nonce);

        SystemUtil.sortList(params);
        StringBuffer paramsStr = new StringBuffer();
        Iterator<String> iter = params.iterator();
        while (iter.hasNext())
            paramsStr.append(iter.next());
        String sig = SystemUtil.SHA1(paramsStr.toString());

        if (sig.equals(signature))
            return echostr;
        return null;
    }

    /**
     * @param msgSignature
     * @param timestamp
     * @param nonce
     * @param postData
     * @return
     */
    public String accept(String msgSignature, String timestamp, String nonce, String postData) {

        //初始化加密/解密
        WXBizMsgCrypt bmc = new WXBizMsgCrypt();
        // 解密微信服务器发送的XML
        String xmlMsg = bmc.decryptMsg(msgSignature, timestamp, nonce, postData);

        System.err.println("xmlMsg:" + xmlMsg);
        //把返回的XML转成MAP
        Map<String, Object> map = SystemUtil.xmlToMap(xmlMsg);
        String pushText = "success";
            /*String toUser = SystemUtil.readXml(xmlMsg, "ToUserName");     // gh_7811f038e0ee
            String fromUser = SystemUtil.readXml(xmlMsg, "FromUserName"); // of_jKs9qS7JZODvd0JMBxApC5QAw
			String msgType = SystemUtil.readXml(xmlMsg, "MsgType");       // "event"
			String event = SystemUtil.readXml(xmlMsg, "Event");			  // "CLICK"
			String eventKey = SystemUtil.readXml(xmlMsg, "EventKey");	  // "CLICK"

			String content = SystemUtil.readXml(xmlMsg, "Content");*/

        String toUser = String.valueOf(map.get("ToUserName"));     // gh_7811f038e0ee
        String fromUser = String.valueOf(map.get("FromUserName"));
        String msgType = String.valueOf(map.get("MsgType"));
        String event = String.valueOf(map.get("Event"));
        String eventKey = String.valueOf(map.get("EventKey"));
        String fromUserName = String.valueOf(fromUser);      //用户Openid

        if ("event".equals(msgType)) {

            /**
             * 首次关注时推送
             */
            if ("subscribe".equals(event)) {

                Document msgDoc = new Document()
                        .append("title", "首次关注")
                        .append("desc", "可是...老板说了,你关注一次小编的工资涨5毛")
                        .append("picUrl", "http://testlib.pang118.com/img/center.png")
                        .append("url", "http://cloud.pang118.com/wx/service/customer.html");

                pushText = subscribeXml("image", msgDoc, toUser, fromUser);
            } else if ("SCAN".equals(event)) {

            }
            /**
             * 导航调出扫码功能
             */
            else if ("scancode_waitmsg".equals(event)) {

            } else if ("CLICK".equals(event)) {
                if ("big_shot_secret".equals(eventKey)) {
                    Document msgDoc = new Document()
                            .append("title", "秀恩爱的都弱爆了！这才是暖到哭的瞬间！！")
                            .append("desc", "这世界，总有人在笨拙地爱着你，想把全部的温柔都给你，它不会说话，却真诚纯粹，总在那么一瞬间，让你整颗心都变得柔软起来。")
                            .append("picUrl", "https://mmbiz.qpic.cn/mmbiz_png/0Y1GYLafc6G8Zzx9V0ricMiaJTPrEibhACmIeBd3z6tlwc2E31JyA2LRicaIss3Yd7wGg8f5iaqjHNNbOgK8HibqaicXA/0?wx_fmt=png")
                            .append("url", "http://mp.weixin.qq.com/s/eQDIfsP-9OI0Mj-b8x9JLg");

                    pushText = subscribeXml("news", msgDoc, toUser, fromUser);
                }
            }


        } else if ("text".equals(msgType)) {
            //接收用户文本信息
            String content = String.valueOf(map.get("Content"));
            // 信息加密
            if ("商城".equals(content)) {
                //跳转的url
                String url = "http://wx.ajimi.cn/weixin?c=shop&p=index";
                pushText = subscribeXml("news", null, toUser, fromUser);
            } else {
            }
        }

        String pushMsg = bmc.encryptMsg(pushText, timestamp, nonce);
        return pushMsg;

    }

    public String deleteMenu() {
        try {
            TencentUtil.deleteMenu();
            return "deleteMenu success";
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 生成菜单
     *
     * @return
     */
    public String createMenu() {
//         final String URI = "http://wechar.ajimi.cn/view";
         final String URI = "http://wechar.8090top.cn/view";
        try {

            // 总菜单
            NavigationMenu navigationMenu = new NavigationMenu();
            // 菜单
            NavigationButton navigationButton = null;
            List<NavigationButton> navigationButtonList = new ArrayList<>();
            List<NavigationButton> childList = null;
            NavigationButton childButton = null;
            // 第一个菜单
            navigationButton = new NavigationButton();
            childList = new LinkedList<>();

            childButton = new NavigationButton();
            childButton.setName("设备列表");
            childButton.setType("view");
            childButton.setUrl(URI+ "?c=device-status&p=index");
            childList.add(childButton);

            childButton = new NavigationButton();
            childButton.setName("运营数据");
            childButton.setType("view");
            childButton.setUrl(URI + "?c=statistic&p=summary");
            childList.add(childButton);

            childButton = new NavigationButton();
            childButton.setName("补货");
            childButton.setType("view");
            childButton.setUrl(URI + "?c=supplement&p=index");
            childList.add(childButton);

            navigationButton.setName("微运营");
            navigationButton.setSub_button(childList);
            navigationButtonList.add(navigationButton);

            //第二个按钮
            navigationButton = new NavigationButton();
            childList = new LinkedList<>();

//            childButton = new NavigationButton();
//            childButton.setName("退款订单");
//            childButton.setType("view");
//            childButton.setUrl(URI + "?c=order&p=refund_order");
//            childList.add(childButton);

//            childButton = new NavigationButton();
//            childButton.setName("仓库管理");
//            childButton.setType("view");
//            childButton.setUrl(URI+"?c=warehouse&p=index");
//            childList.add(childButton);

            childButton = new NavigationButton();
            childButton.setName("无屏设备管理");
            childButton.setType("view");
            childButton.setUrl(URI+  "?c=noscreen&p=entry");
            childList.add(childButton);

            navigationButton.setName("微销售");
            navigationButton.setSub_button(childList);
            navigationButtonList.add(navigationButton);

            // 第三个按钮
            navigationButton = new NavigationButton();
            childList = new LinkedList<>();

            childButton = new NavigationButton();
            childButton.setName("个人中心");
            childButton.setType("view");
            childButton.setUrl(URI + "?c=personal&p=profile");
            childList.add(childButton);

            navigationButton.setName("微服务");
            navigationButton.setSub_button(childList);
            navigationButtonList.add(navigationButton);
            // 总
            navigationMenu.setButton(navigationButtonList);
            String menuJson = SystemUtil.toJson(navigationMenu, true);
            TencentUtil.deleteMenu();
            TencentUtil.createMenu(menuJson);
            return menuJson;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 根据票务(ticket)生成一个序列
     */
    public String signature(String noncestr, String timestamp, String url) {

        try {

            String jsapi_ticket = ticket();
            List<String> params = new ArrayList<>();
            params.add("&jsapi_ticket=" + jsapi_ticket);
            params.add("&noncestr=" + noncestr);
            params.add("&timestamp=" + timestamp);
            params.add("&url=" + url);

            SystemUtil.sortList(params);
            StringBuffer paramsStr = new StringBuffer();
            Iterator<String> iter = params.iterator();
            while (iter.hasNext()) {
                paramsStr.append(iter.next());
            }

            System.err.println("signature:" + JSON.toJSONString(params));
            String signature = SystemUtil.SHA1(paramsStr.toString());
            return signature;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据token生成一个票务(ticket)
     *
     * @return 返回ticket的字符串
     */
    private String ticket() {
        try {
            String token = TencentUtil.currentToken();
            String updateTime = Storage.ticket.getUpdate_date();
            Integer expiresIn = Storage.ticket.getExpires_in() == null ? 0 : Storage.ticket.getExpires_in();
            String currentTime = SystemUtil.currentTime();

            long diff = SystemUtil.distanceTime(updateTime, currentTime);
            if (diff >= expiresIn) {

                String url = String.format(PropertiesUtil.get("WX_TICKET_URI"), token);
                String responseText = SystemUtil.responseText(url);

                Ticket ticket = SystemUtil.fromJson(responseText, Ticket.class);
                ticket.setUpdate_date(currentTime);
                Storage.ticket = ticket;
            }

            return Storage.ticket.getTicket();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    // 微信公众号推送信息
    public String subscribeXml(String responseType, Document msgDoc, String fromUser, String toUser) {


        StringBuffer res = new StringBuffer();
        String timeMillis = String.valueOf(System.currentTimeMillis());
        res.append("<xml>");
        res.append("	<ToUserName><![CDATA[" + toUser + "]]></ToUserName>");
        res.append("	<FromUserName><![CDATA[" + fromUser + "]]></FromUserName>");
        res.append("	<CreateTime>" + timeMillis + "</CreateTime>");
        res.append("	<MsgType><![CDATA[" + responseType + "]]></MsgType>");

        if ("text".equals(responseType)) {
            res.append("<Content><![CDATA[" + msgDoc.getString("url") + "]]></Content>");
        }
        else if ("image".equals(responseType)) {

            res.append("<Image>");
            res.append("<MediaId><![CDATA[bemA7eSXEANnFi8ZGPnGBDAi2Y1yLDj3uKU9qoB4akw]]></MediaId>");
            res.append("</Image>");

        }  else if ("news".equals(responseType)) {

            res.append("<ArticleCount>2</ArticleCount>");
            res.append("<Articles>");
            res.append("<item>");
            res.append("	<Title><![CDATA[" + msgDoc.getString("title") + "]]></Title> ");
            res.append("	<Description><![CDATA[" + msgDoc.getString("desc") + "]]></Description>");
            res.append("	<PicUrl><![CDATA[" + msgDoc.getString("picUrl") + "]]></PicUrl>");
            res.append("	<Url><![CDATA[http://cloud.pang118.com/wx/service/customer.html]]></Url>");
            res.append("</item>");

            res.append("<item>");
            res.append("	<Title><![CDATA[无人零售发展现状,无人新零售怎么做]]></Title> ");
            res.append("	<Description><![CDATA[2017年是无人零售行业的分水岭。在这一年中，受到冷遇十多年的自动贩卖机突然变得炙手可热，以真格、IDG、创新工场、经纬中国为首的顶级VC开始疯狂向该领域开拔;同年]]></Description>");
            res.append("	<PicUrl><![CDATA[http://testlib.pang118.com/img/new_1.jpg]]></PicUrl>");
            res.append("	<Url><![CDATA[http://cloud.pang118.com/wx/service/customer.html]]></Url>");
            res.append("</item>");

            res.append("<item>");
            res.append("	<Title><![CDATA[无人值守 “码”上就走,无人零售研究报告]]></Title> ");
            res.append("	<Description><![CDATA[提到无人零售，大家瞬间想到的都是各种黑科技，如物联网、人工智能、大数据、传感技术等等，投入金额大的如亚马逊的无人超市需几千万美金，小的如办公室无人货架，一个点位几百块钱即可]]></Description>");
            res.append("	<PicUrl><![CDATA[http://testlib.pang118.com/img/new_2.jpg]]></PicUrl>");
            res.append("	<Url><![CDATA[http://cloud.pang118.com/wx/service/customer.html]]></Url>");
            res.append("</item>");

            res.append("</Articles>");
        }
        res.append("</xml> ");
        return res.toString();

    }

    public Map<String, Object> payvalidate(String noncestr, String openid, String no, String fee, String createip) {

        String appId = PropertiesUtil.get("APPID");
        String mchId = PropertiesUtil.get("MCHID");
        String notifyUrl = PropertiesUtil.get("WX_NOTIFY_UEL");
        String apkKey = PropertiesUtil.get("APIKEY");

        Map<String, Object> signParem = new HashMap<>();
        signParem.put("appid", appId);
        signParem.put("attach", "支付测试");
        signParem.put("body", "JSAPI支付测试");
        signParem.put("mch_id", mchId);
        signParem.put("nonce_str", noncestr);
        signParem.put("notify_url", notifyUrl);
        signParem.put("openid", openid);
        signParem.put("out_trade_no", no);
        signParem.put("spbill_create_ip", createip);
        signParem.put("total_fee", fee);
        signParem.put("trade_type", "JSAPI");

        String stringA = SystemUtil.mapToUri(signParem);
        String sign = TencentUtil.paySigin(stringA, apkKey);

        signParem.put("sign", sign);

        String payParem = SystemUtil.createXML(signParem);
        InputStream inputStream = SystemUtil.getInputStream("https://api.mch.weixin.qq.com/pay/unifiedorder", payParem, "POST");
        String xml = SystemUtil.inputStreamToStr(inputStream);

        //服务器返回的支付认证信息
        String prepay_id = SystemUtil.readXml(xml, "/xml/prepay_id");
        Map<String, Object> respTemp = new HashMap<>();

        System.err.println("prepay_id:" + prepay_id);

        respTemp.put("appId", appId);
        respTemp.put("timeStamp", String.valueOf(System.currentTimeMillis()));
        respTemp.put("nonceStr", noncestr);
        respTemp.put("package", "prepay_id=" + prepay_id);
        respTemp.put("signType", "MD5");

        String sginTemp = SystemUtil.mapToUri(respTemp);
        String paySgin = TencentUtil.paySigin(sginTemp, apkKey);
        respTemp.put("paySign", paySgin);
        return respTemp;
    }

    /**
     * 发放裂变红包
     *
     * @param openid
     * @return
     */
    public Document groupRedpack(String noncestr, String openid, String no) {

        String appId = PropertiesUtil.get("APPID");
        String mchId = PropertiesUtil.get("MCHID");
        String apkKey = PropertiesUtil.get("APIKEY");

        Map<String, Object> signParem = new HashMap<>();

        signParem.put("act_name", "抢红包,好友一起分享");
        signParem.put("amt_type", "ALL_RAND");
        signParem.put("mch_billno", no);
        signParem.put("mch_id", mchId);
        signParem.put("nonce_str", noncestr);
        signParem.put("re_openid", openid);
        signParem.put("remark", "微信公众号");
        signParem.put("send_name", "正点");
        signParem.put("total_amount", "300");
        signParem.put("total_num", "3");
        signParem.put("wishing", "恭喜发财");
        signParem.put("wxappid", appId);

        String stringA = SystemUtil.mapToUri(signParem);

        String sign = TencentUtil.paySigin(stringA, apkKey);
        signParem.put("sign", sign);

        Document result = null;
        try {
            String URI = PropertiesUtil.get("WX_SEND_GROUPREDPACK");
            String resp = MoneyUtils.doSendMoney(URI, SystemUtil.createXML(signParem));
            System.err.println(resp);
            if (!StringUtil.isEmpty(resp)) {
                Map<String, Object> map = SystemUtil.xmlToMap(resp);
                result = Document.parse(JSON.toJSONString(map));
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public String cashCoupon(String noncestr, String openid, String no, String createip) {

        String appId = PropertiesUtil.get("APPID");
        String mchId = PropertiesUtil.get("MCHID");
        String apkKey = PropertiesUtil.get("APIKEY");

        Map<String, Object> signParem = new HashMap<>();

        signParem.put("nonce_str", noncestr);
        signParem.put("mch_billno", no);
        signParem.put("mch_id", mchId);
        signParem.put("wxappid", appId);
        signParem.put("send_name", "正点");
        signParem.put("re_openid", openid);
        signParem.put("total_amount", "100");
        signParem.put("total_num", "10");
        signParem.put("wishing", "恭喜发财");
        signParem.put("client_ip", createip);
        signParem.put("act_name", "新年红包");
        signParem.put("remark", "新年红包");

        String stringA = SystemUtil.mapToUri(signParem);
        String sign = TencentUtil.paySigin(stringA, apkKey);

        signParem.put("sign", sign);

        String result = "";
        try {
            result = MoneyUtils.doSendMoney("https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack",
                    SystemUtil.createXML(signParem));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 创建个性化菜单
     *
     * @return
     */
    public String addConditional() {
        try {

            // 总菜单
            NavigationMenu navigationMenu = new NavigationMenu();
            // 菜单
            NavigationButton navigationButton;
            List<NavigationButton> navigationButtonList;

            List<NavigationButton> childList;
            NavigationButton childButton;

            // 第一个菜单
            navigationButtonList = new LinkedList<>();
            navigationButton = new NavigationButton();
            childList = new LinkedList<>();

            childButton = new NavigationButton();
            childButton.setName("最时髦");
            childButton.setType("view");
            childButton.setUrl("https://app.ajimi.cn/wechar?c=tips&p=tips&cid=2");
            childList.add(childButton);

            childButton = new NavigationButton();
            childButton.setName("乐生活");
            childButton.setType("view");
            childButton.setUrl("https://app.ajimi.cn/wechar?c=tips&p=tips&cid=4");
            childList.add(childButton);

            navigationButton.setName("真有用");
            navigationButton.setSub_button(childList);
            navigationButtonList.add(navigationButton);

            //第二个按钮
            navigationButton = new NavigationButton();
            childList = new LinkedList<>();

            childButton = new NavigationButton();
            childButton.setName("最热单品");
            childButton.setType("view");
            childButton.setUrl("https://app.ajimi.cn/wechar?c=shop&p=index");
            childList.add(childButton);

            navigationButton.setName("微推荐");
            navigationButton.setSub_button(childList);
            navigationButtonList.add(navigationButton);

            // 第三个按钮
            navigationButton = new NavigationButton();
            childList = new LinkedList<>();

            childButton = new NavigationButton();
            childButton.setName("分享有礼");
            childButton.setType("view");
            childButton.setUrl("https://app.ajimi.cn/wechar?c=activity&p=result");
            childList.add(childButton);

            childButton = new NavigationButton();
            childButton.setName("个人信息");
            childButton.setType("view");
            childButton.setUrl("https://app.ajimi.cn/wechar?c=joke&p=personal");
            childList.add(childButton);

            childButton = new NavigationButton();
            childButton.setName("红包初始化");
            childButton.setKey("init_lottery");
            childButton.setType("scancode_waitmsg");
            childList.add(childButton);

            /*childButton = new NavigationButton();
            childButton.setName("扫码推事件");
            childButton.setKey("rselfmenu_0_0");
            childButton.setType("scancode_push");
            childList.add(childButton);*/

            navigationButton.setName("一起玩");
            navigationButton.setSub_button(childList);
            navigationButtonList.add(navigationButton);

            // 总
            navigationMenu.setButton(navigationButtonList);

            NavigationCondition matchrule = new NavigationCondition();
            matchrule.setTag_id("106");
            navigationMenu.setMatchrule(matchrule);

            String menuJson = SystemUtil.toJson(navigationMenu, true);
            TencentUtil.addConditional(menuJson);
            return menuJson;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    /***
     *
     * @param title
     * @param thumbMediaId
     * @param author
     * @param digest
     * @param showCoverPic
     * @param content
     * @param contentSourceUrl
     * @param needPpenComment
     * @param onlyFansCanComment
     * @return
     */
    public String materialAdd(String title,
                              String thumbMediaId,
                              String author,
                              String digest,
                              String showCoverPic,
                              String content,
                              String contentSourceUrl,
                              Integer needPpenComment,
                              Integer onlyFansCanComment) {

        String token = TencentUtil.currentToken();
        Map<String, List<Document>> paramMap = new HashMap<>();
        Document doc = new Document()
                .append("title", title)
                .append("thumb_media_id", thumbMediaId)
                .append("author", author)
                .append("digest", digest)
                .append("show_cover_pic", showCoverPic)
                .append("content", content)
                .append("content_source_url", contentSourceUrl)
                .append("need_open_comment", needPpenComment)
                .append("only_fans_can_comment", onlyFansCanComment);

        List<Document> docList = new ArrayList<>();
        docList.add(doc);
        paramMap.put("articles", docList);

        String resp = SystemUtil.toJson(paramMap);

        String materialUrl = PropertiesUtil.get("WX_MATERIAL_ADD");
        String uri = String.format(materialUrl, token);
        InputStream inputStream = SystemUtil.getInputStream(uri, resp, "POST");

        String s = SystemUtil.inputStreamToStr(inputStream);
        System.err.println("" + s);
        return s;
    }

    public String massSend() {
        String token = TencentUtil.currentToken();
        List<String> toUser = new ArrayList<>();
        toUser.add("of_jKs_DZLHefpVvJ_F3D6H1JKm0");
        toUser.add("of_jKs9qS7JZODvd0JMBxApC5QAw");
        toUser.add("of_jKswdQaIHewEYcghn9zFzicl4");

        Document param = new Document();
        param.append("touser", toUser);
        param.append("mpnews", new Document("media_id", "VoMMYPtYQRAAHOztJ0ntFcteZ42XvZiYh6v4CjRQK8Y"));
        param.append("msgtype", "mpnews");
        param.append("send_ignore_reprint", 0);

        String resp = SystemUtil.toJson(param);

        String massSendUrl = PropertiesUtil.get("WX_MASS_SEND");
        String uri = String.format(massSendUrl, token);
        InputStream inputStream = SystemUtil.getInputStream(uri, resp, "POST");
        String s = SystemUtil.inputStreamToStr(inputStream);
        System.err.println("" + s);
        return s;
    }

    /**
     * 发送状态模板信息
     */
    public void statisticalOfflineEquipment(String title, String deviceName, String remark, String toUser) {
        String currentTime = DatetimeUtil.currentTime();
        String currentToken = TencentUtil.currentToken();
        String tempId = PropertiesUtil.get("offline.equipment.tempid");

        Document respDoc = new Document();
        respDoc.append("touser", toUser);
        respDoc.append("template_id", tempId);
        respDoc.append("url", "https://app.ajimi.cn/wechar?c=activity&p=lottery_details");

        Document data = new Document();
        data.append("first", new Document("value", title).append("color", "#000000"));
        data.append("keyword1", new Document("value", deviceName).append("color", "#0000DD"));
        data.append("keyword2", new Document("value", currentTime).append("color", "#000000"));
        respDoc.append("data", data);

        System.err.println(respDoc.toJson());
        String URI = String.format(PropertiesUtil.get("WX_SEND_TEMPLATE"), currentToken);
        if (toUser != null && !"".equals(toUser)) {
            InputStream inputStream = SystemUtil.getInputStream(URI, respDoc.toJson(), "POST");
            String res = SystemUtil.inputStreamToStr(inputStream);
            System.err.println("" + res);
        }
    }

 /*   public static void main(String[] args) {
        String httpUrl = "http://apis.baidu.com/weixinxi/extracter/extracter";
        String httpArg = "url=http://mp.weixin.qq.com/s/TZ5O-Eshy9FBEWh44wC1gg";
        String jsonResult = request(httpUrl, httpArg);
        System.out.println(jsonResult);

    }*/

    /**
     * 请求接口
     *
     * @param httpArg :参数
     * @return 返回结果
     */
    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey", "0146d3781e5a3e560a83be60e75cb4a3");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
