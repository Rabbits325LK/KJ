<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/js/ueditor/ueditor.config.js"></script>
<script src="${pageContext.request.contextPath }/js/ueditor/ueditor.all.min.js"></script>
</head>
<body>
 <script type="text/plain" id="j_ueditorupload" style="height:5px;display:none;" ></script>
    <script>
      //实例化编辑器
      var o_ueditorupload = UE.getEditor('j_ueditorupload',
      {
        autoHeightEnabled:false
      });
      o_ueditorupload.ready(function ()
      {
     
    o_ueditorupload.hide();//隐藏编辑器
     
    
    //监听图片上传
    o_ueditorupload.addListener('beforeInsertImage', function (t,arg)
    {
          alert('这是图片地址：'+arg[0].src);
          $('#LogoImg').attr('src',arg[0].src);
    });
     
    /* 文件上传监听
     * 需要在ueditor.all.min.js文件中找到
     * d.execCommand("insertHtml",l)
     * 之后插入d.fireEvent('afterUpfile',b)
     */
        o_ueditorupload.addListener('afterUpfile', function (t, arg)
        {
          alert('这是文件地址：'+arg[0].url);
        });
      });
       
      //弹出图片上传的对话框
      function upImage()
      {
        var myuploadImage = o_ueditorupload.getDialog("insertimage");
        myuploadImage.open();
      }
      //弹出文件上传的对话框
      function upFiles()
      {
        var myFiles = o_ueditorupload.getDialog("attachment");
        myFiles.open();
      } 
      
      
    </script>
    <img alt="" src="" id="LogoImg">
    <button type="button" onClick="upImage()">调用上传图片模块</button>
    <br>
    <button type="button" onClick="upFiles()">调用上传文件模块</button>
</body>
</html>