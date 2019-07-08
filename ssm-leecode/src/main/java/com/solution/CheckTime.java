package com.solution;

import java.util.Date;

/**
 * @author bo bo
 * @date 2019/7/3 9:00
 * @desc
 *      Java 比较时间大小.compareTo
 *      返回值有 1  0 -1 分别对应 大于 等于 小于
 */
public class CheckTime {


    public static void main(String[] args) {
        Date date = new Date(System.currentTimeMillis());
        Date date2 = new Date(System.currentTimeMillis()-1000);

        System.out.println("date :" + date);
        System.out.println("date2 :" + date);


        if (date.compareTo(date2)==1){
            System.out.println("date > date2");
        }
        if (date.compareTo(date2)==0){
            System.out.println("date = date2");
        }
        if (date.compareTo(date2)==-1){
            System.out.println("date < date2");
        }
    }


}
