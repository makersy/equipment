<%@ page import="com.makersy.equipment.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: yhl
  Date: 2019/5/12
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../WEB-INF/view/static/basePath.jsp" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>

<div style="height: 40px;">
    <%-- 待完成登入登出功能 -->
    <%--NodeMCU远程控制系统 &nbsp;<span style="font-size:12px">您好，${sessionScope.currentUser.userAccount } | <a href="<%=basePath%>/user/logout.do" id="exit">登出</a></span>--%>
    <span style="padding-left: 50px; font-size: 30px; float: left; height: 40px;"><b>NodeMCU远程控制系统</b></span>

    <span style="float: right; line-height: 40px;">
        您好，${sessionScope.currentUser.userAccount }
    </span>
</div>
</body>
</html>
