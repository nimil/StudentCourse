<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
   <base href="<%=basePath%>">
   
   <title>查询所有的课程</title>
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.9.1.js" charset="utf-8"></script>
	<script type="text/javascript">
		//查询所有的课程
		$.get("AdminServlet","operation=selectcourse",function(result){
			//alert(result);
			if(result.length>0){
				//转换成json的数组格式
				var varjson = JSON.parse(result);
				//循环遍历
				for(var i=0;i<varjson.length;i++){
					$("#courseid").append($("<option value="+varjson[i].cno+">"+varjson[i].name+"</option>"));
				}
			}else{
				alert("获取不到班级信息");
			}
		});
		
		//查询所有的老师
		$.get("AdminServlet","operation=selectteacher",function(result){
			//alert(result);
			if(result.length>0){
				//转换成json的数组格式
				var varjson = JSON.parse(result);
				//循环遍历
				for(var i=0;i<varjson.length;i++){
					$("#teacherid").append($("<option value="+varjson[i].tno+">"+varjson[i].tname+"</option>"));
				}
			}else{
				alert("获取不到老师信息");
			}
		});
		
		//给老师分配课程
		function courseToTeacher(){
			//获取课程的id,老师的id
			var courseidval = $("#courseid").val();
			var teacheridval = $("#teacherid").val();
			//判断
			if(courseidval.length>0&&teacheridval.length>0){
				//发送ajax给服务器，做添加操作
				$.post("AdminServlet",{operation:"insertTC",courseid:courseidval,teacherid:teacheridval},function(result){
					//alert(result);
					if(result=="true"){
						alert("该课程已经分配给该老师，请重新分配");
					}else if(result=="success"){
						alert("分配成功");
						//刷新页面
						window.location.href = "AdminServlet?operation=selectcourseteacher";
					}else if(result=="fail"){
						alert("分配失败");
					}else{
						alert("数据出错");
					}
				});
			}else{
				alert("请选择要分配的课程或者老师");
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
								<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td width="20"></td>
										<td>
											给课程
											<select name="course" id="courseid">
												<option value="">--请选择课程--</option>
												
											</select>
											指定教师
											<select name="teacher" id="teacherid">
												<option value="">--请选择教师--</option>
											</select>
											
											<input name="Submit" type="button" class="right-button02" value="提 交" onclick="courseToTeacher();"/>
										</td>
									</tr>
								</table>
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
											<td height="20" colspan="15" align="center" bgcolor="#EEEEEE" class="tablestyle_title" style="text-align: center;"> 课程列表 </td>
													</tr>
	<tr>
		<td width="6%" align="center" bgcolor="#EEEEEE">选择</td>
		<td width="9%" height="20" align="center" bgcolor="#EEEEEE">课程编号</td>
		<td width="9%" align="center" bgcolor="#EEEEEE">课程名</td>
		<td width="9%" align="center" bgcolor="#EEEEEE">学分</td>
		<td width="9%" align="center" bgcolor="#EEEEEE">开课时间</td>
		<td width="10%" align="center" bgcolor="#EEEEEE">结课时间</td>
		<td width="4%" align="center" bgcolor="#EEEEEE">任课老师</td>
		<td width="4%" align="center" bgcolor="#EEEEEE">教师联系方式</td>
		<td width="19%" align="center" bgcolor="#EEEEEE">操作</td>
	</tr>
	<c:forEach items="${courseteacherList }" var="each">
		<tr>
			<td bgcolor="#FFFFFF"><input type="checkbox" name="delid"/></td>
			<td height="20" bgcolor="#FFFFFF"><a href="listyuangongmingxi.html">${each[0] }</a></td>
			<td bgcolor="#FFFFFF"><a href="listyuangongmingxi.html">${each[1] }</a></td>
			<td bgcolor="#FFFFFF">${each[2]}</td>
			<td bgcolor="#FFFFFF"> <fmt:formatDate value="${each[3]}" type="both" pattern="YYYY-MM-dd"/> </td>
			<td bgcolor="#FFFFFF"> <fmt:formatDate value="${each[4]}" type="both" pattern="YYYY-MM-dd"/> </td>
			<td height="20" bgcolor="#FFFFFF">${each[6]}</td>
			<td height="20" bgcolor="#FFFFFF">${each[8]}</td>
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