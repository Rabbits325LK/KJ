<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	com.keepjob.sys.user.User user=(com.keepjob.sys.user.User)session.getAttribute(com.keepjob.common.Constant.USER_KEY);
	pageContext.setAttribute("userName",user.getRealName());
	pageContext.setAttribute("unitName",user.getUnitName());
	pageContext.setAttribute("currentIP",user.getLastLoginIp());
%>
<div id="sessionInfoDiv" style="position: absolute;right: 5px;top:10px;">
	[<strong>${unitName }</strong>][<strong>${userName }</strong>]，欢迎你！您使用[<strong>${currentIP }</strong>]IP登录！
</div>
<div style="position: absolute; right: 0px; bottom: 0px; ">
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_pfMenu',iconCls:'wand'">更换皮肤</a>
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'help'">控制面板</a>
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_zxMenu',iconCls:'house_go'">注销</a>
</div>
<div id="layout_north_pfMenu" style="width: 120px; display: none;">
	<div onclick="changeThemeFun('default');">default</div>
	<div onclick="changeThemeFun('gray');">gray</div>
	<div onclick="changeThemeFun('metro');">metro</div>
	<div onclick="changeThemeFun('bootstrap');">bootstrap</div>
	<div onclick="changeThemeFun('black');">black</div>
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div onclick="showUserInfo();">个人信息</div>
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<div onclick="loginDialog.dialog('open');">锁幕</div>
	<div class="menu-sep"></div>
	<div onclick="logout();">退出</div>
</div>
<script type="text/javascript">
	function logout(){
		window.location.href = "${pageContext.request.contextPath}/j_spring_security_logout";
	}
</script>