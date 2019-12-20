<%@ page language="java" contentType="text/html;charset=UTF-8" %>

<%@include file="WEB-INF/view/static/basePath.jsp" %>

<html>

<body>

<div style="text-align: center">

    <h2>用户登录 | <a href="<%=basePath%>user/registforward.do">用户注册</a> | <a
            href="<%=basePath%>manage/adminlogin.do">管理员登录</a></h2>
    <br>
    <form action="${pageContext.request.contextPath}/user/login.do" method="post">
        <table style="margin: 0 auto;">
            <tr>
                <td>用户名：</td>
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
                    <input type="submit" value="登录"/>
                </td>
            </tr>
        </table>

    </form>


</div>

<script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.js"></script>
<script type="text/javascript">
    var state = -1;
    state = <%=request.getParameter("state")%>;
    if (state == 0) {
        alert("密码错误");
    } else if (state == 1) {
        alert("不存在此用户");
    } else if (state == 2) {
        alert("用户未登录，或不是管理员");
    }
    <%request.removeAttribute("state");%>

</script>

</body>
</html>