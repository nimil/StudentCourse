package com.bjsxt.dao;

import java.util.List;

import com.bjsxt.entity.Admin;
import com.bjsxt.entity.Clazz;
import com.bjsxt.entity.Course;
import com.bjsxt.entity.Student;
import com.bjsxt.entity.Teacher;
import com.bjsxt.util.SxtPageUtil;

public interface AdminDao {

	//管理员登录
	Admin AdminLoginDao(String name,String pwd);
	
	//添加管理员
	int addAdminDao(Admin admin);
	
	//查询所有的班级信息
	List<Clazz> selctAllClazzDao();
	
	//添加学生信息
	int addStudentDao(Student student);
	
	//查询学生的记录数
	int selectTotalStudentDao();
	
	//查询所有学生信息-分页
	void selectAllStudentDao(SxtPageUtil<Student> sxtPageUtil);
	
	//模糊查询总记录数
	int selectLikeTotalStudentDao(String name,String clazz);
	
	//模糊查询学生信息
	void selectLikeStudentDao(SxtPageUtil<Student> sxtPageUtil,String name,String clazz);
	
	//添加班级
	int addCourseDao(Course course);
	
	//查询所有的课程
	List<Course> selectCourseDao();
	
	//查询所有的老师
	List<Teacher> selectTeacherDao();
	
	//查询所有的课程包括授课老师
	List selectCourseTeacherDao();
	
	//查询课程和老师的中间表T_TC
	boolean selectTCDao(String courseid,String teacherid);
	
	//添加数据T_TC
	int addTCDao(String courseid,String teacherid);
	
	//添加老师
	int addTeacherDao(Teacher teacher);
}
