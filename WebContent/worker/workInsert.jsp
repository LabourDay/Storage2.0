<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if(session.getAttribute("username")==null||session.getAttribute("username")==""){
	//System.out.print("aa");
	response.sendRedirect("login.jsp"); 
	
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="${pageContext.request.contextPath}/css/style.css"rel="stylesheet">
<link rel="stylesheet" type="text/css" href="scripts/demo.css"> 
</head>
<body>
	<div class="insert">
		<form action="worker!workerInsert" method="post" name="f1" id="add">
			<table width="320" border="0" align="center">
				<tr>
					<p align="center" class="tabTitle">添加员工</p>
					<td width="120" height="21"><strong>员工编号:</strong></td>
					<td width="175"><input name="worker.w_id" id="worker.w_id"
						type="text" onBlur="isNumberOrLetter(this.value);" /></td>
				</tr>
				<tr>
					<td><strong>员工账号:</strong></td>
					<td><input name="worker.w_username" id="worker.w_username" type="text" /></td>
				</tr>
				<tr>
					<td><strong>员工密码:</strong></td>
					<td><input name="worker.w_password" id="worker.w_password" type="password" /></td>
				</tr>
				<tr>
					<td><strong>真实姓名:</strong></td>
					<td><input name="worker.w_truename" id="worker.w_truename" type="text" /></td>
				</tr>
				<tr>
					<td><strong>员工性别:</strong></td>
					<td><input name="worker.w_sex" id="worker.w_sex" type="text" /></td>
				</tr>
				<tr>
					<td><strong>员工生日:</strong></td>
					<td><input name="worker.w_birth" id="worker.w_birth" type="text" /></td>
				</tr>
				<tr>
					<td><strong>联系方式:</strong></td>
					<td><input name="worker.w_tel" id="worker.w_tel" type="text" /></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><input name="input"
						type="submit" value="添加" />&nbsp;&nbsp; <input name="input2"
						type="reset" value="重填" />&nbsp;&nbsp; <input name="Input"
						type="button" value="返回" onClick="javascript:history.back();"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>