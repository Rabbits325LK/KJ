<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>WeUI</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mobiscroll.custom-3.0.0-beta2.min.css"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/mobiscroll.custom-3.0.0-beta2.min.js"></script>

</head>
<body>
<%--<div class="view active" id="mainView">
    <header>
        <h1></h1>
    </header>
    <!--List of Form Elements-->
    <div class="pages">
        <div class="panel" data-title="出船登记" id="list" data-selected="true">
            <br>
            <form>
                <select>
                    <option value="">请选择驾驶人</option>
                    <option value="1">梁佳</option>
                </select>
                <input type="text" placeholder="姓名">
                <input type="text" placeholder="游艇">
                <textarea rows="6" placeholder="Enter your address"></textarea>
            </form>
        </div>

    </div>
</div>--%>
<div id="demo" style="display:none" mbsc-enhance>
    <div class="mbsc-divider">出船登记</div>
    <label for="employeeCode">
        驾驶员
        <select class="md-select" id="employeeCode">
            <option value="">请选择</option>
            <option value="1">梁佳</option>
            <option value="2">袁昊</option>
        </select>
    </label>
    <label>
        游艇
        <select class="md-select" id="shipCode">
            <option value="">请选择</option>
            <option value="1">曼德号</option>
            <option value="2">红馆</option>
        </select>
    </label>


</div>


</body>
</html>
