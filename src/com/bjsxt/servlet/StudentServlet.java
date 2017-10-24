package com.bjsxt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjsxt.entity.Student;
import com.bjsxt.service.StudentService;
import com.bjsxt.service.impl.StudentServiceImpl;

public class StudentServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("StudentServlet.service()");
		//获取参数，请求方式，业务类型
		String method = req.getMethod().toLowerCase();
		String operation = req.getParameter("operation");
		//判断
		if("get".equals(method)&&"selectedcourse".equals(operation)){
			selectedCourse(req,resp);
		}else if("get".equals(method)&&"selectcourse".equals(operation)){
			selectCourse(req,resp);
		}else if("post".equals(method)&&"choosecourse".equals(operation)){
			chooseCourse(req,resp);
		}else if("post".equals(method)&&"deletetsc".equals(operation)){
			deleteTSC(req,resp);
		}else{
			System.out.println("StudentServlet.service(找不对应的业务)："+method+"--"+operation);
			
		}
	}

	//取消选择
	private void deleteTSC(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//创建业务层对象
		StudentService studentService = new StudentServiceImpl();
		//获取学生的id
		Object studentObj = req.getSession().getAttribute("user");
		if(studentObj!=null){
			//获取学生的id
			long sno = ((Student)studentObj).getSno();
			//获取请求参数
			String courseid = req.getParameter("courseid"); 
			String teacherid = req.getParameter("teacherid");
			//删除操作
			int n = studentService.deleteTSCService(sno, Long.parseLong(courseid), Long.parseLong(teacherid));
			//判断
			if(n>0){
				System.out.println("StudentServlet.deleteTSC(取消选课成功)");
				//响应给ajax
				resp.getWriter().print("true");
			}else{
				System.out.println("StudentServlet.deleteTSC(取消选课失败)");
				//响应给ajax
				resp.getWriter().print("fail");
			}
			
		}else{
			resp.getWriter().print("获取不到学生的session作用域里面的信息");
			System.out.println("StudentServlet.chooseCourse(获取不到学生的id)");
		}
				
		
	}

	//学生选课
	private void chooseCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//创建业务层对象
		StudentService studentService = new StudentServiceImpl();
		//获取学生的id
		Object studentObj = req.getSession().getAttribute("user");
		if(studentObj!=null){
			//获取学生的id
			long sno = ((Student)studentObj).getSno();
			//获取请求参数
			String courseid = req.getParameter("courseid"); 
			String teacherid = req.getParameter("teacherid");
			//先查询这个学生是否已经选择了其他老师的这门课程，如果已经选择了，提示该课程已经选择了，如果没有选择做添加操作
			boolean bool = studentService.studentChoosedCourseService(sno,Long.parseLong(courseid));
			//判断
			if(bool){
				System.out.println("StudentServlet.chooseCourse(该学生已经选择了其他老师的这门课程)");
				//响应给ajax
				resp.getWriter().print("true");
			}else{
				System.out.println("StudentServlet.chooseCourse(该学生未选择了其他老师的这门课程，可以添加)");
				int n = studentService.addTSCService(sno, Long.parseLong(courseid), Long.parseLong(teacherid));
				//判断
				if(n>0){
					System.out.println("StudentServlet.chooseCourse(选课成功)");
					//响应给ajax
					resp.getWriter().print("success");
				}else{
					System.out.println("StudentServlet.chooseCourse(选课失败)");
					//响应给ajax
					resp.getWriter().print("fail");
				}
			}
			
		}else{
			resp.getWriter().print("获取不到学生的session作用域里面的信息");
			System.out.println("StudentServlet.chooseCourse(获取不到学生的id)");
		}
		
		
		
	}

	//查询未选择的课程
	private void selectCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//创建业务层对象
		StudentService studentService = new StudentServiceImpl();
		//获取学生的id
		Object studentObj = req.getSession().getAttribute("user");
		if(studentObj!=null){
			long sno = ((Student)studentObj).getSno();
			List selectCourselist = studentService.selectCourseService(sno);
			//判断
			if(selectCourselist!=null&&selectCourselist.size()>0){
				//存放到作用域
				req.setAttribute("selectCourselist", selectCourselist);
				//请求转发courseSelectedList.jsp
				req.getRequestDispatcher("student/courseSelectList.jsp").forward(req, resp);
				return;
			}else{
				resp.getWriter().print("没有查询到课程");
			}
		}else{
			resp.getWriter().print("获取不到学生的session作用域里面的信息");
			System.out.println("StudentServlet.selectCourse(获取不到学生的id)");
		}
	}

	//查询已经选择的课程
	private void selectedCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//创建业务层对象
		StudentService studentService = new StudentServiceImpl();
		//获取学生的id
		Object studentObj = req.getSession().getAttribute("user");
		if(studentObj!=null){
			long sno = ((Student)studentObj).getSno();
			List selectedCourselist = studentService.selectedCourseService(sno+"");
			//判断
			if(selectedCourselist!=null&&selectedCourselist.size()>0){
				//存放到作用域
				req.setAttribute("selectedCourselist", selectedCourselist);
				//请求转发courseSelectedList.jsp
				req.getRequestDispatcher("student/courseSelectedList.jsp").forward(req, resp);
				return;
			}else{
				resp.getWriter().print("该学生没有选择课程");
			}
		}else{
			resp.getWriter().print("获取不到学生的session作用域里面的信息");
			System.out.println("StudentServlet.selectedCourse(获取不到学生的id)");
		}
		
		
	}

	
}
