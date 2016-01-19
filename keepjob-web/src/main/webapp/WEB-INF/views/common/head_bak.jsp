<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
	*{font-size: 12px;}
</style>
<script>
	var rootPath = '${pageContext.request.contextPath}';
	var ftpHost="${ftpHost}";
	var ftpPort="${ftpPort}";
	var ftpUser="${ftpUser}";
	var ftpPassword="${ftpPassword}";
</script>
<!-- jQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/easyui/<c:out value="${cookie.easyuiThemeName.value}" default="bootstrap"/>/easyui.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.json-2.3.min.js"></script>

<!-- 引入bootstrap样式 -->
<link href="${pageContext.request.contextPath}/tools/bootstrap-2.3.1/css/bootstrap.min.css" rel="stylesheet" media="screen">
<!-- 引入EasyUI 图标样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/easyui/icon.css" type="text/css"></link>

<!-- 解决EasyUI与bootstrap冲突时tip不显示的问题 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/yldIcon.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/extEasyUIIcon.css" type="text/css"></link>

<!-- 引进EasyUI JS文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.lang.zh_CN.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/extEasyUI.js" charset="utf-8"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js" charset="utf-8"></script>

<!-- 引入my97日期时间控件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/tools/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js" charset="utf-8"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/formUtil.js" charset="utf-8"></script>
