package com.ssm.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther: bobo_yu
 * @Date: 2018/12/15 13:33
 * @Description:
 */
public class DateFormatUtil {

    Date date = new Date();
    //获取年
    SimpleDateFormat sdfYeah = new SimpleDateFormat("YYYY");

    public String getYeah() {
        return sdfYeah.format(date);
    }

    //获取月
    SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");

    public String getMonth() {
        return sdfMonth.format(date);
    }

    //获取日
    SimpleDateFormat sdfDay = new SimpleDateFormat("dd");

    public String getDay() {
        return sdfDay.format(date);
    }

    //获取月日
    SimpleDateFormat sdfMonthDay = new SimpleDateFormat("MM-dd");

    public String getMonthDay() {
        return sdfMonthDay.format(date);
    }

    //获取年月日
    SimpleDateFormat sdfYeahMonthDay = new SimpleDateFormat("YYYY-MM-dd");

    public String getYeahMonthDay() {
        return sdfYeahMonthDay.format(date);
    }

    //获取年月日时分
    //hh 小写24时制
    //HH 大写12时制
    SimpleDateFormat sdfYeahMonthDayHourMin = new SimpleDateFormat("YYYY-MM-dd hh-mm");

    public String getYeahMonthDayHourMin() {
        return sdfYeahMonthDayHourMin.format(date);
    }

    //获取年月日时分秒
    SimpleDateFormat sdfYeahMonthDayHourMinSecond = new SimpleDateFormat("YYYY-MM-dd hh-mm");

    public String getYeahMonthDayHourMinSecond() {
        return sdfYeahMonthDayHourMinSecond.format(date);
    }

    public static void main(String[] args) {

        DateFormatUtil dateFormatUtil = new DateFormatUtil();
        String day = dateFormatUtil.getDay();
        System.err.println(day);
    }
}
