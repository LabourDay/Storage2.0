<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
 <%
	
	if(session.getAttribute("username")==null||session.getAttribute("username")==""){
		//System.out.print("aa");
		response.sendRedirect("login.jsp"); 
		
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<link rel="stylesheet" type="text/css" href="scripts/demo.css"> 
</head>
<body>
<div class="main">
<br /><br /><br /><br />

		<form action="login!updatePasswd"
			method="post" name="updatepw"
			onSubmit="return check()">
			<table width="300" border="0" align="center" cellpadding="4"
				cellspacing="1" id="tabList">
				<tr><input type="hidden" name="role" value="${sType }"/></tr>
				<tr align="center">
					<th colspan="2" scope="col">
						修改密码
					</th>
				</tr>
					<tr align="center">
				 	
						<th colspan="2" scope="col">
							<input type="hidden" name="un" value="${username }">						
						</th>					
					</tr>
				
					<tr align="center">
						<th colspan="2" scope="col">
							<input type="hidden" name="holdpw" value="${password }">
						</th>
					</tr>
				
				
				<tr>
					<td width="120" align="right">
						原&nbsp;&nbsp;密&nbsp;&nbsp;码
					</td>
					<td width="175" align="left">
						<input type="password" name="password" />
					</td>
				</tr>
				<tr>
					<td align="right">
						新&nbsp;&nbsp;密&nbsp;&nbsp;码
					</td>
					<td align="left">
						<input type="password" name="newpw1" >
					</td>
				</tr>
				<tr>
					<td align="right">
						确认新密码
					</td>
					<td align="left">
						<input type="password" name="newpw2" />
					</td>
				</tr>
				
				<tr>
					<th colspan="2" align="center">
						<input name="submit1" type="submit" value="确认" onclick="check()">
						&nbsp;
						<input name="submit2" type="reset" value="重置">
						&nbsp;
						<a href="index.jsp"><input name="Input" type="button" value="首页"></a>
						<input name="action" type="hidden" value="301" />
					</th>

				</tr>
			</table>
		</form>
</div>
</body>
</html>