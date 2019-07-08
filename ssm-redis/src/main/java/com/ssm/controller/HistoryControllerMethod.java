package com.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ssm.common.DateFormatUtil;
import com.ssm.common.HttpUtil;
import com.ssm.common.Result;
import com.ssm.dao.IHistoryMapper;
import com.ssm.pojo.HistoryEntity;
import com.ssm.service.IHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bobo
 * @auther: bobo_yu
 * @Date: 2018/12/14 22:28
 * @Description: 历史上的今天        AppKey：22c6f0003bc7b56c9e253a4431a99cfc
 */
@Controller
@RequestMapping( path = "history", produces = {"application/json;charset=utf-8"})
@Api(value = "restful", description = "历史上的今天")
public class HistoryControllerMethod {

    @Autowired
    private IHistoryMapper historyMapper;

    @Autowired
    private IHistoryService historyService;

    Jedis jedis = new Jedis();
    DateFormatUtil dateFormatUtil = new DateFormatUtil();

    //配置您申请的KEY
    public static final String APPKEY = "22c6f0003bc7b56c9e253a4431a99cfc";

    @ApiOperation("历史的今天接口")
    @RequestMapping(value = "today")
    @ResponseBody
    public String todayThings(
            @RequestParam(required = false) String key,
            @RequestParam(required = false) String v,
            @RequestParam(required = false) String month,
            @RequestParam(required = false) String day) {
        String todayRedisKey = "历史" + dateFormatUtil.getMonthDay();
        System.out.println("从redis中读取");
        String sessionData = jedis.get(todayRedisKey);
        if (sessionData != null) {
            return Result.ok("success", sessionData);
        } else {
            String url = "http://api.juheapi.com/japi/toh";//请求接口地址
            Map map = new HashMap();//请求参数
            map.put("key", APPKEY);//应用APPKEY(应用详细页查询)
            map.put("v", "1.0");
            map.put("month", dateFormatUtil.getMonth());
            map.put("day", dateFormatUtil.getDay());
            byte[] byteText = HttpUtil.post(url, map);
            JSONObject object = JSONObject.parseObject(byteText, JSONObject.class);
            String result = object.getString("result");
            if (!StringUtils.isEmpty(result)) {
                Map<String, Object> paramMap = new HashMap<>();
                String historyKey = "历史" + dateFormatUtil.getMonthDay();
                paramMap.put("historyKey", result);
                jedis.set(historyKey, result);
                String json = result;
                List<HistoryEntity> list = JSON.parseArray(json, HistoryEntity.class);
//                System.err.println("list " + JSONObject.toJSONString(list));
                for (int i = 1; i < list.size(); i++) {
                    HistoryEntity historyEntity = list.get(i);
                    historyService.insert(historyEntity);
                }
                return Result.ok("success", paramMap);
            } else {
                return Result.error("Fail", "result is null !!! ");
            }
        }
    }

    @ApiOperation("所有历史list")
    @RequestMapping(value = "getHistoryList", method = RequestMethod.GET)
    @ResponseBody
    public String getHistoryList() {
        List<HistoryEntity> list = historyService.queryList();
        return Result.ok("SUCCESS", list);
    }

    @ApiOperation("根据ID查找")
    @RequestMapping(value = "findObjById", method = RequestMethod.GET)
    @ResponseBody
    public String findObjById(@RequestParam(required = false) String id) {
        HistoryEntity entity = historyService.findObjById(id);
        return Result.ok("SUCCESS", entity);
    }


}