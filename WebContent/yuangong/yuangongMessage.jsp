<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path=request.getContextPath();
	String basePath=request.getScheme()+"://"
					+request.getServerName()+":"+request.getServerPort()
					+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工信息</title>

</head>

<body>
	<div class="stomsg">
		
		<table width="700" border="0" cellspacing="0" cellpadding="4"
				align="center" id="tabInq">
		
		  
		  <tr align="center">
		    <td colspan="2" scope="col" class="tabTitle">员工信息</td>
		  </tr>	
		  <tr align="center" valign="top">
		    
		    <form action="yuangong!yuangongSearch" method="post">
		      <td width="250" style="float: left;">关键字<label>
		        <input placeholder="请输入员工姓名" name="strKey" 
		        		type="text" size="15" value="${sessionScope.strKey}" id="strKey">
		        <input type="button" name="button" id="button" value="重置" onclick="clearData()">
		        <input type="submit" name="button" id="button" value="搜索">
		      </label>
		      </td>
		      <s:if test="#session.sType=='管理员'">
		      <td width="140" style="float: right;">
		        <a href="yuangong!insertJsp"><img src="<%=basePath %>img/record_add.gif">增加员工信息</a>
		      </td>
		      </s:if>
		    </form>
		  </tr>
		</table>
		
		<table  width="1100" border="0" align="center" id="tabList">
		  <tr>
		    <th scope="col">员工编号</th>
		    <th scope="col">用户名</th>
		    <th scope="col">密码</th>
		    <th scope="col">真实姓名</th>
		    <th scope="col">手机号码</th>
		    <th scope="col">邮箱</th>
		    <th scope="col">请假次数</th>
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
		          <td align="center"><s:property value="list[#st.index].g_price"/></td> 
//		本类为根据用户输入的内容检索数据库中的数据的功能
//		iPageSize每页多少行，iPageCnt页数，  iPage当前行数号，  beforePage全记录总行数  本类为控制页面跳转显示下一页上一页功能
//      我目前的功能是通过字段列名   真实姓名  检索需要查到的数据行
		           --%>
		          <s:if test="#session.sType=='管理员'">
		          
		          </s:if>
		        
		    	</s:iterator>
		    	<td align="center">
		            <a href="yuangong!yuangongFindId?Id=<s:property value="w_id"/>">
		              <img src="<%=basePath %>img/record_(edit)_16x16.gif" border="0" 
		              			title="修改员工信息" />&nbsp;&nbsp;
		            </a>
		             <a href="yuangong!yuangongDelete?Id=<s:property value="w_id"/>">
		      
		              <img src="<%=basePath %>img/record_(delete)_16x16.gif" border="0"
								title="删除员工信息" />
		            </a>
		          </td>
		    	</tr>
		      </s:iterator>
			


		  
		    <tr align="center">
		    <td colspan="8">当前页面${iPage }/${iPageCnt }&nbsp;
		      <s:if test="#request.iPage>1">
		        <a href="yuangong!yuangongPage?iPage=1">第一页</a>
		        <a href="yuangong!yuangongPage?iPage=${iPage-1 }">上一页</a>
		      </s:if>
		      
		      <s:if test="#request.iPage<#request.iPageCnt">
		        <a href="yuangong!yuangongPage?iPage=${iPage+1 }">下一页</a>
		        <a href="yuangong!yuangongPage?iPage=${iPageCnt }">尾页</a>
		      </s:if> 
		    </td>
		  </tr>
		</table>
	</div>
</body>
</html>