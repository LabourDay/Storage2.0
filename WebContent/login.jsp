<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
 <script src="${pageContext.request.contextPath}/js/login.js"></script>
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#radio1").click(function(){
		$("#username").attr("name","manager.m_name");
		$("#password").attr("name","manager.m_password");
	});
	$("#radio2").click(function(){
		$("#username").attr("name","worker.w_username");
		$("#password").attr("name","worker.w_password");
	});
});

function loadimage(){
	document.getElementById("randImage").src = "${pageContext.request.contextPath}/image.jsp?"+Math.random();
} 

</script>

</head>
<body>
  
	<div class="l_div">
		<form class="form-signin" action="login!login" method="post" onSubmit="return check()" name="login" id="login">
			<h2 class="form-signin-heading">库存管理系统登录</h2>
			<hr>
		    <table align="center">
		            
		    		
		    		
		            <tr>
	                     <td align="right">用户名：</td>	            
		                 <td><input type="text" name="manager.m_name" id="username" class="input"  ></td>
		                	  
		            </tr><br/>
		            
		             <tr>
	                     <td align="right">密&nbsp;码：</td>	            
		                 <td><input type="password" name="manager.m_password" id="password" class="input" ></td>
		                 	  
		            </tr>
		            
		            <tr>			
						<td >验证码：</td>
						<td><input type="text" value="${imageCode }" name="imageCode"
							id="imageCode" size="5" />&nbsp;<img
							onclick="javascript:loadimage();" title="换一张试试" name="randImage"
							id="randImage" src="image.jsp" 
							align="absmiddle"></td>
					</tr>		            
		      	     <tr>
		      	        <th colspan="6" align="center"><input name="sType" type="radio" id="radio1" value="管理员" checked>
             			<label for="radio">管理员</label>&nbsp;&nbsp;
             			<input name="sType" type="radio" id="radio2" value="普通员工">
             			<label for="radio">普通员工</label><br /></th>
		      	     </tr>
		      		
			  	    <tr>
	                     <td ></td>	            
		                 <td><font color="red">${error }</font></td>
		                 <td></td>	  	  
		            </tr>       		            
		             <tr>
	                     <td ></td>	            
		                 <td><input type="submit" onmousedown="return checkData()" class="btn" value="登录"/>&nbsp;&nbsp;
		                 
		                 <a onclick="checkUser()"><input type="button" class="btn" value="注册"/></a></td>	      
		                 <td></td>	  	  
		            </tr>
		    </table>
		</form>
		
		<div id="demo" style="overflow:hidden; width:100%; margin-top: 180px;">
			
               <table>
                <tr>
                 <td align="center" id="demo1"> <!--第1个单元格“demo1”-->
                   <table border="0" cellspacing="0" cellpadding="0">
		              <tr>
                         <td ><img src="img/ship2.png" style="margin-left: 30px; width: 150; height: 80px"/></td>
                         <td ><img src="img/ship2.png" style="margin-left: 230px; width: 150; height: 80px"/></td>
                         <td ><img src="img/ship2.png" style="margin-left: 30px; width: 150; height: 80px"/></td>
                         <td ><img src="img/ship2.png" style="margin-left: 30px; width: 150; height: 80px"/></td>
                         <td ><img src="img/ship2.png" style="margin-left: 30px; width: 150; height: 80px"/></td>
                         <td ><img src="img/ship2.png" style="margin-left: 30px; width: 150; height: 80px"/></td>
                         <td ><img src="img/ship2.png" style="margin-left: 30px; width: 150; height: 80px"/></td>
		             </tr>
                  </table>
	             </td>
              <td id="demo2"></td><!--第2个单元格“demo2”-->
             </tr>
           </table>
         </div>
	</div>
	<script type="text/javascript">

var speed=30 /*数值越大滚动速度越慢*/
var demo=document.getElementById("demo");
var demo1=document.getElementById("demo1"); /*demo1是滚动内容*/
var demo2=document.getElementById("demo2");
demo2.innerHTML=demo1.innerHTML; /*demo2为demo1的直接克隆*/

function Marquee()
{ 
if(demo.scrollLeft>demo1.offsetWidth) /*当滚动条滚动至demo1和demo2的交界处时*/
  {  demo.scrollLeft=0;   /*滚动条调回到原始位置*/  /*demo.scrollLeft-=demo1.offsetWidth */ }
else
  {    demo.scrollLeft++; }  /*每次加1px，向左滚动一个像素*/
} 

//offsetwidth  对象的可见宽度
//scrollLeft  对象的卷去的左，滚动的距离

var MyMar=setInterval(Marquee,speed);/*每隔30毫秒执行Marquee函数*/
</script>
</body>
</html>