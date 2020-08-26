/**
 * 
 */

function getUser(){
	var AjaxURL="./UserInfo";
	//alert(AjaxURL);
	$.ajax({
		//指定调用接口的url资源地址，url、type参数间用逗号分隔；
//		url:"./UseMysql",
		//指定http方法
		type:"post",
		//返回数据提交给ajax处理的类型，text代表纯文本格式的字符串
		dataType:"json",

		url:AjaxURL,
		//ajax请求完成拿到返回之后进行的处理操作，一种是成功的时候，一种是失败的时候
		//ajax请求提交之后得到的返回数据，就是result，不需要自己进行定义
		success:function(result){
//			alert(result);
			document.getElementById("username").innerHTML=result["username"];
			document.getElementById("nickname").innerHTML=result["nickname"];
			document.getElementById("describe").innerHTML=result["describe"];
		},
		error:function(result){
//			alert("接口请求出错，请检查请求内容");
			alert("调用userinfo接口请求出错，请检查。");
		}
	});
}

function logoutAjax(){
	$.ajax({
		//指定调用接口的url资源地址，url、type参数间用逗号分隔；
		url:"./Logout",
		//指定http方法
		type:"post",
		//返回数据提交给ajax处理的类型，text代表纯文本格式的字符串
		dataType:"json",
		//url:AjaxURL,
		//ajax请求完成拿到返回之后进行的处理操作，一种是成功的时候，一种是失败的时候
		//ajax请求提交之后得到的返回数据，就是result，不需要自己进行定义
		
		success:function(result){
			alert(result["msg"]);
			window.location.href="index.html";
		},
		error:function(result){
			alert("调用logout接口请求出错，请检查。");
			
		}
	
	
	})
}