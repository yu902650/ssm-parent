package com.ssm.pojo.exl;

import com.alibaba.excel.annotation.ExcelColumnNum;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class UserExo {
    @ExcelProperty(index = 0,value = "id")
    private Long id;
    @ExcelProperty(index = 1,value = "名字")
    private String name;
    @ExcelProperty(index = 2,value = "地址")
    private String address;
    @ExcelProperty(index = 3,value = "年龄")
    private int age;
    @ExcelProperty(index = 4,value = "性别")
    private int sex;
    @ExcelProperty(index = 5,value = "电话号码")
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
