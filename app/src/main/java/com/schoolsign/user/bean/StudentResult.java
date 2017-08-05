package com.schoolsign.user.bean;

public class StudentResult {
	private int uid;
	private String uname;
	private int joinState;
	private String require;
	public StudentResult(int uid, String uname, int joinState, String require) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.joinState = joinState;
		this.require = require;
	}
	
	

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getJoinState() {
		return joinState;
	}
	public void setJoinState(int joinState) {
		this.joinState = joinState;
	}
	public String getRequire() {
		return require;
	}
	public void setRequire(String require) {
		this.require = require;
	}


}
