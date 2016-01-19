<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
	response.addHeader("x-frame-options","ALLOW-FROM");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/WEB-INF/views/common/meta.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<jsp:include page="/WEB-INF/views/common/head.jsp" />
		<style type="text/css">
			.logo {
				background: #ccc;
			}
		</style>
		<script type="text/javascript">
			javascript:window.history.forward(1);//防止后退
			var applyView_dataGrid=null;
		</script>
	</head>
	<body class="easyui-layout">
		<div data-options="region:'north'" href="${pageContext.request.contextPath}/main/north.html" style="height: 60px;overflow: hidden;" class="logo"></div>
		<div data-options="region:'west',title:'功能导航',split:true" href="${pageContext.request.contextPath}/main/west.html" style="width: 200px;overflow: hidden;"></div>
		<div data-options="region:'center'" href="${pageContext.request.contextPath}/main/center.html" style="overflow: hidden;"></div>
		<div data-options="region:'south'" href="${pageContext.request.contextPath}/main/south.html" style="height: 20px;overflow: hidden;"></div>
	</body>
</html>