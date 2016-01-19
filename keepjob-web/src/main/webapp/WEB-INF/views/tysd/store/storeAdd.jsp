<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<form id="form" method="post" class="form-inline">
			<div id="tabs" class="easyui-tabs" style="width: 780px;">
				<div id="basicDiv" title="基本信息" data-options="selected:true"
					class="row">
					<table class="table table-hover table-condensed">
						<tr>
							<td style="text-align: right;">店铺名称:</td>
							<td style=""><input id="name" name="name" type="text"
								placeholder="请输入店铺名称"
								class="easyui-validatebox input-sm form-control" value=""
								data-options="required:true"></td>
							<td style="text-align: right;">负责人:</td>
							<td><input type="text" id="principal" name="principal"
								placeholder="请输入店铺负责人姓名"
								class="easyui-validatebox input-sm form-control"
								data-options="required:true" value=""></td>
						</tr>
						<tr>
							<td style="text-align: right;">地址:</td>
							<td><input type="text" name="address" id="address"
								placeholder="" class="easyui-validatebox input-sm form-control"
								data-options=""></td>
							<td style="text-align: right;">电话:</td>
							<td><input type="text" name="phone" id="phone"
								placeholder="" class="easyui-validatebox input-sm form-control"
								data-options=""></td>
						</tr>
						<tr>
							<td style="text-align: right">店铺类型:</td>
							<td><select id="type" name="type"
								class="input-sm form-control">
									<option value="">请选择</option>
									<c:forEach items="${types }" var="t">
										<option value="${t.uniqueCode }">${t.name }</option>
									</c:forEach>
							</select></td>
							<td style="text-align: right">备注:</td>
							<td><input type="text" name="remark" id="remark" value=""
								class="easyui-validatebox input-sm form-control" data-options="">
							</td>
						</tr>
						<tr>
							<td style="text-align: right">简要:</td>
							<td colspan="3"><textarea id="summary" name="summary"
									class="form-control" rows="3" cols="65"></textarea></td>
						</tr>
						<tr>
							<td style="text-align: right">Logo:</td>

							<td colspan="2"><img id="logoImage" alt="" src=""
								style="width: 200px; height: 200px;"></td>
							<td><input type="file" id="logoFile" name="logoFile"><br />
								<input type="button" value="上传图片" onclick="ajaxFileUpload()" /><br />
								<p id="resultContext"></p>
								<input type="hidden" name="logo" id="logo"/>
								</td>
						</tr>
						<tr>
							<td colspan="3" style="text-align:center;"><font color="red"><p id="errorMsg"></p></font></td>
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
						data = data.replace("</pre>", ''); */		//本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
						if (jsonStr.result == 0) { //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
							$("img[id='logoImage']").attr("src", jsonStr.message);
							$("#logo").val(json.message);
							$('#resultContext').html("图片上传成功<br/>");
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
	
	
</script>