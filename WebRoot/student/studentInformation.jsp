<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生详细信息页面</title>
	
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.9.1.js" charset="utf-8"></script>
	<script type="text/javascript">
		//获取所有的班级信息
		$(function(){
			//查询所有的班级信息
  			$.post("AdminServlet",{operation:"selectallclazz"},function(result){
  				//alert(result);
  				//判断
  				if(result.length>0){
  					//转换成json
  					var varjson = JSON.parse(result);
  					//遍历for
  					for(var i=0;i<varjson.length;i++){
  						//获取td对象  $("#tdid")
  						//判断如果学生的所在班级编号 和班级的主键相同，取出班级的名称
  						if(varjson[i].clazzno == "${user.clazzno}"){
  							 $("#tdid").html(varjson[i].cname);
  						}
  					}
  				}else{
  					alert("获取不到班级信息");
  				}
  			});
		});
	</script>
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
														<td width="74%" bgcolor="#FFFFFF">${user.sname }</td>
													</tr>
													<tr>
														<td height="20" align="right" bgcolor="#FFFFFF">编号:</td>
														<td bgcolor="#FFFFFF">${user.sno }</td>
													</tr>
													<tr>
														<td height="20" align="right" bgcolor="#FFFFFF">班级:</td>
														<td bgcolor="#FFFFFF" id="tdid"></td>
													</tr>
													<tr>
														<td height="20" align="right" bgcolor="#FFFFFF">性别:</td>
														<td bgcolor="#FFFFFF">${user.gender }</td>
													</tr>
													<tr>
														<td align="right" bgcolor="#FFFFFF">出生年月:</td>
														<td bgcolor="#FFFFFF">${user.birth }</td>
													</tr>
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