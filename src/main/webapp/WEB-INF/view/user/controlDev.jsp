<%--
  Created by IntelliJ IDEA.
  User: yhl
  Date: 2019/5/12
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../static/basePath.jsp" %>

<html>
<head>
    <title>控制设备</title>
    <link rel="stylesheet" type="text/css" href="${path}/css/main.css"/>
</head>
<body>

<div style="height: 50px;">

    <iframe src="<%=basePath%>template/top.jsp" height="50px" frameborder="0" width="100%" scrolling="no"></iframe>

</div>

<br>
<div style="text-align: center; font-size: 26px;">
    <b> <a href="<%=basePath%>manage/managedev.do">管理设备</a> | <a href="<%=basePath%>manage/adddev.do">添加设备</a> | 控制设备 | <a href="<%=basePath%>manage/adduser.do">添加用户</a> | <a href="<%=basePath%>manage/deluser.do">删除用户</a></b>
</div>

<div style="margin:0 auto; width: 1000px;">
    <br>
    <table width="1000px" style="text-align: center">
        <thead>
        <tr>
            <th>设备id</th>
            <th>设备MAC地址</th>
            <th>设备ip</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="infor">

        </tbody>
    </table>
</div>

<script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.js"></script>
<script type="text/javascript">
    $(function () {
        $.ajax({
            "type": "post",
            "url" : "/manage/show_all_dev.do",
            "data" : "",
            "dataType" : "json",
            "success" : function(json){
                $("#infor tr").remove();
                for( var i=0; i<json.length; i++ ){
                    var dMac = json[i].devMac;
                    $("#infor").append("<tr id='" + dMac +"'><td>" + json[i].devId + "</td><td>" + json[i].devMac + "</td><td>"
                        + json[i].devIp + "</td><td><a target='_blank' href='http://" + json[i].devIp + "/'>控制</a></td></tr>");
                }
            }
        })
    });

</script>
</body>
</html>
