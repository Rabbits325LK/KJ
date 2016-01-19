<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
	*{font-size: 12px;}
</style>
<script>
	var rootPath = '${pageContext.request.contextPath}';
</script>
<!-- jQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js" charset="utf-8"></script>
<script type="text/javascript"src="${pageContext.request.contextPath }/js/easyui/jquery.cookie.js" charset="utf-8"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/easyui/bootstrap/easyui.css" type="text/css" id="easyuiTheme" >
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.json-2.3.min.js"></script>
<script type="text/javascript"src="${pageContext.request.contextPath }/js/easyui/changeEasyuiTheme.js" charset="utf-8"></script>
<!-- 引入bootstrap样式 -->
<link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen">
<!-- 引入EasyUI 图标样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/easyui/icon.css" type="text/css"></link> 

<!-- 解决EasyUI与bootstrap冲突时tip不显示的问题 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/yldIcon.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/easyui/extEasyUIIcon.css" type="text/css"></link>

<!-- 引进EasyUI JS文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.lang.zh_CN.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/extEasyUI.js" charset="utf-8"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js" charset="utf-8"></script>

<!-- 引入my97日期时间控件 -->
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/tools/My97DatePicker/WdatePicker.js" charset="utf-8"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/datagrid-detailview.js" charset="utf-8"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/formUtil.js" charset="utf-8"></script>
<!-- EasyUI扩展 -->
<link href="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/js/jquery/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/easyui/jquery.jdirk.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/jquery/My97DatePicker/WdatePicker.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.menu.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.datagrid.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jquery.my97.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.form.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.tree.js" type="text/javascript"></script>


<%-- <script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.progressbar.js"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.slider.js"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.linkbutton.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.form.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.validatebox.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.combo.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.combobox.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.menu.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.searchbox.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.panel.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.window.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.dialog.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.layout.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.tree.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.datagrid.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.treegrid.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.combogrid.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.combotree.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.tabs.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jeasyui.extensions.theme.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jquery.toolbar.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jquery.comboicons.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jquery.comboselector.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jquery.my97.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/easyui/jeasyui-extensions/jquery.portal.js" type="text/javascript"></script> --%>
