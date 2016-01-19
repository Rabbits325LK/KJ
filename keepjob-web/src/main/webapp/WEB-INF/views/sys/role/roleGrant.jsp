<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	var resourceTree;
	$(function() {
		resourceTree = $('#resourceTree').tree({
			url : '${pageContext.request.contextPath}/sys/resource/combotree.json',
			parentField : 'pid',
			//lines : true,
			checkbox : true,
			onClick : function(node) {},
			onSelect : function(node){
				if(node.checked){
					resourceTree.tree('uncheck', node.target);
				}else{
					resourceTree.tree("check", node.target);
				}
			},
			onLoadSuccess : function(node, data) {
				var ids = ${ids};
				if (ids.length > 0) {
					for ( var i = 0; i < ids.length; i++) {
						if (resourceTree.tree('find', ids[i])) {
							resourceTree.tree('check', resourceTree.tree('find', ids[i]).target);
						}
					}
				}
				parent.$.messager.progress('close');
			},
			cascadeCheck : false
		});
		parent.$.messager.progress('close');
		$('#form').form({url : '${pageContext.request.contextPath}/sys/role/grant.json',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				var checknodes = resourceTree.tree('getChecked');
				var ids = [];
				if (checknodes && checknodes.length > 0) {
					for ( var i = 0; i < checknodes.length; i++) {
						ids.push(checknodes[i].id);
					}
				}
				$('#resourceCodes').val(ids);
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为role.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
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
	
		$('#btnInverse').click(function(){
			var nodes = resourceTree.tree('getChecked');
			if (nodes && nodes.length > 0) {
				for (var i = 0; i < nodes.length; i++) {
					resourceTree.tree('uncheck', nodes[i].target);
				}
				return false;
			}
		});
	});
</script>
<div class="easyui-panel" data-options="fit:true,border:false">
	<div><button id="btnSuccess" class="btn btn-success">全选</button>
		<button id="btnWarning" class="btn btn-warning">反选</button>
		<button id="btnInverse" class="btn btn-inverse">取消</button>
	</div>
	<div style="text-align:center;">角色名称:<font color="red">${result.name }</font></div>
	<div class="easyui-panel" style="width: 470px; height: 262px; broder: 1px solid green">
		<div title="系统资源" style="width: 470px; padding: 1px;">
			<div class="well well-small">
				<form id="form" method="post">
					<input name="id" type="hidden" value="${result.id}">
					<input id="resourceCodes" name="resourceCode" type="hidden" />
					<ul id="resourceTree"></ul>
				</form>
			</div>
		</div>
	</div>
</div>