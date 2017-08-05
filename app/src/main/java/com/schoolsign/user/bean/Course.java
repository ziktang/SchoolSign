package com.schoolsign.user.bean;


/**
 * Course entity. @author MyEclipse Persistence Tools
 */

public class Course implements java.io.Serializable {

	// Fields

	private int cid;
	private int tid;
	private String tname;
	private String cname;
	private int stuNum;
	private String creTime;
	private String remark;

	// Constructors

	/** default constructor */
	public Course() {
	}

	/** minimal constructor */
	public Course(int tid, String tname, String cname, int stuNum,
			String creTime, String remark) {
		this.tid = tid;
		this.tname = tname;
		this.cname = cname;
		this.stuNum = stuNum;
		this.creTime = creTime;
		this.remark = remark;
	}


	// Property accessors

	public int getCid() {
		return this.cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getTid() {
		return this.tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTname() {
		return this.tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getStuNum() {
		return this.stuNum;
	}

	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}

	public String getCreTime() {
		return this.creTime;
	}

	public void setCreTime(String creTime) {
		this.creTime = creTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Course{" +
				"cid=" + cid +
				", tid=" + tid +
				", tname='" + tname + '\'' +
				", cname='" + cname + '\'' +
				", stuNum=" + stuNum +
				", creTime='" + creTime + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}