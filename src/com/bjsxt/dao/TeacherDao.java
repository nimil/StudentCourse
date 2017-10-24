package com.bjsxt.dao;

import java.util.List;

import com.bjsxt.entity.Course;
import com.bjsxt.entity.Teacher;

public interface TeacherDao {

	//教师登陆
	Teacher teacherLoginDao(String name,String pwd);
	
	//查询当前老师下有多少门课程
	List<Course> selectTeacherCourseDao(Long tno);
	
	//查询当前老师下有多少学生选他的课
	List selectTeacherStudentDao(Long tno);
	
	//评分
	int updateTSCDao(Long sno,Long tno,Long cno,double score);
}
