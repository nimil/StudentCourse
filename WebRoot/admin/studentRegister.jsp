<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加学生信息</title>
    <script type="text/javascript" src="js/jquery-1.9.1.js" charset="utf-8"></script>
    <script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script> 
  	<link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />
  	<script type="text/javascript">
  		//当页面加载完成自动调用方法
  		$(function(){
  			//查询所有的班级信息
  			$.post("AdminServlet",{operation:"selectallclazz"},function(result){
  				//alert(result);
  				//判断
  				if(result.length>0){
  					//获取select下拉列表
  					var varselect = $("#clazzid");
  					//转换成json
  					var varjson = JSON.parse(result);
  					//遍历for
  					for(var i=0;i<varjson.length;i++){
  						var varoption = $("<option value="+varjson[i].clazzno+">"+varjson[i].cname+"</option>");
  						//添加子节点
  						varselect.append(varoption);
  					}
  				}else{
  					alert("获取不到班级信息");
  				}
  			});
  		});
  	
  	</script>
  	
</head>

<body>
		<form action="AdminServlet" name="fom" id="fom" method="post">
		<!-- 业务类型 -->
		<input type="hidden" name="operation" value="addstudent"/>
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
						<th class="tablestyle_title">学生信息录入</th>
					</tr>
					<tr>
						<td class="CPanel">
							<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
								<TR>
									<TD width="100%">
										<fieldset style="height:100%;">
											<legend>学生信息</legend>
											<table border="0" cellpadding="2" cellspacing="1" style="width:100%">

												<tr>
													<td>学生名称:</td>
													<td>
														<input name="uname" type="text" class="text" style="width:154px" value="" />
													</td>
												</tr>

												<tr>
													<td>学生密码:</td>
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
													<td>性别:</td>
													<td>
														<input class="text" type="radio" name="sex"  value="男" checked="checked"/>男
														<input class="text" type="radio" name="sex"  value="女" />女 
													</td>
												</tr>
												<tr>
													<td>出生日期:</td>
													<td>
														<input class="Wdate" name="birth" style="width:154px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  value="" />
														
													</td>
												</tr>
												<tr>
													<td>班级:</td>
													<td>
														<select name="clazz" id="clazzid" class="text">
															<!-- <option selected="selected">==请选择==</option>
															<option value="">javase</option>
															<option value="">javaee</option>
															<option value="">oracle</option> -->
														</select>
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
							<input type="submit" name="Submit" value="保存" class="button" />
						</TD>
					</TR>
					</TABLE>
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