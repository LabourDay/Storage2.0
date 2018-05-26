function checkData(){
	
	var userid=document.getElementById("username").value;
	var passwd=document.getElementById("password").value;
	if(userid==""){
	alert("用户ID不能为空！");
		return false;
	}
	
	if(passwd==""){
		alert("用户密码不能为空！");
		return false;
	}
	return true;
}

function checkUser(){
	if(document.getElementById("radio1").checked==true)
		{
		  alert("管理员无法执行注册！");
		}
	if(document.getElementById("radio2").checked==true){
		  window.location.href="register.jsp";
		}
}