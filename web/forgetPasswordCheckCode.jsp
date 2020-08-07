<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/8/7
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>忘记密码</title>
    <script type="text/javascript"src="js/returnToHome.js"></script>
    <script type="text/javascript" src="js/jquery-3.3.1.js" ></script>
    <script type="text/javascript" src="js/checkCode.js"></script>
</head>
<body>
<form action="changePassword.jsp" method="GET">
    <table align="center" border="0" width="300" height="300" cellspacing="0">
        <tr></tr>
        <tr></tr>
        <tr>
            <th colspan="5">忘记密码</th>
        </tr>
        <th colspan="5">
            <%
                String userName="用户名异常";
                Cookie[] cookies=request.getCookies();
                for(Cookie cookie:cookies){
                    if("forgetPasswordUserName".equals(cookie.getName())){
                        userName=cookie.getValue();
                    }
                }
            %>
        </th>
        <tr>
            <th colspan="2"> 用户名:</th>
            <th colspan="3"><%=userName%></th>
        </tr>
        <tr>
            <th colspan="5"> 已向您的注册邮箱发送验证码，</th>
        </tr>
        <tr>
            <th colspan="5">
                验证码：
                <input type="text" name="checkcode" id="checkcodeId" size="4"  />
            </th>
        </tr>
        <tr>
            <th colspan="5">
                <input type="submit" value="找回密码" style="width: 80px" onclick="return checkCode()">
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
