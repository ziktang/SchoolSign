package com.schoolsign.user.bean;

import java.io.Serializable;

public class SchoolResult implements Serializable {
	private int cityCode;
	private int schoolCode;
	private String schoolName;
	public int getCityCode() {
		return cityCode;
	}
	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}
	public int getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(int schoolCode) {
		this.schoolCode = schoolCode;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public SchoolResult(int cityCode, int schoolCode, String schoolName) {
		super();
		this.cityCode = cityCode;
		this.schoolCode = schoolCode;
		this.schoolName = schoolName;
	}
	public SchoolResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
