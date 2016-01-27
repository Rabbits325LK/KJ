<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/common.tld" prefix="common"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/views/common/meta.jsp"%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<%@include file="/WEB-INF/views/common/head.jsp"%>
<style type="text/css">
.logo {
	background: #ccc;
}
</style>
<script type="text/javascript">
	javascript: window.history.forward(1);//防止后退
</script>
</head>
<body>
	<div class="easyui-panel" style="width: 100%;">
		<div id="gardToolBar">
			<common:toolbar id="toolBar">
				<common:button name="新增" id="addAssociator"
					url="/app/associator/addAssociator.html"
					options="plain:true,iconCls:'pencil_add'" />
				<common:button name="修改" id="editAssociator"
					url="/app/associator/editAssociator.html"
					options="plain:true,iconCls:'pencil'" />
				<common:button name="启用" id="start"
					url="/app/associator/startAssociator.json"
					options="plain:true,iconCls:'tick'" />
				<common:button name="锁定" id="lock"
					url="/app/associator/lockAssociator.json"
					options="plain:true,iconCls:'key'" />
				<common:button name="删除" id="removeAssociator"
					url="/app/associator/removeAssociator.json"
					options="plain:true,iconCls:'cancel'" />
			</common:toolbar>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td style="width: 5%; white-space: nowrap;">用户名:</td>
					<td style="width: 5%; white-space: nowrap;"><input
						class="easyui-textbox span2" type="text" placeholder="用户名"
						id="username" name="username" /></td>
					<td style="width: 5%; white-space: nowrap;">真实姓名:</td>
					<td style="width: 5%; white-space: nowrap;"><input
						class="easyui-textbox span2" type="text" placeholder="真实姓名"
						id="realName" name="realName" /></td>
					<td style="width: 5%; white-space: nowarp;">性别:</td>
					<td style="width: 5%; white-space: nowarp;"><select id="sex"
						name="sex" class="easyui-combobox">
							<option value="" selected="selected">请选择</option>
							<option value="1">男</option>
							<option value="2">女</option>
							<option value="9">其他</option>
					</select></td>
					<td style="width: 5%; white-space: nowrap;">用户状态:</td>
					<td style="width: 5%; white-space: nowrap;"><select
						id="status" name="status" class="easyui-combobox">
							<option value="" selected="selected">请选择</option>
							<option value="1">启用</option>
							<option value="0">锁定</option>
					</select></td>
					<td><a href="javascript:;" id="search"
						class="easyui-linkbutton span1"><i class="icon-search"></i> 查询</a></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false" title=""
			style="overflow: hidden;">
			<table id="dataGrid"></table>
		</div>
	</div>
