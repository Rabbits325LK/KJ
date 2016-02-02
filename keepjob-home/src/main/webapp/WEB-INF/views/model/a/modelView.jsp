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
<style type="text/css">
body {
	padding-top: 70px;
}
</style>
</head>
<body class="container-fluid">
<!-- 菜单 -->
<nav class="navbar navbar-default navbar-fixed-top" style="background-color: #999999"><!-- menu color -->
   <div class="container-fluid">
    <ul class="nav navbar-nav navbar-right">
      <li><a href="javascript :;" onClick="javascript :history.back(-1);"><font color="#FFFFFF"><strong>返回</strong></font></a></li>
    </ul>
  </div>
</nav>
<!-- 菜单结束 -->

<!-- 图片 -->
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="margin: 0%;padding: 0%;">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img src="${pageContext.request.contextPath }/images/model/a/banner.jpg" alt="" style="width:100%;"><!-- cover_images_a -->
      <div class="carousel-caption">
       <h3>IMG_1</h3><!-- cover_title_a -->
    		<p>图片1</p><!-- cover_remark_a -->
      </div>
    </div>
    <div class="item">
      <img src="${pageContext.request.contextPath }/images/model/a/banner.jpg" alt="" style="width:100%;">
      <div class="carousel-caption">
        <h3>IMG_2</h3>
    		<p>图片2</p>
      </div>
    </div>
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
<!-- 图片结束 -->

<!-- 文本 -->
<div class="row text-center" style="margin-top: 5%;margin-bottom: 5%;">
	<h1>创意，我们保持大脑活力的源泉。</h1><!-- explain_title -->
	<div class="col-lg-2col-md-2 col-sm-2 col-xs-1" ></div>
	<div class="col-lg-8 col-md-8 col-sm-8 col-xs-10"  ><!-- explain_content -->
		<p class="lead">创意（create new meanings），用作名词时指有创造性的想法，构思等。用作动词时指提出有创造性的想法，构思等。创意也可以说是一种思维方式，新的想法创造出与众不同的产品。创意是传统的叛逆，是打破常规的哲学，是破旧立新的创造与毁灭的循环，是思维碰撞，智慧对接，是具有新颖性和创造性的想法。汉王充在《论衡·超奇》中提到：“ 孔子 得史记以作《春秋》，及其立义创意，褒贬赏诛，不复因史记者，眇思自出於胸中也。</p>
	</div>
	<div class="col-lg-2 col-md-2 col-sm-2 col-xs-1"  ></div>
</div>
<!-- 文本结束 -->

<!-- 公司/个人介绍 -->
<div class="row" style="background-color:#999999;padding-top: 5%;padding-bottom: 5%;"><!-- introduction_color -->
	<div class="col-lg-5 col-md-4 col-sm-4 col-xs-3"></div>
	<div class="col-lg-2 col-md-4 col-sm-4 col-xs-6" >
		<img src="${pageContext.request.contextPath }/images/logo/kj.jpg" alt="" class="img-circle" style="width:100%;" ><!-- introduction_images -->
	</div>
	<div class="col-lg-5 col-md-4 col-sm-4 col-xs-3"></div>
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
		<h2><font color="#FFFFFF">可及工作室</font></h2><!-- introduction_title -->
	<p class="lead"><font color="#FFFFFF">长沙可及工作室成立于2015年，由程序猿 Rabbits 和 Captian John联合创建。</font></p><!-- introduction_content -->
	</div>
	
</div>
<!--  公司/个人介绍结束 -->

<!-- 联系方式 -->
<div class="row" style="padding-top: 5%;padding-bottom: 5%;">
	<div class="col-lg-5 col-md-4 col-sm-4 col-xs-3"></div>
	<div class="col-lg-2 col-md-4 col-sm-4 col-xs-6" >
		<img src="${pageContext.request.contextPath }/images/logo/2wm.jpg" alt="" class="img-thumbnail" style="width:100%;">
	</div>
	<div class="col-lg-5 col-md-4 col-sm-4 col-xs-3"></div>
	
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center" >
	<br>
	<br>
			<p><font color="red"><span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span></font> : 湖南省天心区赤岭路通用时代国际社区</p>
			<p><font color="#999999"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></font> : rabbits325@live.cn</p>
				<p><font color="#663300"><span class="glyphicon glyphicon-phone-alt" aria-hidden="true"></span></font>: 0731-85115702</p>
			<p><font color="#333333"><span class="glyphicon glyphicon-phone" aria-hidden="true"></span></font>: 13510599125</p>
		
			
			
	</div>
</div>
<!-- 联系方式 -->

<!-- 尾页 -->
   <div class="row text-center" style="background-color: #999999">
    			<h4>www.keepjob.com</h4>
  </div>
<!-- 尾页 -->
</body>
</html>