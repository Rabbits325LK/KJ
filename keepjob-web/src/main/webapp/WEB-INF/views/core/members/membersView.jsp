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
				<common:button name="新增" id="addUser"
					url="/core/members/addMembers.html"
					options="plain:true,iconCls:'pencil_add'" />
				<common:button name="修改" id="editUser" url="/core/members/editMembers.html"
					options="plain:true,iconCls:'pencil'" />
				<common:button name="启用" id="start"
					url="/core/members/startMembers.json"
					options="plain:true,iconCls:'tick'" />
				<common:button name="锁定" id="lock"
					url="/core/members/lockMembers.json"
					options="plain:true,iconCls:'key'" />
				<common:button name="删除" id="deleteUser"
					url="/core/members/deleteMembers.json"
					options="plain:true,iconCls:'cancel'" />
			</common:toolbar>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td style="width: 5%; white-space: nowrap;">真实姓名:</td>
					<td style="width: 5%; white-space: nowrap;"><input
						class="easyui-textbox span2" type="text" placeholder="真实姓名"
						id="realName" name="realName" /></td>
					<td style="width: 5%; white-space: nowrap;">手机号:</td>
					<td style="width: 5%; white-space: nowrap;"><input
						class="easyui-textbox span2" type="text" placeholder="手机号码"
						id="phone" name="phone" /></td>
					<td style="width: 5%; white-space: nowrap;">电子邮箱:</td>
					<td style="width: 5%; white-space: nowrap;"><input
						class="easyui-textbox span2" type="text" placeholder="电子邮箱"
						id="email" name="email" /></td>
					<td style="width: 5%; white-space: nowrap;">性别:</td>
					<td style="width: 5%; white-space: nowrap;"><select
						class="easyui-combobox" name="sex" id="sex">
							<option value="">请选择</option>
							<option value="1">男</option>
							<option value="2">女</option>
							<option value="9">其他</option>
					</select></td>
					<td style="width: 5%; white-space: nowrap;">状态:</td>
					<td style="width: 5%; white-space: nowrap;"><select
						class="easyui-combobox" name="status" id="status">
							<option value="">请选择</option>
							<c:forEach items="${types }" var="t">
								<option value="${t.code }">${t.name }</option>
							</c:forEach>
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
<script language="javascript">
	var dataGrid = null;
	$(function() {
		//查询
		$('#search').click(function() {
			$('#dataGrid').datagrid('load', {
				email : $('#email').val(),
				phone : $('#phone').val(),
				sex : $('#sex').combobox("getValue"),
				status : $('#status').combobox("getValue"),
				realName : $('#realName').val()
			});
		});

		//网格构建
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '<c:url value="/core/members/searchMembers.json"/>',
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
							frozenColumns : [ [ {
								field : 'id',
								width : 30,
								checkbox : true
							} ] ],
							columns : [ [
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
										field : 'age',
										title : '所属机构',
										width : 150,
										align : 'left'
									},
									{
										field : 'email',
										title : '联系地址',
										width : 250,
										align : 'left'
									},
									{
										field : 'phone',
										title : '联系电话',
										width : 80,
										align : 'left'
									},
									{
										field : 'lastLoginDate',
										title : '最后登录时间',
										width : 150,
										align : 'left',
										formatter : function(val) {
											if (null != val) {
												var date = new Date(val)
												return date.formatLongDate();
											} else {
												return '未知日期';
											}
										}
									},
									{
										field : 'lastLoginIp',
										title : '最后登录IP',
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
												return '停用';
											} else if (value == '1') {
												return '正常';
											} else if (value == '2') {
												return '锁定';
											} else if (value == '9') {
												return '删除';
											} else {
												return '未知';
											}
										}
									},
									{
										field : 'action',
										title : '操作',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
											return '<common:link id="editRow" url="/core/members/editMembers.html" click="editRecordRow(\\\''
													+ row.id
													+ '\\\');"  title="编辑" icon="${pageContext.request.contextPath}/images/extjs_icons/pencil.png"/>'
													+ '&nbsp;'
													+ '&nbsp;'
													+ '<common:link id="deleteRow" url="/core/members/deleteMembers.json" click="deleteRecordRow(\\\''
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
		$('#addUser')
				.click(
						function() {
							parent.$
									.modalDialog({
										title : '添加用户',
										width : 600,
										height : 660,
										href : '${pageContext.request.contextPath}/core/members/addMembers.html',
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
		$('#editUser')
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
							var codes = '';
							var selections = $('#dataGrid').datagrid(
									'getSelections');
							var prex = '';
							if (null != selections && selections != ''
									&& selections != undefined) {
								for (var i = 0; i < selections.length; i++) {
									codes = codes + prex + selections[i].id;
									prex = ",";
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
																	'${pageContext.request.contextPath}/core/members/startMembers.json',
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
																	'${pageContext.request.contextPath}/core/members/lockMembers.json',
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

	})
	//行编辑
	function editRecordRow(code) {
		parent.$
				.modalDialog({
					title : '编辑用户',
					width : 600,
					height : 600,
					href : '${pageContext.request.contextPath}/core/members/editMembers.html?id='
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
												'${pageContext.request.contextPath}/core/members/removeMembers.json',
												{
													ids : ids.join(',')
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
</html>