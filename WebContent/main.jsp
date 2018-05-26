<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/js/main.js"></script>
<title>库存管理系统</title>

</head>
<body>
  <div class="main">
  
  <div align="center" style="color: red"><h2>欢迎来到库存管理系统</h2>
  		<img src="<%=basePath%>/img/sto.jpg">
  </div>
  </div>
</body>
</html>