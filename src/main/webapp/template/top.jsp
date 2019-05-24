<%@ page import="com.makersy.equipment.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: yhl
  Date: 2019/5/12
  Time: 11:25
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
    <title>Title</title>
</head>
<body>

<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<span style="text-align: right; float: right;">

    NodeMCU远程控制系统 &nbsp;<span style="font-size:12px">您好，${sessionScope.currentUser.userAccount } <a href="<%=basePath%>/user/logout.do" id="exit">登出</a></span>

</span>
</body>
</html>
