<%@ page import="com.biang.www.util.EmailSender" %>
<%@ page import="com.biang.www.po.User" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/8/7
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更改密码</title>
    <script type="text/javascript"src="js/returnToHome.js"></script>
    <script type="text/javascript">
        function check() {
            var password=document.getElementsByName("newPassword");
            if(password[0].value==password[1].value) {
                var patt=new RegExp("^\\w{6,20}$");
                if(patt.test(password[0].value))
                    return true;
                else{
                    alert("密码必须只由下划线，字母，数字组成，长度6~20");
                    return false;
                }
            }
            else {
                alert("两次密码不一致");
                return false;
            }
        }
    </script>
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
                    String userName="用户名异常";
                    Cookie[] cookies=request.getCookies();
                    for(Cookie cookie:cookies){
                        if("forgetPasswordUserName".equals(cookie.getName())){
                            userName=cookie.getValue();
                        }
                    }
                    User errorUser=new User();
                    if(session.getAttribute("changePasswordError")!=null) {
                        out.write("密码修改异常");
                        EmailSender emailSender=new EmailSender();
                        emailSender.ErrorReport("密码修改异常",  errorUser);
                    }
                %>
            </th>
        </tr>
        <tr>
            <th colspan="2"> 用户名:</th>
            <th colspan="3"> <%=userName%></th>
        </tr>
        <tr>
            <th colspan="2"> 密码:</th>
            <th colspan="3"><input type="password" required name="newPassword" value=""/> </th>
        </tr>
        <tr>
            <th colspan="2"> 再次输入密码:</th>
            <th colspan="3"><input type="password" required name="newPassword" value=""/> </th>
        </tr>
        <tr>
            <th>
                <input type="hidden" name="method"  value="changePassword"/>
            </th>
        </tr>
        <tr>
            <th colspan="5">
                <input type="submit" value="更改密码" style="width: 80px" onclick="return check()">
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
