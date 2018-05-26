<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet">
<script src="js/jquery.js"></script>
<script src="laydate/laydate.js"></script>
<script src="../js/json2.js"></script>
<script src="../js/prototype.js"></script>
<script src="js/register.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#birth").click(function(){
		laydate.skin('molv'); //切换皮肤，请查看skins下面皮肤库
	laydate({ elem: '#birth' }); //绑定元素
	});
});

</script>
</head>
<body>
	<body class="l_div">
	<form class="form-signin" action="workerregister?role=worker" method="post" name="form2" id="form2" onsubmit="return testform()" >
   <h2 class="form-signin-heading">员工注册</h2>
   <hr>
    <table align="center" style="margin-left: 80px">
    	<tr>
        
         <td align="right"><strong>用户编号:</strong></td>
         <td width="175"><input name="id" type="text" onBlur="isNumber(this.value);" placeholder="4-16位数字组成"/><br>
		 <span id="idtext"></span></td>
       </tr>
    
    	<tr>
          <td align="right"><strong>用户名称:</strong></td>
          <td><input name="username" type="text" onblur="sUserName(this.value)" id="username" placeholder="4-16位数字，字母组成" size="20" /><br/>
          <span id="nametext"></span></td>
       </tr>
       
       <tr>
         <td align="right"><strong>密&nbsp;&nbsp;码:</strong></td>
         <td><input  name="password" type="password" onBlur="workerPassword(this.value)" id="password" placeholder="4-16位数字，字母组成" size="20" /><br/>
         <span id="pwtext"></span></td>
       </tr>
       
       <tr>
          <td align="right"><strong>姓&nbsp;&nbsp;名:</strong></td>
          <td><input name="truename" type="text" size="20" placeholder="输入员工姓名" /><br/><span id="truenametxt"></span></td>
       </tr>
       
       <tr>
          <td align="right"><strong>性&nbsp;&nbsp;别:</strong></td>
          <td>&nbsp;&nbsp;<input name="sex" type="radio" value="男" checked="checked"/><label>男</label>&nbsp;&nbsp;
              <input name="sex" type="radio" value="女"><label>女</label>
          </td>
       </tr>
       
        <tr>
          <td align="right"><strong>出生日期:</strong></td>
          <td><input name="birth" type="text" id="birth" size="20" placeholder="输入出生日期" /><br/><span id="birthtext"></span></td>
       </tr>
       
       <tr>      
          <td align="right"><strong>手&nbsp;&nbsp;机:</strong></td>
          <td><input name="tel" type="text" onblur="workerTel(this.value)" id="tel" placeholder="11位数字组成" size="20" /><br/>
          <span id="teltext"></span></td>
       </tr>
     
       
       </table>
       <table style="margin: 0 auto;">
       <tr>
          <td colspan="3" align="center" >
          <input name="input" type="submit" value="提交" class="btn" id="submit" />&nbsp;
          <input name="input2" type="reset" value="重填" class="btn" />&nbsp;
          <a href="login.jsp"><input name="input3" type="button" value="返回" class="btn"/></a></td>
       </tr>
    </table>
    </form>
</body>
</html>