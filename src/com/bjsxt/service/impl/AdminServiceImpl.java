package com.bjsxt.service.impl;

import java.util.List;

import com.bjsxt.dao.AdminDao;
import com.bjsxt.dao.impl.AdminDaoImpl;
import com.bjsxt.entity.Admin;
import com.bjsxt.entity.Clazz;
import com.bjsxt.entity.Course;
import com.bjsxt.entity.Student;
import com.bjsxt.entity.Teacher;
import com.bjsxt.service.AdminService;
import com.bjsxt.util.SxtPageUtil;

public class AdminServiceImpl implements AdminService{

	//声明dao层对象
	AdminDao adminDao = new AdminDaoImpl();
	
	//管理员登录
	@Override
	public Admin AdminLoginService(String name, String pwd) {
		
		return adminDao.AdminLoginDao(name, pwd);
	}

	//添加管理员
	@Override
	public int addAdminService(Admin admin) {
		
		return adminDao.addAdminDao(admin);
	}

	//查询所有的班级信息
	@Override
	public List<Clazz> selctAllClazzService() {
		
		return adminDao.selctAllClazzDao();
	}

	//添加学生信息
	@Override
	public int addStudentService(Student student) {
		
		return adminDao.addStudentDao(student);
	}

	//查询学生的记录数
	@Override
	public int selectTotalStudentService() {
		
		return adminDao.selectTotalStudentDao();
	}

	//查询所有学生信息-分页
	@Override
	public void selectAllStudentService(SxtPageUtil<Student> sxtPageUtil) {
		
		adminDao.selectAllStudentDao(sxtPageUtil);
	}

	//模糊查询总记录数
	@Override
	public int selectLikeTotalStudentService(String name, String clazz) {
	
		return adminDao.selectLikeTotalStudentDao(name, clazz);
	}

	//模糊查询学生信息
	@Override
	public void selectLikeStudentService(SxtPageUtil<Student> sxtPageUtil,
			String name, String clazz) {
		adminDao.selectLikeStudentDao(sxtPageUtil, name, clazz);
		
	}

	//添加班级
	@Override
	public int addCourseService(Course course) {
		
		return adminDao.addCourseDao(course);
	}

	//查询所有的课程
	@Override
	public List<Course> selectCourseDao() {
		
		return adminDao.selectCourseDao();
	}

	//查询所有的老师
	@Override
	public List<Teacher> selectTeacherService() {
		
		return adminDao.selectTeacherDao();
	}

	//查询所有的课程包括授课老师
	@Override
	public List selectCourseTeacherService() {
		
		return adminDao.selectCourseTeacherDao();
	}

	//查询课程和老师的中间表T_TC
	@Override
	public boolean selectTCService(String courseid, String teacherid) {
		
		return adminDao.selectTCDao(courseid, teacherid);
	}

	//添加数据T_TC
	@Override
	public int addTCService(String courseid, String teacherid) {
		
		return adminDao.addTCDao(courseid, teacherid);
	}

	//添加老师
	@Override
	public int addTeacherService(Teacher teacher) {
		
		return adminDao.addTeacherDao(teacher);
	}

}