</body>
</html>
<script language="javascript">
	var dataGrid = null;
	$(function() {
		//查询
		$('#search').click(function() {
			$('#dataGrid').datagrid('load', {
				username : $('#username').val(),
				realName : $('#realName').val(),
				status : $('#status').combobox("getValue"),
				sex : $('#sex').combobox("getValue")
			});
		});

		//网格构建
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '<c:url value="/app/associator/searchAssociator.json"/>',
							loadMsg : '正在查询系统用户信息,请稍候....',
							nowrap : false,
							fit : true,
							fitColumns : false,
							border : false,
							singleSelect : false,
							pagination : true,
							idField : 'id',
							checkOnSelect : true,
							toolbar : '#gardToolBar',
							sortName : 'id',
							sortOrder : 'asc',
							pageSize : 20,
							pageList : [ 10, 20, 30, 50 ],
							columns : [ [
									{
										field : 'id',
										width : 30,
										checkbox : true
									},
									{
										field : 'username',
										title : '登录名',
										width : 100,
										align : 'left'
									},
									{
										field : 'realName',
										title : '真实名称',
										width : 120,
										align : 'left'
									},
									{
										field : 'sex',
										title : '性别',
										width : 50,
										align : 'center',
										sortable : true,
										formatter : function(value, row, index) {
											if (value == '0') {
												return '保密';
											} else if (value == '1') {
												return '男性';
											} else if (value == '2') {
												return '女性';
											} else {
												return '未知';
											}
										}
									},
									{
										field : 'address',
										title : '联系地址',
										width : 250,
										align : 'left'
									},
									{
										field : 'phone',
										title : '联系电话',
										width : 120,
										align : 'left'
									},
									{
										field : 'email',
										title : '电子邮箱',
										width : 150,
										align : 'left'
									},
									{
										field : 'wechtCode',
										title : '微信',
										width : 120,
										align : 'left'
									},
									{
										field : 'qq',
										title : 'qq号码',
										width : 120,
										align : 'left'
									},
									{
										field : 'status',
										title : '状态',
										width : 60,
										align : 'center',
										formatter : function(value, row, index) {
											if (value == '0') {
												return '<font color="#FF9933">锁定</font>';
											} else if (value == '1') {
												return '<font color="#00FF66">正常</font>';
											} else {
												return '<font color="#CCCCCC">未知</font>';
											}
										}
									},
									{
										field : 'action',
										title : '操作',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
											return '<common:link id="editRow" url="/app/associator/editAssociator.html" click="editRecordRow(\\\''
													+ row.id
													+ '\\\');"  title="编辑" icon="${pageContext.request.contextPath}/images/extjs_icons/pencil.png"/>'
													+ '&nbsp;&nbsp;'
													+ '<common:link id="deleteRow" url="/app/associator/removeAssociator.json" click="deleteRecordRow(\\\''
													+ row.id
													+ '\\\');" title="删除" icon="${pageContext.request.contextPath}/images/extjs_icons/cancel.png"/>';
										}
									} ] ],
							headerContextMenu : [
									{
										text : "冻结该列",
										disabled : function(e, field) {
											return dataGrid.datagrid(
													"getColumnFields", true)
													.contains(field);
										},
										handler : function(e, field) {
											dataGrid.datagrid("freezeColumn",
													field);
										}
									},
									{
										text : "取消冻结该列",
										disabled : function(e, field) {
											return dataGrid.datagrid(
													"getColumnFields", false)
													.contains(field);
										},
										handler : function(e, field) {
											dataGrid.datagrid("unfreezeColumn",
													field);
										}
									} ],
							enableHeaderClickMenu : true,
							enableHeaderContextMenu : true,
							enableRowContextMenu : false,
							onLoadSuccess : function(data) {
								parent.$.messager.progress('close');
								$(this).datagrid('tooltip');
								if (data.total > 0) {
									return;
								}
								$(".datagrid-btable")
										.css("width", "100%")
										.html(
												"<tr><td><div style='text-align:center;color:red'>未查询到相关记录!</div></td></tr>");
							}
						});

		//设置分页控件 
		var pager = $('#dataGrid').datagrid().datagrid('getPager');
		$(pager).pagination({
			pageSize : 20,
			pageList : [ 10, 20, 30, 50 ],
			beforePageText : '第',//页数文本框前显示的汉字 
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示第 {from}条   - 第{to}条记录   共 {total} 条记录'
		});
		//增加
		$('#addAssociator')
				.click(
						function() {
							parent.$
									.modalDialog({
										title : '添加用户',
										width : 680,
										height : 300,
										href : '${pageContext.request.contextPath}/app/associator/addAssociator.html',
										buttons : [
												{
													text : '添加',
													iconCls : 'tick',
													handler : function() {
														parent.$.modalDialog.openner_dataGrid = dataGrid;
														var f = parent.$.modalDialog.handler
																.find('#form');
														f.submit();
													}
												},
												{
													text : '关闭',
													handler : function() {
														parent.$.modalDialog.handler
																.dialog('close');
														dataGrid
																.datagrid('reload');
													}
												} ]
									});
						});
		//修改
		$('#editAssociator')
				.click(
						function() {
							var selections = $('#dataGrid').datagrid(
									'getSelections');
							if (null == selections || '' == selections
									|| selections.length == 0) {
								parent.$.messager.alert('提示', '请选择需要修改的用户记录行.',
										'info');
								return false;
							} else if (selections.length > 1) {
								parent.$.messager.alert('提示', '每次只可修改一行记录.',
										'info');
								return false;
							} else {
								editRecordRow(selections[0].id);
							}
						});
		//启用
		$('#start')
				.click(
						function() {
							var codes = new Array();
							var selections = $('#dataGrid').datagrid(
									'getSelections');
							if (null != selections && selections != ''
									&& selections != undefined) {
								for (var i = 0; i < selections.length; i++) {
									codes.push(selections[i].id);
								}
							}
							if (codes == null || codes.length == 0) {
								parent.$.messager.alert('提示', '请选择要启用的用户记录行',
										'info');
								return false;
							}
							parent.$.messager
									.confirm(
											'询问',
											'您是否要启用当前所选用户?',
											function(b) {
												if (b) {
													parent.$.messager
															.progress({
																title : '提示',
																text : '数据处理中，请稍后....'
															});
													$
															.post(
																	'${pageContext.request.contextPath}/app/associator/startAssociator.json',
																	{
																		id : codes
																				.join(',')
																	},
																	function(
																			result) {
																		parent.$.messager
																				.progress('close');
																		if (result.success) {
																			parent.$.messager
																					.alert(
																							'提示',
																							'所选用户启用操作成功,请确认.',
																							'info');
																			dataGrid
																					.datagrid('reload');
																		} else {
																			$.messager
																					.alert(
																							'错误',
																							result.message,
																							'error');
																		}
																	}, 'JSON');
												}
											});
						});
		//锁定
		$('#lock')
				.click(
						function() {
							var codes = new Array();
							var selections = $('#dataGrid').datagrid(
									'getSelections');
							if (null != selections && selections != ''
									&& selections != undefined) {
								for (var i = 0; i < selections.length; i++) {
									codes.push(selections[i].id);
								}
							}
							if (codes == null || codes == ''
									|| codes.length == 0) {
								parent.$.messager.alert('提示', '请选择要停用的用户记录行',
										'info');
								return false;
							}
							parent.$.messager
									.confirm(
											'询问',
											'您是否要锁定当前所选用户?',
											function(b) {
												if (b) {
													parent.$.messager
															.progress({
																title : '提示',
																text : '数据处理中，请稍后....'
															});
													$
															.post(
																	'${pageContext.request.contextPath}/app/associator/lockAssociator.json',
																	{
																		id : codes
																				.join(',')
																	},
																	function(
																			result) {
																		parent.$.messager
																				.progress('close');
																		if (result.success) {
																			parent.$.messager
																					.alert(
																							'提示',
																							'所选用户锁定操作成功,请确认.',
																							'info');
																			dataGrid
																					.datagrid('reload');
																		} else {
																			$.messager
																					.alert(
																							'错误',
																							result.message,
																							'error');
																		}
																	}, 'JSON');
												}
											});
						});
		//删除用户
		$('#removeAssociator')
				.click(
						function() {
							var codes = new Array();
							var selections = $('#dataGrid').datagrid(
									'getSelections');
							if (null != selections && selections != ''
									&& selections != undefined) {
								for (var i = 0; i < selections.length; i++) {
									codes.push(selections[i].id);
								}
							}
							if (codes == null || codes == ''
									|| codes.length == 0) {
								parent.$.messager.alert('提示', '请选择要删除的用户记录行',
										'info');
								return false;
							}
							parent.$.messager
									.confirm(
											'询问',
											'您是否要删除当前所选用户?',
											function(b) {
												if (b) {
													parent.$.messager
															.progress({
																title : '提示',
																text : '数据处理中，请稍后....'
															});
													$
															.post(
																	'${pageContext.request.contextPath}/app/associator/removeAssociator.json',
																	{
																		id : codes
																				.join(',')
																	},
																	function(
																			result) {
																		parent.$.messager
																				.progress('close');
																		if (result.success) {
																			parent.$.messager
																					.alert(
																							'提示',
																							'所选用户删除操作成功,请确认.',
																							'info');
																			dataGrid
																					.datagrid('reload');
																		} else {
																			$.messager
																					.alert(
																							'错误',
																							result.message,
																							'error');
																		}
																	}, 'JSON');
												}
											});
						});
	});

	//行编辑
	function editRecordRow(code) {
		parent.$
				.modalDialog({
					title : '编辑用户',
					width : 680,
					height : 300,
					href : '${pageContext.request.contextPath}/app/associator/editAssociator.html?id='
							+ code,
					buttons : [ {
						text : '保存',
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;
							var f = parent.$.modalDialog.handler.find('#form');
							f.submit();
						}
					}, {
						text : '关闭',
						handler : function() {
							parent.$.modalDialog.handler.dialog('close');
						}
					} ]
				});
	}

	//行删除
	function deleteRecordRow(code) {
		if (code == undefined) {
			return;
		}
		parent.$.messager
				.confirm(
						'询问',
						'您是否要删除当前用户信息?',
						function(b) {
							if (b) {
								parent.$.messager.progress({
									title : '提示',
									text : '数据处理中，请稍后....'
								});
								var ids = new Array();
								ids.push(code);
								$
										.post(
												'${pageContext.request.contextPath}/app/associator/removeAssociator.json',
												{
													id : ids.join(',')
												},
												function(result) {
													parent.$.messager
															.progress('close');
													if (result.success) {
														$.messager
																.alert(
																		'提示',
																		'所选用户删除操作成功,请确认.',
																		'info');
														dataGrid
																.datagrid('reload');
													} else {
														$.messager.alert('错误',
																result.message,
																'error');
													}
												}, 'JSON');
							}
						});
	}
</script>