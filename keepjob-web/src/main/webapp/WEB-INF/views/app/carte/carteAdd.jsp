<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<form id="form" method="post" class="form-inline">
			<div id="tabs" class="easyui-tabs" style="width: 650px;">
				<div id="basicDiv" title="基本信息" data-options="selected:true"
					class="row">
					<table class="table table-hover table-condensed" style="width:100%;">
						<tr>
							<td colspan="2" style="width:50%;">
								<img id="logoImage" alt="" src=""
								style="width: 180px; height: 180px;margin-left: 20%;margin-top: 2%;">
							<td>
							<td colspan="2">
								<table style="margin: 0%;">
									<tr>
										<td style="text-align: right;">菜谱名称:</td>
										<td style=""><input id="carteName" name="carteName"
											type="text" placeholder="请输入菜谱名称"
											class="easyui-validatebox input-sm form-control" value=""
											data-options="required:true"></td>
									</tr>
									<tr>
										<td style="text-align: right;">菜谱类型:</td>
										<td><select id="carteType" name="carteType"
											class="input-sm form-control">
												<option value="">请选择</option>
												<c:forEach items="${types }" var="t">
													<option value="${t.uniqueCode }">${t.name }</option>
												</c:forEach>
										</select></td>
									</tr>
									<tr>
										<td style="text-align: right;">状态:</td>
										<td><select id="status" name="status"
											class="input-sm form-control">
												<option value="" selected="selected">请选择</option>
												<option value="1">发布</option>
												<option value="0">暂存</option>
										</select></td>
									</tr>
									<tr>
										<td colspan="2"><input type="file" id="logoFile" name="logoFile"><br/>
										<input type="button" value="上传图片"
											onclick="ajaxFileUpload()" /><br />
											<p id="resultContext"></p> <input type="hidden" name="carteLogo"
											id="carteLogo" /></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="text-align: center">简要:</td>
						</tr>
						<tr>
							<td colspan="4"><textarea id="carteSummary" name="carteSummary"
									class="form-control" rows="3" style="margin-left: 5%;width:90%"></textarea></td>
						</tr>
						<tr>
							<td colspan="4"  style="text-align: center">菜谱:</td>
						</tr>
						<tr>
							<td colspan="4"><textarea id="carteContent" name="carteContent"
									class="form-control" rows="8" style="margin-left: 5%;width:90%"></textarea></td>
						</tr>
						<tr>
							<td colspan="4" style="text-align: center;"><font
								color="red"><p id="errorMsg"></p></font></td>
						</tr>
					</table>
				</div>

			</div>
		</form>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	function ajaxFileUpload() {
		//开始上传文件时显示一个图片,文件上传完成将图片隐藏
		$('#loading').ajaxStart(function() {
			$(this).show();
		}).ajaxComplete(function() {
			$(this).hide();
		});
		//执行上传文件操作的函数
		//alert("uploading...");
		$.ajaxFileUpload({
			url : '${pageContext.request.contextPath}/common/fileUpload.json',
			//url : "${pageContent.reqest.contextPath}/jsp/controller.jsp",
			secureuri : false, //是否启用安全提交,默认为false
			fileElementId : 'logoFile', //文件选择框的id属性
			//dataType : 'text',				//服务器返回的格式,可以是json或xml等
			//dataType:'json',
			success : function(data, status) { //服务器响应成功时的处理函数
				var str = $(data).find("body").text();//获取返回的字符串
				//alert("......");
				//alert(str);
				var jsonStr = $.parseJSON(str);//把字符串转化为json对象
				//alert("upload...OK");
				//alert(jsonStr.result + ':' + jsonStr.message);
				/* data = data.replace("<PRE>", '');		//ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
				data = data.replace("</PRE>", '');
				data = data.replace("<pre>", '');
				data = data.replace("</pre>", ''); *///本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
				if (jsonStr.result == 0) { //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
					$("img[id='logoImage']").attr("src", jsonStr.message);
					$("#carteLogo").val(jsonStr.message);
					$('#resultContext').html("图片上传成功<br/>");
					alert($('#carteLogo').val());
				} else {
					$('#resultContext').html("" + jsonStr.message);
				}
			},
			error : function(data, status, e) { //服务器响应失败时的处理函数
				var str = $(data).find("body").text();//获取返回的字符串
				//alert(str);
				var jsonStr = $.parseJSON(str);//把字符串转化为json对象
				$('#resultContext').html("" + jsonStr.message);
			}
		});
	}
	/*----------------------------------------------------------------------------------- 
	AjaxFileUpload简介
	官网:http://phpletter.com/Our-Projects/AjaxFileUpload/
	简介:jQuery插件AjaxFileUpload能够实现无刷新上传文件,并且简单易用,它的使用人数很多,非常值得推荐
	注意:引入js的顺序(它依赖于jQuery)和页面中并无表单(只是在按钮点击的时候触发ajaxFileUpload()方法)
	常见错误及解决方案如下
	1)SyntaxError: missing ; before statement
	  --检查URL路径是否可以访问
	2)SyntaxError: syntax error
	  --检查处理提交操作的JSP文件是否存在语法错误
	3)SyntaxError: invalid property id
	  --检查属性ID是否存在
	4)SyntaxError: missing } in XML expression
	  --检查文件域名称是否一致或不存在
	5)其它自定义错误
	  --可使用变量$error直接打印的方法检查各参数是否正确,比起上面这些无效的错误提示还是方便很多
	  -----------------------------------------------------------------------------------*/
	$(function() {
		parent.$.messager.progress('close');

		$('#form')
				.form(
						{
							url : '${pageContext.request.contextPath}/app/carte/saveCarte.json',
							onSubmit : function() {
								var isValid = $(this).form('validate');
								if (isValid) {
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
								if (result.success) {
									$.messager.alert('提示', '添加菜谱信息成功,请确认.',
											'info');
									parent.$.modalDialog.openner_dataGrid
											.datagrid('reload');
									parent.$.modalDialog.handler
											.dialog('close');
								} else {
									$('#errorMsg').html(result.message);
								}
							}
						});
	});
</script>