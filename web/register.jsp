<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/7/30
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <script type="text/javascript"src="js/returnToHome.js"></script>
    <script type="text/javascript">
        function check() {
            var password=document.getElementsByName("password");
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
<%
    Cookie[] cookies=request.getCookies();
    String registerUserName="";
    for(int i=0;i<cookies.length;i++){
        if("registerUserName".equals(cookies[i].getName())) {
            registerUserName=cookies[i].getValue();
        }
    }
%>
<form action="register" method="POST">
    <table align="center" border="0" width="300" height="300" cellspacing="0">
        <tr></tr>
        <tr></tr>
        <tr>
            <th colspan="5">注册</th>
        </tr>
        <tr>
            <th colspan="5">
            <%
            if(!registerUserName.equals("")){
                out.write("用户名已存在");
            }
            %>
            </th>
        </tr>
        <tr>
            <th colspan="2"> 用户名:</th>
            <th colspan="3"><input type="text" required name="userName" value="<%out.write(registerUserName);%>"/> </th>
        </tr>
        <tr>
            <th colspan="2"> 密码:</th>
            <th colspan="3"><input type="password" required name="password" value=""/> </th>
        </tr>
        <tr>
            <th colspan="2"> 再次输入密码:</th>
            <th colspan="3"><input type="password" required name="password" value=""/> </th>
        </tr>
        <tr>
            <th colspan="5">
                <input type="submit" value="注册" style="width: 80px" onclick="return check()">
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
