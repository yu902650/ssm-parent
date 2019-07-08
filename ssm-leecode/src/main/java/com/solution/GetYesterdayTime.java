package com.solution;

import java.util.Calendar;

/**
 * @author bo bo
 * @date 2019/7/3 9:23
 * @desc  获取昨天的当前时间
 */
public class GetYesterdayTime {


    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        System.out.println(calendar.getTime());

    }

}
