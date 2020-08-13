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
    <meta charset="UTF-8">
    <title>登录</title>
    <script type="text/javascript"src="js/returnTo.js"></script>
    <script type="text/javascript" src="js/jquery-3.3.1.js" ></script>
    <script type="text/javascript" src="js/checkCode.js"></script>
</head>
<body>
<%
    Cookie[] cookies=request.getCookies();
    String userName="",password="";
    int loginTime=0;
    boolean userNameOrPasswordError=false;
    for(int i=0;i<cookies.length;i++){
        if("loginTime".equals(cookies[i].getName())) {
            loginTime=Integer.parseInt(cookies[i].getValue());
        }
        if("errorUserName".equals(cookies[i].getName())) {
            userName = cookies[i].getValue();
            userNameOrPasswordError=true;
            break;
        }else
        if("userName".equals(cookies[i].getName())) {
            userName=cookies[i].getValue();
        }else
        if("password".equals(cookies[i].getName())) {
            password=cookies[i].getValue();
        }
    }
    Cookie loginTimeCookie=new Cookie("loginTime",String.valueOf(loginTime+1));
    loginTimeCookie.setMaxAge(3000);
    response.addCookie(loginTimeCookie);
%>
<form action="user" method="GET" id="form">
    <table align="center" border="0" width="300" height="<%=((loginTime>5)?370:350)%>" cellspacing="0">
        <tr></tr>
        <tr></tr>
        <tr>
            <th colspan="5">登录</th>
        </tr>
        <tr><th colspan="5">
            <%
                if(userNameOrPasswordError) {
                    out.write("用户名或者密码出错");
                }
            %>
        </th></tr>
        <tr>
            <th colspan="2"> 用户名:</th>
            <th colspan="3"><input type="text" required name="userName" value="<%out.write(userName);%>"/> </th>
        </tr>
        <tr>
            <th colspan="2"> 密码:</th>
            <th colspan="3"><input type="password" required name="password" value="<%out.write(password);%>"/> </th>
        </tr>
        <%
            if(loginTime>5) {
            out.write("<tr>\n" +
                "            <th colspan=\"5\">\n" +
                "                验证码：\n" +
                "                <input type=\"text\" name=\"checkcode\" id=\"checkcodeId\" size=\"4\"  />\n" +
                "                <!-- 验证码-->\n" +
                "                <a href=\"javascript:reloadCheckImg();\"> <img src=\"img.jsp\"/></a>\n" +
                "            </th>\n" +
                "        </tr>");
            }else{
                out.write("<tr>\n" +
                    "            <th>\n" +
                    "                <input type=\"hidden\" name=\"checkcode\" id=\"checkcodeId\" value=\"checkCodePass\"/>\n" +
                    "            </th>\n" +
                    "        </tr>");

            }
        %>
        <tr>
            <th>
                <input type="hidden" name="method"  value="login"/>
            </th>
        </tr>
        <tr>
            <th colspan="1"></th>
            <th colspan="3">
                <input name="rememberPassword" type="checkbox" <%=((password.equals(""))?"":"checked")%> value="remember" />记住密码
            </th>
            <th colspan="1">
                <a href="forgetPassword.jsp">忘记密码</a>
            </th>
        </tr>
        <tr>
            <th colspan="5">
                <input type="submit" value="登录" style="width: 80px" onclick="return checkCode()">
            </th>
        </tr>

    </table>
</form>
<table align="center" border="0" width="300" height="18" cellspacing="0">
    <tr>
        <th> <button onclick="returnTo()" style="width: 80px">返回</button></th>
    </tr>
</table>
</body>
</html>
