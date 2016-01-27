<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap3/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap3/bootstrap.min.js"></script>
</head>
<body>
	<div class="col-lg-12 col-md-12 col-sm-12 navbar-fixed-top" style="border-bottom: 1px solid black">
		<div class="col-lg-1 col-md-1 col-sm-1"></div>
		<div class="col-lg-2 col-md-2 col-sm-2 text-center"><h2><a><font color="black" href="javascript:void(0);" onclick="test();"><strong>首页</strong></font></a></h2></div>
		<div class="col-lg-2 col-md-2 col-sm-2 text-center"><h2>关于</h2></div>
		<div class="col-lg-2 col-md-2 col-sm-2 text-center"><h2>生活</h2></div>
		<div class="col-lg-2 col-md-2 col-sm-2 text-center"><h2>创意</h2></div>
		<div class="col-lg-2 col-md-2 col-sm-2 text-center"><h2>环保</h2></div>
		<div class="col-lg-1 col-md-1 col-sm-1"></div>
	</div>
	
	<div class="col-lg-12 col-md-12 col-sm-12" style=" height:1000px;">
				<img alt="" src="${pageContext.request.contextPath }/images/logo2.jpg" style="margin-top: 15%;margin-left: 34%;" id="imgDemo">
				<img alt="" src="${pageContext.request.contextPath }/images/logo.jpg" style="margin-top: 15%;" id="imgDemo2">
				<br>
				<p class="text-center" style="margin-top: 15%;"id="downFont"></p>
	</div>
	<div class="col-lg-12 col-md-12 col-sm-12" style="height:900px; background:#333333">
				<div class="col-lg-6 col-md-6 col-sm -6"></div>
				<div class="col-lg-6 col-md-6 col-sm -6 text-center" >
					<h1><font color="#FFFFFF">KeepJob.com可及</font></h1>
					<h3></h3>
				</div>
	</div>
	<div class="col-lg-12 col-md-12 col-sm-12" style="height:900px; background:#990066">
	</div>
</body>
<script type="text/javascript">
var downFontFlag = true;
function downFontDemo(){
	if(downFontFlag == true){
		$('#downFont').html('<font size="40px;" ><span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span></font>');
		downFontFlag = false;
	}else{
		$('#downFont').html('');
		downFontFlag = true;
	}
}



$(function(){
	window.setInterval("imgDemo3()",1000);
	window.setInterval("downFontDemo()",500);
});




var logoArrayA = new Array();
logoArrayA[0] = "${pageContext.request.contextPath}/images/logo/a1.jpg";
logoArrayA[1] = "${pageContext.request.contextPath}/images/logo/a2.jpg";
logoArrayA[2] = "${pageContext.request.contextPath}/images/logo/a3.jpg";
logoArrayA[3] = "${pageContext.request.contextPath}/images/logo/a4.jpg";
logoArrayA[4] = "${pageContext.request.contextPath}/images/logo/a5.jpg";
logoArrayA[5] = "${pageContext.request.contextPath}/images/logo/a6.jpg";
logoArrayA[6] = "${pageContext.request.contextPath}/images/logo/a7.jpg";
logoArrayA[7] = "${pageContext.request.contextPath}/images/logo/a8.jpg";
logoArrayA[8] = "${pageContext.request.contextPath}/images/logo/a9.jpg";
logoArrayA[9] = "${pageContext.request.contextPath}/images/logo/a10.jpg";
var logoArrayB = new Array();
logoArrayB[0] = "${pageContext.request.contextPath}/images/logo/b1.jpg";
logoArrayB[1] = "${pageContext.request.contextPath}/images/logo/b2.jpg";
logoArrayB[2] = "${pageContext.request.contextPath}/images/logo/b3.jpg";
logoArrayB[3] = "${pageContext.request.contextPath}/images/logo/b4.jpg";
logoArrayB[4] = "${pageContext.request.contextPath}/images/logo/b5.jpg";
logoArrayB[5] = "${pageContext.request.contextPath}/images/logo/b6.jpg";
logoArrayB[6] = "${pageContext.request.contextPath}/images/logo/b7.jpg";
logoArrayB[7] = "${pageContext.request.contextPath}/images/logo/b8.jpg";
logoArrayB[8] = "${pageContext.request.contextPath}/images/logo/b9.jpg";
logoArrayB[9] = "${pageContext.request.contextPath}/images/logo/b10.jpg";
function imgDemo3(){
	var num = GetRandomNum(0,9);
	var num2 = GetRandomNum(0,9);
	$('#imgDemo').attr("src",logoArrayA[num]);
	$('#imgDemo2').attr("src",logoArrayB[num2]);
}


function GetRandomNum(Min,Max)
{   
var Range = Max - Min;   
var Rand = Math.random();   
return(Min + Math.round(Rand * Range));   
} 


</script>
</html>
