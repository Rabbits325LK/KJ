<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<!-- <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"> -->

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
	/*效果
	----------------------------------------------*/
	var imgflag = true;
	function openLoginPanel() {
		if(imgflag == true){
			$('#imgBtn').animate({marginLeft:"50%"});
			//$('#loginDiv').css("display","");
			$('#LOGODIV').removeClass();
			$('#PANELDIV').removeClass();
			$('#LOGODIV').addClass("col-md-6");
			$('#PANELDIV').addClass("col-md-3");
			setTimeout("showLoginDiv()",800);
			
			//showLogoText();
			imgflag = false;
		}else{
			$('#LOGODIV').removeClass();
			$('#PANELDIV').removeClass();
			$('#LOGODIV').addClass("col-md-8");
			$('#PANELDIV').addClass("col-md-4");
			$('#imgBtn').animate({marginLeft:"63%"});
			
			$('#loginDiv').css("display","none");
			hiddenLogoText();			
			imgflag = true;
		}
		
		
	}
	function showLoginDiv(){
		$('#loginDiv').css("display","");
	}
	
	function showLogoText(){
		var content = "www.keepjob.com";
		$('#LogoText').html(content);
	}
	
	function hiddenLogoText(){
		var content = "";
		$('#LogoText').html(content);
	}
	
	/*登录
	----------------------------------------------*/
	function onLoginClick() {
        if(null == $('#username').val() || '' == $('#username').val() || '手机号/用户名/邮箱'==$('#username').val()){
        	$("#msg").html('请输入您的登录账号.');
        	$('#username').focus();
        	return false;
        }
        if(null == $('#password').val() || '' == $('#password').val()){
        	$("#msg").html('请输入您的登录密码.');
        	$('#password').focus();
        	return false;
        }
        $("#loginForm").submit();
    }
  	function onClearClick(){
		$('#username').val('手机号/用户名/邮箱');
		$('#password').val('');
	}
    
javascript:window.history.forward(1);//防止后退
$(function(){
	if (window != top){top.location.href = location.href;}  //确保session过期登陆页面跳出iframe
	if("${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username }"){
		window.location.href = "${pageContext.request.contextPath}/main/main.html";
	}
	$("#username").focus();
	if("${error}"){
		$.messager.alert("系统提示","${error}","error",function(){
			window.location.href = "${pageContext.request.contextPath}/login.html";
		});
	}
	if("${sessionScope.SPRING_SECURITY_LAST_EXCEPTION}"){
		$("#msg").html("用户名或密码错误");
	}else{
		$("#msg").html("请输入用户名和密码");
	}
});
</script>
</head>
<body>
	
	<div class="row" style="margin-top: 13%;">
		<div class="col-md-8" id="LOGODIV">
			<div  id="imgBtn" onclick="openLoginPanel();" style="margin-left: 63%; ">
			<img src="${pageContext.request.contextPath }/images/logo.jpg">
			<h2 id="LogoText" ></h2>	 
			</div>
		</div>
		<div class="col-md-4" id="PANELDIV">
			<div class="row panel panel-default" id="loginDiv"
				style="margin-left: 2%; margin-right: 30%;display:none;" >
				<form id="loginForm" action="${pageContext.request.contextPath }/j_spring_security_check" method="post">
					<div class="form-group" style="margin-left: 2%; margin-right: 2%;margin-top: 4%;">
						<label for="username">报名号:</label> <input
							type="text" class="form-control" id="username" name="j_username"
							placeholder="账号">
					</div>
					<div class="form-group" style="margin-left: 2%; margin-right: 2%;">
						<label for="password">对暗号:</label> <input
							type="password" class="form-control" id="password" name="j_password"
							placeholder="密码">
					</div>
					<div class="row"
						style="margin-left: 2%; margin-right: 2%; margin-bottom: 4%;">
						<div class="col-md-6">
							<button type="button" class="btn btn-default btn-block" onclick="onLoginClick();">答复</button>
						</div>
						<div class="col-md-6">
							<button type="reset" class="btn btn-default btn-block">再想想</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>