package com.bjsxt.service;

import java.util.List;

import com.bjsxt.entity.Admin;
import com.bjsxt.entity.Clazz;
import com.bjsxt.entity.Course;
import com.bjsxt.entity.Student;
import com.bjsxt.entity.Teacher;
import com.bjsxt.util.SxtPageUtil;

public interface AdminService {

	//管理员登录
	Admin AdminLoginService(String name,String pwd);
	
	//添加管理员
	int addAdminService(Admin admin);
	
	//查询所有的班级信息
	List<Clazz> selctAllClazzService();
	
	//添加学生信息
	int addStudentService(Student student);
	
	//查询学生的记录数
	int selectTotalStudentService();
	
	//查询所有学生信息-分页
	void selectAllStudentService(SxtPageUtil<Student> sxtPageUtil);
	
	//模糊查询总记录数
	int selectLikeTotalStudentService(String name,String clazz);
	
	//模糊查询学生信息
	void selectLikeStudentService(SxtPageUtil<Student> sxtPageUtil,String name,String clazz);
	
	//添加班级
	int addCourseService(Course course);
	
	//查询所有的课程
	List<Course> selectCourseDao();
	
	//查询所有的老师
	List<Teacher> selectTeacherService();
	
	//查询所有的课程包括授课老师
	List selectCourseTeacherService();
	
	//查询课程和老师的中间表T_TC
	boolean selectTCService(String courseid,String teacherid);
	
	//添加数据T_TC
	int addTCService(String courseid,String teacherid);
	
	//添加老师
	int addTeacherService(Teacher teacher);

}
