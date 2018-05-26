<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改员工信息</title>
</head>
<body>
<div class="main">
	<br /><br /><br /><br />
		 <form action="yuangong!yuangongUpdate"
			method="post" name="form1">
			<table width="300" border="0" align="center" cellpadding="4"
				cellspacing="1" id="tabList">
							 
				<tr align="center">
					<th colspan="2" scope="col">
						修改员工信息
					</th>
				</tr>
				
				<tr>
					<td width="120" align="right">
						员工编号
					</td>
					<td width="175" align="left">
						
						<input type="text" name="w_id" size="20" maxlength="20"
							value='<s:property value="listid.w_id"/>' readonly>
						
					</td>
				</tr>
				<tr>
					<td align="right">
						用户名
					</td>
					<td align="left">
						<input type="text" name="w_username" size="20" maxlength="20"
							value='<s:property value="listid.w_username"/>'>
					</td>
				</tr>
				<tr>
					<td align="right">
						密码
					</td>
					<td align="left">
						<input type="text" name="w_password" size="20" maxlength="20"
							value='<s:property value="listid.w_password"/>'>
					</td>
				</tr>
				<tr>
					<td align="right">
						真实姓名
					</td>
					<td align="left">
						<input type="text" name="w_truename" size="20" maxlength="20"
							value='<s:property value="listid.w_truename"/>'>
					</td>
				</tr>
				<tr>
					<td align="right">
						手机号码
					</td>
					<td align="left">
						<input type="text" name="w_tel" size="20" maxlength="20"
							value='<s:property value="listid.w_tel"/>'>
					</td>
				</tr>
				<tr>
					<td align="right">
						邮箱
					</td>
					<td align="left">
						<input type="text" name="w_mail" size="20" maxlength="20"
							value='<s:property value="listid.w_mail"/>'>
					</td>
					
				</tr>
								<tr>
					<td align="right">
						请假次数
					</td>
					<td align="left">
						<input type="text" name="l_num" size="20" maxlength="20"
							value='<s:property value="listid.l_num"/>'>
					</td>
					
				</tr>
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