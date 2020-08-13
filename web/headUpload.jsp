<%@ page import="com.biang.www.po.User" %>
<%@ page import="com.biang.www.util.CommonUtil" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/8/12
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>更换头像</title>
    <script type="text/javascript" src="js/returnTo.js"></script>
    <script type="text/javascript">
        var urlToMain="user?method=detailUser";
    </script>
</head>
<%
    User loginUser= (User) session.getAttribute("loginUser");
    if(CommonUtil.isLogin(request, response)){
        return;
    }
%>
<body>
    <form action="uploadHeadImage" method="POST" enctype="multipart/form-data">
        <table align="center" border="0" width="300" height="370" cellspacing="0">
            <tr></tr>
            <tr></tr>
            <tr>
                <th colspan="5">更换头像</th>
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
            <%%>
            <tr>
                <th>
                <img src="images/<%=(loginUser.getHeadImage()==null)?"default.jpg":loginUser.getHeadImage()%>" width="100" height="100" />
                </th>
            </tr>
            <tr>
                <th > 上传照片:</th>
            </tr>
            <tr>
                <th > <input type="file"  name="headImage"/> </th>
            </tr>
            <tr>
                <th colspan="5">
                    <input type="submit" value="更改头像" style="width: 80px"/>
                </th>
            </tr>

        </table>
    </form>
    <table align="center" border="0" width="300" height="18" cellspacing="0">
        <tr>
            <th> <button onclick="returnTo(urlToMain)" style="width: 80px">返回</button></th>
        </tr>
    </table>
</body>
</html>
