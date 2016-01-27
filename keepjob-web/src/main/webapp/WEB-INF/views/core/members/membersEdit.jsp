<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/common.tld" prefix="common"%>
<div class="easyui-panel" data-options="fit:true,border:false">
	<form id="form" method="post">
		<div class="easyui-tabs" data-options="region:'center',border:false" title="" style="overflow: hidden;">
			<div title="用户信息" style="padding:2px">
				<table class="table table-hover table-condensed">
					<tr>
						<td style="width:20%; text-align:right;">真实姓名<font color="#FF0000">*</font>:</td>
						<td><input id="realName" name="realName" type="text" placeholder="请输入用户真实姓名" class="easyui-validatebox span4" value="${result.realName }" data-options="required:true, validType:'length[2,16]'">
							<input id="id" name="id" value="${result.id }" type="hidden">
						</td>
					</tr>
					<tr>
						<td style="width:20%; text-align:right;">性别<font color="#FF0000">*</font>:</td>
						<td>
						<select id="sexCode" name="sexCode" class="span4">
							<option value="0">保密</option>
							<option value="1" <c:if test="${result.sex == '1' }">selected="selected"</c:if>>男</option>
							<option value="2" <c:if test="${result.sex == '2' }">selected="selected"</c:if>>女</option>
							<option value="3" <c:if test="${result.sex == '9' }">selected="selected"</c:if>>其他</option>
						</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">年龄:</td>
						<td>
							<input type="text" name="age" id="age" placeholder="" class="easyui-validatebox span4" value="${result.age }" data-options="">
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">联系电话:</td>
						<td><input id="phone" name="phone" type="text" placeholder="请输入联系电话" class="easyui-validatebox span4" value="${result.phone }" data-options="validType:'phoneRex'" onblur="javascript:checkPhone();">
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">Email地址:</td>
						<td><input id="email" name="email" type="text" placeholder="请输入Email地址" class="easyui-validatebox span4"  value="${result.email }" data-options="validType:'email'" onblur="javascript:checkEmail();">
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">登录密码<font color="#FF0000">*</font>:</td>
						<td><input id="password" name="password" type="password" placeholder="请输入登录密码" class="easyui-validatebox span4" data-options="required:true, validType:'length[6,16]'">
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">确认密码<font color="#FF0000">*</font>:</td>
						<td><input id="rpassword" name="rpassword" type="password" placeholder="请输入确认密码" class="easyui-validatebox span4" data-options="required:true, validType:'length[6,16]'">
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align:center;"><font id="errorMsg" style="color: red;">&nbsp;</font></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
	parent.$.messager.progress('close');
	
	function checkPhone(){
		if(null != $('#phone').val() && '' != $('#phone').val()){
			$.ajax({
				url: '${pageContext.request.contextPath}/core/members/existsPhone.json',
				dataType: 'json',
				data: {name: $('#phone').val()},
				success: function(result){
					if(result.success){
						$('#errorMsg').html('当前登录名可使用.');
					}else{
						$('#errorMsg').html(result.message);
					}
				},
				error: function(){
					$('#errorMsg').html('请求服务器后台异常,请联系系统管理员!');
				}
			});
		}
	}
	
	function checkEmail(){
		if(null != $('#email').val() && '' != $('#email').val()){
			$.ajax({
				url: '${pageContext.request.contextPath}/core/members/existsEmail.json',
				dataType: 'json',
				data: {name: $('#email').val()},
				success: function(result){
					if(result.success){
						$('#errorMsg').html('当前登录名可使用.');
					}else{
						$('#errorMsg').html(result.message);
					}
				},
				error: function(){
					$('#errorMsg').html('请求服务器后台异常,请联系系统管理员!');
				}
			});
		}
	}
	
	$(function() {
		$.extend($.fn.validatebox.defaults.rules, {
			  phoneRex: {
			    validator: function(value){
				    var rex=/^1[3-8]+\d{9}$/;
				    var rex2=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
				    if(rex.test(value)||rex2.test(value)){
				    	$('#errorMsg').html('');
				      	return true;
				    }else{
				     //alert('false '+value);
				     	$('#errorMsg').html('电话号码格式错误.');
				      	return false;
				    }
			    },
			    message: '请输入正确电话或手机格式'
			  },
			qq:{
				validator: function(value){
					var rex = /^[1-9]\d{4,8}$/;
					if(rex.test(value)){
						$('#errorMsg').html('');
					    return true;	 
					}else{ 
						$('#errorMsg').html('qq号格式错误.');
						return false;
					}
				},
				message: '请输入正确的qq号格式'
			} 
		});
			
		
		$('#form').form({
			url : '${pageContext.request.contextPath}/core/members/saveMembers.json',
			onSubmit : function() {
				if($('input[name="roleCode"]:checked').length > 0){
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					var isValid = $(this).form('validate');
					if (isValid) {
						if($('#password').val() != $('#rpassword').val()){
							$.messager.alert('提示','确认密码与登录密码不一致.','info');
							return false;
						}
						if('02' == $('#userType').combobox("getValue")){
							if(null == $('#mavinCode').combobox("getValue") || '' == $('#mavinCode').combobox("getValue")){
								parent.$.messager.progress('close');
								$.messager.alert('提示','请选择用户对应的专家信息.','info');
								return false;
							}
						}
					}else{
						parent.$.messager.progress('close');
					}
					return isValid;
				}else{
					$('#errorMsg').html('请选择用户对应角色信息.');
					return false;
				}
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					$.messager.alert('提示', '添加系统用户信息成功,请确认.', 'info');
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog('close');
				}else{
					$('#errorMsg').html(result.message);
				}
			}
		});
	});
</script>