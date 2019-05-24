<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: yhl
  Date: 2019/5/10
  Time: 21:19
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
<h2>更新设备信息</h2>


    <table style="text-align: center" width="800px" id="infor">

        <tbody>
        <tr>
            <td>设备id</td>
        </tr>
        <tr>
            <td>设备MAC地址</td>
        </tr>
        <tr>
            <td>设备ip</td>
        </tr>
        </tbody>

    </table>
    <button onclick="submitClick()">提交更新</button>

<script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/top.js"></script>
<script type="text/javascript">
    $(function () {
        var devMac = null;
        devMac = "<%=request.getParameter("devMac")%>";
        console.log("进入更新页面，value=" + devMac);
        $.ajax({
            "type": "post",
            "url" : "/manage/get_dev_infor.do",
            "data" : {"devMac": devMac},
            "dataType" : "json",
            "success" : function(json){

                $("tbody tr:eq(0)").append("<td>当前值：" + json.devId + "</td><td>更新值：<input type='text' id='devId' ></td>");
                $("tbody tr:eq(1)").append("<td>当前值：" + json.devMac + "</td><td>更新值：<input type='text' id='devMac' ></td>");
                $("tbody tr:eq(2)").append("<td>当前值：" + json.devIp + "</td><td>更新值：<input type='text' id='devIp' ></td>");

            }
        })
    });

    //提交按钮点击事件
    function submitClick() {

        var dev = {
            devId : $("#devId").val(),
            devMac : "<%=request.getParameter("devMac")%>",
            devIp : $("#devIp").val()
        };
        console.log("提交点击事件：mac="+dev.devMac+" id="+dev.devId);
        if (dev.devIp.trim().length !== 0 || dev.devId.trim().length !== 0) {
            //保证有一个不为空
            submit(dev);
        } else {
            alert("只能修改id和ip，请填入要修改字段！");
        }
        //跳转到全部设备页面
        // alert("更新成功");
        // history.back();
    }

    function submit(dev) {
        $.ajax({
            "type": "post",
            "url": "/manage/update_dev.do",
            "contentType": "application/json",
            "data": JSON.stringify(dev),
            "dataType": "json",
            "success": function (json) {
                alert(json.data);
                window.location.href = "managedev.do";
            }
        });
    }
</script>

</body>
</html>
