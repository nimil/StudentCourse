package com.bjsxt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bjsxt.dao.TeacherDao;
import com.bjsxt.entity.Course;
import com.bjsxt.entity.Student;
import com.bjsxt.entity.Teacher;
import com.bjsxt.util.BjsxtJdbc;

public class TeacherDaoImpl implements TeacherDao{

	//教师登陆
	@Override
	public Teacher teacherLoginDao(String name, String pwd) {
		//声明对象
		Teacher teacher = null;
		//jdbc
		//声明连接
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			//创建连接
			connection = BjsxtJdbc.getConnect();
			//sql
			String sql = "select * from t_teacher where TNAME = ?  and PWD  = ? "; 
			//创建sql命令发送器
			ps = BjsxtJdbc.getPreparedStatement(connection, sql);
			ps.setString(1, name);
			ps.setString(2, pwd);
			//发送sql获取结果集
			resultSet = ps.executeQuery();
			//处理结果
			if(resultSet.next()){
				teacher = new Teacher(resultSet.getLong("tno"),resultSet.getString("tname") , resultSet.getString("pwd"), resultSet.getString("phone"), resultSet.getDate("hiredate") ,resultSet.getString("remark"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BjsxtJdbc.closeAll(resultSet, ps, connection);
		}
		return teacher;
	}

	//查询当前老师下有多少门课程
	@Override
	public List<Course> selectTeacherCourseDao(Long tno) {
		//声明对象
		List<Course> teacherCourseList = new ArrayList<>();
		//jdbc
		//声明连接
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			//创建连接
			connection = BjsxtJdbc.getConnect();
			//sql
			String sql = "select c.* from t_course c join t_tc tc on c.cno = tc.cno where tno = ? "; 
			//创建sql命令发送器
			ps = BjsxtJdbc.getPreparedStatement(connection, sql);
			ps.setLong(1, tno);
			//发送sql获取结果集
			resultSet = ps.executeQuery();
			//处理结果
			while(resultSet.next()){
				Course course = new Course(resultSet.getLong("cno"), resultSet.getString("name") ,resultSet.getInt("credit"),resultSet.getDate("periodstart"),resultSet.getDate("periodend"));
				teacherCourseList.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BjsxtJdbc.closeAll(resultSet, ps, connection);
		}
		return teacherCourseList;
	}

	//查询当前老师下有多少学生选他的课
	@Override
	public List selectTeacherStudentDao(Long tno) {
		//声明对象
		List teacherStudentList = new ArrayList();
		//jdbc
		//声明连接
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			//创建连接
			connection = BjsxtJdbc.getConnect();
			//sql
			//String sql = "select c.*,t.* from t_course c join t_sc sc on c.cno = sc.cno join t_teacher t on sc.tno = t.tno where sno = ? "; 
			String sql = "select c.*,s.*,sc.score from t_student s  join t_sc sc on s.sno = sc.sno  join t_course c on sc.cno = c.cno  where sc.tno = ? ";
			//创建sql命令发送器
			ps = BjsxtJdbc.getPreparedStatement(connection, sql);
			ps.setLong(1, tno);
			//发送sql获取结果集
			resultSet = ps.executeQuery();
			
			//处理结果
			while(resultSet.next()){
				//创建集合对象
				ArrayList<Object> objlist = new ArrayList<>(); 
				Object cno = resultSet.getObject("cno");
				Object name = resultSet.getObject("name");
				Object credit = resultSet.getObject("credit");
				Object periodstart = resultSet.getObject("periodstart");
				Object periodend = resultSet.getObject("periodend");
				
				Object sno = resultSet.getObject("sno");
				Object sname = resultSet.getObject("sname");
				Object pwd = resultSet.getObject("pwd");
				Object phone = resultSet.getObject("phone");
				Object gender = resultSet.getObject("gender");
				Object birth = resultSet.getObject("birth");
				Object clazzno = resultSet.getObject("clazzno");
				Object remark = resultSet.getObject("remark");
				
				double score = resultSet.getDouble("score");
				//添加到集合中
				objlist.add(cno);
				objlist.add(name);
				objlist.add(credit);
				objlist.add(periodstart);
				objlist.add(periodend);
				
				objlist.add(sno);
				objlist.add(sname);
				objlist.add(pwd);
				objlist.add(phone);
				objlist.add(gender);
				objlist.add(birth);
				objlist.add(clazzno);
				objlist.add(remark);
				objlist.add(score);
				//把小集合放到大集合中
				teacherStudentList.add(objlist);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BjsxtJdbc.closeAll(resultSet, ps, connection);
		}
		return teacherStudentList;			
	}

	//评分
	@Override
	public int updateTSCDao(Long sno, Long tno, Long cno, double score) {
		//sql
		String sql = "update t_sc set score = ? where sno = ? and tno= ? and cno = ? ";
		//数组
		Object[] obj = {score,sno,tno,cno};
		return BjsxtJdbc.excuteDML(sql, obj);
	}

}
