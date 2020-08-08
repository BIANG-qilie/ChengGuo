<%@ page import="com.biang.www.po.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.biang.www.po.Demand" %>
<%@ page import="com.biang.www.service.IEnterpriseService" %>
<%@ page import="com.biang.www.service.impl.EnterpriseServiceImpl" %>
<%@ page import="com.biang.www.controller.DemandServlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%--
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
    <title>成果交易系统</title>
    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        function pageChange(page){
            if(page<1)
                page=1;
            $.get("demand",
                {
                    method:"pageChange",
                    pageNumber:page ,
                },
                function(){
                window.location.href="demand?method=reloadDemand";
            });
        }
    </script>

</head>
<body>
    <table align="center" border="1" width="800" height="30" cellspacing="0" id="tableEnd">
        <tr></tr>
        <tr>
            <th >
                <font color="blue" face="宋体" size="2">欢迎，
                    <%
                        User loginUser = null;
                        boolean isLogin=session.getAttribute("loginUser")!=null;
                        IEnterpriseService enterpriseService=new EnterpriseServiceImpl();
                        if(isLogin) {
                            loginUser=(User)session.getAttribute("loginUser");
                            out.write(loginUser.getUserName());
                        }else {
                            out.write("游客，<a href=\"index.jsp\">点击这里登录</a>");
                        }
                    %>
                </font>
            </th>
        </tr>
        <tr></tr>
    </table>
    <table align="center" border="1" width="800" height="60" cellspacing="0" id="tableTitle">
        <tr>
            <th colspan="8">
                <font color="blue" face="楷体" size="7">成果交易系统</font>
            </th>
        </tr>
    </table>
    <%
        if(session.getAttribute("pageNumber")==null){
            //载入数据并重新回到该界面
            request.getRequestDispatcher("demand?method=reloadDemand").forward(request, response);
            return;
        }
        int pageNumber= Integer.parseInt((String) session.getAttribute("pageNumber"));
        List<Demand> demands= (List<Demand>) session.getAttribute("demands");
        int sizeOfDemands=(int) session.getAttribute("sizeOfDemands");
    %>
    <table align="center" border="1" width="800" height="180" cellspacing="0" id="table01">
        <tr>
            <th>id</th>
            <th>标题</th>
            <th>简介</th>
            <th>发布企业</th>
        </tr>
        <tr>              <th colspan="8">共找到<%=sizeOfDemands%>条需求</th>          </tr>
        <%
            if(demands!=null) {
                for (int i = 0; i < DemandServlet.MAX_NUMBER_OF_MESSAGES; i++) {
                    Demand demand=null;
                    boolean isEmpty=!(i<demands.size());
                    if(isEmpty) {
                        demand=new Demand();
                    }else{
                        demand=demands.get(i);
                    }
                    String outputString="";
                    if(isEmpty){
                        outputString+="<tr>\n" +
                                "            <th>&nbsp;</th>\n" +
                                "            <th>&nbsp;</th>\n" +
                                "            <th>&nbsp;</th>\n" +
                                "            <th>&nbsp;</th>\n" +
                                "        </tr>";
                    }else {
                        outputString += "<tr>\n" +
                                "            <th>" + demand.getDemandId() + "</th>\n";
                        if (isLogin) {
                            outputString += "            <th><a href=\"demand?method=detailDemand&demandId=" + demand.getDemandId() + "\">" + demand.getTitle() + "</a></th>\n";

                        } else {
                            outputString += "            <th><a href=\"index.jsp\">" + demand.getTitle() + "</a></th>\n";
                        }
                        try {
                            outputString += "            <th>" + demand.getIntroduction() + "</th>\n" +
                                    "            <th>" + enterpriseService.getEnterpriseByEnterpriseId(demand.getEnterpriseId()).getEnterpriseName() + "</th>\n" +
                                    "        </tr>\n";
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        out.write(outputString);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        %>
        <tr></tr>
        <tr></tr>
    </table>
    <table align="center" border="1" width="800" height="30" cellspacing="0" id="otherFunction">
        <tr>
            <th>
                <button onclick="pageChange(<%=(pageNumber-1)%>)">上一页</button>
            </th>
            <th>
                <button onclick="pageChange(<%=(1)%>)">首页</button>
            </th>
            <th>
                <button onclick="pageChange(<%=(pageNumber+1)%>)">下一页</button>
            </th>
        </tr>
    </table>
</body>
</html>
