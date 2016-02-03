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
<script src="${pageContext.request.contextPath }/js/ueditor/ueditor.config.js"></script>
<script src="${pageContext.request.contextPath }/js/ueditor/ueditor.all.min.js"></script>
<style type="text/css">
body {
	padding-top: 70px;
}
</style>

</head>
<body class="container-fluid">
 <script type="text/plain" id="j_ueditorupload" style="height:5px;display:none;" ></script>
 <script>
 var ImgVal = 0;
 
//实例化编辑器
var o_ueditorupload = UE.getEditor('j_ueditorupload',
{
  autoHeightEnabled:false
});
o_ueditorupload.ready(function ()
{

o_ueditorupload.hide();//隐藏编辑器


//监听图片上传
o_ueditorupload.addListener('beforeInsertImage', function (t,arg)
{
    //alert('这是图片地址：'+arg[0].src);
    //$('#AImg').attr('src',arg[0].src);
    toImageVal(arg[0].src);
    ImgVal = 0;
});
});
       
      //弹出图片上传的对话框
      function upImageA()
      {
        var myImage = o_ueditorupload.getDialog("insertimage");
        ImgVal = 1;
        myImage.open();
      }
      function upImageB()
      {
        var myImage = o_ueditorupload.getDialog("insertimage");
        ImgVal = 2;
        myImage.open();
      }
      function upImageC()
      {
        var myImage = o_ueditorupload.getDialog("insertimage");
        ImgVal = 3;
        myImage.open();
      }
      function upImageD()
      {
        var myImage = o_ueditorupload.getDialog("insertimage");
        ImgVal = 4;
        myImage.open();
      }
      function upImageE()
      {
        var myImage = o_ueditorupload.getDialog("insertimage");
        ImgVal = 5;
        myImage.open();
      }
      function upImageF()
      {
        var myImage = o_ueditorupload.getDialog("insertimage");
        ImgVal = 6;
        myImage.open();
      }
      
      //上传图片赋值
      function toImageVal(src){
    	  	if(ImgVal == 1){
    	  		$('#AImg').attr('src',src);
    	  		$("#coverImagesA").val(src);
    	  	}else if(ImgVal == 2){
    	  		$('#BImg').attr('src',src);
    	  		$("#coverImagesB").val(src);
    	  	}else if(ImgVal == 3){
    	  		$('#CImg').attr('src',src);
    	  		$("#coverImagesC").val(src);
    	  	}else if(ImgVal == 4){
    	  		$('#DImg').attr('src',src);
    	  		$("#coverImagesD").val(src);
    	  	}else if(ImgVal == 5){
    	  		$('#EImg').attr('src',src);
    	  		$("#coverImagesE").val(src);
    	  	}else if(ImgVal == 6){
    	  		$('#FImg').attr('src',src);
    	  		$("#introductionImages").val(src);
    	  	}else if(ImgVal == 7){
    	  		$('#')
    	  	}
      }
    
