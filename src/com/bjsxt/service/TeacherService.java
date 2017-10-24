package com.bjsxt.service;

import java.util.List;

import com.bjsxt.entity.Course;
import com.bjsxt.entity.Teacher;

public interface TeacherService {

	//教师登陆
	Teacher teacherLoginService(String name,String pwd);
	
	//查询当前老师下有多少门课程
	List<Course> selectTeacherCourseService(Long tno);
	
	//查询当前老师下有多少学生选他的课
	List selectTeacherStudentService(Long tno);
	
	//评分
	int updateTSCService(Long sno,Long tno,Long cno,double score);
}
