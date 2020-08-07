<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/7/30
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>密码修改成功</title>
    <script type="text/javascript" src="js/sleep.js"></script>
</head>
<body>
    密码修改成功，正在为你跳转.
    <c:forEach var="i" begin="1" end="5" step="1">
        <script>sleep(500);</script>
        .
    </c:forEach>
    <%
        response.setHeader("refresh","3;url=main.jsp");
    %>
</body>
</html>
