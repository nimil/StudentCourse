package com.bjsxt.entity;

import java.io.Serializable;
import java.util.Date;

public class Course implements Serializable{

	private long cno;
	private String name;
	private int credit;
	private Date periodstart;
	private Date periodend;
	public long getCno() {
		return cno;
	}
	public void setCno(long cno) {
		this.cno = cno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public Date getPeriodstart() {
		return periodstart;
	}
	public void setPeriodstart(Date periodstart) {
		this.periodstart = periodstart;
	}
	public Date getPeriodend() {
		return periodend;
	}
	public void setPeriodend(Date periodend) {
		this.periodend = periodend;
	}
	public Course(long cno, String name, int credit, Date periodstart,
			Date periodend) {
		super();
		this.cno = cno;
		this.name = name;
		this.credit = credit;
		this.periodstart = periodstart;
		this.periodend = periodend;
	}
	
	
	public Course(String name, int credit, Date periodstart, Date periodend) {
		super();
		this.name = name;
		this.credit = credit;
		this.periodstart = periodstart;
		this.periodend = periodend;
	}
	public Course() {
		super();
	}
	@Override
	public String toString() {
		return "Course [cno=" + cno + ", name=" + name + ", credit=" + credit
				+ ", periodstart=" + periodstart + ", periodend=" + periodend
				+ "]";
	}
	
	
}
