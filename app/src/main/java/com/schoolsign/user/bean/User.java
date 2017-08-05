package com.schoolsign.user.bean;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private int uid;
	private String username;
	private String pwd;
	private String telephone;
	private int userType;
	private int registerType;
	private String registerTime;
	private int province;
	private int city;
	private int school;
	private String nickName;
	private int courseNum;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String username, String pwd, String telephone,
			int userType, int registerType, String registerTime,
			int province, int city, int school, String nickName,
			int courseNum) {
		this.username = username;
		this.pwd = pwd;
		this.telephone = telephone;
		this.userType = userType;
		this.registerType = registerType;
		this.registerTime = registerTime;
		this.province = province;
		this.city = city;
		this.school = school;
		this.nickName = nickName;
		this.courseNum = courseNum;
	}

	// Property accessors

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getUserType() {
		return this.userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getRegisterType() {
		return this.registerType;
	}

	public void setRegisterType(int registerType) {
		this.registerType = registerType;
	}

	public String getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public int getProvince() {
		return this.province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public int getCity() {
		return this.city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getSchool() {
		return this.school;
	}

	public void setSchool(int school) {
		this.school = school;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getCourseNum() {
		return this.courseNum;
	}

	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}

}