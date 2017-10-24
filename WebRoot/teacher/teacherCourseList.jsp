<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   <title>查询当前老师下有多少门课程</title>

	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />
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
													<td height="20" colspan="15" align="center" bgcolor="#EEEEEE" class="tablestyle_title" style="text-align: center;"> 任课列表 </td>
												</tr>
	<tr>
		<!-- <td width="6%" align="center" bgcolor="#EEEEEE">选择</td> -->
		<td width="9%" height="20" align="center" bgcolor="#EEEEEE">课程编号</td>
		<td width="9%" align="center" bgcolor="#EEEEEE">课程名</td>
		<td width="9%" align="center" bgcolor="#EEEEEE">学分</td>
		<td width="9%" align="center" bgcolor="#EEEEEE">开课时间</td>
		<td width="10%" align="center" bgcolor="#EEEEEE">结课时间</td>
	</tr>
	<c:forEach items="${teacherCourseList }" var="each">
		<tr>
			<!-- <td bgcolor="#FFFFFF"><input type="checkbox" name="delid"/></td> -->
			<td height="20" bgcolor="#FFFFFF"><a href="listyuangongmingxi.html">${each.cno }</a></td>
			<td bgcolor="#FFFFFF"><a href="listyuangongmingxi.html">${each.name }</a></td>
			<td bgcolor="#FFFFFF">${each.credit }</td>
			<td bgcolor="#FFFFFF">${each.periodstart }</td>
			<td bgcolor="#FFFFFF">${each.periodend }</td>
		</tr>
	
	</c:forEach>
	
												
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>

</html>