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
				<common:button name="新增" id="addStore" url="/tysd/store/addStore.html"
					options="plain:true,iconCls:'pencil_add'" />
				<common:button name="编辑" id="editStore"
					url="/tysd/store/editStore.html"
					options="plain:true,iconCls:'pencil'" />
				<common:button name="添加简介" id="addStoreContent"
					url="/tysd/store/addStoreContent.html"
					options="plain:true,iconCls:'pencil'" />	
				<common:button name="启用" id="start" url="/tysd/store/startStore.json"
					options="plain:true,iconCls:'tick'" />
				<common:button name="停用" id="stop" url="/tysd/store/stopStore.json"
					options="plain:true,iconCls:'stop'" />
				<common:button name="删除" id="removeStore"
					url="/tysd/store/removeStore.json"
					options="plain:true,iconCls:'cancel'" />
				
			</common:toolbar>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td style="width: 5%; white-space: nowrap;text-align:right;">店铺名:</td>
					<td style="width: 5%; white-space: nowrap;"><input
						class="easyui-textbox span2" type="text"
						placeholder="店铺名" id="name" name="name" /></td>
					<td style="width: 5%; white-space: nowrap;text-align:right;">负责人:</td>
					<td style="width: 5%; white-space: nowrap;"><input
						class="easyui-textbox span2" type="text" placeholder="请输入所属机构名称"
						id="principal" name="principal" /></td>
					<td style="width: 5%; white-space: nowrap;text-align:right;">店铺类型:</td>
					<td style="width: 5%; white-space: nowrap;">
						<select id="type" name="type" class="easyui-combobox">
							<option value="" selected="selected">请选择</option>
							<c:forEach items="${types }" var="t">
								<option value="${t.uniqueCode }">${t.name }</option>
							</c:forEach>
						</select>
					</td>
					<td style="width: 5%; white-space: nowrap;text-align:right;">店铺状态:</td>
					<td style="width: 5%; white-space: nowrap;">
						<select id="status" name="status" class="easyui-combobox">
							<option value="" selected="selected">请选择</option>
							<option value="1">启用</option>
							<option value="0">停用</option>
						</select>
					</td>
					<td><a href="javascript:;" id="search" class="easyui-linkbutton span1"><i class="icon-search"></i> 查询</a></td>
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
				principal : $('#principal').val(),
				type : $('#type').combobox("getValue"),
				status : $('#status').combobox("getValue"),
				name : $('#name').val()
			}); 
		}); 

		//网格构建
		dataGrid = $('#dataGrid').datagrid(
			{
				url : '<c:url value="/tysd/store/searchStore.json"/>',
				loadMsg : '正在查询店铺信息，请稍后....',
				nowrap : false,
				fit : true,
				fitColumns : true,
				border : false,
				singleSelect : false,
				pagination : true,
				idField : 'id',
				checkOnSelect : true,
				sortName : 'id',
				sortOrder : 'asc',
				toolbar : '#gardToolBar',
				pageSize : 20,
				pageList : [10, 20, 30, 50 ],
				columns : [[{
					field : 'id',
					checkbox : true,
					width : 40
				},{
					title : '店铺名',
					field : 'name',
					width : 50
				},{
					title : '负责人',
					field : 'principal',
					width : 40
				},{
					title : '电话',
					field : 'phone',
					width : 40
				},{
					title : '地址',
					field : 'address',
					width : 100
				},{
					title : '简要',
					field : 'summary',
					width : 120
				},{
					title : '状态',
					field : 'status',
					width : 40,
					formatter : function(value, row, index){
						if(value == '0'){
							return '<font color="red">停用</font>';
						}else if(value == '1'){
							return '<font color="green">启用</font>';
						}else{
							return '<font color="#999999">未知</font>';
						}
					}
				},{
					title : '类型',
					field : 'typeName',
					width : 40
				}]],
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
	
	 var p = $('#dataGrid').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [10,20,30,40,50],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	        
	    }); 
		//增加
		$('#addStore')
				.click(
						function() {
							parent.$
									.modalDialog({
										title : '添加店铺',
										width : 650,
										height : 660,
										href : '${pageContext.request.contextPath}/tysd/store/addStore.html',
										buttons : [
												{
													text : '添加',
													iconCls:'tick',
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
		$('#editStore')
				.click(
						function() {
							var selections = $('#dataGrid').datagrid(
									'getSelections');
							if (null == selections || '' == selections
									|| selections.length == 0) {
								parent.$.messager.alert('提示', '请选择需要修改的店铺记录行.',
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
		//修改
		$('#addStoreContent')
				.click(
						function() {
							var selections = $('#dataGrid').datagrid(
									'getSelections');
							if (null == selections || '' == selections
									|| selections.length == 0) {
								parent.$.messager.alert('提示', '请选择需要修改的店铺记录行.',
										'info');
								return false;
							} else if (selections.length > 1) {
								parent.$.messager.alert('提示', '每次只可修改一行记录.',
										'info');
								return false;
							} else {
								addStoreContentRecordRow(selections[0].id);
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
							if (codes == null || codes == ''
									|| codes.length == 0) {
								parent.$.messager.alert('提示', '请选择要启用的店铺记录行',
										'info');
								return false;
							}
							parent.$.messager
									.confirm(
											'询问',
											'您是否要启用当前所选店铺?',
											function(b) {
												if (b) {
													parent.$.messager
															.progress({
																title : '提示',
																text : '数据处理中，请稍后....'
															});
													$
															.post(
																	'${pageContext.request.contextPath}/tysd/store/startStore.json',
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
																							'所选店铺启用操作成功,请确认.',
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
		//停用
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
								parent.$.messager.alert('提示', '请选择要停用的店铺记录行',
										'info');
								return false;
							}
							parent.$.messager
									.confirm(
											'询问',
											'您是否要停用当前所选店铺?',
											function(b) {
												if (b) {
													parent.$.messager
															.progress({
																title : '提示',
																text : '数据处理中，请稍后....'
															});
													$
															.post(
																	'${pageContext.request.contextPath}/tysd/store/stopStore.json',
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
																							'所选店铺停用操作成功,请确认.',
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
		$('#removeStore')
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
								parent.$.messager.alert('提示', '请选择要删除的店铺记录行',
										'info');
								return false;
							}
							parent.$.messager
									.confirm(
											'询问',
											'您是否要删除当前所选店铺?',
											function(b) {
												if (b) {
													parent.$.messager
															.progress({
																title : '提示',
																text : '数据处理中，请稍后....'
															});
													$
															.post(
																	'${pageContext.request.contextPath}/tysd/store/removeStore.json',
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
																							'所选用户店铺删除操作成功,请确认.',
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
					title : '编辑店铺',
					width : 650,
					height : 600,
					href : '${pageContext.request.contextPath}/tysd/store/editStore.html?id='
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

	function addStoreContentRecordRow(code) {
		parent.$
				.modalDialog({
					title : '编辑店铺简介',
					width : 650,
					height : 600,
					href : '${pageContext.request.contextPath}/tysd/store/addStoreContent.html?id='
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
						'您是否要删除当前店铺信息?',
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
												'${pageContext.request.contextPath}/tysd/store/removeStore.json',
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
																		'所选用户店铺删除操作成功,请确认.',
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