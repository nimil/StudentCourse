<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加老师页面</title>
    <script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script> 
 	<link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />
 </head>

<body>
	<form action="AdminServlet" name="fom" id="fom" method="post">
	<!-- 业务类型：添加老师 -->
		<input type="hidden" name="operation" value="addteacher"/>
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
						<table id="subtree1" style="DISPLAY: ;" width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr><td height="40" class="font42">
				<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
					<tr>
						<th class="tablestyle_title">教师信息录入</th>
					</tr>
					<tr>
						<td class="CPanel">
							<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
								<TR>
									<TD width="100%">
										<fieldset style="height:100%;">
											<legend>教师信息</legend>
												<table border="0" cellpadding="2" cellspacing="1" style="width:100%">

												<tr>
													<td>教师名称:</td>
													<td>
														<input name="name" type="text" class="text" style="width:154px" value="" />
													</td>
												</tr>

												<tr>
													<td>教师密码:</td>
													<td>
														<input class="text" type="password" name="pwd" style="width:154px" value="" />
													</td>
												</tr>

												<tr>
													<td>联系方式:</td>
													<td>
														<input class="text" name="phone" style="width:154px" value="" />
													</td>
												</tr>
												
												<tr>
													<td>入职时间:</td>
													<td>
														<input class="Wdate" name="hiredate" style="width:154px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" value="" />														
													</td>
												</tr>
												<tr>
													<td valign="top">描述:</td>
													<td colspan="3">
														<textarea name="remark" cols="100" rows="8"></textarea>
													</td>
												</tr>
											</table>
											<br />
										</fieldset>
									</TD>

								</TR>

							</TABLE>

						</td>
					</tr>

					<TR>
						<TD colspan="2" align="center" height="50px">
							<input type="submit" name="Submit" value="保存" class="button"  />
						</TD>
					</TR>
				</TABLE>

				</td>
								
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