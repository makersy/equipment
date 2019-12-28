<%--
  Created by IntelliJ IDEA.
  User: yhl
  Date: 2019/12/20
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    /*
     * 根据需要更换basePath注释行，线上环境用第一个，本地环境用第二个
     */
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+"47.101.181.184"+path+"/";
//    String basePath = request.getScheme()+"://"+"localhost:8080"+path+"/";
%>