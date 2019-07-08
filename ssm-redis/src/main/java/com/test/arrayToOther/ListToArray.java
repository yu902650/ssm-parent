package com.test.arrayToOther;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobo on 2019/4/26 1:18
 */
public class ListToArray {
    public static void main(String[] args) {

        List<ShopCarRecord> carRecords = new ArrayList<>();
        ShopCarRecord shopCarRecord = new ShopCarRecord();
        shopCarRecord.setAttribute("48");
        shopCarRecord.setBrand("30");
        shopCarRecord.setCount(1);
        shopCarRecord.setPrice("37800");
        shopCarRecord.setSeries("5");
        shopCarRecord.setSpec("1");
        shopCarRecord.setProductId("1");

        ShopCarRecord superRecord = new ShopCarRecord();
        superRecord.setAttribute("99");
        superRecord.setBrand("999");
        superRecord.setCount(1);
        superRecord.setPrice("9999");
        superRecord.setSeries("999");
        superRecord.setSpec("99");
        superRecord.setProductId("22");

        carRecords.add(shopCarRecord);
        carRecords.add(superRecord);

//        System.out.println("=================***********=============="+carRecords);
//
//        System.out.println("=================***********=============="+carRecords.size());

//        json prodList[{"spec":1,"attribute":48,"productId":1,"price":37800,"series":5,"brand":30,"count":1}]

        String inputStr ="{\n" +
                "    \"prodList\":[\n" +
                "        {\n" +
                "            \"spec\":1,\n" +
                "            \"attribute\":48,\n" +
                "            \"productId\":1,\n" +
                "            \"price\":37800,\n" +
                "            \"series\":5,\n" +
                "            \"brand\":30,\n" +
                "            \"count\":1\n" +
                "        }" +
                "    ],\n" +
                "}";
        JSONObject json = JSON.parseObject(inputStr);
        List<ShopCarRecord> carRecords1 = (List<ShopCarRecord>) json.get("prodList");

//        System.err.println();
//        System.out.println("-----carRecords1 " +  carRecords1);
//        System.out.println("-----carRecords1 " + carRecords1.size());


        List<ShopCarRecord>  a1 = new ArrayList<>();

        int k = 0;
        for (int i = 0 ; i< carRecords1.size() ; i++){
            ShopCarRecord record =  carRecords.get(i);
            for (int j = 0; j<carRecords.size();j++){
                ShopCarRecord record1 = carRecords.get(j);
                //两个list遍历. 相同 5 属性, count  ++
                if (record1.getProductId()==record.getProductId()
                        &&record1.getBrand()==record.getBrand()
                        &&record1.getAttribute()==record.getAttribute()
                        &&record1.getSeries()==record.getSeries()
                        &&record1.getSpec()==record.getSpec()){
                    int count =record1.getCount()+record.getCount();
                    record1.setCount(count);
//                    ShopCarRecord s = new ShopCarRecord();
//                    s.setProductId(record1.getProductId());
//                    s.setSpec(record1.getSpec());
//                    s.setBrand(record1.getBrand());
//                    s.setAttribute(record1.getAttribute());
//                    s.setSeries(record1.getSeries());
//                    s.setCount(record1.getCount());
//                    s.setCount(count);
//                    System.out.println(JSON.toJSONString(record1));
                    a1.add(record1);
                }else {
                    System.out.println(record1);
                    a1.add(record1);
                }
            }

        }
//        System.out.println(k);
        System.out.println(a1);
//        System.out.println(carRecords.size());


//        System.out.println(prodList);
//        System.err.println("===============================");
//        System.out.println("   carRecords       "  + carRecords);
    }
}
