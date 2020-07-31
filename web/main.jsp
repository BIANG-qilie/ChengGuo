<%@ page import="com.biang.www.po.User" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/7/31
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成果交易系统</title>
</head>
<body>
    欢迎，
    <%
        Cookie[] cookies=request.getCookies();
        String id="-1";
        for(int i=0;i<cookies.length;i++){
            if("id".equals(cookies[i].getName())) {
                id=cookies[i].getValue();
            }
        }
        User loginUser=null;
        if(id!="-1") {
            loginUser=(User)session.getAttribute(id);
        }
        if(loginUser!=null) {
            out.write(loginUser.getUserName());
        } else {
            out.write("游客，<a href=\"index.jsp\">点击这里登录</a>");
        }
    %>
</body>
</html>
