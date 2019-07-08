package com.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.ssm.common.HttpUtil;
import com.ssm.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther: bobo_yu
 * @Date: 2018/12/17 16:01
 * @Description:
 * AppKey：1cc7cea7c2e80d7486d0ad6903611e71
 *  手机号码归属地
 */
@Controller
@RequestMapping("phone_belongs")
@Api(description = "手机号码归属地")
public class PhoneBelongsController {
    Jedis jedis = new Jedis();

    String key = "1cc7cea7c2e80d7486d0ad6903611e71";

    @ApiOperation("手机号码归属地")
    @RequestMapping(value = "writePhoneNo")
    @ResponseBody
    public String writePhoneNo(
            @RequestParam(required = false) String phone) {
        String url = "http://apis.juhe.cn/mobile/get";
        Map<String, String> stringMap = new HashMap<>(16);
        stringMap.put("key", key);
        stringMap.put("phone", phone);
        byte[] byteText = HttpUtil.post(url, stringMap);
        JSONObject object = JSONObject.parseObject(byteText, JSONObject.class);
        String result = object.getString("result");
        if (!StringUtils.isEmpty(result)) {
            Map<String, Object> paramMap = new HashMap<>();
            String phoneKey = "手机号"+  phone +"归属地";
            paramMap.put("phoneKey",result);
            jedis.set(phoneKey,result);
            return Result.ok("success", paramMap);
        } else {
            return Result.error("Fail", "result is null !!! ");
        }
    }
}
