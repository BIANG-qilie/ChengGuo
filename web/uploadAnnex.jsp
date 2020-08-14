<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/8/13
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>附件添加</title>
    <script type="text/javascript" src="js/returnTo.js"></script>
    <script type="text/javascript">
        var urlToMain="user?method=detailUser";
    </script>
</head>
<%
    List<String> annexes= (List<String>) session.getAttribute("annexes");
%>
<body>
<form action="uploadAnnex" method="POST" enctype="multipart/form-data">
    <table align="center" border="0" width="300" height="370" cellspacing="0">
        <tr></tr>
        <tr></tr>
        <tr>
            <th colspan="5">附件上传</th>
        </tr>
        <tr>
            <th>
                <%
                    if(session.getAttribute("tip")!=null){
                        out.write((String) session.getAttribute("tip"));
                        session.removeAttribute("tip");
                    }
                %>
            </th>
        </tr>
        <%
            if(annexes!=null){
                out.write("<tr>\n" +
                        "            <th>\n" +
                        "               已上传的附件\n" +
                        "            </th>\n" +
                        "        </tr>");
                for(int i=0;i<annexes.size();i++){
                    out.write("<tr>\n" +
                            "            <th>\n" +
                            "               "+annexes.get(i)+"\n" +
                            "            </th>\n" +
                            "        </tr>");
                }
            }
        %>
        <tr></tr>
        <tr></tr>
        <tr>
            <th > 上传附件:</th>
        </tr>
        <tr>
            <th > <input type="file"  name="headImage"/> </th>
        </tr>
        <tr>
            <th colspan="5">
                <input type="submit" value="上传附件" style="width: 80px"/>
            </th>
        </tr>
    </table>
</form>

<table align="center" border="0" width="300" height="18" cellspacing="0">
    <tr>
        <th> <button onclick="returnTo(urlToMain)" style="width: 80px">结束上传</button></th>
    </tr>
</table>
</body>
</html>
