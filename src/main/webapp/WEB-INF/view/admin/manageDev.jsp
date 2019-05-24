<%--
  Created by IntelliJ IDEA.
  User: yhl
  Date: 2019/5/10
  Time: 12:01
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
    <title>Update Dev</title>
</head>
<body>

<br>

<div style="height: 30px;">
<span style="float: right;">
    <iframe src="<%=basePath%>template/top.jsp" height="30px" frameborder="0" width="500px" scrolling="no"></iframe>
</span>
</div>

<div style="text-align: center; font-size: 26px;">
    <b>管理设备 | <a href="<%=basePath%>manage/adddev.do">添加设备</a> | <a href="<%=basePath%>manage/controldev.do">控制设备</a> | <a href="<%=basePath%>manage/adduser.do">添加用户</a> | <a href="<%=basePath%>manage/deluser.do">删除用户</a></b>
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
<script type="text/javascript" src="<%=basePath%>js/ajaxSetup.js"></script>
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
                        + json[i].devIp + "</td><td><button id='updateDev' onclick='updateClick(this)' value='" + dMac +
                        "'>修改</button> &nbsp;<button id='delDev' onclick='delClick(this)' value='" + dMac + "'>删除</button ></td></tr>");
                }
            }

        });
    });



    function delClick(obj) {
        var devMac = obj.value;
        console.log("触发删除按钮事件, value=" + devMac);
        $.ajax({
            "type": "post",
            "url" : "/manage/delete_dev.do",
            "data" : "devMac="+devMac,
            "dataType": "json",
            "success":function (json) {
                //在页面中删除此元素
                alert(json.data);
                $("#infor tr[id='"+ devMac +"']").remove();
            }
        });
    }

    function updateClick(obj) {
        var devMac = obj.value;
        console.log("触发更新按钮事件, value=" + devMac);
        window.location.href = "updatedev.do?devMac=" + devMac;
    }
</script>
</body>
</html>
