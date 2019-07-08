package com.example.wechar.web;

import com.example.wechar.domain.common.Result;
import com.example.wechar.domain.common.Storage;
import com.example.wechar.service.TencentService;
import com.example.wechar.util.PropertiesUtil;
import com.example.wechar.util.SystemUtil;
import com.example.wechar.util.TencentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author bo bo
 * @date 2019/6/17 11:55
 * @desc
 */
@RestController
@RequestMapping(path = "wechat", produces = {"application/json;charset=utf-8"})
public class WecharController {

    @Autowired
    private TencentService tencentService;

    @ResponseBody
    @RequestMapping(value = "test")
    public String test(){
        return "(path = \"wechat\", produces = {\"application/json;charset=utf-8\"})";
    }


    /**
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @param msg_signature
     * @param request
     */
    @RequestMapping(value = "interactive")
    public String interactive(@RequestParam(required = false) String signature,
                              @RequestParam(required = false) String timestamp,
                              @RequestParam(required = false) String nonce,
                              @RequestParam(required = false) String echostr,
                              @RequestParam(required = false) String msg_signature,
                              HttpServletRequest request) throws IOException {

        String respStr;
        //解析请求的类型 ,这个接口规定了必须是get.
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        if (isGet) {
            respStr = tencentService.verification(signature, timestamp, nonce, echostr);
        } else {
            ServletInputStream inputStream = request.getInputStream();
            String postData = SystemUtil.inputStreamToStr(inputStream);
            respStr = tencentService.accept(msg_signature, timestamp, nonce, postData);
        }
        //
        return respStr;
    }

    /**
     * @param noncestr
     * @param timestamp
     * @param url
     * @return
     */
    @RequestMapping(value = "signature")
    public String signature(@RequestParam String noncestr,
                            @RequestParam String timestamp,
                            @RequestParam String url) {

        String signature = tencentService.signature(noncestr, timestamp, url);
        return Result.ok("success", signature);
    }

    /**
     * @return
     */
    @RequestMapping(value = "createMenu")
    public String createMenu() {
        String res = tencentService.createMenu();
        return Result.ok("success", res);
    }

    @RequestMapping(value = "deleteMenu", method = {RequestMethod.GET})
    public void deleteMenu(HttpServletResponse response) {
        TencentUtil.deleteMenu();
    }

    /**
     * 创建个性化菜单
     *
     * @return
     */
    @RequestMapping(value = "addConditional")
    public String addConditional() {
        String res = tencentService.addConditional();
        return Result.ok("success", res);
    }

    /**
     * @param noncestr
     * @param openid
     * @param no
     * @param fee
     * @return
     */
    @RequestMapping(value = "payValidate")
    public String payValidate(@RequestParam String noncestr,
                              @RequestParam String openid,
                              @RequestParam String no,
                              @RequestParam String fee,
                              HttpServletRequest request) {

        //真实IP
        String realIp = request.getHeader("xrealip");

        Map<String, Object> respMap = tencentService.payvalidate(noncestr, openid, no, fee, realIp);
        String payVal = SystemUtil.toJson(respMap);

        System.err.println(payVal);
        return payVal;
    }

    /**
     * 微信普通红包发送
     *
     * @param noncestr
     * @param openid
     * @param no
     * @param forwarded
     * @return
     */
    @RequestMapping(value = "cashCoupon")
    public String cashCoupon(@RequestParam String noncestr,
                             @RequestParam String openid,
                             @RequestParam String no,
                             @RequestParam String forwarded) {

        String result = tencentService.cashCoupon(noncestr, openid, no, forwarded);
        return Result.ok("success", result);

    }

    /**
     * 获取标签
     */
    @RequestMapping(value = "tagAccess")
    public String tagAccess() {
        return TencentUtil.tagAccess();
    }

    /**
     * 删除标签
     */
    @RequestMapping(value = "deleteTag")
    public String deleteTag(String tagId) {
        return TencentUtil.deleteTag(tagId);
    }

    /**
     * 修改标签
     *
     * @param tagName
     */
    @RequestMapping(value = "updateTag")
    public String updateTag(String tagId, String tagName) {
        return TencentUtil.updateTag(tagId, tagName);
    }

    /**
     * 创建标签
     *
     * @param tagName
     */
    @RequestMapping(value = "createTag")
    public String createTag(String tagName) {
        return TencentUtil.createTag(tagName);
    }

    /**
     * 批量为用户打标签
     *
     * @param openid
     * @param tagid
     */
    @RequestMapping(value = "setupUserTag")
    public String setupUserTag(String openid, int tagid) {
        return TencentUtil.setupUserTag(openid, tagid);
    }

    @RequestMapping(value = "accessMenu")
    public void accessMenu() {
        TencentUtil.accessMenu();
    }

    //tag下的粉丝
    @ResponseBody
    @RequestMapping(value = "tagUser")
    public String tagUser(String tagid) {
        String currentToken = TencentUtil.currentToken();
        // 拼装创建菜单的url
        String URI = String.format("https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=%s", currentToken);
        // 调用接口创建菜单
        String param = "{\"tagid\":" + tagid + "}";
        InputStream inputStream = SystemUtil.getInputStream(URI, param, "POST");

        String s = SystemUtil.inputStreamToStr(inputStream);

        return Result.ok("success", s);
    }

    //
    @RequestMapping(value = "tryMatch")
    public void tryMatch(HttpServletRequest request) {
        String currentToken = TencentUtil.currentToken();
        // 拼装创建菜单的url
        String URI = String.format("https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token=%s", currentToken);
        // 调用接口创建菜单
        String openid = request.getParameter("openid");
        String param = "{\"user_id\":\"" + openid + "\"}";
        InputStream inputStream = SystemUtil.getInputStream(URI, param, "POST");
    }

    /**
     * 下载微信上传的图片，保存到本地服务器地址，地址回传给前端
     *
     * @param mid
     * @return
     */
    @RequestMapping("getWixinUploadImgUrl")
    public static String download(String mid) {

        String mediaGetUri = PropertiesUtil.get("MEDIA_GET");
        String accessToken = TencentUtil.currentToken();

        String fileName = mid + ".jpg";
        String requestUrl = String.format(mediaGetUri, accessToken, mid);
        InputStream inputStream = SystemUtil.getInputStream(requestUrl, "POST");
        SystemUtil.saveFile(inputStream, Storage.IMAGE_SAVE_PATH + "wechar/", fileName);
        String filePath = Storage.IMAGE_RESULT_PATH + "wechar/" + fileName;
        System.err.println("Controller 打印 图片路径 ----------" + filePath);
        return filePath;
    }

}
