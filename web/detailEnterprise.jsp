<%@ page import="com.biang.www.po.Enterprise" %>
<%@ page import="com.biang.www.po.Demand" %>
<%@ page import="java.util.List" %>
<%@ page import="com.biang.www.po.User" %>
<%@ page import="com.biang.www.service.IUserService" %>
<%@ page import="com.biang.www.service.impl.UserServiceImpl" %>
<%@ page import="com.biang.www.util.Certification" %>
<%@ page import="com.biang.www.controller.DemandServlet" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/8/10
  Time: 0:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        IUserService userService=new UserServiceImpl();
        User loginUser=(User)session.getAttribute("loginUser");
        Enterprise enterprise= (Enterprise) session.getAttribute("enterprise");
        List<Demand> demands= (List<Demand>) session.getAttribute("demands");
        String conditionOfCertification;
        switch (enterprise.getConditionsOfCertification()){
            case Certification.NOT_PASS_CERTIFICATION:
                conditionOfCertification="认证未通过";
                break;
            case Certification.CERTIFICATION_AUDITING:
                conditionOfCertification="认证中";
                break;
            case Certification.PASS_CERTIFICATION:
                conditionOfCertification="认证已通过";
                break;
            default:
                conditionOfCertification="认证异常";
                break;
        }
    %>
    <title>企业管理</title>
    <script type="text/javascript" src="js/returnTo.js"></script>
    <link rel="stylesheet" type="text/css" href="css/detailDemand.css"/>
</head>
<body>
<body>
<div class="content-buy w1190">
    <div class="c-left fl">
        <div class="goods-box bj-box">
            <div class="bj-top">
                <div class="btm-dash" id="title"><%=enterprise.getEnterpriseName()%></div>
                <div class="btm-dash btm-dash1">
                    <div class="fl bj-num">
                        <ul>
                            <li>
                                <label >简介</label>
                                <span class="lab_l"><%=enterprise.getInformation()%></span> </li>
                            <li>
                                <label >联系人</label>
                                <span class="lab_l"><%=enterprise.getContactPerson()%></span> </li>
                            <li>
                                <label >认证情况</label>
                                <span class="lab_l"><%=conditionOfCertification%></span> </li>
                            <li>
                                <label >注册用户</label>
                                <span class="lab_l"><%=userService.getUserByUserId(enterprise.getUserId()).getUserName()%></span></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="goods-box product-inform-box mt10">
            <div class="pro-info-detail">
                <div id="content" style="display:block" class="pdetail tab_content_event_class">
                    <div class="drop-title">
                        <h4>发布需求</h4>
                        <div style="overflow:hidden;">
                            <table width="868" height="300" >
                                <tr>
                                    <th>需求名</th>
                                    <th>需求单位</th>
                                    <th>预计</th>
                                    <th>时间要求</th>
                                </tr>
                                <%
                                    for(int i = 0; i< DemandServlet.MAX_NUMBER_OF_MESSAGES; i++) {
                                        boolean isEmpty=!(i<demands.size());
                                        if(isEmpty){
                                            out.print("<tr class=\"d-xi-b\">\n" +
                                                    "                                        <th>&nbsp;</th>\n" +
                                                    "                                        <th>&nbsp;</th>\n" +
                                                    "                                        <th>&nbsp;</th>\n" +
                                                    "                                        <th>&nbsp;</th>\n" +
                                                    "                                    </tr>");
                                        }else {
                                            Demand demand = demands.get(i);
                                            try {
                                                out.print("<tr class=\"d-xi-b\">\n" +
                                                        "                                        <th><a href=\"demand?method=detailDemand&demandId="+demand.getDemandId()+"\">" + demand.getTitle() + "</a></th>\n" +
                                                        "                                        <th>" + demand.getDemandUnits() + "</th>\n" +
                                                        "                                        <th>" + demand.getBudget() + "</th>\n" +
                                                        "                                        <th>" + demand.getTimeRequirement() + "</th>\n" +
                                                        "                                    </tr>");
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                %>
                            </table>
                            <table width="868" height="18" class="d-xi-b">
                                <tr>
                                    <th>
                                        <a href="pageChange()">上一页</a>
                                    </th>
                                    <th>
                                        <a href="pageChange(1)">首页</a>
                                    </th>
                                    <th>
                                        <a href="pageChange()">下一页</a>
                                    </th>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<table align="center" border="0" width="300" height="18" cellspacing="0">
    <tr>
        <th>
            <form action="demand" method="post">
                <input type="hidden" name="method" value="addDemand"/>
                <input  type="submit" style="width: 80px" value="发布需求"/>
            </form>
        </th>
    </tr>
</table>
<table align="center" border="0" width="300" height="18" cellspacing="0">
    <tr>
        <th>
            <script type="text/javascript">
                var url="main.jsp";
            </script>
            <button onclick="returnTo(url)" style="width: 80px">返回</button>
        </th>
    </tr>
</table>
</body>
</body>
</html>