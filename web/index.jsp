<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/7/21
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>成果交易系统</title>
    <script type="text/javascript">
        function login() {
            window.location.href="login.jsp";
        }
    </script>
    <script type="text/javascript">
        function register() {
            window.location.href="register.jsp";
        }
    </script>
</head>
<body>
<%
    Cookie[] cookies=request.getCookies();
    for(Cookie cookie:cookies){
        if("registerUserName".equals(cookie.getName())) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }
%>
<table align="center" border="0" width="300" height="300" cellspacing="0">
    <tr></tr>
    <tr>
        <th>    </th>
        <th><font color="blue" face="楷体" size="6">成果交易平台</font></th>
        <th>    </th>
    </tr>
    <tr>
        <th colspan="4"></th>
    </tr>
    <tr>
        <th>    </th>
        <th><button onclick="login()" style="width: 80px">登录</button></th>
        <th>    </th>
    </tr>
    <tr>
        <th>    </th>
        <th><button onclick="register()" style="width: 80px">注册</button></th>
        <th>    </th>
    </tr>
    <tr></tr>
    <tr></tr>
    <tr></tr>
</table>
</body>
</html>
