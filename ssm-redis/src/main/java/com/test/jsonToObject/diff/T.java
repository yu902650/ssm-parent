package com.test.jsonToObject.diff;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by bobo on 2019/4/15 20:43
 */
public class T {

    public static void main(String[] args) {
//        {"txlDataDTO":
//            {"txlList":[
//              {"id":"123456788","idCard":"321023199005305833","name":"蒋利剑","phone":"15250025360"},
//              {"id":"65895453","idCard":"320922199003011172","name":"陈磊","phone":"18862345591"}
//              ]
//            }
//        }

        String inputStr = " {\"txlDataDTO\":\n" +
                "            {\"txlList\":[\n" +
                "              {\"id\":\"123456788\",\"idCard\":\"321023199005305833\",\"name\":\"蒋利剑\",\"phone\":\"15250025360\"},\n" +
                "              {\"id\":\"65895453\",\"idCard\":\"320922199003011172\",\"name\":\"陈磊\",\"phone\":\"18862345591\"}\n" +
                "              ]\n" +
                "            }\n" +
                "        }";
        JSONObject json = JSON.parseObject(inputStr);
        JSONObject s = (JSONObject) json.get("txlDataDTO");

        String ss= String.valueOf(s.getJSONArray("txlList"));
//        System.out.println(ss);
        String str = ss;
        List<TxlBaseInfoDTO> list1 = JSON.parseArray(str, TxlBaseInfoDTO.class);
        System.out.println(list1);
        System.out.println(JSONObject.toJSONString(list1));
    }
}
