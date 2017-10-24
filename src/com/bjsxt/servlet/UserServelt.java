package com.bjsxt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjsxt.entity.Admin;
import com.bjsxt.entity.Student;
import com.bjsxt.entity.Teacher;
import com.bjsxt.service.impl.AdminServiceImpl;
import com.bjsxt.service.impl.StudentServiceImpl;
import com.bjsxt.service.impl.TeacherServiceImpl;

public class UserServelt extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("UserServelt.service()");
		
		//获取参数，请求方式，业务类型
		String method = req.getMethod().toLowerCase();
		String operation = req.getParameter("operation");
		//判断
		if("post".equals(method)&&"login".equals(operation)){
			UserLogin(req,resp);
		}else if("get".equals(method)&&"loginout".equals(operation)){
			UserLoginOut(req,resp);
		}else{
			System.out.println("UserServelt.service(找不对应的业务)："+method+"--"+operation);
			
		}
	}

	//退出登陆
	private void UserLoginOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//销毁session
		req.getSession().removeAttribute("user");
		req.getSession().removeAttribute("role");
		//重定向登陆页面
		resp.sendRedirect(req.getContextPath()+"/login.jsp");
		return;
	}

	//登陆
	private void UserLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取用户名和密码，角色
		String role = req.getParameter("role");
		//判断是哪个角色登陆
		if(role!=null&&!"".equals(role)){
			//添加角色到作用域
			req.getSession().setAttribute("role", role);
			
			String uname = req.getParameter("uname");
			String pwd = req.getParameter("pwd");
			//判断角色
			if("admin".equals(role)){
				//管理登陆
				Admin admin = new AdminServiceImpl().AdminLoginService(uname, pwd);
				//判断
				if(admin!=null){
					System.out.println("UserServelt.UserLogin(查询到管理员)");
					//跳转到主页面index.jsp,把管理员存放到作用域
					req.getSession().setAttribute("user", admin);
					//请求转发
					req.getRequestDispatcher("index.jsp").forward(req, resp);
					return;
				}else{
					System.out.println("UserServelt.UserLogin(查询不到管理员)");
					//设置到req作用域
					req.setAttribute("msg", "找不到该管理员");
					req.getRequestDispatcher("login.jsp").forward(req, resp);
					return;
				}
			}else if("student".equals(role)){
				//学生登录
				Student student = new StudentServiceImpl().studentLoginService(uname, pwd);
				//判断
				if(student!=null){
					System.out.println("UserServelt.UserLogin(查询到学生)");
					//跳转到主页面index.jsp,把管理员存放到作用域
					req.getSession().setAttribute("user", student);
					//请求转发
					req.getRequestDispatcher("index.jsp").forward(req, resp);
					return;
				}else{
					System.out.println("UserServelt.UserLogin(查询不到学生)");
					//设置到req作用域
					req.setAttribute("msg", "找不到该学生");
					req.getRequestDispatcher("login.jsp").forward(req, resp);
					return;
				}
				
			}else if("teacher".equals(role)){
				//教师登陆
				Teacher teacher = new TeacherServiceImpl().teacherLoginService(uname, pwd);
				//判断
				if(teacher!=null){
					System.out.println("UserServelt.UserLogin(查询到教师)");
					//跳转到主页面index.jsp,把管理员存放到作用域
					req.getSession().setAttribute("user", teacher);
					//请求转发
					req.getRequestDispatcher("index.jsp").forward(req, resp);
					return;
				}else{
					System.out.println("UserServelt.UserLogin(查询不到教师)");
					//设置到req作用域
					req.setAttribute("msg", "找不到该教师");
					req.getRequestDispatcher("login.jsp").forward(req, resp);
					return;
				}
			}
		}else{
			System.out.println("UserServelt.UserLogin(请选择角色)");
			//设置到req作用域
			req.setAttribute("msg", "请选择角色");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}
		
	}

	
}
