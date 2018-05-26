<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
<title>修改物品</title>
<script src="js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(document).ready(function() {
			$("form").submit(function(e) {
				
				var unit = $("input[id=unit]").val();
				var id = $("input[id=sid]").val();
				var num = $("input[id=num]").val();
				var price = $("input[id=price]").val();
				$("input[type=text]").each(function(i) {
				/* 	var regex="^[0-9]$";
					var re=new RegExp(regex); */
					if ($(this).val() == "") {						
						e.preventDefault();
						alert("数据不能为空！");
						return false;
					}
					if($.isNumeric(unit)){
						e.preventDefault();
						alert("计量单位不能为数字");
						return false;
					} 
					if(!((/^(\+|-)?\d+$/.test(id))&&id>0)){
						e.preventDefault();
						alert("仓库编号只能为正整数！");
						return false;
					}
					/* if(((/^(\+|-)?\d+$/.test(id))&&id>0)){
						$.ajax( {
							contentType:"application/x-www-form-urlencoded; charset=utf-8", 
							type:"POST",
							url : "goods!checkStorageId",
							data:"sto_id=" + id,
							async : false,
							dataType : "json",
							success : function(data) {
								if (data == 0) {
									alert("该仓库不存在！");
									$('#sid').val("");
									$('#sid').focus();
								} 
							},
							complete : function(XMLHttpRequest, textStatus){},
							error : function() {
								alert("操作失败");
								location.reload();
							}
						});
					} */
					if(!((/^(\+|-)?\d+$/.test(num))&&num>=0)){
						e.preventDefault();
						alert("物品数量最少为0且只能为正整数！");
						return false;
					}
					if(!($.isNumeric(price)&&price>0)){
						e.preventDefault();
						alert("计划单价只能为大于0的数！");
						return false;
					}
					/* else if(!$.isNumeric($("input[id=sid]").val())){
						e.preventDefault();
						alert("仓库编号只能为整数");
						return false;
					} */
				});
				
			
			});
			
		});
		
		$(document).ready(function(){
			$("input[id=name]").blur(function(){
				var p_name = $("#p_name").val();
				var g_name = $("input[id=name]").val();
				if(g_name != p_name){
				$.ajax({
					contentType:"application/x-www-form-urlencoded; charset=utf-8",
					type:"POST",
					url:"goods!checkGoodsName",
					data:"g_name="+g_name,
					async:false,
					dataType:"json",
					success:function(data){
						if(data == 1){
							alert("该物品已存在！");
						}
					},
					complete:function(XMLHttpRequest,textStatus){},
					error:function(){
						alert("操作失败");
						location.reload();
					}
				});}
			});
		});
		
		$(document).ready(function(){
			  $("input[id=sid]").blur(function(){
				 
				   var id=$("input[id=sid]").val();
				  
					if(((/^(\+|-)?\d+$/.test(id))&&id>0)){
				
						$.ajax( {
							contentType:"application/x-www-form-urlencoded; charset=utf-8", 
							type:"POST",
							url : "goods!checkStorageId",
							data:"sto_id=" + id,
							async : false,
							dataType : "json",
							success : function(data) {
								if (data == 0) {
									alert("该仓库不存在！");
								} 
							},
							complete : function(XMLHttpRequest, textStatus){},
							error : function() {
								alert("操作失败");
								location.reload();
							}
						});
					}
			    
			    
			  });
			
		});
	});
	
	
	
</script>
</head>
<body>
<div class="main">
	<br /><br /><br /><br />
	<input type="hidden" name="goods.g_name" size="20" maxlength="20" id="p_name"
							value='<s:property value="listid.g_name"/>' readonly="readonly">
		 <form action="goods!goodsUpdate"
			method="post" name="form1">
			<table width="300" border="0" align="center" cellpadding="4"
				cellspacing="1" id="tabList">
							 
				<tr align="center">
					<th colspan="2" scope="col">
						修改物品
					</th>
				</tr>
				
				<tr>
					<td width="120" align="right">
						物品编号
					</td>
					<td width="175" align="left">
						
						<input type="text" name="goods.g_id" size="20" maxlength="20"
							value='<s:property value="listid.g_id"/>' readonly>
						
					</td>
				</tr>
				<tr>
					<td align="right">
						物品名称
					</td>
					<td align="left">
						<input type="text" name="goods.g_name" size="20" maxlength="20" id="name"
							value='<s:property value="listid.g_name"/>'>
					</td>
				</tr>
				<tr>
					<td align="right">
						计量单位
					</td>
					<td align="left">
						<input type="text" name="goods.g_unit" size="20" maxlength="20" id="unit"
							value='<s:property value="listid.g_unit"/>'>
					</td>
				</tr>
				<tr>
					<td align="right">
						仓库编号
					</td>
					<td align="left">
						<input type="text" name="goods.sto_id" size="20" maxlength="20" id="sid"
							value='<s:property value="listid.sto_id"/>'>
					</td>
				</tr>
				<tr>
					<td align="right">
						物品数量
					</td>
					<td align="left">
						<input type="text" name="goods.g_num" size="20" maxlength="20" id="num"
							value='<s:property value="listid.g_num"/>'>
					</td>
				</tr>
				<tr>
					<td align="right">
						计划单价
					</td>
					<td align="left">
						<input type="text" name="goods.g_price" size="20" maxlength="20" id="price"
							value='<s:property value="listid.g_price"/>'>
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