<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/fullPage/jquery.fullPage.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap3/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/fullPage/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap3/bootstrap.min.js"></script>
<!-- jquery.easings.min.js 用于 easing 参数，也可以使用完整的 jQuery UI 代替，如果不需要设置 easing 参数，可去掉改文件 -->
<%-- <script src="${pageContext.request.contextPath }/js/fullPage/jquery.easings.min.js"></script> --%>

<!-- 如果 scrollOverflow 设置为 true，则需要引入 jquery.slimscroll.min.js，一般情况下不需要 -->
<%-- <script src="${pageContext.request.contextPath }/js/fullPage/jquery.slimscroll.min.js"></script> --%>

<script
	src="${pageContext.request.contextPath }/js/fullPage/jquery.fullPage.min.js"></script>
<style type="text/css">
/* body{
	font-family : " Microsoft YaHei";
} */
</style>
</head>

<body>
	<div class="col-lg-12 col-md-12 col-sm-12" style="" id="menu">
		<div class="col-lg-1 col-md-1 col-sm-1"></div>
		<div class="col-lg-2 col-md-2 col-sm-2 text-center"  data-menuanchor="page1"><a href="#page1"><h2><font color="black" >首页</font></h2></a></div>
		<div class="col-lg-2 col-md-2 col-sm-2 text-center" data-menuanchor="page2"><a href="#page2"><h2><font color="black">关于</font></h2></a></div>
		<div class="col-lg-2 col-md-2 col-sm-2 text-center" data-menuanchor="page3"><a href="#page3"><h2><font color="black" >创意</font></h2></a></div>
		<div class="col-lg-2 col-md-2 col-sm-2 text-center" data-menuanchor="page4"><a href="#page4"><h2><font color="black" >生活</font></h2></a></div>
		<div class="col-lg-2 col-md-2 col-sm-2 text-center" data-menuanchor="page5"><a href="#page5"><h2><font color="black" >环保</font></h2></a></div>
		<div class="col-lg-1 col-md-1 col-sm-1"></div>
	</div>
	<div id="fullpage">
    <div class="section"><!-- 第一屏 -->
    			<img alt="" src="${pageContext.request.contextPath }/images/logo2.jpg" style="margin-top: 15%;margin-left: 34%;" id="imgDemo">
				<img alt="" src="${pageContext.request.contextPath }/images/logo.jpg" style="margin-top: 15%;" id="imgDemo2">
				<br>
				<p class="text-center" style="margin-top: 15%;"id="downFont"></p>
    </div>
    <div class="section">
    			<div class="row">
    				<div class="col-lg-6 col-md-6 col-sm-6 ">
    					<img alt="" src="${pageContext.request.contextPath }/images/ym/2.pic_hd.jpg" style="margin-top: 3%;margin-left: 6%;" id="YMLogo">
    				</div>
    				<div class="col-lg-6 col-md-6 col-sm-6 " style="margin-top: 2%;">
    					<blockquote><h1>WE简介 <small>品牌简介:</small></h1></blockquote>
    					<p><b>遇见的时刻</b></p>
    					<p>THE MOMENT WE MET</p><br>
    					<p><font size="5"><b>品牌价值：</b></font></p>
    					<ul>
    						<li>遇见的时刻或许是人生中的最重要的时刻</li>
    						<li>或许寓意着人生的改变，或许期待着未来的美好</li>
    						<li>又或许是对过去的一种怀念，对过去情感的一种感慨</li>
    						<li>我们本着人们的这份情感创立了该品牌，也为人们的这份情感提供一系列服务</li>
    					</ul>
					<br/> 
					<p><font size="5"><b>品牌由来：</b></font></p>
					<ul>
						<li>品牌创于2015年，由湖南的一个小工作室（遇见的时刻工作室）创立</li>
					</ul>
					<br/>
					<p><font size="5"><b>经营种类：</b></font></p>
					<ul>
						<li>旗下主要分为三大板块</li>
						<li>纯手工皮具，银饰，及文玩用品所组成</li>
					</ul>
    				</div>
    			</div>
    			
    </div>
    <div class="section">
        <div class="slide" >
        		<img alt="" src="${pageContext.request.contextPath }/images/cb/微信江湖1.jpg" style="width:100%;height:100%;">
        </div>
        <div class="slide">
        		<img alt="" src="${pageContext.request.contextPath }/images/cb/1.pic_hd.jpg" style="width:100%;height:100%;">
        </div>
        <div class="slide">
        		<img alt="" src="${pageContext.request.contextPath }/images/cb/013.jpg" style="width:100%;height:100%;">
        </div>
        <div class="slide">
        		<img alt="" src="${pageContext.request.contextPath }/images/cb/012.jpg" style="width:100%;height:100%;">
        </div>
    </div>
    <div class="section">第四屏</div>
