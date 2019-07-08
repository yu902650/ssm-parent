package com.ssm.pojo;

import java.io.Serializable;

/**
 * @auther: bobo_yu
 * @Date: 2018/12/15 14:02
 * @Description:
 */
public class HistoryEntity implements Serializable {

    private static final long serialVersionUID = -9036385214103416500L;

    private Long id;
    private String day;
    private String des;
    private String lunar;
    private String month;
    private String pic;
    private String title;
    private String year;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


}