</script>   
	<nav class="navbar navbar-default navbar-fixed-top"
		style="background-color: #999999">
		<!-- menu color -->
		<div class="container-fluid">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="javascript :;"
					onClick="javascript :history.back(-1);"><font color="#FFFFFF"><strong>返回</strong></font></a></li>
			</ul>
		</div>
	</nav>
	<div class="row" style="padding: 1%;">
		
		<form>
			<!-- <div><input type="text" name="coverImagesA" id="coverImagesA" ><input type="text" name="coverImagesB" id="coverImagesB" ><input type="text" name="introductionImages" id="introductionImages" ></div> -->
			<!-- a -->
			<div class="panel panel-danger col-lg-4 col-md-6 col-sm-12 col-xs-12">
				<div class="panel-heading">
					<h3 class="panel-title">轮番图片A</h3>
				</div>
				<div class="panel-body">
					<div class="col-lg-6 col-md-6 col-sm-4 col-xs-4">
						<img src="${pageContext.request.contextPath }/images/logo/2wm.jpg"
							alt="" class="img-thumbnail" style="width: 100%;" id="AImg">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-8 col-xs-8">
						<br>
						<div class="form-group">
							<label for="exampleInputEmail1">图片标题</label> <input type="text"
								class="form-control" placeholder="图片标题" id="coverTitleA"
								name="coverTitleA" aria-describedby="basic-addon1">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">图片简介</label>
							<textarea class="form-control" rows="2" cols="" id="coverRemarkA" name="coverRemarkA"></textarea>
						</div>
						<button type="button"class="btn btn-block btn-success" onClick="upImageA();">上传图片</button>
					</div>
				</div>
			</div>
			<!-- a end -->
		
			<!-- b -->
			<div class="panel panel-info col-lg-4 col-md-6 col-sm-12 col-xs-12">
				<div class="panel-heading">
					<h3 class="panel-title">轮番图片B</h3>
				</div>
				<div class="panel-body">
					<div class="col-lg-6 col-md-6 col-sm-4 col-xs-4">
						<img src="${pageContext.request.contextPath }/images/logo/2wm.jpg"
							alt="" class="img-thumbnail" style="width: 100%;" id="BImg">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-8 col-xs-8">
						<br>
						<div class="form-group">
							<label for="exampleInputEmail1">图片标题</label> <input type="text"
								class="form-control" placeholder="图片标题" id="coverTitleB"
								name="coverTitleB" aria-describedby="basic-addon1">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">图片简介</label>
							<textarea class="form-control" rows="2" cols="" id="coverRemarkB" name="coverRemarkB"></textarea>
						</div>
						<button type="button"class="btn btn-block btn-success" onClick="upImageB();">上传图片</button>
					</div>
				</div>
			</div>
			<!-- b end -->
			<!-- c -->
			<div class="panel panel-info col-lg-4 col-md-6 col-sm-12 col-xs-12">
				<div class="panel-heading">
					<h3 class="panel-title">轮番图片C</h3>
				</div>
				<div class="panel-body">
					<div class="col-lg-6 col-md-6 col-sm-4 col-xs-4">
						<img src="${pageContext.request.contextPath }/images/logo/2wm.jpg"
							alt="" class="img-thumbnail" style="width: 100%;" id="CImg">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-8 col-xs-8">
						<br>
						<div class="form-group">
							<label for="exampleInputEmail1">图片标题</label> <input type="text"
								class="form-control" placeholder="图片标题" id="coverTitleC"
								name="coverTitleC" aria-describedby="basic-addon1">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">图片简介</label>
							<textarea class="form-control" rows="2" cols="" id="coverRemarkC" name="coverRemarkC"></textarea>
						</div>
						<button type="button"class="btn btn-block btn-success" onClick="upImageC();">上传图片</button>
					</div>
				</div>
			</div>
			<!-- c end -->
		
			<!-- d -->
			<div class="panel panel-info col-lg-4 col-md-6 col-sm-12 col-xs-12">
				<div class="panel-heading">
					<h3 class="panel-title">轮番图片D</h3>
				</div>
				<div class="panel-body">
					<div class="col-lg-6 col-md-6 col-sm-4 col-xs-4">
						<img src="${pageContext.request.contextPath }/images/logo/2wm.jpg"
							alt="" class="img-thumbnail" style="width: 100%;" id="DImg">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-8 col-xs-8">
						<br>
						<div class="form-group">
							<label for="exampleInputEmail1">图片标题</label> <input type="text"
								class="form-control" placeholder="图片标题" id="coverTitleD"
								name="coverTitleD" aria-describedby="basic-addon1">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">图片简介</label>
							<textarea class="form-control" rows="2" cols="" id="coverRemarkD" name="coverRemarkD"></textarea>
						</div>
						<button type="button"class="btn btn-block btn-success" onClick="upImageD();">上传图片</button>
					</div>
				</div>
			</div>
			<!-- d end -->		
			
			<!-- e -->
			<div class="panel panel-info col-lg-4 col-md-6 col-sm-12 col-xs-12">
				<div class="panel-heading">
					<h3 class="panel-title">轮番图片E</h3>
				</div>
				<div class="panel-body">
					<div class="col-lg-6 col-md-6 col-sm-4 col-xs-4">
						<img src="${pageContext.request.contextPath }/images/logo/2wm.jpg"
							alt="" class="img-thumbnail" style="width: 100%;" id="EImg">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-8 col-xs-8">
						<br>
						<div class="form-group">
							<label for="exampleInputEmail1">图片标题</label> <input type="text"
								class="form-control" placeholder="图片标题" id="coverTitleE"
								name="coverTitleE" aria-describedby="basic-addon1">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">图片简介</label>
							<textarea class="form-control" rows="2" cols="" id="coverRemarkE" name="coverRemarkE"></textarea>
						</div>
						<button type="button"class="btn btn-block btn-success" onClick="upImageE();">上传图片</button>
					</div>
				</div>
			</div>
			<!-- e end -->		
			
			<!-- explain -->
			<div class="panel panel-info col-lg-4 col-md-6 col-sm-12 col-xs-12">
				<div class="panel-heading">
					<h3 class="panel-title">功能说明</h3>
				</div>
				<div class="panel-body">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<br>
						<div class="form-group">
							<label for="exampleInputEmail1">标题</label> <input type="text"
								class="form-control" placeholder="标题" id="coverTitleE"
								name="explainTitle" aria-describedby="explainTitle">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">正文</label>
							<textarea class="form-control" rows="4" cols="" id="explainContent" name="explainContent"></textarea>
						</div>
					</div>
				</div>
			</div>
			<!-- explain -->
			
			<!-- introduction -->
			<div class="panel panel-info col-lg-4 col-md-6 col-sm-12 col-xs-12">
				<div class="panel-heading">
					<h3 class="panel-title">个人／公司结束</h3>
				</div>
				<div class="panel-body">
					<div class="col-lg-6 col-md-6 col-sm-4 col-xs-4">
						<img src="${pageContext.request.contextPath }/images/logo/2wm.jpg"
							alt="" class="img-thumbnail" style="width: 100%;" id="FImg">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-8 col-xs-8">
						<br>
						<div class="form-group">
							<label for="exampleInputEmail1">个人/公司名称</label> <input type="text"
								class="form-control" placeholder="个人/公司名称" id="coverTitleE"
								name="introductionTitle" aria-describedby="introductionTitle">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">个人/公司介绍</label>
							<textarea class="form-control" rows="2" cols="" id="introductionTitle" name="introductionTitle"></textarea>
						</div>
						<!-- <div class="form-group">
							<label for="exampleInputEmail1">背景颜色</label>
							<select id="" name="" class="form-control">
									<option value="#CC0000"><font color="#CC0000">红</font></option>
									<option value="#000000"><font color="#000000">黑</font></option>
									<option value="#333333"><font color="#333333">墨黑</font></option>
									<option value="#666666"><font color="#666666">深灰</font></option>
							</select>
						</div> -->
						<button type="button"class="btn btn-block btn-success" onClick="upImageF();">上传图片</button>
					</div>
				</div>
			</div>
			<!-- introduction end -->		
			
			<div class="panel panel-info col-lg-4 col-md-6 col-sm-12 col-xs-12">
				<div class="panel-heading">
					<h3 class="panel-title">联系方式</h3>
				</div>
				<div class="panel-body">
					<div class="col-lg-6 col-md-6 col-sm-4 col-xs-4">
						<img src="${pageContext.request.contextPath }/images/logo/2wm.jpg"
							alt="" class="img-thumbnail" style="width: 100%;" id="GImg">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-8 col-xs-8">
						<br>
						<div class="form-group">
							<label for="exampleInputEmail1">地址</label> <input type="text"
								class="form-control" placeholder="地址" id="address"
								name="address" aria-describedby="basic-addon1">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">Email</label>
							<input type="text"
								class="form-control" placeholder="Email" id="email"
								name="email" aria-describedby="basic-addon1">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">固定电话号码</label> <input type="text"
								class="form-control" placeholder="固定电话号码" id="phone"
								name="phone" aria-describedby="basic-addon1">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">手机号码</label>
							<input type="text"
								class="form-control" placeholder="手机号码" id="mobilePhone"
								name="mobilePhone" aria-describedby="basic-addon1">
						</div>
						<button type="button"class="btn btn-block btn-success" onClick="upImageG();">上传图片</button>
					</div>
				</div>
			</div>
			
		</form>
	</div>
</body>
</html>