<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/common.tld" prefix="common"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/views/common/meta.jsp"%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<jsp:include page="/WEB-INF/views/common/head.jsp" />
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false"
			style="overflow: hidden;">
			<table id="dataGrid"></table>
		</div>
	</div>
	<common:toolbar id="toolbar">
		<common:button name="刷新" id="reload"
			options="plain:true,iconCls:'transmit'" security="false" />
		<common:button name="添加" id="add" url="/sys/role/addrole.html"
			options="plain:true,iconCls:'pencil_add'" />
		<common:button name="修改" id="edit" url="/sys/role/editrole.html"
			options="plain:true,iconCls:'pencil'" />
		<%-- <common:button name="设置权限" id="grant" url="/sys/role/rolegrant.html" options="plain:true,iconCls:'bullet_key'" /> --%>
		<common:button name="启用" id="start" url="/sys/role/startrole.json"
			options="plain:true,iconCls:'tick'" />
		<common:button name="停用" id="stop" url="/sys/role/stoprole.json"
			options="plain:true,iconCls:'stop'" />
	</common:toolbar>
</body>
</html>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/sys/role/loadrole.json',
							title : '',
							loadMsg : '正在加载角色信息',
							fit : true,
							fitColumns : true,
							border : false,
							idField : 'id',
							sortName : 'id',
							sortOrder : 'asc',
							checkOnSelect : true,
							singleSelect : false,
							nowrap : false,
							rownumbers : true,
							frozenColumns : [ [ {
								checkbox : true,
								field : 'code',
								width : 100
							} ] ],
							columns : [ [
									{
										title : '角色名称',
										field : 'name',
										width : 160
									},
									{
										title : '状态',
										field : 'status',
										width : 50,
										align : 'center',
										formatter : function(value) {
											if (value == 0) {
												return '禁用';
											} else {
												return '正常';
											}
										}
									},
									{
										title : '备注',
										field : 'remark',
										width : 200
									},
									{
										field : 'action',
										title : '操作',
										width : 80,
										align : 'center',
										formatter : function(value, row, index) {
											return '<common:link id="editRow" url="/sys/role/editrole.html" click="editRoleRow(\\\''
													+ row.id
													+ '\\\');" title="编辑" icon="${pageContext.request.contextPath}/images/extjs_icons/pencil.png"/>'
													/* + '&nbsp;'
													+ '<common:link id="grantRow" url="/sys/role/rolegrant.html" click="roleGrant(\\\'' + row.id + '\\\');" title="授权" icon="${pageContext.request.contextPath}/images/extjs_icons/bullet_key.png"/>' */
													+ '&nbsp;'
													+ '<common:link id="deleteRow" url="/sys/role/deleterole.json" click="deleteRoleRow(\\\''
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
							toolbar : '#toolbar',
							onLoadSuccess : function(data) {
								$(this).datagrid('tooltip');
								if (data.total > 0)
									return;
								$(".datagrid-btable")
										.css("width", "100%")
										.html(
												"<tr><td><div style='text-align:center;color:red'>没有相关记录！</div></td></tr>");
							}
						});

		$('#reload').click(function() {
			dataGrid.datagrid('reload');
		});

		//添加角色按钮
		$('#add')
				.click(
						function() {
							parent.$
									.modalDialog({
										title : '添加角色',
										width : 500,
										height : 400,
										href : '${pageContext.request.contextPath}/sys/role/addrole.html',
										buttons : [
												{
													text : '添加',
													handler : function() {
														parent.$.modalDialog.openner_dataGrid = dataGrid;
														var f = parent.$.modalDialog.handler
																.find('#form');
														if ($(f).find(
																"#checkName")
																.val() == 0) {
															f.submit();
														}
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

		$('#edit')
				.click(
						function() {
							var selections = $('#dataGrid').datagrid(
									'getSelections');
							if (null == selections || '' == selections
									|| selections.length == 0) {
								parent.$.messager.alert('提示', '请选择需要修改的角色记录行.',
										'info');
								return false;
							} else if (selections.length > 1) {
								parent.$.messager.alert('提示', '每次只可修改一行记录.',
										'info');
								return false;
							} else {
								editRoleRow(selections[0].id);
							}
						});

		$('#grant').click(
				function() {
					var selections = $('#dataGrid').datagrid('getSelections');
					if (null == selections || '' == selections
							|| selections.length == 0) {
						parent.$.messager.alert('提示', '请选择需要设置权限的角色记录行.',
								'info');
						return false;
					} else if (selections.length > 1) {
						parent.$.messager.alert('提示', '每次只可修改一行记录.', 'info');
						return false;
					} else {
						roleGrant(selections[0].id);
					}
				});

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
							if (codes == null || codes == ''
									|| codes.length == 0) {
								parent.$.messager.alert('提示', '请选择要启动的角色记录行.',
										'info');
								return false;
							}
							parent.$.messager
									.confirm(
											'询问 ',
											'您是否要启动当前所选角色 ?',
											function(b) {
												if (b) {
													parent.$.messager
															.progress({
																title : '提示',
																text : '数据处理中，请稍候....'
															});
													$
															.post(
																	'${pageContext.request.contextPath}/sys/role/startrole.json',
																	{
																		ids : codes
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
																							'启用所选角色操作成功,请确认。',
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

		$('#stop')
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
								parent.$.messager.alert('提示', '请选择要停用的角色记录行。',
										'info');
								return false;
							}
							parent.$.messager
									.confirm(
											'询问 ',
											'您是否要停用当前所选角色?',
											function(b) {
												if (b) {
													parent.$.messager
															.progress({
																title : '提示',
																text : '数据处理中，请稍候....'
															});
													$
															.post(
																	'${pageContext.request.contextPath}/sys/role/stoprole.json',
																	{
																		ids : codes
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
																							'停用所选角色操作成功,请确认。',
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
	})

	function deleteRoleRow(id) {
		var ids = new Array();
		ids.push(id);
		parent.$.messager
				.confirm(
						'咨询',
						'您是否要删除当前角色？',
						function(b) {
							if (b) {
								parent.$.messager.progress({
									title : '提示',
									text : '数据处理中,请稍后....'
								});
								$
										.post(
												'${pageContext.request.contextPath}/sys/role/deleterole.json',
												{
													ids : ids.join(',')
												},
												function(result) {
													parent.$.messager
															.progress('close');
													if (result.success) {
														parent.$.messager
																.alert(
																		'提示',
																		'删除角色操作成功,请确认.',
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

	function editRoleRow(id) {
		parent.$
				.modalDialog({
					title : '编辑角色',
					width : 500,
					height : 400,
					href : '${pageContext.request.contextPath}/sys/role/editrole.html?id='
							+ id,
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
							dataGrid.datagrid('reload');
						}
					} ]
				});
	}

	function roleGrant(id) {
		parent.$
				.modalDialog({
					title : '权限设置',
					width : 500,
					height : 400,
					href : '${pageContext.request.contextPath}/sys/role/rolegrant.html?id='
							+ id,
					buttons : [ {
						text : '授权',
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;
							var f = parent.$.modalDialog.handler.find('#form');
							f.submit();
						}
					}, {
						text : '关闭',
						handler : function() {
							parent.$.modalDialog.handler.dialog('close');
							dataGrid.datagrid('reload');
						}
					} ]
				});
	}
</script>