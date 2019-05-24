<%--
  Created by IntelliJ IDEA.
  User: yhl
  Date: 2019/5/22
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()
            +":"+request.getServerPort()+path+"/";
%>

<html>
<head>
    <title>添加设备</title>
</head>
<body>

<div style="height: 30px;">
<span style="float: right;">
    <iframe src="<%=basePath%>template/top.jsp" height="30px" frameborder="0" width="500px" scrolling="no"></iframe>
</span>
</div>

<div style="text-align: center; font-size: 26px;">
    <b> <a href="<%=basePath%>manage/managedev.do">管理设备</a> | 添加设备 | <a href="<%=basePath%>manage/controldev.do">控制设备</a> | <a href="<%=basePath%>manage/adduser.do">添加用户</a> | <a href="<%=basePath%>manage/deluser.do">删除用户</a></b>
</div>

<br>

<div>
    <table style="text-align: center; margin: 0 auto;">
        <tr style="text-align: left">
            <td>设备Mac地址</td>
            <td>
                <input id="devMac" type="text" />
            </td>
        </tr>
        <tr style="text-align: left">
            <td>设备IP</td>
            <td>
                <input id="devIp" type="text" />
            </td>
        </tr>
        <tr style="text-align: right">
            <td colspan="2">
                <button onclick="addDev()">添加用户</button>
            </td>
        </tr>
    </table>
    <br>
</div>

<script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/top.js"></script>
<script type="text/javascript">
    //提交按钮点击事件
    function addDev() {

        var dev = {
            devMac : $("#devMac").val(),
            devIp : $("#devIp").val()
        };
        console.log("提交点击事件：mac="+dev.devMac+" ip="+dev.devIp);
        if (dev.devIp.trim().length !== 0 || dev.devMac.trim().length !== 0) {
            //保证有一个不为空
            submit(dev);
        } else {
            alert("只能修改id和ip，请填入要修改字段！");
        }
    }
    function submit(dev) {
        $.ajax({
            "type": "post",
            "url": "/equipment/manage/add_dev.do",
            "contentType": "application/json",
            "data": JSON.stringify(dev),
            "dataType": "json",
            "success": function (json) {
                alert(json.data);
                window.location.href = "adddev.do";
            }
        });
    }
</script>
</body>
</html>
