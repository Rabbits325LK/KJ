<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	var resourceTree;
	$(function() {
		resourceTree = $('#resourceTree').tree({
			url : '${pageContext.request.contextPath}/sys/resource/combotree.json',
			parentField : 'pid',
			cascadeCheck : false,
			lines : true,
			checkbox : true,
			onClick : function(node) {},
			onSelect : function(node) {
				if (node.checked) {
					resourceTree.tree('uncheck', node.target);
				} else {
					resourceTree.tree("check", node.target);
				}
			},
			onLoadSuccess : function(node, data) {
				var ids = ${ids};
				if (ids.length > 0) {
					for (var i = 0; i < ids.length; i++) {
						if (resourceTree.tree('find', ids[i])) {
							resourceTree.tree('check', resourceTree.tree('find', ids[i]).target);
						}
					}
				}
				parent.$.messager.progress('close');
			},
		});

		parent.$.messager.progress('close');
		$('#form').form({url : '${pageContext.request.contextPath}/sys/role/save.json',
			onSubmit : function() {
				var parentchecknodes = resourceTree.tree('getChecked','indeterminate');
				var checknodes = resourceTree.tree('getChecked');
				var ids = [];
				if (parentchecknodes.length > 0){
					for(var i = 0; i< parentchecknodes.length; i++){
						ids.push(parentchecknodes[i].id);
					}
				}
				if (checknodes && checknodes.length > 0) {
					for (var i = 0; i < checknodes.length; i++) {
						ids.push(checknodes[i].id);
					}
				}
				if(ids.length > 0){
					parent.$.messager.progress({title : '提示', text : '数据处理中，请稍后....'});
					var isValid = $(this).form('validate');
					if (!isValid) {
						parent.$.messager.progress('close');
					}
					$('#resourceCodes').val(ids.join(','));
					return isValid;
				}else{
					$.messager.alert('提示','请为角色设置资源权限.','info');
					return false;
				}
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					$.messager.alert('提示', '角色添加成功,请确认。', 'info');
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog('close');
				}else{
					$.messager.alert('提示', result.massage, 'info');
				}
			}
		});
		
		$('#btnSuccess').click(function(){
			var nodes = resourceTree.tree('getChecked', 'unchecked');
			if (nodes && nodes.length > 0) {
				for (var i = 0; i < nodes.length; i++) {
					resourceTree.tree('check', nodes[i].target);
				}
				return false;
			}
		}); 
		
		$('#btnInverse').click(function(){
			var nodes = resourceTree.tree('getChecked');
			if (nodes && nodes.length > 0) {
				for (var i = 0; i < nodes.length; i++) {
					resourceTree.tree('uncheck', nodes[i].target);
				}
				return false;
			}
		});
	
		$('#btnWarning').click(function(){
			var unchecknodes = resourceTree.tree('getChecked', 'unchecked');
			var checknodes = resourceTree.tree('getChecked');
			if (unchecknodes && unchecknodes.length > 0) {
				for (var i = 0; i < unchecknodes.length; i++) {
					resourceTree.tree('check', unchecknodes[i].target);
				}
				return false;
			}
			if (checknodes && checknodes.length > 0) {
				for (var i = 0; i < checknodes.length; i++) {
					resourceTree.tree('uncheck', checknodes[i].target);
				}
				return false;
			}
		});
	});

	
	function checkName(){
		if(null == $('#name').val() || '' == $('#name').val()){
			$("#errorMsg").html("角色名称不能为空。");
			return false;
		}
		$.ajax({type : "POST",
			url : "${pageContext.request.contextPath}/sys/role/checkName.json",
			data : {id:'', 'name' : name},
			success : function(result){
				result = $.parseJSON(result);
				if (result.success) {
					$("#errorMsg").html('角色名称可用,请继续.');
				}else{
					$("#errorMsg").html(result.message);
				}
			},
			error : function(e){
				$("#errorMsg").html(e);
			}
		});
	}
</script>
<div class="easyui-panel" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">

		<div class="easyui-tabs">
			<div title="角色信息" style="padding: 10px">
				<form id="form" method="post">
					<table class="table table-hover table-condensed">
						<tr>
							<td style="text-align:right;">角色名称<font color="#FF0000">*</font>:</td>
							<td><input type="hidden" id="id" name="id" value="${result.id }"><input id="name"name="name" type="text" placeholder="请输入角色名称"
								class="easyui-validatebox span4" data-options="required:true"
								value="${result.name }" onblur="checkName();">
								<input id="resourceCodes" name="resourceCode" type="hidden" /></td>
						</tr>
						<tr>
							<td style="text-align:right;">备      注:</td>
							<td><textarea name="remark" rows="8" cols="" class="span4">${result.remark }</textarea></td>
						</tr>
						<tr>
							<td colspan="2" style="text-align:center;"><font id="errorMsg" style="color: red;"></font></td>
						</tr>
					</table>
				</form>
			</div>
			<div title="设置权限">
				<div><button id="btnSuccess" class="btn btn-success">全选</button>
					<button id="btnWarning" class="btn btn-warning">反选</button>
					<button id="btnInverse" class="btn btn-inverse">取消</button>
				</div>
				<div class="easyui-panel" style="width: 470px; height: 262px; broder: 1px solid green">
					<div title="系统资源" style="width: 470px; padding: 1px;">
						<div class="well well-small">
							<ul id="resourceTree"></ul>
						</div>
					</div>
					
				</div>
			</div>
		</div>

	</div>
</div>