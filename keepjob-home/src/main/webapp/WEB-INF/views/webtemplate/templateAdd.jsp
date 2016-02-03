<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/bootstrap3/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/bootstrap3/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath }/js/ueditor/ueditor.config.js"></script>
<script
	src="${pageContext.request.contextPath }/js/ueditor/ueditor.all.min.js"></script>
</head>
<body class="container-fluid">
	<script type="text/plain" id="j_ueditorupload"
		style="height:5px;display:none;"></script>
	<script>
		var ImgVal = 0;

		//实例化编辑器
		var o_ueditorupload = UE.getEditor('j_ueditorupload', {
			autoHeightEnabled : false
		});
		o_ueditorupload.ready(function() {

			o_ueditorupload.hide();//隐藏编辑器

			//监听图片上传
			o_ueditorupload.addListener('beforeInsertImage', function(t, arg) {
				//alert('这是图片地址：'+arg[0].src);
				//$('#AImg').attr('src',arg[0].src);
				toImageVal(arg[0].src);
				ImgVal = 0;
			});
		});

		//弹出图片上传的对话框
		function upImageA() {
			var myImage = o_ueditorupload.getDialog("insertimage");
			ImgVal = 1;
			myImage.open();
		}
		function upImageB() {
			var myImage = o_ueditorupload.getDialog("insertimage");
			ImgVal = 2;
			myImage.open();
		}
		function upImageC() {
			var myImage = o_ueditorupload.getDialog("insertimage");
			ImgVal = 3;
			myImage.open();
		}
		function upImageD() {
			var myImage = o_ueditorupload.getDialog("insertimage");
			ImgVal = 4;
			myImage.open();
		}
		function upImageE() {
			var myImage = o_ueditorupload.getDialog("insertimage");
			ImgVal = 5;
			myImage.open();
		}
		function upImageF() {
			var myImage = o_ueditorupload.getDialog("insertimage");
			ImgVal = 6;
			myImage.open();
		}

		//上传图片赋值
		function toImageVal(src) {
			if (ImgVal == 1) {
				$('#AImg').attr('src', src);
				$("#coverImagesA").val(src);
			} else if (ImgVal == 2) {
				$('#BImg').attr('src', src);
				$("#coverImagesB").val(src);
			} else if (ImgVal == 3) {
				$('#CImg').attr('src', src);
				$("#coverImagesC").val(src);
			} else if (ImgVal == 4) {
				$('#DImg').attr('src', src);
				$("#coverImagesD").val(src);
			} else if (ImgVal == 5) {
				$('#EImg').attr('src', src);
				$("#coverImagesE").val(src);
			} else if (ImgVal == 6) {
				$('#FImg').attr('src', src);
				$("#introductionImages").val(src);
			} else if (ImgVal == 7) {
				$('#')
			}
		}
	</script>
	<div class="row text-center">
		<h1>Web Template Add</h1>
	</div>
	<div class="row">
		<!-- COVER -->
		<div class="col-lg-4 col-md-3 col-sm-2 col-xs-12"></div>
		<div class="col-lg-4 col-md-6 col-sm-8 col-xs-12">
			<!-- Nav tabs -->
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active"><a href="#coverA"
					aria-controls="home" role="tab" data-toggle="tab">图片1</a></li>
				<li role="presentation"><a href="#coverB"
					aria-controls="profile" role="tab" data-toggle="tab">图片2</a></li>
				<li role="presentation"><a href="#coverC"
					aria-controls="messages" role="tab" data-toggle="tab">图片3</a></li>
				<li role="presentation"><a href="#coverD"
					aria-controls="settings" role="tab" data-toggle="tab">图片4</a></li>
				<li role="presentation"><a href="#coverE"
					aria-controls="settings" role="tab" data-toggle="tab">图片5</a></li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active panel panel-default"
					id="coverA">
					<div class="panel-body">
						<div class="col-lg-6 col-md-6 col-sm-4 col-xs-4">
							<img
								src="${pageContext.request.contextPath }/images/logo/2wm.jpg"
								alt="" class="img-thumbnail" style="width: 100%;" id="AImg">
						</div>
						<div class="col-lg-6 col-md-6 col-sm-8 col-xs-8">
							<br>
							<div class="form-group">
								<label for="exampleInputEmail1">A图片标题</label> <input type="text"
									class="form-control" placeholder="图片标题" id="coverTitleA"
									name="coverTitleA" aria-describedby="basic-addon1">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">A图片简介</label>
								<textarea class="form-control" rows="2" cols=""
									id="coverRemarkA" name="coverRemarkA"></textarea>
							</div>
							<button type="button" class="btn btn-block btn-success"
								onClick="upImageA();">上传图片</button>
						</div>
					</div>
				</div>
				<div role="tabpanel" class="tab-pane panel panel-default"
					id="coverB">
					<div class="panel-body">
						<div class="col-lg-6 col-md-6 col-sm-4 col-xs-4">
							<img
								src="${pageContext.request.contextPath }/images/logo/2wm.jpg"
								alt="" class="img-thumbnail" style="width: 100%;" id="BImg">
						</div>
						<div class="col-lg-6 col-md-6 col-sm-8 col-xs-8">
							<br>
							<div class="form-group">
								<label for="exampleInputEmail1">B图片标题</label> <input type="text"
									class="form-control" placeholder="图片标题" id="coverTitleB"
									name="coverTitleB" aria-describedby="basic-addon1">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">B图片简介</label>
								<textarea class="form-control" rows="2" cols=""
									id="coverRemarkB" name="coverRemarkB"></textarea>
							</div>
							<button type="button" class="btn btn-block btn-success"
								onClick="upImageB();">上传图片</button>
						</div>
					</div>
				</div>
				<div role="tabpanel" class="tab-pane panel panel-default"
					id="coverC">
					<div class="panel-body">
						<div class="col-lg-6 col-md-6 col-sm-4 col-xs-4">
							<img
								src="${pageContext.request.contextPath }/images/logo/2wm.jpg"
								alt="" class="img-thumbnail" style="width: 100%;" id="CImg">
						</div>
						<div class="col-lg-6 col-md-6 col-sm-8 col-xs-8">
							<br>
							<div class="form-group">
								<label for="exampleInputEmail1">C图片标题</label> <input type="text"
									class="form-control" placeholder="图片标题" id="coverTitleC"
									name="coverTitleC" aria-describedby="basic-addon1">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">C图片简介</label>
								<textarea class="form-control" rows="2" cols=""
									id="coverRemarkC" name="coverRemarkC"></textarea>
							</div>
							<button type="button" class="btn btn-block btn-success"
								onClick="upImageC();">上传图片</button>
						</div>
					</div>
				</div>
				<div role="tabpanel" class="tab-pane panel panel-default"
					id="coverD">
					<div class="panel-body">
						<div class="col-lg-6 col-md-6 col-sm-4 col-xs-4">
							<img
								src="${pageContext.request.contextPath }/images/logo/2wm.jpg"
								alt="" class="img-thumbnail" style="width: 100%;" id="DImg">
						</div>
						<div class="col-lg-6 col-md-6 col-sm-8 col-xs-8">
							<br>
							<div class="form-group">
								<label for="exampleInputEmail1">D图片标题</label> <input type="text"
									class="form-control" placeholder="图片标题" id="coverTitleD"
									name="coverTitleD" aria-describedby="basic-addon1">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">D图片简介</label>
								<textarea class="form-control" rows="2" cols=""
									id="coverRemarkD" name="coverRemarkD"></textarea>
							</div>
							<button type="button" class="btn btn-block btn-success"
								onClick="upImageD();">上传图片</button>
						</div>
					</div>
				</div>
				<div role="tabpanel" class="tab-pane panel panel-default"
					id="coverE">
					<div class="panel-body">
						<div class="col-lg-6 col-md-6 col-sm-4 col-xs-4">
							<img
								src="${pageContext.request.contextPath }/images/logo/2wm.jpg"
								alt="" class="img-thumbnail" style="width: 100%;" id="EImg">
						</div>
						<div class="col-lg-6 col-md-6 col-sm-8 col-xs-8">
							<br>
							<div class="form-group">
								<label for="exampleInputEmail1">E图片标题</label> <input type="text"
									class="form-control" placeholder="图片标题" id="coverTitleE"
									name="coverTitleE" aria-describedby="basic-addon1">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">E图片简介</label>
								<textarea class="form-control" rows="2" cols=""
									id="coverRemarkE" name="coverRemarkE"></textarea>
							</div>
							<button type="button" class="btn btn-block btn-success"
								onClick="upImageE();">上传图片</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-4 col-md-3 col-sm-2 col-xs-12"></div>
		<!-- COVER END -->

		

		<!--  -->
	</div>
</body>
</html>