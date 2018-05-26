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
<script type="text/javascript">
function deleteg(s){
	if(confirm("您确定要删除该记录吗？")){
		window.location.href="goods!goodsDelete?Id="+s;
	}
}
</script>
<title>物品信息</title>
</head>
<body>
	<div class="stomsg">
		<table width="700" border="0" cellspacing="0" cellpadding="4"
				align="center" id="tabInq">
		  <tr align="center">
		    <td colspan="2" scope="col" class="tabTitle">物品信息</td>
		  </tr>	
		  <tr align="center" valign="top">
		    <form action="goods!goodsSearch" method="post">
		      <td width="250" style="float: left;">关键字<label>
		        <input placeholder="请输入物品名称" name="strKey" 
		        		type="text" size="15" value="${sessionScope.strKey}" id="strKey">
		        <input type="button" name="button" id="button" value="重置" onclick="clearData()">
		        <input type="submit" name="button" id="button" value="搜索">
		      </label>
		      </td>
		      <s:if test="#session.sType=='管理员'">
		      <td width="100" style="float: right;">
		        <a href="goods!insertJsp"><img src="<%=basePath %>img/record_add.gif">增加物品</a>
		      </td>
		      </s:if>
		    </form>
		  </tr>
		</table>
		
		<table  width="760" border="0" align="center" id="tabList">
		  <tr>
		    <th scope="col">物品编号</th>
		    <th scope="col">物品名称</th>
		    <th scope="col">计量单位</th>
		    <th scope="col">仓库编号</th>
		    <th scope="col">物品数量</th>
		    <th scope="col">计划单价</th>
		    <s:if test="#session.sType=='管理员'">
		    <th scope="col" style="border-right: none">操作</th>
		    </s:if>
		  </tr>
		  
				<%-- <s:iterator id="lt" value="list">
					<tr>
						<td><s:property value="list[#st.index].g_id"/></td>
						<td><s:property value="list[#st.index].g_id"/></td>
						<td><s:property value="list[#st.index].g_id"/></td>
						<td><s:property value="list[#st.index].g_id"/></td>
						<td><s:property value="list[#st.index].g_id"/></td>
						<td><s:property value="list[#st.index].g_id"/></td>
						<td><s:property value="list[#st.index].g_id"/></td>
					</tr>
				</s:iterator> --%>
		       <s:iterator value="#request.list"  status="st">
		       <tr>
		        <s:iterator value="#request.list.get(#st.index)">
		        	 <td align="center">
		        	 <s:property value="value"/>
		        	 <%-- <s:property value="list[#st.index].g_id"/>
		        	 <s:property value="list[#st.index].g_name"/>
		        	 <s:property value="list[#st.index].g_unit"/>
		        	 <s:property value="list[#st.index].sto_id"/>
		        	 <s:property value="list[#st.index].g_numb"/>
		        	 <s:property value="list[#st.index].g_price"/> --%>
		        	 </td>
		          <%-- <td align="center"><s:property value="list[#st.index].g_id"/></td>
		          <td align="center"><s:property value="list[#st.index].g_name"/></td>
		          <td align="center"><s:property value="list[#st.index].g_unit"/></td>
		          <td align="center"><s:property value="list[#st.index].sto_id"/></td>
		          <td align="center"><s:property value="list[#st.index].g_num"/></td>
		          <td align="center"><s:property value="list[#st.index].g_price"/></td>  --%>
		          
		          
		         
		        
		    	</s:iterator>
		    	<s:if test="#session.sType=='管理员'">
		    	<td align="center">
		            <a href="goods!goodsFindId?Id=<s:property value="g_id"/>">
		              <img src="<%=basePath %>img/record_(edit)_16x16.gif" border="0" 
		              			title="修改物品" />&nbsp;&nbsp;
		            </a>
		            <%--  <a href="goods!goodsDelete?Id=<s:property value="g_id"/>"> --%>
		            <%--  <a onclick="deleteg('<s:property value="g_id"/>')">  --%>
		            <a onclick="deleteg('<s:property value="g_id"/>')">
		              <img src="<%=basePath %>img/record_(delete)_16x16.gif" border="0"
								title="删除物品" />
		            </a>
		          </td>
		           </s:if>
		    	</tr>
		    	
		      </s:iterator>
			
		 <%--    <s:otherwise>
		      <tr align="center">
		        <td colspan="8"><span style="color: red">无信息！</span></td>
		      </tr>
		    </s:otherwise> --%>

		  
		    <tr align="center">
		    <td colspan="8">当前页面${iPage }/${iPageCnt }&nbsp;
		      <s:if test="#request.iPage>1">
		        <a href="goods!goodsPage?iPage=1">第一页</a>
		        <a href="goods!goodsPage?iPage=${iPage-1 }">上一页</a>
		      </s:if>
		      
		      <s:if test="#request.iPage<#request.iPageCnt">
		        <a href="goods!goodsPage?iPage=${iPage+1 }">下一页</a>
		        <a href="goods!goodsPage?iPage=${iPageCnt }">尾页</a>
		      </s:if> 
		    </td>
		  </tr>
		</table>
	</div>
</body>
</html>