package com.ssm.controller;

import com.ssm.common.Constants;
import com.ssm.common.Result;
import com.test.HttpRequestUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther: bobo_yu
 * @Date: 2018/12/21 15:03
 * @Description:
 */
@Controller
@RequestMapping(value = "Test")
@Api(value = "restful", description = "测试")
public class TestApiController {

    @ApiOperation(value = "测试专用")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(HttpServletRequest request) {
        String str = "1234";
        System.err.println(str);
        String ipStr = HttpRequestUtil.getRemoteIp(request);
        String remoteUrl = HttpRequestUtil.getRemoteUrl(request);
        String hostName = HttpRequestUtil.getHostName(request);
        String userAgent = HttpRequestUtil.getUserAgent(request);
        String URIFormat = HttpRequestUtil.getURIFormat(request);
        String requestMethod = HttpRequestUtil.getRequestMethod(request);
        Map cookieMap = HttpRequestUtil.getCookieMap(request);
        Map<String, Object> map = new HashMap<>();
        map.put("ipStr", ipStr);
        map.put("remoteUrl",remoteUrl);
        map.put("hostName", hostName);
        map.put("userAgent",userAgent);
        map.put("URIFormat",URIFormat);
        map.put("RequestMethod",requestMethod);
        map.put("cookieMap",cookieMap);
        map.put("str", str);
        return Result.ok(Constants.SUCCESS, "SUCCESS", map);
    }
}
