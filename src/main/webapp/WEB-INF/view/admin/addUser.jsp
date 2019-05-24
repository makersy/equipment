<%--
  Created by IntelliJ IDEA.
  User: yhl
  Date: 2019/5/24
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+"47.101.181.184"
            +path+"/";
%>

<html>
<head>
    <title>添加用户</title>
</head>
<body>

<div style="height: 30px;">
<span style="float: right;">
    <iframe src="<%=basePath%>template/top.jsp" height="30px" frameborder="0" width="500px" scrolling="no"></iframe>
</span>
</div>

<div style="text-align: center; font-size: 26px;">
    <b> <a href="<%=basePath%>manage/managedev.do">管理设备</a> | 添加设备 | <a href="<%=basePath%>manage/controldev.do">控制设备</a> | 添加用户 | <a href="<%=basePath%>manage/deluser.do">删除用户</a></b>
</div>

<br>

<div style="margin:0 auto; width: 1000px;">
    <br>
    <table width="1000px" style="text-align: center">
        <tr>
            <td>
                用户名：<input type="text" id="username" />
            </td>
        </tr>
        <tr>
            <td>
                密码：<input type="password" id="pwd" />
            </td>
        </tr>
        <tr style="text-align: right">
            <td colspan="2">
                <button onclick="addClick()">添加用户</button>
            </td>
        </tr>
    </table>
</div>

<script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/top.js"></script>
<script type="text/javascript">
    function addClick() {
        //提交按钮点击事件
        var user = {
            userPassword : $("#pwd").val(),
            userAccount : $("#username").val()
        };
        if (user.userAccount.trim().length !== 0 || user.userPassword.trim().length !== 0) {
            //保证有一个不为空
            submit(user);
        } else {
            alert("请填入完整的信息！");
        }

    }

    function submit(user) {
        $.ajax({
            "type": "post",
            "url": "/equipment/manage/add_user.do",
            "contentType": "application/json",
            "data": JSON.stringify(user),
            "dataType": "json",
            "success": function (json) {
                alert(json.msg);
                window.location.href = "managedev.do";
            }
        });
    }

</script>
</body>
</html>
