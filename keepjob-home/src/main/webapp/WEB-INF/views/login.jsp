<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary btn-lg"
		data-toggle="modal" data-target="#LoginModal">Login</button>
	<button type="button" class="btn btn-primary btn-lg"
		data-toggle="modal" data-target="#RegisterModal">Register</button>
	<!-- Login Modal -->
	<div class="modal fade" id="LoginModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLable">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<font color="#CCCCCC" size="120"><span
						class="glyphicon glyphicon-lock" aria-hidden="true"></span>LOGIN</font>
				</div>
				<div class="modal-body">
					<form id="loginForm" method="post">
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1"><span
								class="glyphicon glyphicon-user" aria-hidden="true"></span></span> <input
								type="text" class="form-control" placeholder="手机/邮箱"
								id="searchCode" name="searchCode"
								aria-describedby="basic-addon1">
						</div>
						<br>
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1"><span
								class="glyphicon glyphicon-lock" aria-hidden="true"></span></span> <input
								type="password" class="form-control" placeholder="密码"
								name="password" aria-describedby="basic-addon1">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="loginSubmit">登录</button>
				</div>
			</div>
		</div>
	</div>

	<!-- register Modal -->
	<div class="modal fade" id="RegisterModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLable">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<font color="#CCCCCC" size="120"><span
						class="glyphicon glyphicon-paste" aria-hidden="true"></span>REGISTER</font>
				</div>
					<form id="registerForm" method="post">
						<div class="input-group" id="RE">
							<span class="input-group-addon" id="basic-addon1"><span
								class="glyphicon glyphicon-envelope" aria-hidden="true"></span></span>
							<input type="text" class="form-control" placeholder="邮箱"
								id="email" name="email" aria-describedby="basic-addon1" onblur="checkEmail();">
						</div>
						<br>
						<div class="input-group" id="RP">
							<span class="input-group-addon" id="basic-addon1"><span
								class="glyphicon glyphicon-phone" aria-hidden="true"></span></span> <input
								type="text" class="form-control" placeholder="手机" id="phone"
								name="phone" aria-describedby="basic-addon1">
						</div>
						<br>
						<div class="input-group" id="RPW">
							<span class="input-group-addon" id="basic-addon1"><span
								class="glyphicon glyphicon-lock" aria-hidden="true"></span></span> <input
								type="password" class="form-control" placeholder="密码"
								name="password" id="password" aria-describedby="basic-addon1">
						</div>
						<br>
						<div class="input-group" id="RRPW">
							<span class="input-group-addon" id="basic-addon1"><span
								class="glyphicon glyphicon-lock" aria-hidden="true"></span></span> <input
								type="password" class="form-control" placeholder="密码"
								name="rpassword" id="rpassword" aria-describedby="basic-addon1">
						</div>
						<br>
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1"><span
								class="glyphicon glyphicon-user" aria-hidden="true"></span></span> <input
								type="text" class="form-control" placeholder="真实姓名"
								name="realName" id="realName" aria-describedby="basic-addon1">
						</div>
						<br>
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1"><span
								class="glyphicon glyphicon-user" aria-hidden="true"></span></span> <input
								type="text" class="form-control" placeholder="年龄" name="age"
								id="age" aria-describedby="basic-addon1">
						</div>
						<br>
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1"><span
								class="glyphicon glyphicon-user" aria-hidden="true"></span></span> <select
								class="form-control" name="sex" id="sex">
								<option value="" selected="selected">请选择</option>
								<option value="1">男</option>
								<option value="2">女</option>
								<option value="9">其他</option>
							</select>
						</div>
					</form>
					<div class="alert alert-warning">
						<a href="#" class="close" data-dismiss="alert"> &times; </a> <strong>警告！</strong>您的网络连接有问题。
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="registerSubmit">注册</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {

		/*登录
		--------------------------------------------------------*/
		$('#loginSubmit')
				.click(
						function() {
							var options = {
								url : '${pageContext.request.contextPath}/main/login.json',
								type : 'post',
								dataType : 'json',
								data : $("#loginForm").serialize(),
								success : function(data) {
									alert(data);
									if (data.success) {
										console.info(data.success);
										window.location.href = "${pageContenxt.request.contextPath}/keepjob-home/user/index.html";
									}
								}
							};
							$.ajax(options);
							//$('#LoginModal').modal('hide');
						})

		/*注册
		--------------------------------------------------------*/
		$('#registerSubmit').click(function() {
			var options = {
				url : '${pageContext.request.contextPath}/main/register.json',
				type : 'post',
				dataType : 'json',
				data : $("#registerForm").serialize(),
				success : function(data) {

					if (data.success) {
						alert(data.success);
						$('#RegisterModal').modal('hide');
					} else {
						alert(data.messge);
					}
				}
			};
			$.ajax(options);
		});
	});
	
	/*验证邮箱
	--------------------------------------------------------*/
	function checkEmail(){
		var email = $('#email').val();
		if(email == '' || email == null){
			$('#RE').removeClass();
			$('#RE').addClass("input-group has-warning has-feedback");
		}else{
			$.ajax({
				url : '${pageContext.request.contextPath}/main/checkEmail.json',
				type : 'post',
				dataType : 'json',
				data : { email : email},
				success : function(data){
					if(data.success){
						$('#RE').removeClass();
						$('#RE').addClass("input-group has-success has-feedback");
					}else{
						$('#RE').removeClass();
						$('#RE').addClass("input-group has-error has-feedback");
					}
				}
			});
		}
	}
	
	/*验证手机号码
	--------------------------------------------------------*/
	function checkPhone(){
		var phone = $('#phone').val();
		if(phone == '' || phone == null){
			$('#RP').removeClass();
			$('#RP').addClass("input-group has-warning has-feedback");
		}else{
			$.ajax({
				url : '${pageContext.request.contextPath}/main/checkPhone.json',
				type : 'post',
				dataType : 'json',
				data : { phone : phone},
				success : function(data){
					if(data.success){
						$('#RP').removeClass();
						$('#RP').addClass("input-group has-success has-feedback");
					}else{
						$('#RP').removeClass();
						$('#RP').addClass("input-group has-error has-feedback");
					}
				}
			});
		}
	}
	
	/*验证手机号码
	--------------------------------------------------------*/
	function checkPassword(){
		var password = $('#password').val();
		var rpassword = $('#password').val();
		if(password.equeal(rpassword)){
			$('#RPW').removeClass();
			$('#RRPW').removeClass();
			$('#RPW').addClass("input-group has-success has-feedback");
			$('#RRPW').addClass("input-group has-success has-feedback");
		}else{
			$('#RPW').removeClass();
			$('#RRPW').removeClass();
			$('#RPW').addClass("input-group has-error has-feedback");
			$('#RRPW').addClass("input-group has-error has-feedback");
		}
	}
</script>
</html>