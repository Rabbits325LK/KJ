<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<form id="form" method="post" class="form-inline">
			<div id="tabs" class="easyui-tabs" style="width: 660px;">
				<div id="basicDiv" title="基本信息" data-options="selected:true"
					class="row">
					<table class="table table-hover table-condensed" style="width:95%;margin-left: 5%;">
						<tr>
							<td style="text-align:right;">帐号</td>
							<td>
								<input type="text" name="username" id="username" value="${result.username }" class="easyui-validatebox span3"  data-options="required:true, validType:'length[4,16]'" onblur="javascript:checkUserName();" disabled="disabled">
								<input type="hidden" name="id" id="id" value="${result.id }">
							</td>
							<td style="text-align:right;">性别</td>
							<td>
								<select name="sex" id="sex" class="easyui-combobox span3" style="height:28px;">
									<option value="" selected="selected">请选择</option>
									<option value="1"<c:if test="${result.sex == 1 }">selected="selected"</c:if>>男</option>
									<option value="2"<c:if test="${result.sex == 2 }">selected="selected"</c:if>>女</option>
									<option value="9"<c:if test="${result.sex == 9 }">selected="selected"</c:if>>其他</option>
								</select>
							</td>
						</tr>
						
						<tr>
							<td style="text-align:right;">电话号码:</td>
							<td>
								<input type="text" name="phone" id="phone" class="easyui-validatebox span3" data-options="validType:'phoneRex'" value="${result.phone }">
							</td>
							<td style="text-align:right;">邮箱地址:</td>
							<td>
								<input type="text" name="email" id="email" class="easyui-validatebox span3" data-options="validType:'email'" value="${result.email }">
							</td>
						</tr>
						
						<tr>
							<td style="text-align:right;">QQ:</td>
							<td>
								<input type="text" name="qq" id="qq"  class="easyui-validatebox span3" data-options="" value="${result.qq }">
							</td>
							<td style="text-align:right;">微信:</td>
							<td>
								<input type="text" name="wechtCode" id="wechtCode" class="easyui-validatebox span3" data-options="" value="${result.wechtCode }">
							</td>
						</tr>
						<tr>
							<td colspan="4"><font color="red"><p id="errorMsg"></p></font></td>
						</tr>
					</table>
				</div>

			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
	
	$(function() {
		parent.$.messager.progress('close');

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
		
		$('#form')
				.form(
						{
							url : '${pageContext.request.contextPath}/app/associator/saveAssociator.json',
							onSubmit : function() {
								var isValid = $(this).form('validate');
								if (isValid) {
									
									parent.$.messager.progress({
										title : '提示',
										text : '数据处理中,请稍后....'
									});
								}
								return isValid;
							},
							success : function(result) {
								parent.$.messager.progress('close');
								result = $.parseJSON(result);
								if (result.success) {
									$.messager.alert('提示', '添加菜谱信息成功,请确认.',
											'info');
									parent.$.modalDialog.openner_dataGrid
											.datagrid('reload');
									parent.$.modalDialog.handler
											.dialog('close');
								} else {
									$('#errorMsg').html(result.message);
								}
							}
						});
	});
</script>