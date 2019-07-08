package com.example.wechar.domain.entity.wechar;

import com.example.wechar.domain.entity.BaseEntity;

import java.util.Date;

/**
 * @program: backend
 * @Date: 2018/9/6 18:20
 * @Author: Mr.bobo
 * @Description:
 */
public class WxProblemSubmit extends BaseEntity {

    private Long id ;
    private String deviceNo;
    private String problemTypes;
    private String problemDescription;
    private String problemImgUrl;
    private String username;
    private Integer tenantId;
    private String phone;
    private Integer isCustomer;
    private Date submitTime;
    private Long customerId;
    private Integer problemStatus;

    public Integer getProblemStatus() {
        return problemStatus;
    }

    public void setProblemStatus(Integer problemStatus) {
        this.problemStatus = problemStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getProblemTypes() {
        return problemTypes;
    }

    public void setProblemTypes(String problemTypes) {
        this.problemTypes = problemTypes;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getProblemImgUrl() {
        return problemImgUrl;
    }

    public void setProblemImgUrl(String problemImgUrl) {
        this.problemImgUrl = problemImgUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsCustomer() {
        return isCustomer;
    }

    public void setIsCustomer(Integer isCustomer) {
        this.isCustomer = isCustomer;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
