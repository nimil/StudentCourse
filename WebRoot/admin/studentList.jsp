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
    <title>查询所有的学生信息</title>  
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		//跳转到第几页
		function selectNum(){
			var val = document.getElementById("textid").value;
			window.location.href = "AdminServlet?operation=selectallstudent&num="+val;
			
		}		
	</script>
</head>	
<body>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30">
			
			<form  action="AdminServlet" name="fom" id="fom" method="get">
			<!-- 隐藏域-业务类型 -->
				<input type="hidden" name="operation" value="selectlikestudent"/>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="62" background="images/nav04.gif">

							<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td width="24"><img src="images/ico07.gif" width="20" height="18" /></td>
									<td>
										<select name="clazz">
											<option value="">--请选择--</option>
											<c:forEach items="${clazzList }" var="ceach">
												<option value="${ceach.clazzno }">${ceach.cname }</option>
											</c:forEach>
										</select>&emsp;
										<span class="newfont06">姓名 : </span><input name="sname" type="text" size="12" value="" />
										<input name="Submit" type="submit" class="right-button02" value="查 询" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</form>
			
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
												<td height="20" colspan="15" align="center" bgcolor="#EEEEEE" class="tablestyle_title" style="text-align: center;"> 学生列表 </td>
											</tr>
											<tr>
												<td width="6%" align="center" bgcolor="#EEEEEE">选择</td>
												<td width="9%" height="20" align="center" bgcolor="#EEEEEE">学生编号</td>
												<td width="9%" align="center" bgcolor="#EEEEEE">姓名</td>
												<td width="9%" align="center" bgcolor="#EEEEEE">性别</td>
												<td width="9%" align="center" bgcolor="#EEEEEE">班级</td>
												<td width="9%" align="center" bgcolor="#EEEEEE">教室</td>
												<td width="10%" align="center" bgcolor="#EEEEEE">出生年月</td>
												<td width="10%" align="center" bgcolor="#EEEEEE">联系电话</td>
												<td width="19%" align="center" bgcolor="#EEEEEE">操作</td>
											</tr>

											<c:forEach items="${studentListPage.list }" var="each" varStatus="varstatus">
												<tr>
												<td bgcolor="#FFFFFF"><input type="checkbox" name="delid"/></td>
												<%-- <td height="20" bgcolor="#FFFFFF"><a href="listyuangongmingxi.html">${varstatus.count + studentListPage.start }</a></td> --%>
												<td height="20" bgcolor="#FFFFFF"><a href="listyuangongmingxi.html">${each.sno }</a></td>
												<td bgcolor="#FFFFFF"><a href="listyuangongmingxi.html">${each.sname }</a></td>
												<td bgcolor="#FFFFFF">${each.gender }</td>
												<td bgcolor="#FFFFFF">
													<c:forEach items="${clazzList }" var="ceach">
														<c:if test="${each.clazzno == ceach.clazzno  }">
															${ceach.cname }
														</c:if>
													</c:forEach>
												
												</td>
												<td bgcolor="#FFFFFF">
													<c:forEach items="${clazzList }" var="ceach">
														<c:if test="${each.clazzno == ceach.clazzno  }">
															${ceach.clazzroom }
														</c:if>
													</c:forEach>
												</td>
												<td height="20" bgcolor="#FFFFFF">${each.birth }</td>
												<td height="20" bgcolor="#FFFFFF">${each.phone }</td>
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
									<td width="50%">共 <span class="right-text09">${studentListPage.count } </span> 页 | 第 <span class="right-text09">${studentListPage.num }</span> 页</td>
									<td width="49%" align="right">
										[ 
										<c:if test="${studentListPage.num != 1 }">
											<a href="AdminServlet?operation=selectallstudent&num=1" class="right-font08">首页</a>
										   |<a href="AdminServlet?operation=selectallstudent&num=${studentListPage.num-1 }" class="right-font08">上一页</a> | 
										</c:if>
										
										<c:if test="${studentListPage.num != studentListPage.count }">	
											<a href="AdminServlet?operation=selectallstudent&num=${studentListPage.num+1 }" class="right-font08">下一页</a> |						
											<a href="AdminServlet?operation=selectallstudent&num=${studentListPage.count }" class="right-font08">末页</a>
										</c:if>
										] 转至：
									</td>
									<td width="1%">
										<table width="20" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="1%">
													<input name="textfield3" id="textid" type="text" class="right-textfield03" size="1"  />
												</td>
												<td width="87%">
													<a name="Submit23222" href="javascript:" onclick="selectNum();"><img src="images/button08.gif" alt="" /></a>
												<!-- 	<input name="Submit23222" type="submit" class="right-button06" value="" /> -->
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


</body>

</html>