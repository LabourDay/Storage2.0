<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-table.min.css">

<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/tableExport.js"></script>
<script src="js/jquery.base64.js"></script>
<script src="js/bootstrap-table.js"></script>
<script src="js/bootstrap-table-export.js"></script>

<title>仓库信息</title>
</head>
<body>
	<div style="margin:0 auto; height:550px;width: 1200px;">
	<table align="center"  width="700" >
		<tr>
			<td colspan="2" align="center" ><font size="6">仓库信息</font></td>
		</tr>
		<tr align="center">
			<td width="300">
			<s:if test="#session.sType=='管理员'">
			<a href="storageTiao!Add"><font size="4">增加仓库信息</font></a>
			</s:if>
			</td>
			<td>
				<form action="storageSelectId">
				<input type="text" id="selectsto" name="selectsto" placeholder="请输入仓库号" />
				<input type="submit" value="搜索" />
				</form>
			</td>
		</tr>
	</table>
	<div class="container">
	<table id="table"
           data-toggle="table"
           data-url=""
           data-show-columns="true"
           data-search=""
           data-show-refresh=""
           data-show-toggle="true"
           data-pagination="true" 
           data-height="480">
     <thead>
	<tr>
	<th data-field="仓库号">仓库号</th>
	<th data-field="仓库名">仓库名</th>
	<th data-field="类型">类型</th>
	<th data-field="金额">金额</th>
	<th data-field="地址">地址</th>
	<s:if test="#session.sType=='管理员'">
	<th data-field="操作">操作</th>
	</s:if>
	</tr>
	</thead>
	<tbody>
	 <s:iterator value="list"  status="st">
		       <tr>
		       <td><s:property value="sto_id" /></td>
		       <td><s:property value="sto_name" /></td>
		       <td><s:property value="sto_type" /></td>
		       <td><s:property value="sto_money" /></td>
		       <td><s:property value="sto_addr" /></td>
		    	
		    	<s:if test="#session.sType=='管理员'">
		    	<td align="center">		    	
		            <a href="storageTiao!Updata?id=<s:property value="sto_id"/>">
		              <img src="<%=basePath %>img/record_(edit)_16x16.gif" border="0" 
		              			title="修改仓库信息" />&nbsp;&nbsp;
		            </a>
		             <a href="storageDelete?id=<s:property value="sto_id"/>">
		              <img src="<%=basePath %>img/record_(delete)_16x16.gif" border="0"
								title="删除仓库信息" />
		            </a> 
		          </td>
		          </s:if>
		    	</tr>
		      </s:iterator>
		</tbody>
	</table>
	</div>
	</div>
</body>
</html>