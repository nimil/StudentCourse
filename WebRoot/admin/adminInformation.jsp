<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'adminInformation.jsp' starting page</title>
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	</head>
	<SCRIPT language=JavaScript>
		function sousuo() {
			window.open("gaojisousuo.htm", "", "depended=0,alwaysRaised=1,width=800,height=510,location=0,menubar=0,resizable=0,scrollbars=0,status=0,toolbar=0");
		}

		function selectAll() {
			var obj = document.fom.elements;
			for (var i = 0; i < obj.length; i++) {
				if (obj[i].name == "delid") {
					obj[i].checked = true;
				}
			}
		}

		function unselectAll() {
			var obj = document.fom.elements;
			for (var i = 0; i < obj.length; i++) {
				if (obj[i].name == "delid") {
					if (obj[i].checked == true) obj[i].checked = false;
					else obj[i].checked = true;
				}
			}
		}
	</SCRIPT>

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
												<table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646">

													<tr>
														<td height="20" colspan="2" align="center" bgcolor="#EEEEEE" class="tablestyle_title"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															个人信息列表 &nbsp;</td>
													</tr>
													<tr>
														<td width="21%" height="20" align="right" bgcolor="#FFFFFF">姓名:</td>
														<td width="74%" bgcolor="#FFFFFF">${user.username }</td>
													</tr>
													<tr>
														<td height="20" align="right" bgcolor="#FFFFFF">编号:</td>
														<td bgcolor="#FFFFFF">${user.userid }</td>
													</tr>
													
													<tr>
														<td height="20" align="right" bgcolor="#FFFFFF">年龄:</td>
														<td bgcolor="#FFFFFF">${user.age }</td>
													</tr>
													
													<tr>
														<td align="right" bgcolor="#FFFFFF">分数:</td>
														<td bgcolor="#FFFFFF">${user.score }</td>
													</tr>
													<tr>
														<td align="right" bgcolor="#FFFFFF">入职日期:</td>
														<td bgcolor="#FFFFFF">${user.enterdate }</td>
													</tr>
													
													<tr>
														<td align="right" bgcolor="#FFFFFF">身份:</td>
														<td bgcolor="#FFFFFF">管理员</td>
													</tr>
													
													<tr>
														<td align="right" bgcolor="#FFFFFF">爱好:</td>
														<td bgcolor="#FFFFFF">${user.hobby }</td>
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