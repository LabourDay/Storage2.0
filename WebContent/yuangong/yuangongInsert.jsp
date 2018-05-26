<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加员工信息</title>
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet">
<script src="../scripts/jquery.js"></script>


</head>

<body>
	<div class="insert">
	<form action="yuangong!yuangongInsert" method="post" name="f1" id="add">
    <table width="320" border="0" align="center">
      <tr>
        
        <p align="center" class="tabTitle">增加员工信息</p>
         
         <td width="120" height="21"><strong>员工编号:</strong></td>
         <td width="175"><input name="w_id" type="text" onBlur="isNumberOrLetter(this.value);"/></td>
         
       </tr>
       <tr>
         <td><strong>用户名:</strong></td>
         <td><input  name="w_username" type="text"/></td>  
       </tr>
       <tr>
         <td><strong>密码:</strong></td>
         <td><input  name="w_password" type="text"  /></td>
       </tr>
       <tr>
          <td><strong>真实姓名:</strong></td>
          <td><input name="w_truename" type="text" /></td>
       </tr>
       <tr>
          <td><strong>手机号码:</strong></td>
          <td><input name="w_tel" type="text" /></td>
       </tr>
       <tr>
          <td><strong>邮箱:</strong></td>
          <td><input name="w_mail" type="text" /></td>
       </tr>   
       <tr>
          <td><strong>请假次数:</strong></td>
          <td><input name="l_num" type="text" /></td>
       </tr>           
       <tr>
          <td colspan="3" align="center">
          <input name="input" type="submit" value="增加" />&nbsp;&nbsp;
          <input name="input2" type="reset" value="重填"  />&nbsp;&nbsp;
          <input name="Input" type="button" value="返回"
				onClick="javascript:history.back();"></td>
       </tr>
    </table>
</form>
</div>
</body>
</html>