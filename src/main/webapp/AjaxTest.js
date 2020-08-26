/**
**/

function loginAjax(){
	$.ajax({
		//指定调用接口的url资源地址，url、type参数间用逗号分隔；
		url:"./Login",
		//指定http方法
		type:"post",
		//返回数据提交给ajax处理的类型，text代表纯文本格式的字符串
		dataType:"json",
		//ajax向接口提交的数据，读取指定form元素当中的内容，通过serialize方法进行初始化
		data:$("#Loginform").serialize(),
		//ajax请求完成拿到返回之后进行的处理操作，一种是成功的时候，一种是失败的时候
		//ajax请求提交之后得到的返回数据，就是result，不需要自己进行定义
		success:function(result){
//			alert(result);
			document.getElementById("msgDiv").innerText=result["mag"];
			//alert(result["mag"]);
			if(result["mag"]=="登录成功"){
				window.location.href="userinfo.html";
			}
		},
		error:function(result){
//			alert("接口请求出错，请检查请求内容");
			document.getElementById("msgDiv").innerText="接口请求出错，请检查请求内容";
		}
	
	
	})
}