<%--
  Created by IntelliJ IDEA.
  User: yhl
  Date: 2019/5/24
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()
            +":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<br>

<div style="height: 30px;">
<span style="float: right;">
    <iframe src="<%=basePath%>template/top.jsp" height="30px" frameborder="0" width="500px" scrolling="no"></iframe>
</span>
</div>

<div style="text-align: center; font-size: 26px;">
    <b><a href="<%=basePath%>manage/adddev.do">管理设备</a> | <a href="<%=basePath%>manage/adddev.do">添加设备</a> | <a href="<%=basePath%>manage/controldev.do">控制设备</a> | <a href="<%=basePath%>manage/adduser.do">添加用户</a> | 删除用户</b>
</div>

<div style="margin:0 auto; width: 1000px;">
    <br>
    <table width="1000px" style="text-align: center">
        <thead>
        <tr>
            <th>用户id</th>
            <th>用户名</th>
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
            "url" : "/equipment/manage/show_all_user.do",
            "data" : "",
            "dataType" : "json",
            "success" : function(json){
                $("#infor tr").remove();
                for( var i=0; i<json.length; i++ ){
                    var userId = json[i].userId;
                    $("#infor").append("<tr id='" + userId +"'><td>" + json[i].userId + "</td><td>" + json[i].userAccount +
                        "</td><td><button id='delDev' onclick='delClick(this)' value='" + userId + "'>删除</button ></td></tr>");
                }
            }

        });
    });



    function delClick(obj) {
        var userId = obj.value;
        console.log("触发删除按钮事件, value=" + userId);
        $.ajax({
            "type": "post",
            "url" : "/equipment/manage/delete_user.do",
            "data" : "userId="+userId,
            "dataType": "json",
            "success":function (json) {
                //在页面中删除此元素
                alert(json.msg);
                $("#infor tr[id='"+ userId +"']").remove();
            }
        });
    }

</script>
</body>
</html>
