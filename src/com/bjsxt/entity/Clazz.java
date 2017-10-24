package com.bjsxt.entity;

import java.io.Serializable;

public class Clazz implements Serializable{

	private long clazzno; 
	private String cname; 
	private String chteacher; 
	private long clazzroom;
	public long getClazzno() {
		return clazzno;
	}
	public void setClazzno(long clazzno) {
		this.clazzno = clazzno;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getChteacher() {
		return chteacher;
	}
	public void setChteacher(String chteacher) {
		this.chteacher = chteacher;
	}
	public long getClazzroom() {
		return clazzroom;
	}
	public void setClazzroom(long clazzroom) {
		this.clazzroom = clazzroom;
	}
	public Clazz(long clazzno, String cname, String chteacher, long clazzroom) {
		super();
		this.clazzno = clazzno;
		this.cname = cname;
		this.chteacher = chteacher;
		this.clazzroom = clazzroom;
	}
	public Clazz() {
		super();
	}
	@Override
	public String toString() {
		return "Clazz [clazzno=" + clazzno + ", cname=" + cname
				+ ", chteacher=" + chteacher + ", clazzroom=" + clazzroom + "]";
	} 
	
	
}
