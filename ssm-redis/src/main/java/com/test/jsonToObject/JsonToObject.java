package com.test.jsonToObject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ssm.pojo.HistoryEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: bobo_yu
 * @Date: 2018/12/20 16:28
 * @Description:
 *  Alibaba fastJson
 *  =======================================================
 *  List-->JSON
 *  JSON-->List
 */
public class JsonToObject {


    public static void main(String[] args) {

        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setId(1L);
        historyEntity.setMonth("12");
        historyEntity.setTitle("标题");
        historyEntity.setYear("2018");
        historyEntity.setDay("20");
        historyEntity.setDes("描述");
        historyEntity.setLunar("辛亥年");
        historyEntity.setPic("https://pic.com/pro.jpg");


        HistoryEntity historyEntity1 = new HistoryEntity();
        historyEntity1.setId(2L);
        historyEntity1.setMonth("12");
        historyEntity1.setTitle("标题");
        historyEntity1.setYear("2019");
        historyEntity1.setDay("21");
        historyEntity1.setDes("描述");
        historyEntity1.setLunar("辛亥年");
        historyEntity1.setPic("https://pic.com/pro1.jpg");


        List<HistoryEntity> list = new ArrayList<>();
        list.add(historyEntity);
        list.add(historyEntity1);

        //List --> 数组
        String str = JSON.toJSONString(list);
//        System.err.println("List --> 数组");
//        System.err.println(str);
//        System.err.println("========================================");

        //JSON str >>> List method 1
        // 获取的Json数据
        String json = str;
        List<HistoryEntity> entities = JSON.parseObject(json, new TypeReference<List<HistoryEntity>>() {});
//        System.err.println("JSON str >>> List method 1");
//        System.err.println(entities);
//        System.err.println("========================================");

        //JSON str >>> List method 2
        System.out.println("json        " +json);
        List<HistoryEntity> list1 = JSON.parseArray(json, HistoryEntity.class);
        System.err.println("JSON str >>> List method 2");
        System.err.println(list1);
        System.err.println("========================================");

    }
}
