<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/7/31
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册成功</title>
    <script type="text/javascript" src="js/sleep.js"></script>
</head>
<body>
注册成功，正在为你跳转.
<c:forEach var="i" begin="1" end="5" step="1">
    <script>sleep(500);</script>
    .
</c:forEach>
<%
    response.setHeader("refresh","3;url=index.jsp");
%>
</body>
</html>
