<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <base href="<%=basePath%>">
   
   <title>老师的详细信息</title>
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
								<td height="62" background="images/nav04.gif" class="style10">
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
														<td height="20" colspan="2" align="center" bgcolor="#EEEEEE" class="tablestyle_title"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															个人信息列表 &nbsp;</td>
													</tr>
													<tr>
														<td width="21%" height="20" align="right" bgcolor="#FFFFFF">姓名</td>
														<td width="74%" bgcolor="#FFFFFF">${user.tname }</td>
													</tr>
													<tr>
														<td height="20" align="right" bgcolor="#FFFFFF">员工唯一编号:</td>
														<td bgcolor="#FFFFFF">${user.tno }</td>
													</tr>
													<tr>
														<td height="20" align="right" bgcolor="#FFFFFF">入职日期:</td>
														<td bgcolor="#FFFFFF">${user.hiredate }</td>
													</tr>
													<!-- <tr>
														<td height="20" align="right" bgcolor="#FFFFFF">性别:</td>
														<td bgcolor="#FFFFFF">男</td>
													</tr> -->
													<tr>
														<td align="right" bgcolor="#FFFFFF">联系方式:</td>
														<td bgcolor="#FFFFFF">${user.phone }</td>
													</tr>
													<tr>
														<td align="right" bgcolor="#FFFFFF">备注:</td>
														<td bgcolor="#FFFFFF"><textarea rows="10" cols="130">${user.remark }</textarea></td>
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