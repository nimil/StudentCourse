package com.bjsxt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjsxt.entity.Course;
import com.bjsxt.entity.Teacher;
import com.bjsxt.service.impl.TeacherServiceImpl;

public class TeacherServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("TeacherServlet.service()");
		//获取参数，请求方式，业务类型
		String method = req.getMethod().toLowerCase();
		String operation = req.getParameter("operation");
		//判断
		if("get".equals(method)&&"selectteachercourse".equals(operation)){
			selectTeacherCourse(req,resp);
		}else if("get".equals(method)&&"teachermark".equals(operation)){
			teacherMark(req,resp);
		}else if("post".equals(method)&&"pfscore".equals(operation)){
			pfScore(req,resp);
		}else{
			System.out.println("TeacherServlet.service(找不对应的业务)："+method+"--"+operation);
			
		}
	}

	//评分
	private void pfScore(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//获取tno
		Object teacherobj = req.getSession().getAttribute("user");
		//判断
		if(teacherobj!=null){
			//获取tno
			Long tno = ((Teacher)teacherobj).getTno();
			//获取参数信息
			String score = req.getParameter("score");
			String sno = req.getParameter("sno");
			String cno = req.getParameter("cno");
			//调用业务层评分，修改数据
			int n = new TeacherServiceImpl().updateTSCService(Long.parseLong(sno) , tno,  Long.parseLong(cno), Double.parseDouble(score));
			//判断
			if(n>0){
				System.out.println("TeacherServlet.pfScore(评分成功)");
				//响应给ajax
				resp.getWriter().print("true");
			}else{
				System.out.println("TeacherServlet.pfScore(评分失败)");
				//响应给ajax
				resp.getWriter().print("false");
				
			}
		}else{
			System.out.println("TeacherServlet.pfScore(不能从session作用域获取老师的信息)");
			resp.getWriter().print("不能从session作用域获取老师的信息");
		}
		
		
	}

	//查询当前老师下有多少学生选他的课
	private void teacherMark(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//获取tno
		Object teacherobj = req.getSession().getAttribute("user");
		//判断
		if(teacherobj!=null){
			Long tno = ((Teacher)teacherobj).getTno();
			//调用业务层传递tno查询
			List teacherStudentList = new TeacherServiceImpl().selectTeacherStudentService(tno);
			//判断
			if(teacherStudentList!=null&&teacherStudentList.size()>0){
				//存放到req作用域
				req.setAttribute("teacherStudentList", teacherStudentList);
				//请求转发
				req.getRequestDispatcher("teacher/teacherMark.jsp").forward(req, resp);
				return;
			}else{
				System.out.println("TeacherServlet.teacherMark(没有学生选着您的课)");
				resp.getWriter().print("没有学生选着您的课");
			}
		}else{
			System.out.println("TeacherServlet.teacherMark(不能从session作用域获取老师的信息)");
			resp.getWriter().print("不能从session作用域获取老师的信息");
		}
		
	}

	//查询当前老师下有多少门课程
	private void selectTeacherCourse(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
		//获取tno
		Object teacherobj = req.getSession().getAttribute("user");
		//判断
		if(teacherobj!=null){
			Long tno = ((Teacher)teacherobj).getTno();
			//调用业务层传递tno查询
			List<Course> teacherCourseList = new TeacherServiceImpl().selectTeacherCourseService(tno);
			//判断
			if(teacherCourseList!=null&&teacherCourseList.size()>0){
				//存放到req作用域
				req.setAttribute("teacherCourseList", teacherCourseList);
				//请求转发
				req.getRequestDispatcher("teacher/teacherCourseList.jsp").forward(req, resp);
				return;
			}else{
				System.out.println("TeacherServlet.selectTeacherCourse(您没有课)");
				resp.getWriter().print("您没有课");
			}
		}else{
			System.out.println("TeacherServlet.selectTeacherCourse(不能从session作用域获取老师的信息)");
			resp.getWriter().print("不能从session作用域获取老师的信息");
		}
	}

	
}
