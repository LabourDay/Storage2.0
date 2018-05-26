<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>库存管理系统</title>
 <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String mainpage=(String)request.getAttribute("mainpage");
	
	if(mainpage==null || mainpage.equals("")){
		mainpage="main.jsp";
	}
	if(session.getAttribute("username")==null||session.getAttribute("username")==""){
		//System.out.print("aa");
		response.sendRedirect("login.jsp"); 
		
	}
%>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="scripts/demo.css"> 
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="${pageContext.request.contextPath}/js/reset.js"></script> 
<title>Insert title here</title>
<style type="text/css">
@import "${pageContext.request.contextPath}/css/jquery.dataTables.css";
</style>
<script type="text/javascript" language="javascript"
	src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" language="javascript"
	src="${pageContext.request.contextPath}/js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript">
	$(document).ready(function() {
		$('#example').dataTable();
	});
</script>
</head>
<body>
	 <jsp:include page="common/header.jsp"></jsp:include>
	 <jsp:include page="<%=mainpage%>"></jsp:include>
	 <jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>