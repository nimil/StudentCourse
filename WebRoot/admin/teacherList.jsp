<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <base href="<%=basePath%>">
   
   <title>老师列表页面</title>
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
												<td height="20" colspan="15" align="center" bgcolor="#EEEEEE" class="tablestyle_title" style="text-align: center;"> 教师列表 </td>
												</tr>
	<tr>
		<td width="6%" align="center" bgcolor="#EEEEEE">选择</td>
		<td width="9%" height="20" align="center" bgcolor="#EEEEEE">编号</td>
		<td width="9%" align="center" bgcolor="#EEEEEE">姓名</td>

		<td width="9%" align="center" bgcolor="#EEEEEE">联系方式</td>
		<td width="9%" align="center" bgcolor="#EEEEEE">入职日期</td>
		<td width="9%" align="center" bgcolor="#EEEEEE">备注信息</td>
		<td width="19%" align="center" bgcolor="#EEEEEE">操作</td>
	</tr>
	
	<c:forEach items="${teacherList }" var="each">
		<tr>
			<td bgcolor="#FFFFFF"><input type="checkbox" name="delid"/></td>
			<td height="20" bgcolor="#FFFFFF"><a href="listyuangongmingxi.html">${each.tno }</a></td>
			<td bgcolor="#FFFFFF"><a href="listyuangongmingxi.html">${each.tname }</a></td>

			<td bgcolor="#FFFFFF">${each.phone }</td>
			<td bgcolor="#FFFFFF">${each.hiredate }</td>
			<td bgcolor="#FFFFFF">${each.remark }</td>
			<td bgcolor="#FFFFFF"><a href="#">删除</a>&nbsp;|&nbsp;<a href="#">修改</a></td>
		</tr>
	</c:forEach>
	
										
												
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td height="6"><img src="images/spacer.gif" width="1" height="1" /></td>
						</tr>
						<tr>
							<td height="33">
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="right-font08">
									<tr>
										<td width="50%">共 <span class="right-text09">5</span> 页 | 第 <span class="right-text09">1</span> 页</td>
										<td width="49%" align="right">[<a href="#" class="right-font08">首页</a> | <a href="#" class="right-font08">上一页</a> | <a href="#" class="right-font08">下一页</a> | <a href="#" class="right-font08">末页</a>] 转至：</td>
										<td width="1%">
											<table width="20" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="1%">
														<input name="textfield3" type="text" class="right-textfield03" size="1" />
													</td>
													<td width="87%">
														<input name="Submit23222" type="submit" class="right-button06" value=" " />
													</td>
												</tr>
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