package com.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.ssm.common.DateFormatUtil;
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
 * @Date: 2018/12/15 15:12
 * @Description: 彩票开奖结果查询        AppKey：2e88f445063a3d08f7ba55d6baaa0c3c
 */
@Controller
@RequestMapping("color_ticket")
@Api(value = "restful",description = "彩票开奖结果查询")
public class ColorTicketController {
    Jedis jedis = new Jedis();
    DateFormatUtil dateFormatUtil = new DateFormatUtil();

    //支持彩种列表
    @ApiOperation("彩票开奖结果")
    @RequestMapping(value = "getTypes",method = RequestMethod.POST)
    @ResponseBody
    public String getResult(@RequestParam(required = false) String key) {
        String url = "http://apis.juhe.cn/lottery/types";
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("key", "2e88f445063a3d08f7ba55d6baaa0c3c");
        byte[] byteText = HttpUtil.post(url, stringMap);
        JSONObject object = JSONObject.parseObject(byteText, JSONObject.class);
        String result = object.getString("result");
        if (!StringUtils.isEmpty(result)) {
            Map<String, Object> paramMap = new HashMap<>();
            String colorTicketKey = "彩票" + dateFormatUtil.getYeahMonthDayHourMin();
            paramMap.put("count", 7);
            paramMap.put("colorTicketKey", result);
            jedis.set(colorTicketKey, result);
            return Result.ok("success", paramMap);
        } else {
            return Result.error("Fail", "result is null !!! ");
        }
    }

    //支持彩种列表
    @ApiOperation("详情结果")
    @RequestMapping(value = "queryResult",method = RequestMethod.POST)
    @ResponseBody
    public String queryResult(@RequestParam(required = false) String key,
                              @RequestParam(required = true) String lottery_id,
                              @RequestParam(required = false) String lottery_no) {
        String url = "http://apis.juhe.cn/lottery/query";
        Map<String, String> stringMap = new HashMap<>(16);
        stringMap.put("key", "2e88f445063a3d08f7ba55d6baaa0c3c");
        stringMap.put("lottery_id", lottery_id);
        byte[] byteText = HttpUtil.post(url, stringMap);
        JSONObject object = JSONObject.parseObject(byteText, JSONObject.class);
        String result = object.getString("result");
        if (!StringUtils.isEmpty(result)) {
            Map<String, Object> paramMap = new HashMap<>();
            String colorTicketKey = "彩票历史开奖 " + lottery_id;
            paramMap.put("count", 1);
            paramMap.put("colorTicketKey", result);
            jedis.set(colorTicketKey, result);
            return Result.ok("success", paramMap);
        } else {
            return Result.error("Fail", "result is null !!! ");
        }
    }

}