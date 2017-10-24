package com.bjsxt.service.impl;

import java.util.List;

import com.bjsxt.dao.TeacherDao;
import com.bjsxt.dao.impl.TeacherDaoImpl;
import com.bjsxt.entity.Course;
import com.bjsxt.entity.Teacher;
import com.bjsxt.service.TeacherService;

public class TeacherServiceImpl implements TeacherService{

	//声明dao对象
	TeacherDao teacherDao = new TeacherDaoImpl();
	
	//教师登陆
	@Override
	public Teacher teacherLoginService(String name, String pwd) {
		
		return teacherDao.teacherLoginDao(name, pwd);
	}

	//查询当前老师下有多少门课程
	@Override
	public List<Course> selectTeacherCourseService(Long tno) {
	
		return teacherDao.selectTeacherCourseDao(tno);
	}

	//查询当前老师下有多少学生选他的课
	@Override
	public List selectTeacherStudentService(Long tno) {
		
		return teacherDao.selectTeacherStudentDao(tno);
	}

	//评分
	@Override
	public int updateTSCService(Long sno, Long tno, Long cno, double score) {
		
		return teacherDao.updateTSCDao(sno, tno, cno, score);
	}

}
