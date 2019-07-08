package com.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.ssm.common.Constants;
import com.ssm.common.DateFormatUtil;
import com.ssm.common.HttpUtil;
import com.ssm.common.Result;
import com.ssm.service.INewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther: bobo_yu
 * @Date: 2018/12/14 16:12
 * @Description: 接口地址：http://v.juhe.cn/toutiao/index
 * 返回格式：json
 * 请求方式：get/post
 * 请求示例：http://v.juhe.cn/toutiao/index?type=top&key=APPKEY
 * 接口备注：返回头条，社会，国内，娱乐，体育，军事，科技，财经，时尚等新闻信息
 * <p>
 * 请求参数说明：
 * <p>
 * 名称	   必填	  类型	   说明
 * key	    是	 string	   应用     APPKEY
 * type   否	 string	   类型     top(头条，默认),shehui(社会), guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
 */
@Controller
@RequestMapping("/news_api")
@Api(description = "热点新闻")
public class NewsApiController {

    @Autowired
    private INewService newService;

    //  String key = "f1a7610580626060fec68239b1b86c70";
    Jedis jedis = new Jedis();
    DateFormatUtil dateFormatUtil = new DateFormatUtil();

    @ApiOperation("今日新闻")
    @RequestMapping(value = "getNews")
    @ResponseBody
    public String getNews(@RequestParam(required = false) String type) {
        Map<String, String> objectMap = new HashMap<>();
        if (type == "top") {
            objectMap.put("type", "top");
        }
        if (type == "shehui") {
            objectMap.put("type", "shehui");
        }
        if (type == "guonei") {
            objectMap.put("type", "guonei");
        }
        if (type == "guoji") {
            objectMap.put("type", "guoji");
        }
        if (type == "yule") {
            objectMap.put("type", "yule");
        }
        if (type == "tiyu") {
            objectMap.put("type", "tiyu");
        }
        if (type == "junshi") {
            objectMap.put("type", "junshi");
        }
        if (type == "keji") {
            objectMap.put("type", "keji");
        }
        if (type == "caijing") {
            objectMap.put("type", "caijing");
        }
        if (type == "shishang") {
            objectMap.put("type", "shishang");
        }
        String resultKey = dateFormatUtil.getYeahMonthDay() + "新闻接口数据";
        String redisData = jedis.get(resultKey);
        if (redisData!=null) {
            System.err.println("redisDate Locahost");
            String json = redisData;
//            List<News> newsList = JSONArray.parseArray(json,News.class);
//            for (int i = 1; i< newsList.size(); i++){
//                News news = newsList.get(i);
//                newService.addNews(news);
//            }
            return Result.ok("success", redisData);
        } else {
            String GET_NEWS_URI = "http://v.juhe.cn/toutiao/index?key=f1a7610580626060fec68239b1b86c70";
            byte[] byteText = HttpUtil.post(GET_NEWS_URI, objectMap);
            JSONObject object = JSONObject.parseObject(byteText, JSONObject.class);
            String result = object.getString("result");
            if (!StringUtils.isEmpty(result)) {
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("result", result);
                paramMap.put("count", 1);
                jedis.set(resultKey, result);
                String json = result;
//                List<News> newsList = JSONArray.parseArray(json,News.class);
//                for (int i = 1; i< newsList.size(); i++){
//                    News news = newsList.get(i);
//                    newService.addNews(news);
//                }
                return Result.ok(Constants.SUCCESS, "Success", paramMap);
            } else {
                return Result.error("Fail", "result is null !!! ");
            }
        }
    }
}
