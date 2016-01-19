<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/common.tld" prefix="common"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/views/common/meta.jsp"%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<jsp:include page="/WEB-INF/views/common/head.jsp" />
<script type="text/javascript">
	var treeGrid;
	$(function() {
		treeGrid = $('#treeGrid').treegrid({
			url : '${pageContext.request.contextPath}/sys/resource/loadresource.json?${_csrf.parameterName}=${_csrf.token}',
			idField : 'code',
			treeField : 'name',
			width : '100%',
			fit : true,
			fitColumns : true,
			border : false,
			onlyNodeExpand: true,
			toggleOnClick: false,
            enableContextMenu: false,
			frozenColumns : [ [ {title : '编号', field : 'code', width : 80, hidden : true
			} ] ],
			columns : [ [ {field : 'name', title : '资源名称', width : 200}, 
			              {field : 'code', title : '编号', width : 80},
			              {field : 'parentCode', title : '父节点编号', width : 80},
			              {field : 'url', title : '资源路径', width : 150},
			              {field : 'isTop', title : '是否是顶级资源', width : 80, align : 'center', formatter : function(value){
								if(value=='1'){
									return '是';
								}else{
									return '否';
								}
							}
			              },
			              {field : 'category', title : '资源类型', width : 80, align : 'center', formatter : function(value){
								if(value=='1'){
									return '菜单';
								}else{
									return '功能';
								}
							}
						  },
						  {field : 'operateCode', title : '样式', width : 150, hidden : true}, 
						  {field : 'index', title : '排序', width : 80}, 
						  {field : 'remark', title : '备注', width : 80}, 
						  {field : 'status', title : '是否可用', width : 80, align : 'center', formatter : function(value){
								if(value=='1'){
									return '启用';
								}else{
									return '停用';
								}
							}
						  },
						  {field : 'remark', title : '备注', width : 150}, 
						  {field : 'action', title : '操作', width : 80, align : 'center', formatter : function(value, row, index) {
							  return '<common:link id="editRow" url="/sys/resource/editresource.html" click="editRowPage(\\\''+row.code+'\\\');" title="编辑" icon="${pageContext.request.contextPath}/images/extjs_icons/pencil.png"/>' +'&nbsp;'+
							  '<common:link id="deleteRow" url="/sys/resource/deleteresource.json" click="deleteResourceRow(\\\''+row.code+'\\\');" title="删除" icon="${pageContext.request.contextPath}/images/extjs_icons/cancel.png"/>';
							}
						  } ] ],
			toolbar : '#toolbar',
			success : function(data) {
				$(this).treegrid('tooltip');
				if(data.length > 0)return;
				$(".datagrid-btable").css("width","100%").html("<tr><td><div style='text-align:center;color:red'>没有相关记录！</div></td></tr>");
			}
		});
		
		//添加按钮操作
		$('#addResource').click(function(){
			parent.$.modalDialog({
				title : '添加资源',
				width : 600,
				height : 400,
				href : '${pageContext.request.contextPath}/sys/resource/addresource.html',
				buttons : [ {
					text : '添加',
					handler : function() {
						parent.$.modalDialog.openner_treeGrid = treeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
						var f = parent.$.modalDialog.handler.find('#form');
						f.submit();
					}
				} ,{
					text : '关闭',
					handler : function() {
						parent.$.modalDialog.handler.dialog('close');
					}
				}]
			});
		});
		
		//展开按钮操作
		$('#redo').click(function(){
			var node = treeGrid.treegrid('getSelected');
			if (node) {
				treeGrid.treegrid('expandAll', node.code);
			} else {
				treeGrid.treegrid('expandAll');
			}
		});
		
		//折叠按钮操作
		$('#undo').click(function(){
			var node = treeGrid.treegrid('getSelected');
			if (node) {
				treeGrid.treegrid('collapseAll', node.code);
			} else {
				treeGrid.treegrid('collapseAll');
			}
		});
		
		//刷新按钮操作
		$('#reload').click(function(){
			treeGrid.treegrid('reload');
		});
	});
	
	function deleteResourceRow(code) {
		if (code != undefined) {
			treeGrid.treegrid('select', code);
		}
		var codes = new Array();
		codes.push(code);
		parent.$.messager.confirm('询问', '您是否确定删除当前资源？', function(b) {
			if (b) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${pageContext.request.contextPath}/sys/resource/deleteresource.json', {
					code : codes.join(',')
				}, function(result) {
					parent.$.messager.progress('close');
					if (result.success) {
						$.messager.alert('提示', '所选资源信息删除成功,请确认.', 'info');
						treeGrid.treegrid('reload');
				    }else{
				    	$.messager.alert('错误', result.message, 'error'); 
				    }
				}, 'JSON');
			}
		});
	}
	
	function editRowPage(code) {
		if (code != undefined) {
			treeGrid.treegrid('select', code);
		}
		parent.$.modalDialog({
			title : '编辑资源',
			width : 600,
			height : 400,
			href : '${pageContext.request.contextPath}/sys/resource/editresource.html?code=' + code,
			buttons : [ {
				text : '保存',
				handler : function() {
					parent.$.modalDialog.openner_treeGrid = treeGrid;
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ,{
				text : '关闭',
				handler : function() {
					parent.$.modalDialog.handler.dialog('close');
				}
			}]
		});
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
			<table id="treeGrid"></table>
		</div>
	</div>
	<common:toolbar id="toolbar">
		<common:button name="添加" id="addResource" url="/sys/resource/addresource.html" options="plain:true,iconCls:'pencil_add'"/>
		<common:button name="展开" id="redo" options="plain:true,iconCls:'resultset_next'" security="false"/>
		<common:button name="折叠" id="undo" options="plain:true,iconCls:'resultset_previous'" security="false"/>
		<common:button name="刷新" id="reload" options="plain:true,iconCls:'transmit'" security="false"/>
	</common:toolbar>
</body>
</html>
