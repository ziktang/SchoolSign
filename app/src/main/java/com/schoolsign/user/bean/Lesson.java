package com.schoolsign.user.bean;

/**
 * Lesson entity. @author MyEclipse Persistence Tools
 */

public class Lesson implements java.io.Serializable {

	// Fields

	private int lid;
	private int cid;
	private String lname;
	private String startTime;
	private String endTime;
	private int duration;
	private int signedNum;
	private int totalNum;
	private int state;
	private String signCode;
	

	// Constructors

	/** default constructor */
	public Lesson() {
	}

	public Lesson(int cid, String lname, String startTime, String endTime,
			int duration, int signedNum, int totalNum, int state,
			String signCode) {
		super();
		this.cid = cid;
		this.lname = lname;
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
		this.signedNum = signedNum;
		this.totalNum = totalNum;
		this.state = state;
		this.signCode = signCode;
	}

	// Property accessors

	public int getLid() {
		return this.lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getLname() {
		return this.lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getSignedNum() {
		return this.signedNum;
	}

	public void setSignedNum(int signedNum) {
		this.signedNum = signedNum;
	}

	public int getTotalNum() {
		return this.totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getSignCode() {
		return this.signCode;
	}

	public void setSignCode(String signCode) {
		this.signCode = signCode;
	}

}