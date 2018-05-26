<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>增加物品</title>
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="scripts/demo.css"> 

<script src="js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(document).ready(function() {
			$("form").submit(function(e) {
				var gid = $("input[id=gid]").val();			
				var unit = $("input[id=unit]").val();
				var id = $("input[id=sid]").val();
				var num = $("input[id=num]").val();
				var price = $("input[id=price]").val();
				$("input[type=text]").each(function(i) {
					if ($(this).val() == "") {
						e.preventDefault();
						alert("数据不能为空！");
						return false;
					}
					if(!$.isNumeric(gid)){
						e.preventDefault();
						alert("物品编号只能为数字");
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
				});
			});
		});
		
		$(document).ready(function(){
			$("input[id=gid]").blur(function(){
				var gid = $("input[id=gid]").val();
				if($.isNumeric(gid)){
					$.ajax( {
						contentType:"application/x-www-form-urlencoded; charset=utf-8", 
						type:"POST",
						url : "goods!checkId",
						data:"g_id=" + gid,
						async : false,
						dataType : "json",
						success : function(data) {
							if (data == 1) {
								alert("该商品ID已存在！");
							} 
						},
						complete:function(XMLHttpRequest,textStatus){},
						error : function() {
							alert("操作失败");
							location.reload();
						}
					});
				}
			});
		});
		
		$(document).ready(function(){
			$("input[id=name]").blur(function(){
				var g_name = $("input[id=name]").val();
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
				});
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
									 $("#sid").val("");
									$("#sid").focus(); 
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
	<div class="insert">
	<form action="goods!goodsInsert" method="post" name="f1" id="add">
    <table width="320" border="0" align="center">
      <tr>
        <p align="center" class="tabTitle">增加物品</p>
         <td width="120" height="21"><strong>物品编号:</strong></td>
         <td width="175"><input name="goods.g_id" id="gid" type="text"/></td>
       </tr>
       <tr>
         <td><strong>物品名称:</strong></td>
         <td><input  name="goods.g_name" id="name" type="text"/></td>
       </tr>
       <tr>
         <td><strong>计量单位:</strong></td>
         <td><input  name="goods.g_unit" id="unit" type="text"  /></td>
       </tr>
       <tr>
          <td><strong>仓库编号:</strong></td>
          <td><input name="goods.sto_id" id="sid" type="text" /></td>
       </tr>
       <tr>
          <td><strong>物品数量:</strong></td>
          <td><input name="goods.g_num" id="num" type="text" /></td>
       </tr>
       <tr>
          <td><strong>计划单价:</strong></td>
          <td><input name="goods.g_price" id="price" type="text" /></td>
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