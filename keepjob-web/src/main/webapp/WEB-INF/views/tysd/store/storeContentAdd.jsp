<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<form id="form" method="post" class="form-inline">
			<div id="tabs" class="easyui-tabs" style="width: 780px;">
				<input type="hidden" id="id" name="${result.id }" />
				<div id="businessDiv" title="内容编辑" data-options="border:false">
					<textarea id="newsEditor" name="content">${result.content }</textarea>
				</div>
			</div>
		</form>
	</div>
</div>
<!--start Ueditor  -->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/ueditor/ueditor.all.min.js">
	
</script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/ueditor/lang/zh-cn/zh-cn.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ueditor/themes/default/css/ueditor.css">
<!-- end Ueditor -->
<script type="text/javascript">
	
	$(function() {
		parent.$.messager.progress('close');
		
		$('#form').form({
			url : '${pageContext.request.contextPath}/tysd/store/saveStore.json',
			onSubmit : function() {
				var isValid = $(this).form('validate');
				if(isValid) {
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
				if(result.success){
					$.messager.alert('提示', '添加店铺信息成功,请确认.', 'info');
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog('close');
				}else{
					$('#errorMsg').html(result.message);
				}
			}
		});
	});
	
	/*Ueditor
	----------------------------------------------- */
	var editor = new baidu.editor.ui.Editor();
	editor.render("newsEditor");
</script>