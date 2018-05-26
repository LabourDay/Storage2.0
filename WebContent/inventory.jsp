<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if(session.getAttribute("username")==null||session.getAttribute("username")==""){
	//System.out.print("aa");
	response.sendRedirect("login.jsp"); 
	
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>出入库管理</title>
<script type="text/javascript" src="scripts/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="scripts/js/jquery.easyui.min.js"></script>
<!-- CSS样式引入 -->
<link rel="stylesheet" type="text/css"
	href="scripts/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="scripts/themes/icon.css">
<link rel="stylesheet" type="text/css" href="scripts/demo.css">
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet">
</head>
<body>
		<jsp:include page="common/header.jsp"></jsp:include>
		<div class="stomsg">
		<table id="dg" class="easyui-datagrid" title="出入库"
			style="width: 1000px; height: auto"
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				url: 'StorAction!selectInventory?storage.v_type=0',
				method: 'get'
			">

			<thead>
				<tr>
					<th data-options="field:'g_id',width:180">商品编号</th>
					<th data-options="field:'g_name',width:180">商品名</th>
					<th data-options="field:'v_type',width:150">类型（进货/出货）</th>
					<th data-options="field:'v_amount',width:150">（进货/出货）数量</th>
					<th data-options="field:'v_time',width:250">（进货/出货）时间</th>
				</tr>
			</thead>
		</table>
		</div>
		<jsp:include page="common/footer.jsp"></jsp:include>
		<div id="tb" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="add_data()">添加出入库信息</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <select
				id="search" name="search">
				<option value="0" selected="selected">请选择查看类型</option>
				<option value="0">所有</option>
				<option value="入库">入库</option>
				<option value="出库">出库</option>
			</select>
		</div>
		<!-- 列表展示结束   -->

		<!-- 添加窗口开始   -->
		<div id="dialog_add_edit" style="display: none;">
			<form name="addEditForm" id="addEditForm" method="post">
				<table align="center">
					<tr class="formRow">
						<td class="formLabel">商品编号：</td>
						<td class="formField"><input type="text" name="storage.g_id"
							style="width: 220px;" class="orShortInput easyui-validatebox"
							data-options="required:true"> <font style="color: red">*</font>
						</td>
					</tr>
					<tr class="formRow">
						<td class="formLabel">商品名：</td>
						<td class="formField"><input type="text"
							name="storage.g_name" style="width: 220px;"
							class="orShortInput easyui-validatebox"> <font
							style="color: red">*</font></td>
					</tr>
					<tr class="formRow">
						<td class="formLabel">类型（进货/出货）：</td>
						<td class="formField"><input name="storage.v_type"
							type="radio" id="radio1" value="入库" checked> <label
							for="radio">入库</label>&nbsp;&nbsp; <input name="storage.v_type"
							type="radio" id="radio2" value="出库"> <label for="radio">出库</label><br />
						</td>
					</tr>
					<tr class="formRow">
						<td class="formLabel">（进货/出货）数量：</td>
						<td class="formField"><input type="text"
							name="storage.v_amount" style="width: 220px;"
							class="orShortInput"></td>
					</tr>
					<tr class="formRow">
						<td class="formLabel">（进货/出货）时间：</td>
						<td class="formField"><input type="text"
							name="storage.v_time" style="width: 220px;" class="orShortInput">
						</td>
					</tr>

				</table>
			</form>
		</div>

	<!-- 添加窗口结束   -->
	<script type="text/javascript">
		var editIndex = undefined;
		var check_res;
		function endEditing(){
			if (editIndex == undefined){return true}
			if ($('#dg').datagrid('validateRow', editIndex)){
				var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'productid'});
				var productname = $(ed.target).combobox('getText');
				$('#dg').datagrid('getRows')[editIndex]['productname'] = productname;
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		
		function add_data() {
		$('#dialog_add_edit').dialog({
			title : '添加出入库信息',
			id : "dialog_add_edit",
			width : 600,
			height : 280,
			modal : true,
			buttons : [ {
				text : '确定',
				handler : function() {
					if (!$("#addEditForm").form("validate")) {
						return;
					} else {
						var Formdata = $('#addEditForm').serialize();
						$.ajax({
							type : "POST",
							url : "StorAction!insert.action",
							async : false,
							cache : false,
							data : Formdata,
							success: function(result){
							alert(result);
							location.href = "inventory.jsp";
							},
							error:function(result){
							alert(result);
							location.href = "inventory.jsp";
							}
						});
						return check_res;
					}

				}
			}, {
				text : '取消',
				handler : function() {
					$("#dialog_add_edit").dialog("close");
				}
			} ]
		});

	}
	
	$(function(){
           $("#search").change(function(){
                  var v_type=$("#search option:selected").val();
                  $.ajax({
							type : "POST",
							url : "StorAction!selectInventory",
							cache : false,
							data : "storage.v_type=" + v_type,
							success : function(dat) {
								location.href = "inventory.jsp";
							},
							error : function() {
								alert("搜索失败！");
							}
						});

           })
      })
	
	</script>
</body>
</html>
