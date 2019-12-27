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
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${path}/css/main.css"/>
</head>
<body>
<h2>用户注册</h2>
<div>
    <form action="${pageContext.request.contextPath}/user/regist.do">
        <table>
            <tr>
                <td>用户名： </td>
                <td>
                    <input type="text" name="username">
                </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td>
                    <input type="password" name="password">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="注册"/>
                </td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript" src="<%=basePath%>/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
    var flag = ${requestScope.flag};
    if( flag == 0 ){
        alert("注册失败");
    }
    <% request.removeAttribute("flag");%>
</script>
</body>
</html>
