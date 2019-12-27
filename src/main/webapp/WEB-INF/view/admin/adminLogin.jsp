<%--
  Created by IntelliJ IDEA.
  User: yhl
  Date: 2019/5/10
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../static/basePath.jsp" %>
<html>
<head>
    <title>管理员登录</title>
    <link rel="stylesheet" type="text/css" href="${path}/css/main.css"/>
</head>
<body>

<br>


<div style="text-align: center; font-size: 26px;">
    <b>管理员登录</b>
</div>

<br>

<div>
    <form action="${pageContext.request.contextPath}/manage/login.do" method="post">
        <table style="text-align: center; margin: 0 auto;">
            <tr>
                <td>用户名： </td>
                <td>
                    <input type="text" name="username"/>
                </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td>
                    <input type="password" name="password"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="登录" />
                </td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript" src="<%=basePath%>/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
    var state = -1;
    state = <%=request.getParameter("state")%>;
    if( state == 0 ){
        alert("密码错误");
    } else if (state == 1) {
        alert("不存在此用户");
    } else if(state == 2){
        alert("不是管理员");
    }
    state = -1;
    <% request.removeAttribute("state"); %>
</script>
</body>
</html>
