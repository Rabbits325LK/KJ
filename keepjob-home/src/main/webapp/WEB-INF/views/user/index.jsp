<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style type="text/css">
body {
	padding-top: 70px;
}
</style>
</head>
<body class="container-fluid">
	<nav class="navbar navbar-fixed-top navbar-inverse">
	<div class="container-fluid">
		<a class="navbar-brand" href="#">KEEPJOB</a>
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false">系统<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="#">修改资料</a></li>
					<li><a href="#">修改密码</a></li>
					<li><a href="#">注销</a></li>
					<li role="separator" class="divider"></li>
					<li><a href="#">退出</a></li>
				</ul></li>
		</ul>
	</div>
	</nav>
	<div class="row">
		<div class="panel col-lg-6 col-md-6 col-sm-12 col-xs-12">
			<h1>
				基本信息 <small>Basic information</small>
			</h1>
			<hr>
			<blockquote>
				<p>真实姓名： ${result.realName }</p>
			</blockquote>
			<blockquote>
				<p>性别： ${result.sex == 1 ? '男' : '女'}</p>
			</blockquote>
			<blockquote>
				<p>年龄： ${result.age }</p>
			</blockquote>
			<blockquote>
				<p>最后登陆时间：${result.lastLoginDate }</p>
			</blockquote>
			<blockquote>
				<p>最后登陆IP地址： ${result.lastLoginIp }</p>
			</blockquote>
		</div>

		<div class="panel col-lg-6 col-md-6 col-sm-12 col-xs-12">
			<h1>
				模版信息 <small>Model information</small>
			</h1>
			<c:forEach items="${models }" var="model">
				<div class="panel">${model.headTitle }</div>
			</c:forEach>
			<hr>
			<button class="btn btn-warning btn-lg" onclick="toModelAdd();">
				<span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
				创建主页模版
			</button>
			<button class="btn btn-success btn-lg" onclick="toModelShow();">
				<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
				预览主页模版
			</button>
			<br />
		</div>
	</div>
</body>
<script type="text/javascript">
	function toModelAdd(){
		window.location.href = "${pageContext.request.contextPath}/user/toAddModel.html";
	}
	function toModelShow(){
		window.location.href = "${pageContext.request.contextPath}/user/showModel.html";
	}
</script>
</html>