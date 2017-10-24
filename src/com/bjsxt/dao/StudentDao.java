package com.bjsxt.dao;

import java.util.List;

import com.bjsxt.entity.Student;

public interface StudentDao {

	//学生的请情况
	
	//学生登陆
	Student studentLoginDao(String name,String pwd);
	
	//查询已选课程
	List selectedCourseDao(String sno);
	
	//查询未选课程
	List selectCourseDao(Long sno);
	
	//查询该学生是否已经选择了本门课程
	boolean studentChoosedCourseDao(Long sno,Long cno);
	
	//学生选课
	int addTSCDao(Long sno,Long cno,Long tno);
	
	//取消选择
	int deleteTSCDao(Long sno,Long cno,Long tno);
}
