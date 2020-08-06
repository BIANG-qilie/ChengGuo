<%@ page import="com.biang.www.util.EmailSender" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>忘记密码</title>
    <script type="text/javascript"src="js/returnToHome.js"></script>
    <script type="text/javascript" src="js/jquery-3.3.1.js" ></script>
    <script type="text/javascript" src="js/checkCode.js"></script>
</head>
<body>
<%--<%
    EmailSender emailSender=new EmailSender();
    emailSender.textInitialization();
    emailSender.send();
%>--%>
<form action="user" method="GET">
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
                }%>
        </th>
        <tr>
            <th colspan="2"> 用户名:</th>
            <th colspan="3"><%=userName%></th>
        </tr>
        <tr>
            <th colspan="2"> 注册时邮箱:</th>
            <th colspan="3"><input type="text" required name="email" value=""/> </th>
        </tr>
        <tr>
            <th colspan="5">
                验证码：
                <input type="text" name="checkcode" id="checkcodeId" size="4"  />
                <!-- 验证码-->
                <a href="javascript:reloadCheckImg();"> <img src="img.jsp"/></a>
            </th>
        </tr>
        <tr>
            <th>
                <input type="hidden" name="method"  value="forgetPasswordEmail"/>
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

