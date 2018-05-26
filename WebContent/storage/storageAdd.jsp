<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="scripts/demo.css">
<title>增加仓库信息</title>
<script src="js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(document).ready(function() {
			$("form").submit(function(e) {
				$("input[type=text]").each(function(i) {
					if ($(this).val() == "") {
						e.preventDefault();
						alert("数据输入有误！");
						return false;
					}
				});
			});
		});
	});
</script>
</head>
<body>
	<div class="stomsg">
		<div style="margin: 0 auto; width: 200px">
			<font size="5">增加仓库信息</font>
		</div>
		<div
			style="width: 500px; margin: 0 auto; border: 1px solid; padding: 10px; margin-top: 10px;">
			<form action="storageAdd" method="post">
				<table align="center">
					<tr>
						<td align="right">仓库号：</td>
						<td align="left"><input type="text" name="storage.sto_id"
							id="id" /></td>
					</tr>
					<tr>
						<td align="right">仓库名：</td>
						<td align="left"><input type="text" name="storage.sto_name"
							id="name" /></td>
					</tr>
					<tr>
						<td align="right">类&nbsp;&nbsp;&nbsp;&nbsp;型：</td>
						<td align="left"><input type="text" name="storage.sto_type"
							id="type" /></td>
					</tr>
					<tr>
						<td align="right">金&nbsp;&nbsp;&nbsp;&nbsp;额：</td>
						<td align="left"><input type="text" name="storage.sto_money"
							id="money" />
						</td>
					</tr>
					<tr>
						<td align="right">地&nbsp;&nbsp;&nbsp;&nbsp;址：</td>
						<td align="left"><input type="text" name="storage.sto_addr"
							id="addr" />
						</td>
					</tr>
				</table>
				<div style="width: 200px; margin: 0 auto; padding: 10px;">
					<input type="submit" value="提交" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="storageSelect"><input type="button" value="返回" /> </a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>