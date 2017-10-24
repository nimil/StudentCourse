package com.bjsxt.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjsxt.entity.Admin;
import com.bjsxt.entity.Clazz;
import com.bjsxt.entity.Course;
import com.bjsxt.entity.Student;
import com.bjsxt.entity.Teacher;
import com.bjsxt.service.AdminService;
import com.bjsxt.service.impl.AdminServiceImpl;
import com.bjsxt.util.BjsxtUtil;
import com.bjsxt.util.SxtPageUtil;
import com.google.gson.Gson;

public class AdminServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("AdminServlet.service()");
		//获取参数，请求方式，业务类型
		String method = req.getMethod().toLowerCase();
		String operation = req.getParameter("operation");
		//判断
		if("post".equals(method)&&"addadmin".equals(operation)){
			addAdmin(req,resp);
		}else if("post".equals(method)&&"selectallclazz".equals(operation)){
			selectAllClazz(req,resp);
		}else if("post".equals(method)&&"selectallclazz".equals(operation)){
			selectAllClazz(req,resp);
		}else if("post".equals(method)&&"addstudent".equals(operation)){
			addStudent(req,resp);
		}else if("get".equals(method)&&"selectallstudent".equals(operation)){
			selectAllStudent(req,resp);
		}else if("get".equals(method)&&"selectlikestudent".equals(operation)){
			selectLikeStudent(req,resp);
		}else if("post".equals(method)&&"addcourse".equals(operation)){
			addCourse(req,resp);
		}else if("get".equals(method)&&"selectcourse".equals(operation)){
			selectCourse(req,resp);
		}else if("get".equals(method)&&"selectteacher".equals(operation)){
			selectTeacher(req,resp);
		}else if("get".equals(method)&&"selectcourseteacher".equals(operation)){
			selectCourseTeacher(req,resp);
		}else if("post".equals(method)&&"insertTC".equals(operation)){
			insertTC(req,resp);
		}else if("post".equals(method)&&"addteacher".equals(operation)){
			addTeacher(req,resp);
		}else if("get".equals(method)&&"selectteacher2".equals(operation)){
			selectTeacher2(req,resp);
		}else{
			System.out.println("AdminServlet.service(找不对应的业务)："+method+"--"+operation);
			
		}
	}

	//查询所有的老师
	private void selectTeacher2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//创建业务层对象
		AdminService adminService = new AdminServiceImpl();
		//调用业务层
		List<Teacher> teacherList = adminService.selectTeacherService();
		//判断
		if(teacherList!=null&&teacherList.size()>0){
			System.out.println("AdminServlet.selectTeacher2(查询到所有的老师)");
			//把老师的信息存放到作用域
			req.setAttribute("teacherList", teacherList);
			//请求转发到teacherList.jsp
			req.getRequestDispatcher("admin/teacherList.jsp").forward(req, resp);
			return;
		}else{
			System.out.println("AdminServlet.selectTeacher2(查询不到所有的老师)");
			resp.getWriter().print("查询不到所有的老师");
		}
		
	}

	//添加老师
	private void addTeacher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//获取请求参数
		String tname = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		String phone = req.getParameter("phone");
		String hiredate = req.getParameter("hiredate");
		String remark = req.getParameter("remark");
		//创建老师对象
		Teacher teacher = new Teacher(tname, pwd, phone, java.sql.Date.valueOf(hiredate), remark);
		//创建业务层对象
		AdminService adminService = new AdminServiceImpl();
		int n = adminService.addTeacherService(teacher);
		//判断
		if(n>0){
			System.out.println("AdminServlet.addTeacher(添加成功)");
			resp.getWriter().print("添加成功");
		}else{
			System.out.println("AdminServlet.addTeacher(添加失败)");
			resp.getWriter().print("添加失败");
		}
		
		
	}

	//给老师分配课程
	private void insertTC(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//获取请求参数
		String courseid = req.getParameter("courseid");
		String teacherid = req.getParameter("teacherid");
		//给老师分配课程首先先查询是否已经分配过了，如果分配过，给提示（已经分配），如果没有做插入操作
		//创建业务层对象
		AdminService adminService = new AdminServiceImpl();
		//调用业务层
		boolean bool = adminService.selectTCService(courseid, teacherid);
		//判断
		if(bool){
			System.out.println("AdminServlet.insertTC(已经分配过了)");
			//响应给ajax
			resp.getWriter().print("true");
		}else{
			System.out.println("AdminServlet.insertTC(未分配)");
			//添加操作
			int n = adminService.addTCService(courseid, teacherid);
			//判断
			if(n>0){
				System.out.println("AdminServlet.insertTC(分配成功)");
				//响应给ajax
				resp.getWriter().print("success");
			}else{
				System.out.println("AdminServlet.insertTC(分配失败)");
				//响应给ajax
				resp.getWriter().print("fail");
			}
			
		}
	}
	
	
	//查询所有的课程包括授课老师
	private void selectCourseTeacher(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		//创建业务层对象
		AdminService adminService = new AdminServiceImpl();
		//调用业务层
		List courseteacherList = adminService.selectCourseTeacherService();
		//判断
		if(courseteacherList!=null&&courseteacherList.size()>0){
			System.out.println("AdminServlet.selectCourseTeacher(查询到所有的课程包括授课老师):"+courseteacherList);
			//存放到req作用域
			req.setAttribute("courseteacherList", courseteacherList);
			req.getRequestDispatcher("admin/courseList.jsp").forward(req, resp);
			return;
		}else{
			System.out.println("AdminServlet.selectCourseTeacher(查询不到所有的课程包括授课老师)");
			resp.getWriter().print("查询不到所有的课程包括授课老师");
		}
		
	}

	//查询所有的老师
	private void selectTeacher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//创建业务层对象
		AdminService adminService = new AdminServiceImpl();
		//调用业务层
		List<Teacher> teacherList = adminService.selectTeacherService();
		//判断
		if(teacherList!=null&&teacherList.size()>0){
			System.out.println("AdminServlet.selectTeacher(查询到所有的老师)");
			//响应给ajax
			resp.getWriter().print(new Gson().toJson(teacherList));
		}else{
			System.out.println("AdminServlet.selectCourse(查询不到所有的老师)");
			resp.getWriter().print("");
		}
		
	}

	//查询所有的课程
	private void selectCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//创建业务层对象
		AdminService adminService = new AdminServiceImpl();
		//调用业务层
		List<Course> courseList = adminService.selectCourseDao();
		//判断
		if(courseList!=null&&courseList.size()>0){
			System.out.println("AdminServlet.selectCourse(查询到所有的课程)");
			//响应给ajax
			resp.getWriter().print(new Gson().toJson(courseList));
		}else{
			System.out.println("AdminServlet.selectCourse(查询不到所有的课程)");
			resp.getWriter().print("");
		}
	}

	//添加课程
	private void addCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//获取参数
		String name = req.getParameter("name");
		String credit = req.getParameter("credit");
		String periodstart = req.getParameter("periodstart");
		String periodend = req.getParameter("periodend");
		//创建课程对象
		Course course = new Course(name,Integer.parseInt(credit),java.sql.Date.valueOf(periodstart) ,java.sql.Date.valueOf(periodend));
		//传递给业务层
		int n = new AdminServiceImpl().addCourseService(course);
		//判断
		if(n>0){
			System.out.println("AdminServlet.addCourse(添加课程成功)");
			resp.getWriter().print("添加课程成功");
		}else{
			System.out.println("AdminServlet.addCourse(添加课程失败)");
			resp.getWriter().print("添加课程失败");
			
		}
		
	}

	//模糊查询
	private void selectLikeStudent(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		//创建业务层对象
		AdminService adminService = new AdminServiceImpl();
				
		//获取查询的字段
		String sname = req.getParameter("sname");
		String clazz = req.getParameter("clazz");
		//当前页面数
		String numString = req.getParameter("num");
		//每页显示多少条
		String sizeString = req.getParameter("size");
		//一共有多少条记录
		int total = adminService.selectLikeTotalStudentService(sname, clazz);
		//创建分页对象
		SxtPageUtil<Student> studentListPage = new SxtPageUtil<>(numString, sizeString, total);
		//把分页对象传递给业务层做分页
		adminService.selectLikeStudentService(studentListPage, sname, clazz);
		//把分页对象存放到req作用域，请求转发给studentList.jsp页面
		if(studentListPage.getList()!=null&&studentListPage.getList().size()>0){
			
			//把name，clazz存放到req作用域传给studentList2.jsp
			req.setAttribute("sname", sname);
			req.setAttribute("clazzno", clazz);
			
			//查询所有的班级信息
			List<Clazz> clazzList = adminService.selctAllClazzService();
			//存放到作用域
			req.setAttribute("clazzList", clazzList);
			
			System.out.println("AdminServlet.selectLikeStudent(查询到学生的信息)");
			req.setAttribute("studentListPage", studentListPage);
			req.getRequestDispatcher("admin/studentList2.jsp").forward(req, resp);
			return;
		}else{
			System.out.println("AdminServlet.selectLikeStudent(查询不到学生的信息)");
			resp.getWriter().print("查询不到学生的信息");
		}
	}

	//查询所有的学生
	private void selectAllStudent(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		//创建业务层对象
		AdminService adminService = new AdminServiceImpl();
		
		//当前页面数
		String numString = req.getParameter("num");
		//每页显示多少条
		String sizeString = req.getParameter("size");
		//一共有多少条记录
		int total = adminService.selectTotalStudentService();
		//创建分页对象
		SxtPageUtil<Student> studentListPage = new SxtPageUtil<>(numString, sizeString, total);
		//把分页对象传递给业务层做分页
		adminService.selectAllStudentService(studentListPage);
		//把分页对象存放到req作用域，请求转发给studentList.jsp页面
		if(studentListPage.getList()!=null&&studentListPage.getList().size()>0){
			
			//查询所有的班级信息
			List<Clazz> clazzList = adminService.selctAllClazzService();
			//存放到作用域
			req.setAttribute("clazzList", clazzList);
			
			System.out.println("AdminServlet.selectAllStudent(查询到学生的信息)");
			req.setAttribute("studentListPage", studentListPage);
			req.getRequestDispatcher("admin/studentList.jsp").forward(req, resp);
			return;
		}else{
			System.out.println("AdminServlet.selectAllStudent(查询不到学生的信息)");
			resp.getWriter().print("查询不到学生的信息");
		}
	}

	//添加学生信息
	private void addStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//获取请求参数
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		String phone = req.getParameter("phone");
		String sex = req.getParameter("sex");
		String birth = req.getParameter("birth");
		String clazzno = req.getParameter("clazz");
		String remark = req.getParameter("remark");
		//创建学生id--用orcale中的序列
		Student student = new Student(uname, pwd, phone, sex, java.sql.Date.valueOf(birth),Long.parseLong(clazzno), remark);
		//调用业务层
		int n = new AdminServiceImpl().addStudentService(student);
		//判断
		if(n>0){
			System.out.println("AdminServlet.addStudent(添加成功)");
			resp.getWriter().print("添加成功");
		}else{
			System.out.println("AdminServlet.addStudent(添加失败)");
			resp.getWriter().print("添加失败");
		}
		
		
		
		
		
	}

	//查询所有的班级信息
	private void selectAllClazz(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		List<Clazz> clazzList = new AdminServiceImpl().selctAllClazzService();
		//判断
		if(clazzList!=null&&clazzList.size()>0){
			//响应ajax
			String clazzListJson = new Gson().toJson(clazzList);
			resp.getWriter().print(clazzListJson);
		}else{
			System.out.println("AdminServlet.selectAllClazz(查询不到班级的信息)");
			resp.getWriter().print("");
		}
		
	}

	//添加管理员
	private void addAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//获取请求参数
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		String age = req.getParameter("age");
		String score = req.getParameter("score");
		String enterdate = req.getParameter("enterdate");
		String[] hobbies = req.getParameterValues("fav");
		//创建uuid
		String userid = BjsxtUtil.createuuid();
		//创建管理员对象
		Admin admin = new Admin(userid, uname, pwd, Integer.parseInt(age), Double.parseDouble(score), java.sql.Date.valueOf(enterdate), Arrays.toString(hobbies));
		//调用业务层传递admin
		int n = new AdminServiceImpl().addAdminService(admin);
		//判断
		if(n>0){
			System.out.println("AdminServlet.addAdmin(添加完成)");
			resp.getWriter().print("添加完成");
		}else{
			System.out.println("AdminServlet.addAdmin(添加失败)");
			resp.getWriter().print("添加失败");
		}
	}

	
}
