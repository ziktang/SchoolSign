package com.schoolsign.user.bean;

/**
 * Record entity. @author MyEclipse Persistence Tools
 */

public class Record implements java.io.Serializable {

	// Fields

	private int rid;
	private int lid;
	private int cid;
	private int uid;
	private String uname;
	private int state;
	private String signedTime;

	// Constructors

	/** default constructor */
	public Record() {
	}

	/** full constructor */
	public Record(int lid, int cid, int uid, String uname, int state,
			String signedTime) {
		this.lid = lid;
		this.cid = cid;
		this.uid = uid;
		this.uname = uname;
		this.state = state;
		this.signedTime = signedTime;
	}

	// Property accessors

	public int getRid() {
		return this.rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public int getCid() {
		return this.cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getSignedTime() {
		return this.signedTime;
	}

	public void setSignedTime(String signedTime) {
		this.signedTime = signedTime;
	}

	@Override
	public String toString() {
		return "Record{" +
				"rid=" + rid +
				", lid=" + lid +
				", cid=" + cid +
				", uid=" + uid +
				", uname='" + uname + '\'' +
				", state=" + state +
				", signedTime='" + signedTime + '\'' +
				'}';
	}
}