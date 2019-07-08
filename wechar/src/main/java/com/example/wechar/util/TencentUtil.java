package com.example.wechar.util;

import com.alibaba.fastjson.JSON;
import com.example.wechar.domain.common.Storage;
import com.example.wechar.domain.entity.wechar.Token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class TencentUtil {


    /**
     * 修改标签
     *
     * @return
     */
    public static String updateTag(String tagId, String tagName) {

        try {
            String accessTagUri = PropertiesUtil.get("WX_UPDATE_TAG");
            String currentToken = TencentUtil.currentToken();
            String requestUrl = String.format(accessTagUri, currentToken);
            String tagModel = "{\"tag\":{\"id\":%s,\"name\":\"%s\"}}";
            InputStream inputStream = SystemUtil.getInputStream(requestUrl, String.format(tagModel, tagId, tagName), "POST");
            return SystemUtil.inputStreamToStr(inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 获取公众号已创建的标签
     *
     * @return
     */
    public static String createTag(String tagName) {

        try {
            String accessTagUri = PropertiesUtil.get("WX_CREATE_TAG");
            String currentToken = TencentUtil.currentToken();
            String requestUrl = String.format(accessTagUri, currentToken);
            String tagModel = "{\"tag\":{\"name\":\"%s\" }}";

            InputStream inputStream = SystemUtil.getInputStream(requestUrl, String.format(tagModel, tagName), "POST");
            return SystemUtil.inputStreamToStr(inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String deleteTag(String tagId) {

        try {
            String accessTagUri = PropertiesUtil.get("WX_DELETE_TAG");
            String currentToken = TencentUtil.currentToken();
            String requestUrl = String.format(accessTagUri, currentToken);
            String tagModel = "{\"tag\":{\"id\":%s}}";

            InputStream inputStream = SystemUtil.getInputStream(requestUrl, String.format(tagModel, tagId), "POST");
            return SystemUtil.inputStreamToStr(inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 获取公众号已创建的标签
     *
     * @return
     */
    public static String tagAccess() {

        try {
            String accessTagUri = PropertiesUtil.get("WX_ACCESS_TAG");
            String currentToken = TencentUtil.currentToken();
            String requestUrl = String.format(accessTagUri, currentToken);

            InputStream inputStream = SystemUtil.getInputStream(requestUrl, "GET");
            return SystemUtil.inputStreamToStr(inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前的token
     *
     * @return
     */
    public static String currentToken() {

        String tokenUri = PropertiesUtil.get("WX_TOKEN_URI");
        String appId = PropertiesUtil.get("APPID");
        String secret = PropertiesUtil.get("SECRET");

        try {
            String updateTime = Storage.token.getUpdateDate();
            Integer expiresIn = Storage.token.getExpiresIn() == null ? 0 : Storage.token.getExpiresIn();
            String currentTime = SystemUtil.currentTime();
            long diff = SystemUtil.distanceTime(updateTime, currentTime);
            if (diff >= expiresIn) {

                String url = String.format(tokenUri, "client_credential", appId, secret);
                String responseText = SystemUtil.responseText(url);

                System.err.println("currentToken:" + responseText);
                Token token = SystemUtil.fromJson(responseText, Token.class);
                token.setUpdateDate(currentTime);
                Storage.token = token;
            }
            return Storage.token.getAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 创建菜单
     *
     * @param menu 菜单实例
     * @return 0表示成功, 其他值表示失败
     * @throws UnsupportedEncodingException
     */
    public static void createMenu(String menu) {
        try {
            String createMenuUri = PropertiesUtil.get("WX_CREATE_MENU");
            String currentToken = TencentUtil.currentToken();
            String requestUrl = String.format(createMenuUri, currentToken);
            InputStream inputStream = SystemUtil.getInputStream(requestUrl, menu, "POST");

            System.err.println(JSON.toJSONString(menu));
            System.err.println(SystemUtil.inputStreamToStr(inputStream));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 删除菜单
     *
     * @return
     */
    public static void deleteMenu() {

        String deleteMenuUri = PropertiesUtil.get("WX_DELETE_MENU");
        String currentToken = TencentUtil.currentToken();
        // 拼装创建菜单的url
        String URI = String.format(deleteMenuUri, currentToken);
        // 调用接口创建菜单
        InputStream inputStream = SystemUtil.getInputStream(URI, "POST");

    }

    /**
     * 获取当前菜单
     *
     * @return
     */
    public static String accessMenu() {
        String accessMenuUri = PropertiesUtil.get("WX_ACCESS_MENU");
        String currentToken = TencentUtil.currentToken();
        // 拼装创建菜单的url
        String URI = String.format(accessMenuUri, currentToken);
        // 调用接口创建菜单
        InputStream inputStream = SystemUtil.getInputStream(URI, "GET");

        return SystemUtil.inputStreamToStr(inputStream);
    }

    /**
     * 生成微信支付的SIGIN
     */
    public static String paySigin(String param, String keyword) {
        try {
            String signTemp = param + "&key=" + keyword;
            return SystemUtil.MD5(signTemp).toUpperCase();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String formatPush(String msgType, String event, String eventKey, String fromUser, String toUser) {
        StringBuffer res = new StringBuffer();
        //新用户关注时自动推送
        if ("event".equals(msgType) && "subscribe".equals(event)) {

            res.append("<xml>");
            res.append("<ToUserName><![CDATA[" + toUser + "]]></ToUserName>");
            res.append("<FromUserName><![CDATA[" + fromUser + "]]></FromUserName>");
            res.append("<CreateTime>1453283273</CreateTime>");
            res.append("<MsgType><![CDATA[news]]></MsgType>");
            res.append("<ArticleCount>3</ArticleCount>");
            res.append("<Articles>");

            res.append("<item>");
            res.append("<Title><![CDATA[2016第一届网络女神比拼评选大赛投票]]></Title> ");
            res.append("<Description><![CDATA[点击关注正点定期推送三台本土文化,传播正能量,同城活动,吃喝玩乐,资讯八卦,商家优惠等诸多优质内]]></Description>");
            res.append("<PicUrl><![CDATA[http://wx.ajimi.cn/img/20160215194326.jpg]]></PicUrl>");
            res.append("<Url><![CDATA[http://wx.ajimi.cn/weixin?c=joke&p=vote&t=3]]></Url>");
            res.append("</item>");

            res.append("<item>");
            res.append("<Title><![CDATA[这个情人节 穿条牛仔裤去约会可好？]]></Title> ");
            res.append("<Description><![CDATA[说到情人节约会装扮，美人儿们首先想到的就是和温柔甜美挂钩的连衣裙，或者是和性感优雅挂钩的细高跟，循规蹈矩固然保守不出错，但是偶尔出奇制胜也未尝不好，这个情人节，穿条牛仔裤去约会可好？]]></Description>");
            res.append("<PicUrl><![CDATA[http://wx.ajimi.cn/img/1455364546607.jpg]]></PicUrl>");
            res.append("<Url><![CDATA[http://wx.ajimi.cn/weixin?c=joke&p=vote&t=2]]></Url>");
            res.append("</item>");

            res.append("<item>");
            res.append("<Title><![CDATA[马云把全国人民当猴耍了一回？]]></Title> ");
            res.append("<Description><![CDATA[春节期间，支付宝推行咻一咻集福以来，人们对“敬业福”的话题一直没有消停过，为求“敬业福”发朋友圈，发同学群等等；平日里不怎么来往，不怎么联系的人也纷纷跟你联系，为的是求“敬业福”]]></Description>");
            res.append("<PicUrl><![CDATA[http://wx.ajimi.cn/img/0376.jpg]]></PicUrl>");
            res.append("<Url><![CDATA[http://wx.ajimi.cn/weixin?c=joke&p=vote&t=2]]></Url>");
            res.append("</item>");

            res.append("</Articles>");
            res.append("</xml> ");
        }

        // 新用户关注时自动推送
        else if ("event".equals(msgType) && "CLICK".equals(event)) {
            return eventClick(eventKey, fromUser, toUser);
        }
        return res.toString();

    }

    private static String eventClick(String eventKey, String fromUser, String toUser) {
        StringBuffer res = new StringBuffer();
        if ("big_shot_secret".equals(eventKey)) {

            res.append("<xml>");
            res.append("<ToUserName><![CDATA[" + toUser + "]]></ToUserName>");
            res.append("<FromUserName><![CDATA[" + fromUser + "]]></FromUserName>");
            res.append("<CreateTime>1453283273</CreateTime>");
            res.append("<MsgType><![CDATA[news]]></MsgType>");
            res.append("<ArticleCount>1</ArticleCount>");
            res.append("<Articles>");
            res.append("<item>");

            res.append("<Title><![CDATA[2015第三届网络女神比拼评选大赛投票]]></Title> ");
            res.append("<Description><![CDATA[点击关注正点定期推送三台本土文化,传播正能量,同城活动,吃喝玩乐,资讯八卦,商家优惠等诸多优质内]]></Description>");
            res.append("<PicUrl><![CDATA[https://mmbiz.qlogo.cn/mmbiz/0Y1GYLafc6H5bJtMhwZA49mngqmicNyM6JMUBFxFfkWChqMw56orRZZjlcw6QdvWRb2ibNYaYRZRP1GL2dke2mXg/0?wx_fmt=jpeg]]></PicUrl>");
            res.append("<Url><![CDATA[http://wx.ajimi.cn/weixin?c=joke&p=vote&t=3]]></Url>");
            res.append("</item>");

            res.append("</Articles>");
            res.append("</xml> ");
        }
        return res.toString();
    }

    /**
     * 用户分组
     */
    public static String setupUserTag(String openId, int tagId) {
        try {
            String userBatchUri = PropertiesUtil.get("WX_USER_BATCH");
            String currentToken = TencentUtil.currentToken();
            String requestUrl = String.format(userBatchUri, currentToken);

            InputStream inputStream = SystemUtil.getInputStream(requestUrl, "{\"openid_list\" : [\"" + openId + "\"],\"tagid\" : " + tagId + "}", "POST");

            return SystemUtil.inputStreamToStr(inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 添加个性化菜单
     *
     * @param menu
     */
    public static void addConditional(String menu) {
        try {

            String addConditionalUri = PropertiesUtil.get("WX_ADD_CONDITIONAL");
            String currentToken = TencentUtil.currentToken();

            String requestUrl = String.format(addConditionalUri, currentToken);
            InputStream inputStream = SystemUtil.getInputStream(requestUrl, menu, "POST");
            System.out.println(SystemUtil.inputStreamToStr(inputStream));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 删除个性化菜单
     */
    public static void delConditional() {

        String delConditionalUri = PropertiesUtil.get("WX_DEL_CONDITIONAL");
        String currentToken = TencentUtil.currentToken();
        // 拼装创建菜单的url
        String URI = String.format(delConditionalUri, currentToken);
        // 调用接口创建菜单
        InputStream inputStream = SystemUtil.getInputStream(URI, "POST");
    }

    /**
     * 从服务器上下载微信客户端上传的图片
     *
     * @param request
     * @param response
     */
    public static String download(HttpServletResponse response, HttpServletRequest request) {

//        String mediaGetUri = PropertiesUtil.get("MEDIA_GET");
//        String accessToken = TencentUtil.currentToken();
//        String mediaId = request.getParameter("mid"); // 媒体ID
//
//        String fileNmae = "driver_" + mediaId + ".jpg";
//        String requestUrl = String.format(mediaGetUri, accessToken, mediaId);
//        InputStream inputStream = SystemUtil.getInputStream(requestUrl, "POST");
//        SystemUtil.saveFile(inputStream, Storage.IMAGE_SAVE_PATH + fileNmae,null);
//        String filePath = Storage.IMAGE_RESULT_PATH + fileNmae;
//        return filePath;

        String mediaGetUri = PropertiesUtil.get("MEDIA_GET");
        String accessToken = TencentUtil.currentToken();
        String mid = request.getParameter("mid"); // 媒体ID
//

        String fileName = mid + ".jpg";
        String requestUrl = String.format(mediaGetUri, accessToken, mid);
        InputStream inputStream = SystemUtil.getInputStream(requestUrl, "POST");
        SystemUtil.saveFile(inputStream, Storage.IMAGE_SAVE_PATH + fileName, fileName);
        String filePath = Storage.IMAGE_RESULT_PATH + fileName;
        return filePath;

    }

}