</div>
	
</body>
<script type="text/javascript">
$(function(){
    $('#fullpage').fullpage({
        'verticalCentered': false,
        'css3': true,
       // 'sectionsColor': ['', '#00FF00', '#254587', '#695684'],
        anchors: ['page1', 'page2', 'page3', 'page4'],
        //menu : '#menu',
        'navigation': true,
        'navigationPosition': 'right',
        'navigationTooltips': ['fullPage.js', 'Powerful', 'Amazing', 'Simple']
    })
    
    window.setInterval("imgDemo()",1000);
	window.setInterval("downFontDemo()",500);
	window.setInterval("YMLogoDemo()",1000);
})

var YMLogoFlag = true;
function YMLogoDemo(){
	if(YMLogoFlag == true){
		$('#YMLogo').attr("src","${pageContext.request.contextPath }/images/ym/3.pic_hd.jpg");
		YMLogoFlag = false;
	}else{
		$('#YMLogo').attr("src","${pageContext.request.contextPath }/images/ym/2.pic_hd.jpg");
		YMLogoFlag = true;
	}
}

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
function imgDemo(){
	var num = GetRandomNum(0,9);
	var num2 = GetRandomNum(0,9);
	$('#imgDemo').attr("src",logoArrayA[num]);
	$('#imgDemo2').attr("src",logoArrayB[num2]);
}

/*获得随机数
--------------------------------------------------------------- */
function GetRandomNum(Min,Max)
{   
var Range = Max - Min;   
var Rand = Math.random();   
return(Min + Math.round(Rand * Range));   
} 

/*导航栏
--------------------------------------------------------------- */
function showMenu(){
	$('#menu').removeClass() ;
	$("#menu").addClass("col-lg-12 col-md-12 col-sm-12 navbar-fixed-top");	
}
function closeMenu(){
	$('#menu').removeClass() ;
	$("#menu").addClass("col-lg-12 col-md-12 col-sm-12 ");
}

var scrollFunc=function(e){ 
    e=e || window.event; 
    if(e.wheelDelta){//IE/Opera/Chrome 
    		/* if(e.wheelDelta > 0){
    			showMenu();
    		}else{
    			closeMenu();
    		} */
    		//e.wheelDelta > 0 ? $('#fullpage').fullpage({menu : ""}) : $('#fullpage').fullpage({menu : "#menu"});
    			e.wheelDelta > 0 ? showMenu() : closeMenu();
    }else if(e.detail){//Firefox 
        /* if(e.detail > 0){
        		showMenu();
		}else{
			closeMenu();
		} */
		e.detail > 0 ?showMenu() : closeMenu();
    } 
} 

/*鼠标滚轮监听
--------------------------------------------------------------- */
/*注册事件*/ 
if(document.addEventListener){ 
    document.addEventListener('DOMMouseScroll',scrollFunc,false); 
}//W3C 
window.onmousewheel=document.onmousewheel=scrollFunc;//IE/Opera/Chrome 
</script>
</html>

