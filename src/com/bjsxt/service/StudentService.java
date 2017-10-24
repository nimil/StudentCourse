package com.bjsxt.service;

import java.util.List;

import com.bjsxt.entity.Student;

public interface StudentService {

	//学生登陆
	Student studentLoginService(String name,String pwd);
	
	//查询已选课程
	List selectedCourseService(String sno);
	
	//查询未选课程
	List selectCourseService(Long sno);
	
	//查询该学生是否已经选择了本门课程
	boolean studentChoosedCourseService(Long sno,Long cno);
	
	//学生选课
	int addTSCService(Long sno,Long cno,Long tno);
	
	//取消选择
	int deleteTSCService(Long sno,Long cno,Long tno);

}
