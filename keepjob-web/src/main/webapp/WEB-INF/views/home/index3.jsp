<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap3/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/fullPage/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap3/bootstrap.min.js"></script>
<!-- jquery.easings.min.js 用于 easing 参数，也可以使用完整的 jQuery UI 代替，如果不需要设置 easing 参数，可去掉改文件 -->
<%-- <script src="${pageContext.request.contextPath }/js/fullPage/jquery.easings.min.js"></script> --%>

<!-- 如果 scrollOverflow 设置为 true，则需要引入 jquery.slimscroll.min.js，一般情况下不需要 -->
<%-- <script src="${pageContext.request.contextPath }/js/fullPage/jquery.slimscroll.min.js"></script> --%>

</head>
<body>
<div class="col-lg-12 col-md-12 col-sm-12 " style="" id="menu"><!-- navbar-fixed-top -->
		<div class="col-lg-1 col-md-1 col-sm-1"></div>
		<div class="col-lg-2 col-md-2 col-sm-2 text-center"  data-menuanchor="page1"><a href="#page1"><h2><font color="black" >首页</font></h2></a></div>
		<div class="col-lg-2 col-md-2 col-sm-2 text-center" data-menuanchor="page2"><a href="#page2"><h2><font color="black">关于</font></h2></a></div>
		<div class="col-lg-2 col-md-2 col-sm-2 text-center" data-menuanchor="page3"><a href="#page3"><h2><font color="black" >创意</font></h2></a></div>
		<div class="col-lg-2 col-md-2 col-sm-2 text-center" data-menuanchor="page4"><a href="#page4"><h2><font color="black" >生活</font></h2></a></div>
		<div class="col-lg-2 col-md-2 col-sm-2 text-center" data-menuanchor="page5"><a href="#page5"><h2><font color="black" >环保</font></h2></a></div>
		<div class="col-lg-1 col-md-1 col-sm-1"></div>
</div>
<div class="row">
	<div class="col-lg-12 col-md-12 col-sm-12" style="height:1000px;background-color: red"></div>
</div>
<div class="row" style="border:3px solid black">
	<div class="col-lg-12 col-md-12 col-sm-12" style="height:1000px;background-color: black"></div>
</div>



<label for="wheelDelta">滚动值:</label>(IE/Opera)<input type="text" id="wheelDelta"/>
<label for="detail">滚动值:(Firefox)</label><input type="text" id="detail"/>
<script type="text/javascript"> 
var scrollFunc=function(e){ 
    e=e || window.event; 
    var t1=document.getElementById("wheelDelta"); 
    var t2=document.getElementById("detail"); 
    if(e.wheelDelta){//IE/Opera/Chrome 
        t1.value=e.wheelDelta; 
    		if(e.wheelDelta > 0){
    			showMenu();
    		}else{
    			closeMenu();
    		}
    }else if(e.detail){//Firefox 
        t2.value=e.detail; 
        if(e.detail > 0){
        		showMenu();
		}else{
			closeMenu();
		}
    } 
} 



function showMenu(){
	$('#menu').removeClass() ;
	$("#menu").addClass("col-lg-12 col-md-12 col-sm-12 navbar-fixed-top");	
}
function closeMenu(){
	$('#menu').removeClass() ;
	$("#menu").addClass("col-lg-12 col-md-12 col-sm-12 ");
}
</script>
</body>
</html>