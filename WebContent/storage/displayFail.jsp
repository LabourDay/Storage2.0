<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Refresh" content="3; URL=<%=basePath%>storageSelect">
<title>信息提示</title>
<link href="${pageContext.request.contextPath }/css/style.css"
	rel="stylesheet">
</head>
<body>
	<div class="stomsg">
		<center>
			<h2 style="color: red;" class="main">
				<br>
				<br>
				<br>
				<br>${sRet}</h2>
		</center>
	</div>
</body>
</html>