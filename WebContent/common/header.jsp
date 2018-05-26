<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<base href="<%=basePath%>">
<script>
function getTime(){
	var arrWeekDay = new Array(
		"星期日","星期一","星期二","星期三","星期四","星期五","星期六","星期日"
	);
	var today;
	today = new Date();
	var minutes = today.getMinutes();
	if(minutes<10){
		minutes="0"+minutes;
	}
	var seconds = today.getSeconds();
	if(seconds<10){
		seconds="0"+seconds;
	}
	document.getElementById("time").innerHTML="现在是"+today.getFullYear()+"年"+(today.getMonth()+1)+"月"+today.getDate()
					+"日"+arrWeekDay[today.getDay()]+today.getHours()+":"+minutes
					+":"+seconds;
	setTimeout("getTime()",1000);
}
</script>
<title>库存管理系统</title>
</head>
<body>
	<div class="header">
		<div>
		<div align="center">
			<img src="/Storage/img/logo.jpg">
		</div>
		<div class="extra">
			<div class="headRight">
				<s:if test="#session.sType=='管理员'">
						欢迎
						<font color="red">${username}&nbsp;${sType}</font>
					<%-- <font color="red">${password}</font> --%>
				</s:if>

				<s:if test="#session.sType=='普通员工'">
						  欢迎
						  <font color="red">${username}</font>
					<%-- <font color="red">${password}</font> --%>
				</s:if>
				<%-- <input type="text" name="password" value="${password }"> --%>
			</div>
			<div class="headLeft">
				<font id="time"> <script>getTime()</script></font>
			</div>
		</div>
		</div>
		<div id="menu">
			<ul>
				<li><a href="index.jsp">首页</a></li>
				<li><a href="storageSelect">仓库信息</a></li>
				<li><a href="goods!goodsMessage">物品信息</a></li>
				<li><a href="inventory.jsp">出入库信息</a></li>
				<s:if test="#session.sType=='管理员'">
					<!-- <li><a href="worker">员工信息</a></li> -->
					<li><a href="yuangong!yuangongMessage">员工信息</a></li>
				</s:if>
				<li><a href="login!updatePasswdJsp">修改密码</a></li>
				<li><a onclick="cf()">退出系统</a></li>
			</ul>
		</div>
	</div>
</body>
</html>