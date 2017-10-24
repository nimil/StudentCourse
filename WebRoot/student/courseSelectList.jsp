<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查询学生未选的课程</title>

	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.9.1.js" charset="utf-8"></script>
	<script type="text/JavaScript">
		//学生选课
		function chooseCourse(courseid,teacherid){
			//获取课程和老师的id
			//alert(courseid+"--"+teacherid);
			if((courseid>0)&&(teacherid>0)){
				//调用ajax向servlet发送数据做添加操作
				$.post("StudentServlet",{operation:"choosecourse",courseid:courseid,teacherid:teacherid},function(result){
					//alert(result);
					if(result=="true"){
						alert("您已经选择了其他老师的这门课程，请重新选择");
						//刷新页面
						window.location.href = "StudentServlet?operation=selectcourse";
					}else if(result=="success"){
						alert("选课成功");
						//刷新页面
						window.location.href = "StudentServlet?operation=selectcourse";
					}else if(result=="fail"){
						alert("选课失败");
					}
				});
			}else{
				alert("获取不到老师或者学生的id");
			}
		}
	</script>
	</head>

	<body>
		<form name="fom" id="fom" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">

				<tr>
					<td height="30">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="62" background="images/nav04.gif">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table id="subtree1" style="DISPLAY: " width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
										<tr>
											<td height="40" class="font42">
												<table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03">

													<tr>
														<td height="20" colspan="15" align="center" bgcolor="#EEEEEE" class="tablestyle_title" style="text-align: center;"> 以下课程可选 </td>
													</tr>
													<tr>
														<td width="6%" align="center" bgcolor="#EEEEEE">选择</td>
														<td width="9%" height="20" align="center" bgcolor="#EEEEEE">课程编号</td>
														<td width="9%" align="center" bgcolor="#EEEEEE">课程名称</td>
														<td width="9%" align="center" bgcolor="#EEEEEE">学分</td>
														<td width="9%" align="center" bgcolor="#EEEEEE">开课时间</td>
														<td width="10%" align="center" bgcolor="#EEEEEE">结课时间</td>
														<td width="4%" align="center" bgcolor="#EEEEEE">任课老师</td>
														<td width="10%" align="center" bgcolor="#EEEEEE">联系电话</td>
													</tr>
													<c:forEach items="${selectCourselist }" var="each" varStatus="varstatus">
														<tr>
															<td bgcolor="#FFFFFF"><input type="checkbox" name="delid" value="${each[0] },${each[5]}"  onclick="chooseCourse(${each[0] },${each[5]});"/></td>
															<td height="20" bgcolor="#FFFFFF"><a href="listyuangongmingxi.html">${varstatus.count }</a></td>
															<td bgcolor="#FFFFFF"><a href="listyuangongmingxi.html">${each[1] }</a></td>
															<td bgcolor="#FFFFFF">${each[2] }</td>
															<td bgcolor="#FFFFFF"> <fmt:formatDate value="${each[3]}" type="both" pattern="YYYY-MM-dd"/> </td>
															<td bgcolor="#FFFFFF"> <fmt:formatDate value="${each[4]}" type="both" pattern="YYYY-MM-dd"/> </td>
															<td height="20" bgcolor="#FFFFFF">${each[6] }</td>
															<td height="20" bgcolor="#FFFFFF">${each[8] }</td>
														</tr>
													</c:forEach>
													
											
													
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					<!-- <tr>
						<td align="center" height="40px">
							<input type="button" name="Submit" value="提交选中课程" class="button" onclick="alert('提交成功！');"/>　
						</td>
					</tr> -->
					</td>
				</tr>
			</table>
		</form>
	</body>

</html>