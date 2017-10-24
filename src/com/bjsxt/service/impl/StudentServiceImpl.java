package com.bjsxt.service.impl;

import java.util.List;

import com.bjsxt.dao.StudentDao;
import com.bjsxt.dao.impl.StudentDaoImpl;
import com.bjsxt.entity.Student;
import com.bjsxt.service.StudentService;

public class StudentServiceImpl implements StudentService{

	//声明dao对象
	StudentDao studentDao = new StudentDaoImpl();
	//学生登陆
	@Override
	public Student studentLoginService(String name, String pwd) {
		
		return studentDao.studentLoginDao(name, pwd);
	}
	
	//查询已选课程
	@Override
	public List selectedCourseService(String sno) {
		
		return studentDao.selectedCourseDao(sno);
	}

	//查询未选课程
	@Override
	public List selectCourseService(Long sno) {
		
		return studentDao.selectCourseDao(sno);
	}

	//查询该学生是否已经选择了本门课程
	@Override
	public boolean studentChoosedCourseService(Long sno, Long cno) {
		
		return studentDao.studentChoosedCourseDao(sno, cno);
	}

	//学生选课
	@Override
	public int addTSCService(Long sno, Long cno, Long tno) {
		//System.out.println("StudentServiceImpl.addTSCService():"+sno+"--"+cno+"--"+tno);
		return studentDao.addTSCDao(sno, cno, tno);
	}

	//取消选择
	@Override
	public int deleteTSCService(Long sno, Long cno, Long tno) {
		
		return studentDao.deleteTSCDao(sno, cno, tno);
	}

}
