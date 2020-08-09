<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/8/9
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="js/returnOneStep.js"></script>
    <script type="text/javascript" src="js/checkCode.js"></script>
</head>

<body>
<form action="user" method="GET">
    <table align="center" border="0" width="300" height="300" cellspacing="0">
        <tr></tr>
        <tr></tr>
        <tr>
            <th colspan="5">修改密码</th>
        </tr>
        <tr>
            <th colspan="5">
                <%
                    Cookie[] cookies=request.getCookies();
                    for(Cookie cookie:cookies){
                        if("errorPassword".equals(cookie.getName())) {
                            out.print("密码输入错误");
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                        }
                    }
                %>
            </th>
        </tr>
        <tr>
            <th colspan="2"> 当前的密码:</th>
            <th colspan="3"><input type="password" required name="password" value=""/> </th>
        </tr>
        <tr>
            <th>
                <input type="hidden" name="method"  value="checkPassword"/>
            </th>
        </tr>
        <tr>
            <th colspan="5">
                验证码：
                <input type="text" name="checkcode" id="checkcodeId" size="4"  />
                <a href="javascript:reloadCheckImg();"> <img src="img.jsp"/></a>
            </th>
        </tr>
        <tr>
            <th colspan="5">
                <input type="submit" value="修改密码" style="width: 80px" onclick="return checkCode()">
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
