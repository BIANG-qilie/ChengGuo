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
    <meta charset="UTF-8">
    <script type='text/javascript'>
        function selectSubject(){
            var selectObj=document.getElementsByName("subjectSelect");
            if(selectObj[0].value==0)
                return false;
        }
    </script>
    <script type='text/javascript'>
        function editSubject(){
            window.location.href="editSubject";
        }
    </script>
    <script type="text/javascript">
        function manage() {
            window.location.href="managerSubject";
        }
    </script>
    <script type='text/javascript'>
        function faculty() {
            window.location.href="facultyManage";
        }
    </script>
    <title>成果交易系统</title>
</head>
<body>
    欢迎，
    <%
        User loginUser;
        if(session.getAttribute("loginUser")!=null) {
            loginUser=(User)session.getAttribute("loginUser");
            out.write(loginUser.getUserName());
        }else {
            out.write("游客，<a href=\"index.jsp\">点击这里登录</a>");
        }
    %>
    <table align="center" border="1" width="800" height="60" cellspacing="0" id="tableTitle">
        <tr>
            <th colspan="8">
                <font color="blue" face="楷体" size="7">选课系统</font>
            </th>
        </tr>
    </table>
    <table align="center" border="1" width="800" height="180" cellspacing="0" id="table01">
        <tr>
            <th>课程id</th>
            <th>课程名</th>
            <th>上课日</th>
            <th>上课时间</th>
            <th>下课时间</th>
            <th>开始选课时间</th>
            <th>结束选课时间</th>
            <th>选课</th>
        </tr>
        <tr>              <th colspan="8">共找到3条课程</th>          </tr>
        <tr>
            <td align="center">1</td>
            <td align="center">高等数学</td>
            <td align="center">星期一</td>
            <td align="center">14:40:00</td>
            <td align="center">16:15:00</td>
            <td align="center">2020-05-25 00:00:00</td>
            <td align="center">2020-06-25 23:59:59</td>
            <th>已选 </th>
        </tr>
        <tr>
            <td align="center">2</td>
            <td align="center">大学物理实验</td>
            <td align="center">星期二</td>
            <td align="center">14:00:00</td>
            <td align="center">16:15:00</td>
            <td align="center">2020-05-24 00:00:00</td>
            <td align="center">2020-05-31 18:30:00</td>
            <th>已选 </th>
        </tr>
        <tr>
            <td align="center">14</td>
            <td align="center">大学英语</td>
            <td align="center">星期一</td>
            <td align="center">20:11:32</td>
            <td align="center">20:11:32</td>
            <td align="center">2020-06-05 20:11:32</td>
            <td align="center">2020-06-09 20:11:32</td>
            <th>已选 </th>
        </tr>
        <tr>
            <th></th>
            <th>
                <button onclick="manage()">
                    管理课程
                </button>
            </th>
            <form action="chooseSubject" method="get">
                <th colspan="2">
                    <select name="subjectSelect">
                        <option value="0">
                            --选择课程--
                        </option>
                        <option value="1">
                            高等数学
                        </option>
                        <option value="2">
                            大学物理实验
                        </option>
                        <option value="14">
                            大学英语
                        </option>
                    </select>
                </th>
                <th></th>
                <th>                       <input type="submit" value="选课" onclick="return selectSubject()"/>                   </th>
                <th>                       <input type="button" value="编辑已选课程" onclick="return editSubject()"/>
                </th>
                <th></th>
            </form>          </tr>
    </table>
    <table align="center" border="1" width="800" height="30" cellspacing="0" id="tableEnd">
        <tr></tr>
        <tr>
            <th >
                <font color="blue" face="宋体" size="2">ps:每个学生只可以选择3门课</font>
            </th>
        </tr>
        <tr></tr>
    </table>
    <table align="center" border="1" width="800" height="30" cellspacing="0" id="otherFunction">
        <tr>
            <th>
                <button onclick="manager()">管理员管理</button>
                <script type='text/javascript'>
                    function manager() {
                        window.location.href="managerManage";
                    }
                </script>
            </th>
            <th>
                <button onclick="faculty()">学 院 管 理</button>
            </th>
            <th>
                <button onclick="user()">学 生 管 理</button>
                <script type='text/javascript'>
                    function user() {
                        window.location.href="userManage";
                    }
                </script>        </th>
        </tr>
    </table>
</body>
</html>
