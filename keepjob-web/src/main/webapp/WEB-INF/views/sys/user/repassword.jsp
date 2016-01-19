<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/common.tld" prefix="common"%>
<div class="easyui-panel" data-options="fit:true,border:false">
	<form id="form" method="post">
		<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
			<table class="table table-hover table-condensed">
				<tr>
					<td style="width:30%; text-align:right;">登录名<font color="#FF0000">*</font>:</td>
					<td><input id="id" name="id" type="hidden" value="${result.id }"><input id="loginCode" name="loginCode" type="text" disabled class="easyui-validatebox span3" value="${result.loginCode }" data-options="required:true">
					</td>
				</tr>
				<tr>
					<td style="width:30%; text-align:right;">真实姓名<font color="#FF0000">*</font>:</td>
					<td><input id="loginCode" name="loginCode" type="text" disabled class="easyui-validatebox span3" value="${result.realName }" data-options="required:true">
					</td>
				</tr>
				<tr>
					<td style="text-align:right;">新登录密码<font color="#FF0000">*</font>:</td>
					<td><input id="password" name="password" type="password" placeholder="请输入登录密码" class="easyui-validatebox span3" value="" data-options="required:true, validType:'length[6,16]'">
					</td>
				</tr>
				<tr>
					<td style="text-align:right;">确认密码<font color="#FF0000">*</font>:</td>
					<td><input id="rpassword" name="rpassword" type="password" placeholder="请输入确认密码" class="easyui-validatebox span3" value="" data-options="required:true, validType:'length[6,16]'">
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;"><font id="errorMsg" style="color: red;">&nbsp;</font></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	parent.$.messager.progress('close');
	$(function() {
		$('#form').form({
			url : '${pageContext.request.contextPath}/sys/user/repassword.json',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (isValid) {
					if($('#password').val() != $('#rpassword').val()){
						parent.$.messager.progress('close');
						$.messager.alert('提示','确认密码与登录密码不一致.','info');
						return false;
					}
				}else{
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					$.messager.alert('提示', '重置密码操作成功,请确认.', 'info');
					parent.$.modalDialog.handler.dialog('close');
				}else{
					$('#errorMsg').html(result.message);
				}
			}
		});
	});
</script>