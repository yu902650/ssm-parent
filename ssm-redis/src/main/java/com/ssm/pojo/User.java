package com.ssm.pojo;

import lombok.Data;

/**
 * 项目名:      parent
 * 包名:        com.ssm.pojo
 * 创建时间:    2019/3/20 11:30
 *
 * @author: Bobo_Yu
 * 描述:
 */
public class User {
    private Long id ;
    private String name;
    private String address;
    private int age;
    private int sex;
    private String phoneNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
