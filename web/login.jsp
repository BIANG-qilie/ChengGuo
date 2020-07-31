<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/7/30
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <script type="text/javascript"src="js/returnToHome.js"></script>
</head>
<body>
<%
    Cookie[] cookies=request.getCookies();
    String userName="",password="";
    for(int i=0;i<cookies.length;i++){
        if("userName".equals(cookies[i].getName())) {
            userName=cookies[i].getValue();
        }else if("password".equals(cookies[i].getName())) {
            password=cookies[i].getValue();
        }
    }
%>
<form action="check" method="POST">
    <table align="center" border="0" width="300" height="300" cellspacing="0">
        <tr></tr>
        <tr></tr>
        <tr>
            <th colspan="5">登录</th>
        </tr>
        <tr></tr>
        <tr>
            <th colspan="2"> 用户名:</th>
            <th colspan="3"><input type="text" required name="userName" value="<%out.write(userName);%>"/> </th>
        </tr>
        <tr>
            <th colspan="2"> 密码:</th>
            <th colspan="3"><input type="password" required name="password" value="<%out.write(password);%>"/> </th>
        </tr>
        <tr>
            <th colspan="5">
                <input name="rememberPassword" type="radio" value="remember" />记住密码
            </th>
        </tr>
        <tr>
            <th colspan="5">
                <input type="submit" value="登录" style="width: 80px">
            </th>
        </tr>
    </table>
</form>
<table align="center" border="0" width="300" height="18" cellspacing="0">
    <tr>
        <th> <button onclick="returnToHome()" style="width: 80px">返回</button></th>
    </tr>
</table>
</body>
</html>
