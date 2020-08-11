<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/8/10
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>升级用户</title>
    <script type="text/javascript" src="js/returnOneStep.js"></script>
</head>
<body>
<form action="user" method="GET">
    <table align="center" border="0" width="300" height="470" cellspacing="0">
        <tr></tr>
        <tr></tr>
        <tr>
            <th colspan="5">企业登记</th>
        </tr>
        <tr>
            <th colspan="5"></th>
        </tr>
        <tr>
            <th colspan="2"> 企业名:</th>
            <th colspan="3"><input type="text" required name="enterpriseName" value=""/> </th>
        </tr>
        <tr>
            <th colspan="2"> 企业信息:</th>
            <th colspan="3"><input type="text" required name="information" value=""/> </th>
        </tr>
        <tr>
            <th colspan="2"> 联系人:</th>
            <th colspan="3"><input type="text" required name="contactPerson" value=""/> </th>
        </tr>
        <tr>
            <th>
                <input type="hidden" name="method"  value="upgrade"/>
            </th>
        </tr>
        <tr>
            <th colspan="5">
                <input type="submit" value="企业登记" style="width: 80px" >
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
