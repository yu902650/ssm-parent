package com.example.wechar.domain.entity.wechar;

public class Token {
	
	private String access_token;
	private Integer expires_in;
	private String update_date;
	
	public String getAccessToken() {
		return access_token;
	}
	public void setAccessToken(String access_token) {
		this.access_token = access_token;
	}
	public Integer getExpiresIn() {
		return expires_in;
	}
	public void setExpiresIn(Integer expires_in) {
		this.expires_in = expires_in;
	}
	public String getUpdateDate() {
		return update_date;
	}
	public void setUpdateDate(String update_date) {
		this.update_date = update_date;
	}
}
