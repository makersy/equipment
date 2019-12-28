<%--
  Created by IntelliJ IDEA.
  User: yhl
  Date: 2019/5/10
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../static/basePath.jsp" %>

<html>
<head>
    <title>用户注册</title>
    <link rel="stylesheet" type="text/css" href="${path}/css/main.css"/>
</head>
<body>
<div class="regist" style="text-align: center;">
    <br>
    <h2>用户注册</h2>
    <div style="text-align: center">
        <form action="${pageContext.request.contextPath}/user/regist.do" method="post" style="text-align: center;">
            <table style="margin: 0 auto; font-size: larger;">
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
                    <td></td>
                    <td style="float: right;">
                        <button type="submit" value="注册">注册</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

<script type="text/javascript" src="<%=basePath%>/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
    var flag = ${requestScope.flag};
    if( flag == 0 ){
        alert("用户已注册");
    }else if (flag == 1) {
        alert("注册成功！");
        window.location.href = "<% request.getContextPath(); %>/index.jsp";
    }
    <% request.removeAttribute("flag");%>
</script>
</body>
</html>
