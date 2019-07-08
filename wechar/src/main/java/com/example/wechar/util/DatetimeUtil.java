package com.example.wechar.util;

import com.example.wechar.domain.entity.req.TimeParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 日期操作的工具类
 *
 * @author ch
 */
public class DatetimeUtil {

    public final static String DATE_PATTERN = "yyyy-MM-dd";
    public final static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public final static String DATE_PATTERN_SIMPLE = "MM-dd HH:MM";

    public final static String TIME_PATTERN = "HH:mm:ss";

    public final static String DATE_TIME_PATTERN = DATE_PATTERN + " " + TIME_PATTERN;

    /****
     * 获取格式化时间
     *
     * @Title: getDate
     * @Description: TODO
     * @param @param
     *            format
     * @param @return
     * @return String
     * @throws @author
     *             liu zheng yang
     * @date May 8, 2012 10:35:23 PM
     */
    public static String getDate(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date());
    }

    /**
     * 获取当月的1号日期
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static final Date getMonthFirstDay() {
        Date d = new Date();
        int m = d.getMonth();
        int y = d.getYear();
        // Date firstDay = new Date(y,m+1,1) ;
        // int min = 24*60*60*1000; \
        Date from = new Date(y, m, 1);
        // Date to = new Date(firstDay.getTime()-min);
        return from;
    }

    /**
     * 获取指定月的第一天
     *
     * @return
     */
    public static Date getMonthFirstDay(Date date) {

        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天

        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal.getTime();
    }

    /**
     * 获取指定月的最后一天
     *
     * @return
     */
    public static Date getMonthEndDay(Date date) {

        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);    //加一个月
        cal.set(Calendar.DATE, 1);              //设置为该月第一天
        cal.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天

        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * String类型 to Date类型
     *
     * @param pattern 时间格式
     * @param locale  本地类，用于选择本地语言
     * @param zone    区
     * @param strDate 时间字符串 "2008-06-18"
     */
    public static final Date convertStringToDate(String pattern, Locale locale, TimeZone zone, String strDate)
            throws ParseException {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        if (zone == null) {
            zone = TimeZone.getDefault();
        }
        SimpleDateFormat df = new SimpleDateFormat(pattern, locale);
        df.setTimeZone(zone);
        try {
            return df.parse(strDate);
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }
    }

    /**
     * 完整格式化时间
     *
     * @param date
     * @return
     */
    public static String formateDateFull(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    /**
     * 完整格式化时间
     *
     * @param date
     * @return
     */
    public static String formateSimpleDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN_SIMPLE);
        return df.format(date);
    }

    /**
     * 格式化成日期
     *
     * @param date
     * @return
     */
    public static String formateDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    /**
     * String 转 date 类型（yyyy-MM-dd）
     *
     * @param strDate String的日期
     * @return
     */
    public static final Date convertStringToDate(String strDate) {
        Locale locale = Locale.CHINESE;
        try {
            return convertStringToDate(DATE_PATTERN, locale, null, strDate);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据传入的格式类型进行转化成date类型数据
     *
     * @param sytle 时间格式 "yyyy-MM-dd"
     */
    public static final Date convertStringToDate(String strDate, String sytle) {
        Locale locale = Locale.CHINESE;
        try {
            return convertStringToDate(sytle, locale, null, strDate);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param aDate 默认时间对象
     */
    public static final String convertDateToString(String pattern, Locale locale, TimeZone zone, Date aDate) {
        if (locale == null){
            locale = Locale.getDefault();
        }
        if (zone == null){
            zone = TimeZone.getDefault();
        }
        SimpleDateFormat df = new SimpleDateFormat(pattern, locale);
        df.setTimeZone(zone);
        try {
            return df.format(aDate);
        } catch (Exception e) {
            return "";
        }
    }

    public static final String convertDateToString(String pattern, Date aDate) {
        Locale locale = Locale.CHINESE;
        return convertDateToString(pattern, locale, null, aDate);
    }

    public static final Date covertString2Date(String pattern, String strTime) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strTime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
//			e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取开始时间
     */
    public static final Date getBeginDate(String beginDate) {
        Locale locale = Locale.CHINESE;
        try {
            return convertStringToDate("yyyy-MM-dd HH:mm:ss", locale, null, beginDate + " 00:00:00");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取一天结束时间
     */
    public static final Date getEndDate(String endDate) {
        Locale locale = Locale.CHINESE;
        try {
            Date date = convertStringToDate("yyyy-MM-dd HH:mm:ss", locale, null, endDate + " 00:00:00");
            return new Date(date.getTime() + 24 * 3600 * 1000);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getNextDay(Date date) {
        try {
            return new Date(date.getTime() + 24 * 3600 * 1000);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 完整的时间字符串
     */
    public static String getFullDateStr() {
        DateFormat format = DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINESE);
        return format.format(new Date());
    }

    /**
     * 获取两个时间的间隔天数
     *
     * @param date1
     * @param date2
     * @return
     */

    public static int diffdates(Date date1, Date date2) {
        int result = 0;

        GregorianCalendar gc1 = new GregorianCalendar();
        GregorianCalendar gc2 = new GregorianCalendar();

        gc1.setTime(date1);
        gc2.setTime(date2);
        result = getDays(gc1, gc2);

        return result;
    }

    /**
     * 获取时间间隔天数
     */
    public static int getDays(GregorianCalendar g1, GregorianCalendar g2) {
        int elapsed = 0;
        GregorianCalendar gc1, gc2;

        if (g2.after(g1)) {
            gc2 = (GregorianCalendar) g2.clone();
            gc1 = (GregorianCalendar) g1.clone();
        } else {
            gc2 = (GregorianCalendar) g1.clone();
            gc1 = (GregorianCalendar) g2.clone();
        }

        gc1.clear(Calendar.MILLISECOND);
        gc1.clear(Calendar.SECOND);
        gc1.clear(Calendar.MINUTE);
        gc1.clear(Calendar.HOUR_OF_DAY);

        gc2.clear(Calendar.MILLISECOND);
        gc2.clear(Calendar.SECOND);
        gc2.clear(Calendar.MINUTE);
        gc2.clear(Calendar.HOUR_OF_DAY);

        while (gc1.before(gc2)) {
            gc1.add(Calendar.DATE, 1);
            elapsed++;
        }
        return elapsed;
    }

    /**
     * @param formatStyle 时间格式
     * @param formatStr   时间字符串
     * @param hour        获取hour小时后时间
     */
    public static Date formartDate(String formatStyle, String formatStr, int hour) {
        SimpleDateFormat format = new SimpleDateFormat(formatStyle, Locale.CHINA);
        try {
            Date date = new Date();
            date.setTime(format.parse(formatStr).getTime() + hour * 60 * 60 * 1000);
            return date;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取昨天时间
     */
    public static Date getYesterday() {
        return new Date(new Date().getTime() - 24 * 3600 * 1000L);
    }

    /**
     * 获取昨天时间
     *
     * @param day
     */
    public static Date getYesterdayDate(Date day) {
        return new Date(day.getTime() - 24 * 3600 * 1000L);
    }

    /**
     * 获取明天时间
     */
    public static Date getTomorrowDate(Date day) {
        return new Date(day.getTime() + 24 * 3600 * 1000L);
    }

    /**
     * 获取上个星期这个时候时间
     */
    public static Date getLastWeek(Date day) {
        return new Date(day.getTime() - 7 * 24 * 3600 * 1000L);
    }

    /**
     * 获取下个星期这个时候时间
     */
    public static Date getNextWeek(Date day) {
        return new Date(day.getTime() + 7 * 24 * 3600 * 1000L);
    }

    /**
     * 获取上个月这个时候时间
     */
    public static Date getLastMonth() {
        return getLastMonth(new Date());
    }

    /**
     * ��ȡ�ƶ�ʱ����ϸ���
     */
    public static Date getLastMonth(Date date) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        cal.clear();
        cal.setTime(date);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
        cal.getTime();
        return cal.getTime();
    }

    /**
     * 获取下个月这个时候时间
     */
    public static Date getNextMonth(Date date) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        cal.clear();
        cal.setTime(date);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        cal.getTime();
        return cal.getTime();
    }

    /**
     * 获取月最大天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMaxDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        cal.clear();
        cal.set(year, month - 1, 1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取给定星期的时间
     *
     * @param year
     * @param weekNo 给定某个星期
     * @return Date[0]开始时间 Date[1]结束时间
     */
    public static Date[] getGivenWeekDates(int year, int weekNo) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        cal.clear();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        Date begin = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, 6);
        Date end = cal.getTime();
        return new Date[]{begin, end};
    }

    /**
     * 获取时间对应的星期
     */
    public static int getWeekNo(Date date) {
        if (date == null)
            date = new Date();
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        cal.clear();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取年份
     */
    public static int getYear(Date date) {
        if (date == null)
            date = new Date();
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        cal.clear();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /*
     * public static void main(String[] args) { Date dd =
     * convertStringToDate("2006-9-1"); Date d = getLastMonth(dd);
     * System.out.println(fmtDate(d, "yyyy-MM-dd")); }
     *
     *
     * public static String fmtDate(Date date, String style) { SimpleDateFormat
     * dateFormat = new SimpleDateFormat(style); return dateFormat.format(date);
     * }
     */

    public static int[] getCurrentDate() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        cal.setTime(new Date());
        int[] date = new int[6];
        date[0] = cal.get(Calendar.YEAR);
        date[1] = cal.get(Calendar.MONTH) + 1;
        date[2] = cal.get(Calendar.DATE);
        date[3] = cal.get(Calendar.HOUR_OF_DAY);
        date[4] = cal.get(Calendar.MINUTE);
        date[5] = cal.get(Calendar.SECOND);
        return date;
    }

    public static int[] getLimitMonthDate(int year, int month, int monthSect) {
        year = year < 1 ? 1 : year;
        month = month > 12 ? 12 : month;
        month = month < 1 ? 1 : month;
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), new Locale("zh", "CN"));
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.add(Calendar.MONTH, monthSect);
        int[] yAndM = new int[2];
        yAndM[0] = cal.get(Calendar.YEAR);
        yAndM[1] = cal.get(Calendar.MONTH);
        if (yAndM[1] == 0) {
            yAndM[0] = yAndM[0] - 1;
            yAndM[1] = 12;
        }
        return yAndM;
    }

    /**
     * 格式化时间转Date型
     *
     * @param str
     * @return
     */
    public static Date fomateStringDate(String str) {
        try {
            int index = str.indexOf(":");
            SimpleDateFormat df = null;

            if (index != -1) {
                df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            } else {
                df = new SimpleDateFormat("yyyy-MM-dd");
            }
            Date date = df.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到系统的当前时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String currentTime(String format) {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(cal.getTime());
    }

    /**
     * 得到系统的当前时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String currentTime() {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    /**
     * 返回两个时间相差的秒数
     *
     * @param startTime 较早的时间
     * @param endTime   较晚的时间
     */
    public static long dateDiff(String startTime, String endTime) {
        try {
            if (startTime == null) startTime = endTime;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date bt = sdf.parse(startTime);
            Date et = sdf.parse(endTime);
            long diff = et.getTime() - bt.getTime();
            return diff / 1000;

        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }

    }

    /**
     * 比较2个日期相差的小时数
     *
     * @param startTime
     * @param endTime
     */
    @SuppressWarnings("unused")
    public static Long dateDiff(Date startTime, Date endTime) {
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数long diff;try {
        // 获得两个时间的毫秒时间差异
        // System.out.println("endTime.getTime()=="+endTime.getTime());
        // System.out.println("startTime.getTime()=="+startTime.getTime());
        Long diff = endTime.getTime() - startTime.getTime();
        // long day = diff/nd;//计算差多少天
        // long hour = diff%nd/nh;//计算差多少小时
        // long min = diff%nd%nh/nm;//计算差多少分钟
        // long sec = diff%nd%nh%nm/ns;//计算差多少秒//输出结果
        // System.out.println("时间相差："+day+"天"+hour+"小时"+min+"分钟"+sec+"秒。");
        return diff / nh;
    }

    /**
     * 把选择的日期加1天
     *
     * @param day
     * @return
     */
    public static String getNextDay(String day) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dd;
        try {
            dd = format.parse(day);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dd);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            return format.format(calendar.getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取日期的月份和日期的四位字符串
     * @param date
     * @return
     */
//	public static String getMouthAndDay(Calendar  date){
//		int month = date.get(Calendar.MONTH) + 1;
//		int day = date.get(Calendar.DAY_OF_MONTH);
//		String monthNum = month < 10 ? "0" + month : month + "";
//		String dayNum = day < 10 ? "0" + day : day + "";
//		return monthNum + dayNum;
//	}

    /**
     * 获取日期的分钟和秒的四位字符串
     *
     * @param date
     * @return
     */
    public static String getMinuteAndSecond(Calendar date) {
        int month = date.get(Calendar.MINUTE);
        int day = date.get(Calendar.SECOND);
        String monthNum = month < 10 ? "0" + month : month + "";
        String dayNum = day < 10 ? "0" + day : day + "";
        return monthNum + dayNum;
    }

    /**
     * 把选择的日期减1天
     *
     * @param day
     * @return
     */
    public static String getLastDay(String day) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dd;
        try {
            dd = format.parse(day);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dd);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            return format.format(calendar.getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取下一天
     *
     * @param day
     * @return
     */
    public static Date getNextDaytoDate(String day) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dd;
        try {
            dd = format.parse(day);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dd);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            return calendar.getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 获取上一天
     *
     * @param day
     * @return
     */
    public static Date getLastDaytoDate(String day) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dd;
        try {
            dd = format.parse(day);
            // Calendar calendar=Calendar.getInstance();
            // calendar.setTime(dd);
            // calendar.add(Calendar.DAY_OF_MONTH,-1);
            return dd;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 获取两个日期之间的日期
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 日期集合
     */
    public static List<Date> getBetweenDates(Date start, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        tempStart.add(Calendar.DAY_OF_YEAR, 1);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd)) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }

    // 获得当天0点时间
    public static Date getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getDaysFromNow(Integer num) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getTimesmorning().getTime() + 3600 * 24 * 1000 * Long.valueOf(num));
        return cal.getTime();
    }

    /**
     * @param searchType
     * @param timeParam
     * @return
     */
    public static TimeParam conversionTime(String searchType, TimeParam timeParam) {
        // 今天
        if ("0".equals(searchType)) {
            timeParam.setStartTime(DatetimeUtil.getDaysFromNow(0));
            timeParam.setEndTime(DatetimeUtil.getDaysFromNow(0));
        }
        // 一周
        if ("1".equals(searchType)) {
            timeParam.setStartTime(DatetimeUtil.getDaysFromNow(-7));
            timeParam.setEndTime(DatetimeUtil.getDaysFromNow(0));
        }
        // 30天
        if ("2".equals(searchType)) {
            timeParam.setStartTime(DatetimeUtil.getDaysFromNow(-30));
            timeParam.setEndTime(DatetimeUtil.getDaysFromNow(0));
        }
        // 本月
        if ("10".equals(searchType)) {
            timeParam.setStartTime(DatetimeUtil.getMonthFirstDay());
            timeParam.setEndTime(DatetimeUtil.getDaysFromNow(0));
        }
        return timeParam;
    }

    /**
     * 当前时间之后？秒
     *
     * @param second
     * @return
     */
    public static Date after(int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        Date target = DatetimeUtil.after(30);
        String s = convertDateToString(DatetimeUtil.yyyyMMddHHmmss, target);
        System.err.println(s);
    }
}
