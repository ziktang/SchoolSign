package com.schoolsign.user.bean;

public class StuLessonResult {

	private int lid;
	private String lname;
	private int uid;
	private String startTime;
	private String endTime;
	private int lessonState;

	private String signCode;
	private int rid;
	private int signState;
	private String signedTime;

	public StuLessonResult(int lid, String lname, int uid, String startTime,
			String endTime, int lessonState,String signCode, int rid, int signState,
			String signedTime) {
		super();
		this.lid = lid;
		this.lname = lname;
		this.uid = uid;
		this.startTime = startTime;
		this.endTime = endTime;
		this.lessonState = lessonState;
		this.rid = rid;
		this.signState = signState;
		this.signedTime = signedTime;
	}

	public String getSignCode() {
		return signCode;
	}

	public void setSignCode(String signCode) {
		this.signCode = signCode;
	}

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getLessonState() {
		return lessonState;
	}

	public void setLessonState(int lessonState) {
		this.lessonState = lessonState;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getSignState() {
		return signState;
	}

	public void setSignState(int signState) {
		this.signState = signState;
	}

	public String getSignedTime() {
		return signedTime;
	}

	public void setSignedTime(String signedTime) {
		this.signedTime = signedTime;
	}

	public StuLessonResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "StuLessonResult{" +
				"lid=" + lid +
				", lname='" + lname + '\'' +
				", uid=" + uid +
				", startTime='" + startTime + '\'' +
				", endTime='" + endTime + '\'' +
				", lessonState=" + lessonState +
				", signCode='" + signCode + '\'' +
				", rid=" + rid +
				", signState=" + signState +
				", signedTime='" + signedTime + '\'' +
				'}';
	}
}
