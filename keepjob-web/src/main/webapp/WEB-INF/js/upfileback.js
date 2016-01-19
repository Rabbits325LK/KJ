/**
一、ajaxFileUpload是一个异步上传文件的jQuery插件。
　　传一个不知道什么版本的上来，以后不用到处找了。
　　语法：$.ajaxFileUpload([options])
options参数说明：
1、url　　　　　　　　　　  上传处理程序地址。　　
2，fileElementId　　　　　  需要上传的文件域的ID，即<input type="file">的ID。
3，secureuri　　　　　　　 是否启用安全提交，默认为false。 
4，dataType　　　　　　　 服务器返回的数据类型。可以为xml,script,json,html。如果不填写，jQuery会自动判断。
5，success　　　　　　　　提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
6，error　　　　　　　　　 提交失败自动执行的处理函数。
7，data	　　　　　　　　　 自定义参数。这个东西比较有用，当有数据是与上传的图片相关的时候，这个东西就要用到了。
8, type	　　　　　　　　　  当要提交自定义参数时，这个参数要设置成post
错误提示:
1，SyntaxError: missing ; before statement错误
　　如果出现这个错误就需要检查url路径是否可以访问
2，SyntaxError: syntax error错误
　　如果出现这个错误就需要检查处理提交操作的服务器后台处理程序是否存在语法错误
3，SyntaxError: invalid property id错误
　　如果出现这个错误就需要检查文本域属性ID是否存在
4，SyntaxError: missing } in XML expression错误
　　如果出现这个错误就需要检查文件name是否一致或不存在
5，其它自定义错误
　　大家可使用变量$error直接打印的方法检查各参数是否正确，比起上面这些无效的错误提示还是方便很多。
 */
function ajaxFileUpload(resultElementId, fileElementId, dir) {
	dir = encodeURIComponent(dir);
	$.ajaxFileUpload({
        url: rootPath + "/fileUpload.do?dir="+dir,
        type : "post",
        secureuri:false,
        fileElementId: fileElementId,
        dataType:"text",
        success:function(data){
            if (data != 0) {
            	$("#" + resultElementId).attr("value",data);
            }else{
            	alert("上传失败，请重试！！");
            }
        },
        error:function(data){
        	alert("上传失败，请重试！！");
        }
    });
}
