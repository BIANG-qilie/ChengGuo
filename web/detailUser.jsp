<%@ page import="com.biang.www.po.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.biang.www.po.Demand" %>
<%@ page import="com.biang.www.service.IEnterpriseService" %>
<%@ page import="com.biang.www.service.impl.EnterpriseServiceImpl" %>
<%@ page import="com.biang.www.controller.DemandServlet" %>
<%@ page import="com.biang.www.util.Certification" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/8/9
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>个人资料</title>
    <%
        IEnterpriseService enterpriseService=new EnterpriseServiceImpl();
        User loginUser= (User) session.getAttribute("loginUser");
        if(loginUser==null){
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        List<Demand> demands= (List<Demand>) session.getAttribute("demands");
    %>
    <link rel="stylesheet" type="text/css" href="css/detailDemand.css"/>
    <script type="text/javascript" src="js/returnTo.js"></script>
</head>
<body>
<div class="content-buy w1190">
    <div class="c-left fl">
        <div class="goods-box bj-box">
            <div class="bj-top">
                <div class="btm-dash" id="title"><%=loginUser.getUserName()%></div>
                <div class="btm-dash btm-dash1">
                    <div class="fl bj-num">
                        <ul>
                            <li>
                                <label >级别</label>
                                <span class="lab_l">
                                    <i>
                                        <%
                                            switch (loginUser.getLevel()){
                                                case User.COMMON_USER:
                                                    out.write("普通用户 <a href=\"user?method=upgrade\">升级用户</a>");
                                                    break;
                                                case User.ENTERPRISE_USER:
                                                    out.write("企业用户");
                                                    break;
                                                case User.MANAGER:
                                                    out.write("系统管理员");
                                                    break;
                                                default:
                                                    out.write("异常");
                                                    break;
                                            }
                                        %>
                                    </i>
                                </span> </li>
                            <li>
                                <label >邮箱</label>
                                <span class="lab_l"><%=loginUser.getEmail()%></span> </li>
                            <li>
                                <label >密码</label>
                                <span class="lab_l"><a href="checkPassword.jsp">修改密码</a></span> </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="goods-box product-inform-box mt10">
            <div class="pro-info-detail">
                <div id="content" style="display:block" >
                    <div class="drop-title">
                        <h4>报名需求</h4>
                            <div style="overflow:hidden;">
                                <table width="868" height="300" >
                                    <tr>
                                        <th>需求名</th>
                                        <th>发布企业</th>
                                        <th>报名情况</th>
                                        <th>需求情况</th>
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
                                                String conditionOfApply,conditionOfDemand;
                                                switch (demand.getConditionOfApply()){
                                                    case Certification.NOT_PASS_CERTIFICATION:
                                                        conditionOfApply="报名未通过";
                                                        break;
                                                    case Certification.CERTIFICATION_AUDITING:
                                                        conditionOfApply="报名审核中";
                                                        break;
                                                    case Certification.PASS_CERTIFICATION:
                                                        conditionOfApply="报名通过";
                                                        break;
                                                    default:
                                                        conditionOfApply="报名异常";
                                                        break;
                                                }
                                                switch (demand.getConditionOfDemand()){
                                                    case Certification.NOT_PASS_CERTIFICATION:
                                                        conditionOfDemand="需求已取消";
                                                        break;
                                                    case Certification.CERTIFICATION_AUDITING:
                                                        conditionOfDemand="需求中";
                                                        break;
                                                    case Certification.PASS_CERTIFICATION:
                                                        conditionOfDemand="需求已满";
                                                        break;
                                                    default:
                                                        conditionOfDemand="需求异常";
                                                        break;
                                                }
                                                try {
                                                    out.print("<tr class=\"d-xi-b\">\n" +
                                                            "                                        <th>" + demand.getTitle() + "</th>\n" +
                                                            "                                        <th>" + enterpriseService.getEnterpriseByEnterpriseId(demand.getEnterpriseId()).getEnterpriseName() + "</th>\n" +
                                                            "                                        <th>" + conditionOfApply + "</th>\n" +
                                                            "                                        <th>" + conditionOfDemand + "</th>\n" +
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
            <script type="text/javascript">
                var url="main.jsp";
            </script>
            <button onclick="returnTo(url)" style="width: 80px">返回</button>
        </th>
    </tr>
</table>
</body>
</html>
