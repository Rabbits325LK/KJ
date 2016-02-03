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
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/views/login.js"></script>
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
					<font color="#FF6633" size="120"><span
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
					</form><br>
					<div id="ErrorMsgLoginDiv"></div>
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
					<font color="#9966FF" size="120"><span
						class="glyphicon glyphicon-paste" aria-hidden="true"></span>REGISTER</font>
				</div>
				<div class="modal-body">
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
								type="text" class="form-control" placeholder="手机" id="phone" onblur="checkPhone();"
								name="phone" aria-describedby="basic-addon1">
						</div>
						<br>
						<div class="input-group" id="RPW">
							<span class="input-group-addon" id="basic-addon1"><span
								class="glyphicon glyphicon-lock" aria-hidden="true"></span></span> <input
								type="password" class="form-control" placeholder="密码" onblur="checkPassword();"
								name="password" id="password" aria-describedby="basic-addon1">
						</div>
						<br>
						<div class="input-group" id="RRPW">
							<span class="input-group-addon" id="basic-addon1"><span
								class="glyphicon glyphicon-lock" aria-hidden="true"></span></span> <input
								type="password" class="form-control" placeholder="密码"
								name="rpassword" id="rpassword" aria-describedby="basic-addon1" onblur="checkPassword();">
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
					</form><br>
					<div id="ErrorMsgRegisterDiv"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="registerSubmit" disabled>注册</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- MsgModel -->
	<div class="modal fade" id="MsgModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLable">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<font color="#33FFCC" size="120"><span
						class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>SUCCESS</font>
				</div>
				<div class="modal-body">
					注册成功!
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>