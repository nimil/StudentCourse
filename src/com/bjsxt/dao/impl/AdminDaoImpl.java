package com.bjsxt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bjsxt.dao.AdminDao;
import com.bjsxt.entity.Admin;
import com.bjsxt.entity.Clazz;
import com.bjsxt.entity.Course;
import com.bjsxt.entity.Student;
import com.bjsxt.entity.Teacher;
import com.bjsxt.util.BjsxtJdbc;
import com.bjsxt.util.SxtPageUtil;

public class AdminDaoImpl implements AdminDao{

	//管理员登录
	@Override
	public Admin AdminLoginDao(String name, String pwd) {
		//声明对象
		Admin admin = null;
		//jdbc
		//声明连接
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			//创建连接
			connection = BjsxtJdbc.getConnect();
			//sql
			String sql = "select * from t_admin where USERNAME = ?  and PASSWORD  = ? "; 
			//创建sql命令发送器
			ps = BjsxtJdbc.getPreparedStatement(connection, sql);
			ps.setString(1, name);
			ps.setString(2, pwd);
			//发送sql获取结果集
			resultSet = ps.executeQuery();
			//处理结果
			if(resultSet.next()){
				admin = new Admin(resultSet.getString(1),resultSet.getString(2) , resultSet.getString(3), resultSet.getInt(4), resultSet.getDouble(5) ,resultSet.getDate(6) , resultSet.getString(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BjsxtJdbc.closeAll(resultSet, ps, connection);
		}
		return admin;
	}

	//添加管理员
	@Override
	public int addAdminDao(Admin a) {
		//sql
		String sql = "insert into t_admin values(?,?,?,?,?,?,?)";
		//数组
		Object[] objs = {a.getUserid(),a.getUsername(),a.getPassword(),a.getAge(),a.getScore(),a.getEnterdate(),a.getHobby()};
		return BjsxtJdbc.excuteDML(sql, objs);
	}

	//查询所有的班级信息
	@Override
	public List<Clazz> selctAllClazzDao() {
		//声明对象
		List<Clazz> clazzList = new ArrayList<>();
		//jdbc
		//声明连接
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			//创建连接
			connection = BjsxtJdbc.getConnect();
			//sql
			String sql = "select * from t_clazz "; 
			//创建sql命令发送器
			ps = BjsxtJdbc.getPreparedStatement(connection, sql);
			//发送sql获取结果集
			resultSet = ps.executeQuery();
			//处理结果
			while(resultSet.next()){
				Clazz clazz = new Clazz(resultSet.getLong("clazzno"), resultSet.getString("cname") ,resultSet.getString("chteacher"),resultSet.getLong("clazzroom"));
				clazzList.add(clazz);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BjsxtJdbc.closeAll(resultSet, ps, connection);
		}
		return clazzList;
	}

	//添加学生信息
	@Override
	public int addStudentDao(Student s) {
		//sql
		String sql = "insert into t_student(sno,sname,pwd,phone,gender,birth,clazzno,remark) values (seq_student_sno.nextval,?,?,?,?,?,?,?) ";
		//数组
		Object[] obj = {s.getSname(),s.getPwd(),s.getPhone(),s.getGender(),s.getBirth(),s.getClazzno(),s.getRemark()};
		return BjsxtJdbc.excuteDML(sql, obj);
	}

	//查询学生的记录数
	@Override
	public int selectTotalStudentDao() {
		//声明一个变量
		int n = 0;
		//jdbc
		//声明连接
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			//创建连接
			connection = BjsxtJdbc.getConnect();
			//sql
			String sql = "select count(*) from t_student "; 
			//创建sql命令发送器
			ps = BjsxtJdbc.getPreparedStatement(connection, sql);
			//发送sql获取结果集
			resultSet = ps.executeQuery();
			//处理结果
			if(resultSet.next()){
				n = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BjsxtJdbc.closeAll(resultSet, ps, connection);
		}
		return n;
	}

	//查询所有学生信息-分页
	@Override
	public void selectAllStudentDao(SxtPageUtil<Student> sxtPageUtil) {
		//声明对象
		List<Student> studentList = new ArrayList<>();
		//jdbc
		//声明连接
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			//创建连接
			connection = BjsxtJdbc.getConnect();
			//sql
			String sql = "select * from( "
							+ "select rownum r, t.* from( "
								+ "select rownum, s.* from t_student s "
							+ ") t where rownum<= "+(sxtPageUtil.getNum()*sxtPageUtil.getSize())
					+ ") where r> "+sxtPageUtil.getStart(); 
			//创建sql命令发送器
			ps = BjsxtJdbc.getPreparedStatement(connection, sql);
			//发送sql获取结果集
			resultSet = ps.executeQuery();
			//处理结果
			while(resultSet.next()){
				Student student = new Student(resultSet.getLong("sno"), resultSet.getString("sname") , 
						resultSet.getString("pwd") ,resultSet.getString("phone"),
						resultSet.getString("gender"),resultSet.getDate("birth"),
						resultSet.getLong("clazzno"), resultSet.getString("remark"));
				//存放到集合
				studentList.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BjsxtJdbc.closeAll(resultSet, ps, connection);
		}
		//添加到分页的封装对象中
		sxtPageUtil.setList(studentList);
		
	}

	//模糊查询总记录数
	@Override
	public int selectLikeTotalStudentDao(String name, String clazz) {
		//声明一个变量
		int n = 0;
		//jdbc
		//声明连接
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			//创建连接
			connection = BjsxtJdbc.getConnect();
			//sql
			StringBuilder sql =  new StringBuilder("select count(*) from t_student where 1=1 "); 
			//判断name，clazz是否为空拼接sql语句
			if((name!=null&&!"".equals(name))&&(clazz!=null&&!"".equals(clazz))){//都不为空
				sql.append(" and sname like ? and clazzno = ? ");
				//创建sql命令发送器
				ps = BjsxtJdbc.getPreparedStatement(connection, sql);
				ps.setString(1, "%"+name+"%");
				ps.setLong(2, Long.parseLong(clazz));
			}else if((name==null||"".equals(name))&&(clazz!=null&&!"".equals(clazz))){//name为空，clazz不为空
				sql.append(" and clazzno = ? ");
				//创建sql命令发送器
				ps = BjsxtJdbc.getPreparedStatement(connection, sql);
				ps.setLong(1, Long.parseLong(clazz));
			}else if((name!=null&&!"".equals(name))&&(clazz==null||"".equals(clazz))){//name不为空，clazz为空
				sql.append(" and sname like ? ");
				//创建sql命令发送器
				ps = BjsxtJdbc.getPreparedStatement(connection, sql);
				ps.setString(1, "%"+name+"%");
			}else{
				//创建sql命令发送器
				ps = BjsxtJdbc.getPreparedStatement(connection, sql);
			}
			//发送sql获取结果集
			resultSet = ps.executeQuery();
			//处理结果
			if(resultSet.next()){
				n = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BjsxtJdbc.closeAll(resultSet, ps, connection);
		}
		return n;
	}

	//模糊查询学生信息
	@Override
	public void selectLikeStudentDao(SxtPageUtil<Student> sxtPageUtil,
			String name, String clazz) {
		//声明对象
		List<Student> studentList = new ArrayList<>();
		//jdbc
		//声明连接
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			//创建连接
			connection = BjsxtJdbc.getConnect();
			//sql
			StringBuilder sql = new StringBuilder("select * from( select rownum r, t.* from( select rownum, s.* from t_student s where 1=1 ");
						
			String sql2 = " ) t where rownum<= "+(sxtPageUtil.getNum()*sxtPageUtil.getSize())+") where r> "+sxtPageUtil.getStart();
			//判断name，clazz是否为空拼接sql语句
			if((name!=null&&!"".equals(name))&&(clazz!=null&&!"".equals(clazz))){//都不为空
				sql.append(" and sname like ? and clazzno = ? ");
				sql.append(sql2);
				//创建sql命令发送器
				ps = BjsxtJdbc.getPreparedStatement(connection, sql);
				ps.setString(1, "%"+name+"%");
				ps.setLong(2, Long.parseLong(clazz));
			}else if((name==null||"".equals(name))&&(clazz!=null&&!"".equals(clazz))){//name为空，clazz不为空
				sql.append(" and clazzno = ? ");
				sql.append(sql2);
				//创建sql命令发送器
				ps = BjsxtJdbc.getPreparedStatement(connection, sql);
				ps.setLong(1, Long.parseLong(clazz));
			}else if((name!=null&&!"".equals(name))&&(clazz==null||"".equals(clazz))){//name不为空，clazz为空
				sql.append(" and sname like ? ");
				sql.append(sql2);
				//创建sql命令发送器
				ps = BjsxtJdbc.getPreparedStatement(connection, sql);
				ps.setString(1, "%"+name+"%");
			}else{
				//创建sql命令发送器
				ps = BjsxtJdbc.getPreparedStatement(connection, sql);
				sql.append(sql2);
			}
			//发送sql获取结果集
			resultSet = ps.executeQuery();
			//处理结果
			while(resultSet.next()){
				Student student = new Student(resultSet.getLong("sno"), resultSet.getString("sname") , 
						resultSet.getString("pwd") ,resultSet.getString("phone"),
						resultSet.getString("gender"),resultSet.getDate("birth"),
						resultSet.getLong("clazzno"), resultSet.getString("remark"));
				//存放到集合
				studentList.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BjsxtJdbc.closeAll(resultSet, ps, connection);
		}
		//添加到分页的封装对象中
		sxtPageUtil.setList(studentList);
		
	}

	//添加班级
	@Override
	public int addCourseDao(Course c) {
		//sql
		String sql = "insert into t_course(CNO,NAME,CREDIT,PERIODSTART,PERIODEND) values(seq_course_cno.nextval,?,?,?,?) ";
		//数组
		Object[] obj = {c.getName(),c.getCredit(),c.getPeriodstart(),c.getPeriodend()};
		return BjsxtJdbc.excuteDML(sql, obj);
	}

	//查询所有的课程
	@Override
	public List<Course> selectCourseDao() {
		//声明对象
		List<Course> courseList = new ArrayList<>();
		//jdbc
		//声明连接
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			//创建连接
			connection = BjsxtJdbc.getConnect();
			//sql
			String sql = "select * from t_course "; 
			//创建sql命令发送器
			ps = BjsxtJdbc.getPreparedStatement(connection, sql);
			//发送sql获取结果集
			resultSet = ps.executeQuery();
			//处理结果
			while(resultSet.next()){
				Course course = new Course(resultSet.getLong("cno"), resultSet.getString("name") ,resultSet.getInt("credit"),resultSet.getDate("periodstart"),resultSet.getDate("periodend"));
				courseList.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BjsxtJdbc.closeAll(resultSet, ps, connection);
		}
		return courseList;
	}

	//查询所有的老师
	@Override
	public List<Teacher> selectTeacherDao() {
		//声明对象
		List<Teacher> teacherList = new ArrayList<>();
		//jdbc
		//声明连接
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			//创建连接
			connection = BjsxtJdbc.getConnect();
			//sql
			String sql = "select * from t_teacher "; 
			//创建sql命令发送器
			ps = BjsxtJdbc.getPreparedStatement(connection, sql);
			//发送sql获取结果集
			resultSet = ps.executeQuery();
			//处理结果
			while(resultSet.next()){
				Teacher teacher = new Teacher(resultSet.getLong("tno"), resultSet.getString("tname") ,resultSet.getString("pwd"),resultSet.getString("phone"),resultSet.getDate("hiredate"),resultSet.getString("remark"));
				teacherList.add(teacher);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BjsxtJdbc.closeAll(resultSet, ps, connection);
		}
		return teacherList;
	}

	//查询所有的课程包括授课老师
	@Override
	public List selectCourseTeacherDao() {
		//声明对象
		List courseteacherList = new ArrayList();
		//jdbc
		//声明连接
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			//创建连接
			connection = BjsxtJdbc.getConnect();
			//sql
			String sql = "select c.*,t.* from t_course c left join t_tc tc on c.cno = tc.cno left join t_teacher t on tc.tno = t.tno "; 
			//创建sql命令发送器
			ps = BjsxtJdbc.getPreparedStatement(connection, sql);
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
				courseteacherList.add(objlist);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BjsxtJdbc.closeAll(resultSet, ps, connection);
		}
		return courseteacherList;
	}

	//查询课程和老师的中间表T_TC
	@Override
	public boolean selectTCDao(String courseid, String teacherid) {
		//jdbc
		//声明连接
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			//创建连接
			connection = BjsxtJdbc.getConnect();
			//sql
			String sql = "select * from t_tc where cno = ? and tno = ? "; 
			//创建sql命令发送器
			ps = BjsxtJdbc.getPreparedStatement(connection, sql);
			ps.setLong(1, Long.parseLong(courseid));
			ps.setLong(2, Long.parseLong(teacherid));
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

	//添加数据T_TC
	@Override
	public int addTCDao(String courseid, String teacherid) {
		//sql
		String sql = "insert into t_tc(cno,tno) values(?,?)";
		//数组
		Object[] obj = {courseid,teacherid};
		return BjsxtJdbc.excuteDML(sql, obj);
	}

	//添加老师
	@Override
	public int addTeacherDao(Teacher t) {
		//sql
		String sql = "insert into t_teacher(tno,tname,pwd,phone,hiredate,remark) values(seq_teacher_tno.nextval,?,?,?,?,?)";		
		//数组
		Object[] obj = {t.getTname(),t.getPwd(),t.getPhone(),t.getHiredate(),t.getRemark()};
		return BjsxtJdbc.excuteDML(sql, obj);
	}

}
