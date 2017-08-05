package com.schoolsign.user.bean;

import java.util.List;


public class SignStuResult {
	private List<Record> records;
	private Lesson lesson;
	public List<Record> getRecords() {
		return records;
	}
	public void setRecords(List<Record> records) {
		this.records = records;
	}
	public Lesson getLesson() {
		return lesson;
	}
	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	public SignStuResult(List<Record> records, Lesson lesson) {
		super();
		this.records = records;
		this.lesson = lesson;
	}
	public SignStuResult() {
		super();
		// TODO Auto-generated constructor stub
	}
}
