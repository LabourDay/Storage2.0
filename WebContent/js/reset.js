/**
 * 
 */
function clearData() {
	document.getElementById("strKey").value=null;
}
function check()
{
  if(updatepw.password.value==0){
    alert("请输入密码");updatepw.password.focus();return false;
  }
  if(updatepw.newpw1.value==0){
    alert("请输入新密码");updatepw.newpw1.focus();return false;
  }
  if(updatepw.newpw2.value==0){
    alert("请再次输入新密码");updatepw.newpw2.focus();return false;
  }
  if(updatepw.newpw1.value!=updatepw.newpw2.value){
    alert("两次新密码输入不一致,请从新输入");
    updatepw.newpw1.value="";
    updatepw.newpw2.value="";
    updatepw.newpw1.focus();
    return false;
  }
  return true;
}

function cf(){
	
	 if(confirm("您确定要退出吗?")){
	        window.location.href = "login!logout";
 
        }
}

function deleteSto(s){

	if(confirm("您确定要删除该记录吗？")){
		window.location.href="StorageDeleteServlet?Id="+s;
	}
}

function deleteGoods(s){
	if(confirm("您确定要删除该记录吗？")){
		window.location.href = "login!logout";
	}
}

function deleteIn(s){
	if(confirm("您确定要删除该记录吗？")){
		window.location.href="InDeleteServlet?Id="+s;
	}
}

