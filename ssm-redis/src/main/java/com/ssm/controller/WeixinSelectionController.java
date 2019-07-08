package com.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.ssm.common.DateFormatUtil;
import com.ssm.common.HttpUtil;
import com.ssm.common.Result;
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
 * @Date: 2018/12/15 14:51
 * @Description: 请求参数说明：
 * 名称   	必填	    类型	    说明
 * pno     否	        int 	    当前页数，默认1
 * ps      否	        int       每页返回条数，最大50，默认20
 * key     是	        string	    在个人中心->我的数据,接口名称上方查看
 * dtype   否	        string  	返回数据的格式,xml或json，默认json
 * <p>
 * 返回参数说明：
 * 名称	         类型	    说明
 * error_code      int     	返回码
 * reason          string  	返回说明
 * result          string  	返回结果集
 */
@Controller
@RequestMapping("weixin_selection")
@Api(description = "微信精选文章")
public class WeixinSelectionController {
    Jedis jedis = new Jedis();
    DateFormatUtil dateFormatUtil = new DateFormatUtil();

    //AppKey：4b7e3f97eb7239fef959aa5efe47a80f

    @ResponseBody
    @RequestMapping(value = "getWeixinInfo",method = RequestMethod.POST)
    @ApiOperation("获取微信精选文章")
    public String getWeixinInfo(
            @RequestParam(required = false) String key,
            @RequestParam(required = false) String pno,
            @RequestParam(required = false) String ps) {
        String url = "http://v.juhe.cn/weixin/query";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("key", "4b7e3f97eb7239fef959aa5efe47a80f");
        paramMap.put("pno", "1");
        paramMap.put("ps", "50");
        byte[] byteText = HttpUtil.post(url, paramMap);
        JSONObject object = JSONObject.parseObject(byteText, JSONObject.class);
        String result = object.getString("result");
        if (!StringUtils.isEmpty(result)) {
            Map<String, Object> map = new HashMap<>();
            String weixinSeclection = "微信精选" + dateFormatUtil.getYeahMonthDay();
            //返回给前端
            map.put("weixinSeclection", result);
            //放入redis
            jedis.set(weixinSeclection, result);
            return Result.ok("success", map);
        } else {
            return Result.error("Fail", "result is null !!! ");
        }
    }


}
