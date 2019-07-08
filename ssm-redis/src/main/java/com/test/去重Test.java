package com.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名:      backend
 * 包名:        PACKAGE_NAME
 * 创建时间:    2019/3/26 09:14
 *
 * @author: Bobo_Yu
 * 描述:
 */

public class 去重Test {

    public static void main(String[] args) {
        List<So> list = new ArrayList<So>();
        So s = new So();
        s.setProductName("灌装雪");
        s.setCount(1);

        So s2 = new So();
        s2.setProductName("灌装雪");
        s2.setCount(1);

        So s3= new So();
        s3.setProductName("粉色可口可乐");
        s3.setCount(1);

        list.add(s);
        list.add(s2);
        list.add(s3);

        List<So> list1=new ArrayList<So>();
        for (int i=0;i<list.size() ;i++){
            if (i==0){
                So z1 = new So();
                z1.setProductName(list.get(i).getProductName());
                z1.setCount(list.get(i).getCount());
                list1.add(z1);
                i++;
            }
            System.out.println( "元数据"  + JSONObject.toJSONString(list1));
            for (int j = 0 ; j < list1.size() ; j++){
                if (list.get(i).getProductName()==list1.get(j).getProductName()){
                    int a = list.get(i).getCount()+list1.get(j).getCount();
                    list1.get(j).setCount(a);
                    System.err.println(a +"                /          a aaa");

                }else {
                    Boolean b = false;
                    for (int j1=0 ; j1< list1.size() ; j1++){
                        if (list1.get(j1).getProductName()==list.get(i).getProductName()){
                            b=true;
                        }
                    }
                    if (b==false){
                      So so = new So();
                        System.out.println("aaa");
                        so.setProductName(list.get(i).getProductName());
                        so.setCount(list.get(i).getCount());
                        list1.add(so);
                    }

                }

            }

        }
        System.out.println("list1:"+ JSON.toJSONString(list1));
    }

    static class So {
        String productName;
        int orderAmount;
        int count;

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(int orderAmount) {
            this.orderAmount = orderAmount;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}





//
//    List<WecharProductVo> p1 = new ArrayList<WecharProductVo>();
//        for (int i = 0; i < p.size(); i++) {
//        if (i == 0) {
//        WecharProductVo z1 = new WecharProductVo();
//        z1.setProductName(p.get(i).getProductName());
//        z1.setCount(p.get(i).getCount());
//        z1.setOrderAmount(p.get(i).getOrderAmount());
//        p1.add(z1);
//        i++;
//        }
//        for (int j = 0; j < p1.size(); j++) {
//        if (p.get(i).getProductName() == p1.get(j).getProductName()) {
//        int a = p.get(i).getCount() + p1.get(j).getCount();
//        int b = p.get(i).getOrderAmount() + p1.get(j).getOrderAmount();
//        p1.get(j).setCount(a);
//        p1.get(j).setOrderAmount(b);
//        } else {
//        Boolean b = false;
//        for (int j1 = 0; j1 < p1.size(); j1++) {
//        if (p1.get(j1).getProductName() == p.get(i).getProductName()) {
//        b = true;
//        }
//        }
//        if (b == false) {
//        WecharProductVo so = new WecharProductVo();
//        System.out.println("aaa");
//        so.setProductName(p.get(i).getProductName());
//        so.setCount(p.get(i).getCount());
//        so.setOrderAmount(p.get(i).getOrderAmount());
//        p1.add(so);
//        }
//        }
//        }
//        }
