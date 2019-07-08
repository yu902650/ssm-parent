/**
 * Copyright 2015  All rights reserved.  
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */
package com.example.wechar.domain.entity.wechar;

/**
 *  
 * @author: zhangxin  
 * @date:2015年12月17日  
 * @time:上午9:09:37   
 * @email:zhangxinjxq@163.com  
 * @version: 1.0
 */
public class NavigationCondition {
 
	/**用户分组id，可通过用户分组管理接口获取**/
	private String tag_id;
	/**性别：男（1）女（2），不填则不做匹配**/
	private String sex;
	/**客户端版本，当前只具体到系统型号：IOS(1), Android(2),Others(3)，不填则不做匹配**/
	private String client_platform_type;
	/**国家信息，是用户在微信中设置的地区**/
	private String country;
	/**省份信息，是用户在微信中设置的地区**/
	private String province;
	/**城市信息，是用户在微信中设置的地区**/
	private String city;

	public String getTag_id() {
		return tag_id;
	}

	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}

	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getClient_platform_type() {
		return client_platform_type;
	}
	public void setClient_platform_type(String client_platform_type) {
		this.client_platform_type = client_platform_type;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
}

