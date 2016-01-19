<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		$('#iconCls').combobox({
			data : $.iconData,
			formatter : function(v) {
				return $.formatString('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}', v.value, v.value);
			},
			value : '${resource.iconCls}'
		});

		$('#parentCode').combotree({
			url : '${pageContext.request.contextPath}/sys/resource/combotree.json',
			valueField:'id', 
			textField: 'text', 
			parentField : 'pid',
			lines : true,
			panelHeight : 'auto',
			value:'${resource.parentCode }',
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			}
		});
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/sys/resource/saveresource.json',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					$.messager.alert('提示', '资源信息修改成功,请确认.', 'info');
					parent.$.modalDialog.openner_treeGrid.treegrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为resource.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				}else{
					parent.$.messager.alert('错误', result.message, 'error');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
			<table class="table table-hover table-condensed">
				<tr>
					<td style="width:26%; text-align:right;">资源名称<font color="#FF0000">*</font>:</td>
					<td><input id="code" name="code" type="hidden" value="${resource.code }"/><input name="name" type="text" placeholder="请输入资源名称" class="easyui-validatebox span4" data-options="required:true" value="${resource.name }" maxlength="64"></td>
				</tr>
				<tr>
					<td style="text-align:right;">资源路径:</td>
					<td><input name="url" type="text" placeholder="请输入资源路径" class="easyui-validatebox span4" value="${resource.url }" maxlength="256"></td>
				</tr>
				<tr>
					<td style="text-align:right;">资源类型<font color="#FF0000">*</font>:</td>
					<td><select name="category" class="easyui-combobox span4" data-options="required:true">
						<option value="1" <c:if test="${resource.category=='1' }">selected</c:if>>菜单</option>
						<option value="2" <c:if test="${resource.category=='2' }">selected</c:if>>功能</option>
					</select></td>
				</tr>
				<tr>
					<td style="text-align:right;">菜单图标:</td>
					<td>
						<select name="operateCode" id="operateCode" class="easyui-combobox span4">
							<c:forEach items="${operates }" var="o">
								<option value="${o.code }"<c:if test="${o.code==resource.operateCode}"> selected</c:if>>${o.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align:right;">上级资源<font color="#FF0000">*</font>:</td>
					<td><select id="parentCode" name="parentCode" class="span4" data-options="required:true"></select><img src="${pageContext.request.contextPath}/images/extjs_icons/cut_red.png" onclick="$('#parentCode').combotree('clear');" title="清空"/></td>
				</tr>
				<tr>
					<td style="text-align:right;">备       注:</td>
					<td><textarea name="remark" rows="" cols="" class="span4" maxlength="128">${resource.remark }</textarea></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;"><font id="errorMsg" style="color: red;"></font></td>
				</tr>
			</table>
		</form>
	</div>
</div>
