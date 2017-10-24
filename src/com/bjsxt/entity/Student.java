package com.bjsxt.entity;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable{

	private long sno;
	private String sname;
	private String pwd;
	private String phone;
	private String gender;
	private Date birth;
	private long clazzno;
	private String remark;
	public long getSno() {
		return sno;
	}
	public void setSno(long sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public long getClazzno() {
		return clazzno;
	}
	public void setClazzno(long clazzno) {
		this.clazzno = clazzno;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Student(long sno, String sname, String pwd, String phone,
			String gender, Date birth, long clazzno, String remark) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.pwd = pwd;
		this.phone = phone;
		this.gender = gender;
		this.birth = birth;
		this.clazzno = clazzno;
		this.remark = remark;
	}
	
	
	public Student(String sname, String pwd, String phone, String gender,
			Date birth, long clazzno, String remark) {
		super();
		this.sname = sname;
		this.pwd = pwd;
		this.phone = phone;
		this.gender = gender;
		this.birth = birth;
		this.clazzno = clazzno;
		this.remark = remark;
	}
	public Student() {
		super();
	}
	@Override
	public String toString() {
		return "Student [sno=" + sno + ", sname=" + sname + ", pwd=" + pwd
				+ ", phone=" + phone + ", gender=" + gender + ", birth="
				+ birth + ", clazzno=" + clazzno + ", remark=" + remark + "]";
	}
	
	

}
