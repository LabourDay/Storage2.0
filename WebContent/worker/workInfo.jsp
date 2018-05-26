<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	 if(session.getAttribute("username")==null||session.getAttribute("username")==""){
	 //System.out.print("aa");
	 response.sendRedirect("login.jsp"); 
	
	 } 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
@import "../css/jquery.dataTables.css";
</style>
<script type="text/javascript" language="javascript"
	src="../js/jquery.js"></script>
<script type="text/javascript" language="javascript"
	src="../js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript">
	$(document).ready(function() {
		$('#example').dataTable();
	});
</script>
</head>
<body>
	<div class="stomsg">
	<p class="tabTitle" align="center">员工信息</p>
	<p align="center" style="margin-top: 8px"><a href="worker!insertJsp">
	<img src="<%=basePath %>img/record_add.gif">添加员工</a></p>
	<table cellpadding="0" cellspacing="0" border="1" class="display"
		id="example" width="90%" align="center">

		<thead>
			<tr>
				<th>员工编号</th>
				<th>员工账号</th>
				<th>真实姓名</th>
				<th>员工性别</th>
				<th>员工生日</th>
				<th>联系方式</th>
				<th>操&nbsp;&nbsp;&nbsp;&nbsp;作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="st">
				<tr>
					<td align="center"><s:property value="w_id" /></td>
					<td align="center"><s:property value="w_username" /></td>
					<td align="center"><s:property value="w_truename" /></td>
					<td align="center"><s:property value="w_sex" /></td>
					<td align="center"><s:property value="w_birth" /></td>
					<td align="center"><s:property value="w_tel" /></td>
					<td align="center"><a
						href="worker!workerFindId?id=<s:property value="w_id"/>"> <img
							src="<%=basePath%>img/record_(edit)_16x16.gif" border="0"
							title="修改员工信息" />&nbsp;&nbsp;
					</a> <a href="worker!workerDel?id=<s:property value="w_id"/>"> <img
							src="<%=basePath%>img/record_(delete)_16x16.gif" border="0"
							title="删除员工信息" />
					</a></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	</div>
</body>
</html>