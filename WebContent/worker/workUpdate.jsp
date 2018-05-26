<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path=request.getContextPath();
	String basePath=request.getScheme()+"://"
					+request.getServerName()+":"+request.getServerPort()
					+path+"/";
	if(session.getAttribute("username")==null||session.getAttribute("username")==""){
		//System.out.print("aa");
		response.sendRedirect("login.jsp"); 
		
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="scripts/demo.css"> 
<title>修改物品</title>
</head>
<body>
<div class="main">
	<br /><br /><br /><br />
		 <form action="worker!workerUpdate"
			method="post" name="form1">
			<table width="300" border="0" align="center" cellpadding="4"
				cellspacing="1" id="tabList">
							 
				<tr align="center">
					<th colspan="2" scope="col">
						修改员工
					</th>
				</tr>
				<s:iterator value="infolist" status="st">
				<tr>
					<td width="120" align="right">
						员工编号
					</td>
					<td width="175" align="left">
						
						<input type="text" name="worker.w_id" size="20" maxlength="20"
							value='<s:property value="w_id"/>' readonly>
						
					</td>
				</tr>
				
						
						<input type="hidden" name="worker.w_username" size="20" maxlength="20"
							value='<s:property value="w_username"/>' readonly>
						
				
						
						<input type="hidden" name="worker.w_password" size="20" maxlength="20"
							value='<s:property value="w_password"/>' readonly>
						
				
				<tr>
					<td align="right">
						真实姓名
					</td>
					<td align="left">
						<input type="text" name="worker.w_truename" size="20" maxlength="20"
							value='<s:property value="w_truename"/>'>
					</td>
				</tr>
				<tr>
					<td align="right">
						员工性别
					</td>
					<td align="left">
						<input type="text" name="worker.w_sex" size="20" maxlength="20"
							value='<s:property value="w_sex"/>'>
					</td>
				</tr>
				<tr>
					<td align="right">
						员工生日
					</td>
					<td align="left">
						<input type="text" name="worker.w_birth" size="20" maxlength="20"
							value='<s:property value="w_birth"/>'>
					</td>
				</tr>
				<tr>
					<td align="right">
						联系方式
					</td>
					<td align="left">
						<input type="text" name="worker.w_tel" size="20" maxlength="20"
							value='<s:property value="w_tel"/>'>
					</td>
				</tr>
				</s:iterator>
				<tr>
					<th colspan="2" align="center">
						<input name="submit1" type="submit" value="提交">
						&nbsp;
						<input name="submit2" type="reset" value="重置">
						&nbsp;
						<input name="Input" type="button" value="返回"
							onClick="javascript:history.back();">
						<input name="action" type="hidden" value="301" />
					</th>

				</tr>
			</table>
		</form> 
		</div>
</body>
</html>