package com.test.去重;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.arrayToOther.ShopCarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名:      cloud
 * 包名:        com.cloud.backend
 * 创建时间:    2019/3/20 14:18
 *
 * @author: Bobo_Yu
 * 描述:
 */
public class JST {
    public static void main(String[] args) {
//        /**
//         * 30  ,      5,       1,           48,       52,     18100    count
//         * 品牌      系列     商品ID         属性       规格      单价       1
//         * brand   series   productId    attribute    spec    price      1
//         */
//        String curr = "30,5,1,48,52,18100,1|30,5,2,38,59,20000,1";
//        String [] order = curr.split("\\|");
//        List<OrderBo> orderBoList = new ArrayList<>();
//
//        for (String record : order){
//            OrderBo orderBo = new OrderBo();
//            String [] chang = record.split(",");
//            //品牌
//            String brand = chang[0];
//            //系列
//            String series = chang[1];
//            //
//            String productId = chang[2];
//            String attribute = chang[3];
//            String spec =chang[4];
//            Double price = Double.valueOf(chang[5]);
//            int count = Integer.parseInt(chang[6]);
//            orderBo.setBrand(brand);
//            orderBo.setSeries(series);
//            orderBo.setProductId(productId);
//            orderBo.setAttribute(attribute);
//            orderBo.setSpec(spec);
//            orderBo.setPrice(price);
//            orderBo.setCount(String.valueOf(count));
//            orderBoList.add(orderBo);
//        }
//        System.out.println(orderBoList);
//        System.out.println(orderBoList.size());
//    }
//

//           [ShopCarRecord(spec=1, attribute=48, productId=1, price=37800.00, series=5, brand=30, count=1)]

        List<ShopCarRecord> carRecords = new ArrayList<>();
        ShopCarRecord shopCarRecord = new ShopCarRecord();
        shopCarRecord.setAttribute("48");
        shopCarRecord.setBrand("30");
        shopCarRecord.setCount(1);
        shopCarRecord.setPrice("37800");
        shopCarRecord.setSeries("5");
        shopCarRecord.setSpec("1");
        shopCarRecord.setProductId("1");
//        shopCarRecord.setId("24");

        ShopCarRecord superRecord = new ShopCarRecord();
        superRecord.setAttribute("99");
        superRecord.setBrand("999");
        superRecord.setCount(1);
        superRecord.setPrice("9999");
        superRecord.setSeries("999");
        superRecord.setSpec("99");
        superRecord.setProductId("22");
//        superRecord.setId("24");

        ShopCarRecord superRecord1 = new ShopCarRecord();
        superRecord1.setAttribute("99");
        superRecord1.setBrand("999");
        superRecord1.setCount(3);
        superRecord1.setPrice("9999");
        superRecord1.setSeries("999");
        superRecord1.setSpec("99");
        superRecord1.setProductId("22");
//        superRecord1.setId("24");

        carRecords.add(shopCarRecord);
        carRecords.add(superRecord);
        carRecords.add(superRecord1);

//        Map<Integer, ShopCarRecord> map = new HashMap<Integer, ShopCarRecord>();
//        for (ShopCarRecord e : carRecords) {
//            int id = Integer.parseInt(e.getId());
//            String productId = e.getProductId();
//            String brand = e.getBrand();
//            String series = e.getSeries();
//            String spec = e.getSpec();
//            if (map.containsKey(id) && map.containsKey(productId) && map.containsKey(brand) && map.containsKey(brand) && map.containsKey(brand)) {
//                int count = map.get(id).getCount();
//                count += e.getCount();
//                map.get(id).setCount(count);
//            } else {
//                map.put(id, e);
//
//            }
//        }
//        System.out.println(map);

//        List<ShopCarRecord> list = new ArrayList<>(carRecords);
//        for (ShopCarRecord s: carRecords){
//            ShopCarRecord record = s;
//            System.out.println(record);
//            if (record.getId()==superRecord1.getId()&&record.getProductId()==superRecord1.getProductId()){
//                ShopCarRecord newObj = new ShopCarRecord();
//                newObj.setId(record.getId());
//                newObj.setSeries(record.getSeries());
//                newObj.setAttribute(record.getAttribute());
//                newObj.setSpec(record.getSpec());
//                newObj.setProductId(record.getProductId());
//                newObj.setBrand(record.getBrand());
//                int count = record.getCount();
//                newObj.setCount(count);
//                list.add(newObj);
//            }else {
//                ShopCarRecord newObj = new ShopCarRecord();
//                newObj.setId(record.getId());
//                newObj.setSeries(record.getSeries());
//                newObj.setAttribute(record.getAttribute());
//                newObj.setSpec(record.getSpec());
//                newObj.setProductId(record.getProductId());
//                newObj.setBrand(record.getBrand());
//                newObj.setCount(record.getCount());
//                list.add(newObj);
//            }
//        }
//
//        System.out.println(list);
//

//        System.out.println("=================***********=============="+carRecords);
//
//        System.out.println("=================***********=============="+carRecords.size());
//
////        json prodList[{"spec":1,"attribute":48,"productId":1,"price":37800,"series":5,"brand":30,"count":1}]
//
        String inputStr = "{\n" +
                "    \"prodList\":[\n" +
                "        {\n" +
                "            \"spec\":2,\n" +
                "            \"attribute\":125,\n" +
                "            \"productId\":678,\n" +
                "            \"price\":37800,\n" +
                "            \"series\":5,\n" +
                "            \"brand\":30,\n" +
                "            \"count\":1\n" +
                "        }\n" +
                "    ],\n" +
                "    \"customerId\":1\n" +
                "}";
        JSONObject json = JSON.parseObject(inputStr);
        List<ShopCarRecord> carRecords1 = (List<ShopCarRecord>) json.get("prodList");

        List<ShopCarRecord> a1 = new ArrayList<>();
        for (int i = 0; i < carRecords1.size(); i++) {
            ShopCarRecord record = carRecords.get(i);
            for (int j = 0; j < carRecords.size(); j++) {
                ShopCarRecord record1 = carRecords.get(j);
                //两个list遍历. 相同 5 属性, count  ++
                if (record1.getProductId() == record.getProductId()
                        && record1.getBrand() == record.getBrand()
                        && record1.getAttribute() == record.getAttribute()
                        && record1.getSeries() == record.getSeries()
                        && record1.getSpec() == record.getSpec()) {
                    int count = record1.getCount() + record.getCount();
                    record1.setCount(count);
                    a1.add(record1);
                } else {
                    a1.add(record1);
                }
            }
        }
        System.err.println("  aaaa  " + a1);

        List<ShopCarRecord> resultList = new ArrayList<ShopCarRecord>();
        for (ShopCarRecord o : a1) {
            boolean isFind = false;
            for(int i=0;i<resultList.size();i++){
                if(o.getAttribute().equals(resultList.get(i).getAttribute())&& o.getProductId().equals(resultList.get(i).getProductId())
                        &&o.getBrand().equals(resultList.get(i).getBrand())&&o.getSpec().equals(resultList.get(i).getSpec())&&o.getSeries().equals(resultList.get(i).getSeries())){
                    isFind = true;
                    resultList.get(i).setCount(resultList.get(i).getCount() + o.getCount());
                }
            }
            if(!isFind){
                resultList.add(o);
            }
        }
        System.out.println(resultList);
    }



//        List<ShopCarRecord> carRecords = new ArrayList<>();
//        ShopCarRecord shopCarRecord = new ShopCarRecord();
//        shopCarRecord.setAttribute("48");
//        shopCarRecord.setBrand("30");
//        shopCarRecord.setCount(1);
//        shopCarRecord.setPrice("37800");
//        shopCarRecord.setSeries("5");
//        shopCarRecord.setSpec("1");
//        shopCarRecord.setProductId("1");
//
//        ShopCarRecord superRecord = new ShopCarRecord();
//        superRecord.setAttribute("99");
//        superRecord.setBrand("999");
//        superRecord.setCount(1);
//        superRecord.setPrice("9999");
//        superRecord.setSeries("999");
//        superRecord.setSpec("99");
//        superRecord.setProductId("22");
//
//        carRecords.add(shopCarRecord);
//        carRecords.add(superRecord);
//
//        HashMap<String,Object> hashMap = new HashMap<>();
//        for (ShopCarRecord obj : carRecords){
//            Map<String, Object>  map = transBean2Map(obj);
//            System.out.println(map);
//
//        }
//
//
//    }
//
//    public static  Map<String, Object> transBean2Map(Object obj) {
//
//        if (obj == null) {
//            return null;
//        }
//        TreeMap<String, Object> map = new TreeMap<>();
//        try {
//            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
//            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
//            for (PropertyDescriptor property : propertyDescriptors) {
//                String key = property.getName();
//                // 过滤class属性
//                if (key.equals("class")) {
//                    continue;
//                }
//
//                // 得到property对应的getter方法
//                Method getter = property.getReadMethod();
//                Object value = getter.invoke(obj);
//
//                map.put(key, value);
//            }
//        } catch (Exception e) {
//            System.out.println("transBean2Map Error " + e);
//        }
//        return map;
//    }

//    [
//    ShopCarRecord(id=25, spec=2, attribute=49, productId=1, price=37800.00, series=5, brand=30, count=2),
//    ShopCarRecord(id=25, spec=2, attribute=48, productId=2, price=37800.00, series=5, brand=30, count=1),
//    ShopCarRecord(id=25, spec=2, attribute=49, productId=1, price=37800.00, series=5, brand=30, count=5)
//]


    }
