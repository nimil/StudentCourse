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
    
    <title>评分页面</title>
	<link href="css/css.css" rel="stylesheet" type="text/css" />	
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.9.1.js" charset="utf-8"></script>
	<script type="text/javascript">
	//给学生评分
		function pfScore(sno,cno,inputid) {
			//alert(inputid);
			//获取输入框输入的分数
			var varval = $("#"+inputid).val();
			//var varscore = document.getElementById(inputid).value;
			//alert(varscore+"--"+varval);
			//判断
			if(varval.length>0){
				$.post("TeacherServlet",{operation:"pfscore",sno: sno,cno:cno,score:varval},function(result){
					//alert(result);
					//判断
					if(result=="true"){
						alert("评分成功");
						window.location.href = "TeacherServlet?operation=teachermark";
					}else if(result=="false"){
						alert("评分失败");
					}else{
						alert("数据出错");
					}
				});
			}else{
				alert("请输入分数");
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
														<td height="20" colspan="15" align="center" bgcolor="#EEEEEE" class="tablestyle_title" style="text-align: center;">评分 </td>
													</tr>
		<tr>
			<td width="6%" align="center" bgcolor="#EEEEEE">选择</td>
			<td width="9%" height="20" align="center" bgcolor="#EEEEEE">学生编号</td>
			<td width="9%" align="center" bgcolor="#EEEEEE">学生姓名</td>
			<td width="9%" align="center" bgcolor="#EEEEEE">学生班级</td>
			<td width="9%" align="center" bgcolor="#EEEEEE">课程编号</td>
			<td width="10%" align="center" bgcolor="#EEEEEE">课程名称</td>
			<!-- <td width="4%" align="center" bgcolor="#EEEEEE">课时</td> -->
			<td width="4%" align="center" bgcolor="#EEEEEE">学分</td>
			<td width="19%" align="center" bgcolor="#EEEEEE">操作</td>
		</tr>
		<c:forEach items="${teacherStudentList }" var="each">
			<tr>
				<td bgcolor="#FFFFFF"><input type="checkbox" name="delid"/></td>
				<td height="20" bgcolor="#FFFFFF"><a href="listyuangongmingxi.html">${each[5] }</a></td>
				<td bgcolor="#FFFFFF"><a href="listyuangongmingxi.html">${each[6] }</a></td>
				<td bgcolor="#FFFFFF">${each[11] }</td>
				<td bgcolor="#FFFFFF">${each[0] }</td>
				<td bgcolor="#FFFFFF">${each[1] }</td>
				<!-- <td height="20" bgcolor="#FFFFFF">70</td> -->
				<td height="20" bgcolor="#FFFFFF">${each[2] }</td>
				<c:if test="${each[13] == 0 }">
					<td bgcolor="#FFFFFF">
						<input type="text" name="score" id="${each[5] }${each[0] }"/>&nbsp;&nbsp;
						<a href="javascript:" onclick="pfScore(${each[5] },${each[0] },${each[5] }${each[0] });">评分</a>
					</td>
				</c:if>
				<c:if test="${each[13] != 0 }">
					<td bgcolor="#FFFFFF">${each[13] }  已评分</td>
				</c:if> 
				
				
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