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
    <script type="text/javascript" src="js/jquery-3.3.1.js" ></script>
    <script type="text/javascript" src="js/checkCode.js"></script>
</head>
<body>
<%
    Cookie[] cookies=request.getCookies();
    String registerUserName="";
    int registerTime=0;
    for(int i=0;i<cookies.length;i++){
        if("registerTime".equals(cookies[i].getName())) {
            registerTime=Integer.parseInt(cookies[i].getValue());
        }
        if("registerUserName".equals(cookies[i].getName())) {
            registerUserName=cookies[i].getValue();
        }
    }
    Cookie registerTimeCookie=new Cookie("registerTime",String.valueOf(registerTime+1));
    registerTimeCookie.setMaxAge(3000);
    response.addCookie(registerTimeCookie);
%>
<form action="user" method="GET">
    <table align="center" border="0" width="300" height="<%=((registerTime>5)?370:320)%>" cellspacing="0">
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
            <th colspan="2"> 邮箱:</th>
            <th colspan="3"><input type="text" required name="email" value=""/> </th>
        </tr>
        <%if(registerTime>5) {
            out.write("<tr>\n" +
                    "            <th colspan=\"5\">\n" +
                    "                验证码：\n" +
                    "                <input type=\"text\" name=\"checkcode\" id=\"checkcodeId\" size=\"4\"  />\n" +
                    "                <!-- 验证码-->\n" +
                    "                <a href=\"javascript:reloadCheckImg();\"> <img src=\"img.jsp\"/></a>\n" +
                    "            </th>\n" +
                    "        </tr>");
        }else{out.write("<tr>\n" +
                "            <th>\n" +
                "                <input type=\"hidden\" name=\"checkcode\" id=\"checkcodeId\" value=\"checkCodePass\"/>\n" +
                "            </th>\n" +
                "        </tr>");

        }
        %>
        <tr>
            <th>
                <input type="hidden" name="method"  value="register"/>
            </th>
        </tr>
        <tr>
            <th colspan="5">
                <input type="submit" value="注册" style="width: 80px" onclick="return checkCode()">
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
