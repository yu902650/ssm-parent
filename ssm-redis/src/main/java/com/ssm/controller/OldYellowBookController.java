package com.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.ssm.common.HttpUtil;
import com.ssm.common.Result;
import com.ssm.common.DateFormatUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
 * @Date: 2018/12/15 11:54
 * @Description:
 *
 *      AppKey：6f5e17f137bd31742685446021cd31fc
 */
@Controller
@RequestMapping("oldYellow")
@Api(description = "老黄历")
public class OldYellowBookController {
    Jedis jedis = new Jedis();
    DateFormatUtil dateFormatUtil= new DateFormatUtil();

    @ApiOperation("获取老黄历信息")
    @RequestMapping(value = "getMessage",method = RequestMethod.POST)
    @ResponseBody
    public String getMessage(
            @RequestParam(required = false) String key,
            @RequestParam(required = false) String date) {
        String url = "http://v.juhe.cn/laohuangli/d";
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("key", "6f5e17f137bd31742685446021cd31fc");
        stringMap.put("date", dateFormatUtil.getMonthDay());
        byte[] byteText = HttpUtil.post(url, stringMap);
        JSONObject object = JSONObject.parseObject(byteText, JSONObject.class);
        String result = object.getString("result");
        if (!StringUtils.isEmpty(result)) {
            Map<String, Object> paramMap = new HashMap<>();
            String oldYellowBookKey = "老黄历" + dateFormatUtil.getMonthDay();
            paramMap.put("oldYellowBookKey", result);
            jedis.set(oldYellowBookKey, result);
            return Result.ok("success",paramMap);
        } else {
            return Result.error("Fail", "result is null !!! ");
        }
    }
}

