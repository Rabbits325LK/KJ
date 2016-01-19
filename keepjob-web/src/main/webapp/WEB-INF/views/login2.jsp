<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	request.setAttribute("currDate", com.keepjob.common.util.DateUtils.getNowDate());
	request.setAttribute("proxDate", com.keepjob.common.util.DateUtils.getDaydiffDate(com.keepjob.common.util.DateUtils.getNowDate(), -1));
	request.setAttribute("currYear", com.keepjob.common.util.DateUtils.getNowYear());
	request.setAttribute("ctx", request.getContextPath());
	request.setAttribute("webUrl", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<title>可及工作室管理服务平台</title>
	<script src="${webUrl }js/jquery/jquery.min.js" type="text/javascript"></script>
	<link href="favicon.ico" rel="icon" type="image/x-icon" />
	<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
	<link href="${webUrl }css/login.css" rel="stylesheet" type="text/css" />
	<script type="text/JavaScript">
	<!--
	function MM_swapImgRestore() { //v3.0
	  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
	}
	
	function MM_preloadImages() { //v3.0
	  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
	    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
	    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
	}
	
	function MM_findObj(n, d) { //v4.01
	  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
	    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
	  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
	  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
	  if(!x && d.getElementById) x=d.getElementById(n); return x;
	}
	
	function MM_swapImage() { //v3.0
	  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
	   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
	}
	//-->
	</script>
</head>
	
<body onload="MM_preloadImages('images/login18_20.jpg','images/login28_22.jpg')">
<form  id="loginForm" action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
<div class="ym">
<div class="ym1">
<div class="ym2"></div><!-- <img src="images/login8_04.png" width="850" height="48" /> -->
<div class="ym3">


<div class="ym55">
<div class="ym5">
<div class="ym6"><img src="images/login8_10.png" width="162" height="23" /></div>
<div class="ym7">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="15%" height="52" align="right">帐号：</td>
    <td width="85%"><input name="j_username" id="username" type="text" value="手机号/用户名/邮箱" onfocus="if(this.value=='手机号/用户名/邮箱'){this.value='';}" onblur="if(this.value==''){this.value='手机号/用户名/邮箱';}" onkeydown="javascript:if(event.keyCode==13){$('#password').focus();}" style="color:#000000"/></td>
  </tr>
    <tr>
    <td colspan="2" height="1"></td>
  </tr>
  <tr>
    <td height="52" align="right">密码：</td>
    <td><input name="j_password" id="password" type="password" onkeydown="javascript:if(event.keyCode==13){onLoginClick();}" style="color:#000000"/></td>
  </tr>
</table>
</div>
<div class="ym8"><center><span id="msg" style="color:red;"></span></center></div>
<div class="ym11"><p class="ym12"><a href="#" onclick="onLoginClick()" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image5','','images/login18_20.jpg',1)"><img src="images/login8_16.jpg" name="Image5" width="172" height="50" border="0" id="Image5" /></a></a></p>
<p class="ym13"><a href="#" onmouseout="MM_swapImgRestore()" onclick="onClearClick()" onmouseover="MM_swapImage('Image6','','images/login28_22.jpg',1)"><img src="images/login8_18.jpg" name="Image6" width="172" height="50" border="0" id="Image6" /></a></p>
</div>

</div>
<div class="ym14"></div><!-- <img src="images/login8_26.jpg" width="225" height="50" /> -->
<div class="clear"></div>
</div>

</div>
</div>
</div>
</form>
</body>
</html>
<script type="text/javascript">
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