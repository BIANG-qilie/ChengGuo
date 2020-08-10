<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/8/10
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发布需求</title>
    <script type="text/javascript" src="js/returnOneStep.js"></script>
</head>
<body>
<form action="demand" method="GET">
    <table align="center" border="0" width="300" height="470" cellspacing="0">
        <tr></tr>
        <tr></tr>
        <tr>
            <th colspan="5">发布需求</th>
        </tr>
        <tr>
            <th colspan="5"></th>
        </tr>
        <tr>
            <th colspan="2"> 标题:</th>
            <th colspan="3"><input type="text" required name="title" value=""/> </th>
        </tr>
        <tr>
            <th colspan="2"> 简介:</th>
            <th colspan="3"><input type="text" required name="introduction" value=""/> </th>
        </tr>
        <tr>
            <th colspan="2"> 需求单位:</th>
            <th colspan="3"><input type="text" required name="demandUnits" value=""/> </th>
        </tr>
        <tr>
            <th colspan="2"> 预算:</th>
            <th colspan="3"><input type="text" required name="budget" value=""/> </th>
        </tr>
        <tr>
            <th colspan="2"> 时间需求:</th>
            <th colspan="3"><input type="text" required name="timeRequirement" value=""/> </th>
        </tr>
        <tr>
            <th colspan="2"> 详细资料:</th>
            <th colspan="3"><textarea name="specificContent" style="width:200px;height:80px;"></textarea></th>
        </tr>
        <tr>
            <th>
                <input type="hidden" name="method"  value="addDemand"/>
            </th>
        </tr>
        <tr>
            <th colspan="5">
                <input type="submit" value="发布需求" style="width: 80px" >
            </th>
        </tr>
    </table>
</form>
<table align="center" border="0" width="300" height="18" cellspacing="0">
    <tr>
        <th> <button onclick="returnOneStep()" style="width: 80px">返回</button></th>
    </tr>
</table>
</body>
</html>
