package com.bjsxt.entity;

import java.io.Serializable;
import java.util.Date;

public class Admin implements Serializable{

	private String userid;
	private String username;
	private String password;
	private int age;
	private double score;
	private Date enterdate;
	private String hobby;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public Date getEnterdate() {
		return enterdate;
	}
	public void setEnterdate(Date enterdate) {
		this.enterdate = enterdate;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public Admin(String userid, String username, String password, int age,
			double score, Date enterdate, String hobby) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.age = age;
		this.score = score;
		this.enterdate = enterdate;
		this.hobby = hobby;
	}
	public Admin() {
		super();
	}
	@Override
	public String toString() {
		return "Admin [userid=" + userid + ", username=" + username
				+ ", password=" + password + ", age=" + age + ", score="
				+ score + ", enterdate=" + enterdate + ", hobby=" + hobby + "]";
	}
}
