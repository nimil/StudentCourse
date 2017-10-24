<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'adminRegister.jsp' starting page</title>
   		<link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />
		<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script> 
	</head>

	<body>
		

	<form action="AdminServlet" method="post">
		<input type="hidden" name="operation" value="addadmin"/>
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
											<table width="100%" border="0" cellpadding="4" cellspacing="1" class="Content">
										<tr>
											<th class="tablestyle_title">管理员信息录入</th>
										</tr>

										<tr>
											<td class="font42">
											<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
												<TR>
													<TD width="100%">
														<fieldset style="height:100%;">
															<legend>管理员信息</legend>
															<table border="0" cellpadding="2" cellspacing="1" style="width:100%">

																<tr>
																	<td>管理员名称 :</td>
																	<td>
																		<input name="uname" type="text" class="text" style="width:154px" value="" />
																	</td>
																</tr>

																<tr>
																	<td>管理员密码:</td>
																	<td>
																		<input class="text" type="password" name="pwd" style="width:154px" value="" />
																	</td>
																</tr>

																<tr>
																	<td>年龄:</td>
																	<td>
																		<input class="text" name="age" style="width:154px" value="" />
																	</td>
																</tr>
																<tr>
																	<td>分数:</td>
																	<td>
																		<input class="text" name="score" style="width:154px" value="" />
																	</td>
																</tr>
																<tr>
																	<td>入职日期:</td>
																	<td>
																		<input class="Wdate" name="enterdate" style="width:154px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"   value="" />
							
																	 </td>
																</tr>
																<tr>
																	<td>爱好:</td>
																	<td>
																		<input type="checkbox" name="fav" value="sports"/> sports
																		<input type="checkbox" name="fav" value="music"/> music
																		<input type="checkbox" name="fav" value="drawing"/>drawing
																	</td>
																</tr>
															</table>
															<br />
														</fieldset>
													</TD>

												</TR>

											</table>
									</td>
										</tr>
										<tr>
											<td colspan="2" align="center" height="50px">
												<input type="submit"  value="保存" class="button"  />
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