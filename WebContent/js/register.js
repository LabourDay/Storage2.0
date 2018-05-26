/**
 * 
 */
function sUserName(s)
{
	var regu="^[0-9a-zA-Z]{4,16}$";
	var re=new RegExp(regu);
	if(!re.test(s))
	{
		document.getElementById("nametext").innerHTML="用户名格式不对!";
		document.getElementById("nametext").style.color="red";
	}
	else
	{
		document.getElementById("nametext").innerHTML="用户名格式通过!";
		document.getElementById("nametext").style.color="green";
	}
}
function isNumberOrLetter(s)
{
	var regu="^[0-9a-zA-Z]{2}$";
	var re=new RegExp(regu);
	if(!re.test(s))
	{
		document.getElementById("idtext").innerHTML="用户编号不对!";
		document.getElementById("idtext").style.color="red";
	}
	else
	{
		document.getElementById("idtext").innerHTML="用户编号通过!";
		document.getElementById("idtext").style.color="green";
	}
}
	
	function validatorPassword(s)
	{
		regu="^[0-9a-zA-Z]{4,16}$";
		re=new RegExp(regu);
		if(!re.test(s))
		{
			document.getElementById("passtext").innerHTML="密码4-16位数字，字母组成";
		document.getElementById("passtext").style.color="red";
	}
	else
	{
		document.getElementById("passtext").innerHTML="通过!";
		document.getElementById("passtext").style.color="green";
		}
	}
	
	function isNumber(s)
	{
		var regu="^[0-9]{4,16}$";
		var re=new RegExp(regu);
		if(!re.test(s))
		{
			document.getElementById("idtext").innerHTML="用户编号不对!";
			document.getElementById("idtext").style.color="red";
		}
		else
		{
			document.getElementById("idtext").innerHTML="用户编号通过!";
			document.getElementById("idtext").style.color="green";
		}
	}	
	
	function workerPassword(s)
	{
		regu="^[0-9a-zA-Z]{4,16}$";
		re=new RegExp(regu);
		if(!re.test(s))
		{
			document.getElementById("pwtext").innerHTML="密码4-16位数字，字母组成";
		document.getElementById("pwtext").style.color="red";
	}
	else
	{
		document.getElementById("pwtext").innerHTML="通过!";
		document.getElementById("pwtext").style.color="green";
		}
	}
	
	function workerTel(s)
	{
		var regu="^[0-9]{11}$";
		var re=new RegExp(regu);
		if(!re.test(s))
		{
			document.getElementById("teltext").innerHTML="手机号不对!";
			document.getElementById("teltext").style.color="red";
		}
		else
		{
			document.getElementById("teltext").innerHTML="手机号通过!";
			document.getElementById("teltext").style.color="green";
		}
	}

function passwordConfirm()
{
	if(document.f1.pass.value.toLowerCase()!=document.f1.pass1.value.toLowerCase())
	{
		document.getElementById("pass1text").innerHTML="密码不一致!";
		document.getElementById("pass1text").style.color="red";
	}
	else
	{
		document.getElementById("pass1text").innerHTML="通过!";
		document.getElementById("pass1text").style.color="green";
		}
		}
		function isEmail(strEmail) //邮箱验证
           {
               if(strEmail.search("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$") == -1)
              {//search  如果包含匹配的字符串，则返回字符串第一次出现的起始位置，否则返回-1
                 document.getElementById ("emailtext").innerHTML="格式不对！";
                 document.getElementById ("emailtext").style.color="red";
              }
              else
              {
                  document.getElementById ("emailtext").innerHTML="通过！";
                  document.getElementById ("emailtext").style.color="green";
                    }

           }
		   function idNumber(s)//身份证验证
           {
           reg1=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
           re1=new RegExp(reg1)
		 if(!re1.test(s))
             {
                   document.getElementById ("idCardtext").innerHTML="格式不对！";
                   document.getElementById ("idCardtext").style.color="red";
                
             }
             else
             {
                  document.getElementById ("idCardtext").innerHTML="通过！";
                  document.getElementById ("idCardtext").style.color="green";
                 
             }
             
           }
		   function isMobileNO(s)//手机验证
           { 
          	var a= /^1[3,5,8]\d{9}$/;
           if(!s.match(a))
           {//字符串match方法，如果匹配成功，返回由匹配成功的字符串组成的数组，否则返回null
               document.getElementById ("mobiletext").innerHTML="格式不对！";
               document.getElementById ("mobiletext").style.color="red";
           }
           else
           {
                  document.getElementById ("mobiletext").innerHTML="通过！";
                  document.getElementById ("mobiletext").style.color="green";
                 
           }
           
           }
		