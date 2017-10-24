package com.bjsxt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bjsxt.dao.StudentDao;
import com.bjsxt.entity.Admin;
import com.bjsxt.entity.Student;
import com.bjsxt.util.BjsxtJdbc;

public class StudentDaoImpl implements StudentDao{

	//学生登陆
	@Override
	public Student studentLoginDao(String name, String pwd) {
		//声明对象
		Student student = null;
		//jdbc
		//声明连接
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			//创建连接
			connection = BjsxtJdbc.getConnect();
			//sql
			String sql = "select * from t_student where SNAME = ?  and PWD  = ? "; 
			//创建sql命令发送器
			ps = BjsxtJdbc.getPreparedStatement(connection, sql);
			ps.setString(1, name);
			ps.setString(2, pwd);
			//发送sql获取结果集
			resultSet = ps.executeQuery();
			//处理结果
			if(resultSet.next()){
				student = new Student(resultSet.getLong("sno"),resultSet.getString("sname") , resultSet.getString("pwd"), resultSet.getString("phone"), resultSet.getString("gender") ,resultSet.getDate("birth") , resultSet.getLong("clazzno"),resultSet.getString("remark"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BjsxtJdbc.closeAll(resultSet, ps, connection);
		}
		return student;
	}

	//查询已选课程
	@Override
	public List selectedCourseDao(String sno) {
		//声明对象
		List selectedCourseList = new ArrayList();
		//jdbc
		//声明连接
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			//创建连接
			connection = BjsxtJdbc.getConnect();
			//sql
			String sql = "select c.*,t.* from t_course c join t_sc sc on c.cno = sc.cno join t_teacher t on sc.tno = t.tno where sno = ? "; 
			//创建sql命令发送器
			ps = BjsxtJdbc.getPreparedStatement(connection, sql);
			ps.setLong(1, Long.parseLong(sno));
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
				Object tno = resultSet.getObject("tno");
				Object tname = resultSet.getObject("tname");
				Object pwd = resultSet.getObject("pwd");
				Object phone = resultSet.getObject("phone");
				Object hiredate = resultSet.getObject("hiredate");
				Object remark = resultSet.getObject("remark");
				//添加到集合中
				objlist.add(cno);
				objlist.add(name);
				objlist.add(credit);
				objlist.add(periodstart);
				objlist.add(periodend);
				objlist.add(tno);
				objlist.add(tname);
				objlist.add(pwd);
				objlist.add(phone);
				objlist.add(hiredate);
				objlist.add(remark);
				//把小集合放到大集合中
				selectedCourseList.add(objlist);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BjsxtJdbc.closeAll(resultSet, ps, connection);
		}
		return selectedCourseList;		
	}

	//查询未选课程
	@Override
	public List selectCourseDao(Long sno) {
		//声明对象
		List selectCourseList = new ArrayList();
		//jdbc
		//声明连接
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			//创建连接
			connection = BjsxtJdbc.getConnect();
			//sql
			String sql = "select c.*,t.* from t_course c join t_tc tc on c.cno = tc.cno join t_teacher t on tc.tno = t.tno "
						+ "minus "
						+ "select c.*,t.* from t_course c join t_sc sc on c.cno = sc.cno join t_teacher t on sc.tno = t.tno where sno = ? "; 
			//创建sql命令发送器
			ps = BjsxtJdbc.getPreparedStatement(connection, sql);
			ps.setLong(1, sno);
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
				Object tno = resultSet.getObject("tno");
				Object tname = resultSet.getObject("tname");
				Object pwd = resultSet.getObject("pwd");
				Object phone = resultSet.getObject("phone");
				Object hiredate = resultSet.getObject("hiredate");
				Object remark = resultSet.getObject("remark");
				//添加到集合中
				objlist.add(cno);
				objlist.add(name);
				objlist.add(credit);
				objlist.add(periodstart);
				objlist.add(periodend);
				objlist.add(tno);
				objlist.add(tname);
				objlist.add(pwd);
				objlist.add(phone);
				objlist.add(hiredate);
				objlist.add(remark);
				//把小集合放到大集合中
				selectCourseList.add(objlist);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BjsxtJdbc.closeAll(resultSet, ps, connection);
		}
		return selectCourseList;		
	
	}

	//查询该学生是否已经选择了本门课程
	@Override
	public boolean studentChoosedCourseDao(Long sno, Long cno) {
		//jdbc
		//声明连接
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			//创建连接
			connection = BjsxtJdbc.getConnect();
			//sql
			String sql = "select * from t_sc where sno = ?  and cno  = ? "; 
			//创建sql命令发送器
			ps = BjsxtJdbc.getPreparedStatement(connection, sql);
			ps.setLong(1, sno);
			ps.setLong(2, cno);
			//发送sql获取结果集
			resultSet = ps.executeQuery();
			//处理结果
			if(resultSet.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BjsxtJdbc.closeAll(resultSet, ps, connection);
		}
		return false;
	}

	//学生选课
	@Override
	public int addTSCDao(Long sno, Long cno, Long tno) {
		//sql
		String sql = "insert into t_sc(sno,cno,tno) values(?,?,?) ";
		//数组
		Object[] obj = {sno,cno,tno};
		return BjsxtJdbc.excuteDML(sql, obj);
	}

	//取消选择
	@Override
	public int deleteTSCDao(Long sno, Long cno, Long tno) {
		//sql
		String sql = "delete from t_sc where sno = ?  and cno = ?  and tno = ? ";
		
		//数组
		Object[] obj = {sno,cno,tno};
		return BjsxtJdbc.excuteDML(sql, obj);
	}

}
