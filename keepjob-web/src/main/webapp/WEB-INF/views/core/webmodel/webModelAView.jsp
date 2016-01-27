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
				<common:button name="暂存" id="start"
					url="/core/members/startMembers.json"
					options="plain:true,iconCls:'tick'" />
				<common:button name="下线" id="lock"
					url="/core/members/lockMembers.json"
					options="plain:true,iconCls:'key'" />
				<common:button name="发布" id="deleteUser"
					url="/core/members/deleteMembers.json"
					options="plain:true,iconCls:'cancel'" />
			</common:toolbar>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td style="width: 5%; white-space: nowrap;">会员编号:</td>
					<td style="width: 5%; white-space: nowrap;"><input
						class="easyui-textbox span2" type="text" placeholder="真实姓名"
						id="createrCode" name="createrCode" /></td>
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
										field : 'headTitle',
										title : '标题',
										width : 120,
										align : 'left'
									},
									{
										field : 'createrCode',
										title : '创建人编号',
										width : 250,
										align : 'left'
									},
									{
										field : 'browsingTimes',
										title : '浏览量',
										width : 80,
										align : 'left'
									},
									{
										field : 'niceNum',
										title : '被赞次数',
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
</script>
</html>