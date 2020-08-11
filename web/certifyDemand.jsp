<%@ page import="java.util.List" %>
<%@ page import="com.biang.www.po.Demand" %>
<%@ page import="com.biang.www.controller.DemandServlet" %>
<%@ page import="com.biang.www.po.User" %>
<%@ page import="com.biang.www.service.IUserService" %>
<%@ page import="com.biang.www.service.impl.UserServiceImpl" %>
<%@ page import="com.biang.www.util.Certification" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/8/11
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>审核报名需求</title>
    <script type="text/javascript" src="js/returnTo.js"></script>
    <link rel="stylesheet" type="text/css" href="css/detailDemand.css"/>
    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        function pageChange(page){
            if(page<1)
                page=1;
            $.get("demand",
                {
                    method:"pageChange",
                    pageNumberName:"pageNumberInCertifyDemand",
                    pageNumber:page,
                },
                function(){
                    window.location.href="demand?method=certifyDemand";
                }
            );
        }
    </script>
</head>
<body>
<%
    IUserService userService=new UserServiceImpl();
    List<Object[]> conditionsOfApply= (List<Object[]>) session.getAttribute("conditionsOfApply");
    Demand demand= (Demand) session.getAttribute("demand");
    if(demand==null){
        response.sendRedirect(request.getContextPath() + "/main.jsp");
        return;
    }
    int pageNumberInCertifyDemand;
    if(session.getAttribute("pageNumberInCertifyDemand")==null){
        request.getRequestDispatcher("demand?method=certifyDemand").forward(request, response);
        return;
    }
    pageNumberInCertifyDemand=Integer.parseInt((String)session.getAttribute("pageNumberInCertifyDemand"));
%>
<form action="demandUser" method="post">
    <div class="content-buy w1190">
        <div class="c-left fl">
            <div class="goods-box bj-box">
                <div class="bj-top">
                    <div class="btm-dash" id="title"><%=demand.getTitle()%></div>
                    <div class="btm-dash btm-dash1">
                        <div class="fl bj-num">
                            <ul>
                                <li>
                                    <label >预算</label>
                                    <span class="lab_l"><i><%=demand.getBudget()%></i></span>
                                </li>
                                <li>
                                    <label >简介</label>
                                    <span class="lab_l"><%=demand.getIntroduction()%></span>
                                </li>
                                <li>
                                    <label >需求单位</label>
                                    <span class="lab_l"><%=demand.getDemandUnits()%></span>
                                </li>
                                <li>
                                    <label >时间要求</label>
                                    <span class="lab_l"><%=demand.getTimeRequirement()%></span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="goods-box product-inform-box mt10">
                <div class="pro-info-detail">
                    <div id="content" style="display:block" class="pdetail tab_content_event_class">
                        <div class="drop-title">
                            <h4>审核需求</h4>
                            <div style="overflow:hidden;">
                                <table width="868" height="300" >
                                    <tr>
                                        <th>用户名</th>
                                        <th>用户等级</th>
                                        <th>审核情况</th>
                                    </tr>
                                    <%
                                        for(int i = 0; i< DemandServlet.MAX_NUMBER_OF_MESSAGES; i++) {
                                            boolean isEmpty=!(i<conditionsOfApply.size());
                                            if(isEmpty){
                                                out.print("<tr class=\"d-xi-b\">\n" +
                                                        "                                        <th>&nbsp;</th>\n" +
                                                        "                                        <th>&nbsp;</th>\n" +
                                                        "                                        <th>&nbsp;</th>\n" +
                                                        "                                        <th>&nbsp;</th>\n" +
                                                        "                                    </tr>");
                                            }else {
                                               User user=userService.getUserByUserId((Integer) conditionsOfApply.get(i)[1]);
                                                try {
                                                    String level;
                                                    switch (user.getLevel()){
                                                        case User.COMMON_USER:
                                                            level="普通用户";
                                                            break;
                                                        case User.ENTERPRISE_USER:
                                                            level="企业用户";
                                                            break;
                                                        case User.MANAGER:
                                                            level="系统管理员";
                                                            break;
                                                        default:
                                                            level="异常";
                                                            break;
                                                    }
                                                    out.print("<tr class=\"d-xi-b\">\n" +
                                                            "                                        <th>" + user.getUserName() + "</a></th>\n" +
                                                            "                                        <th>" + level+ "</th>\n" +
                                                            "                                        <th>\n" +
                                                            "<select name=\"conditionOfApply"+user.getUserId()+"\" >\n" +
                                                            "    <option value=\""+Certification.CERTIFICATION_AUDITING+"\" "+(((int)conditionsOfApply.get(i)[2]==Certification.CERTIFICATION_AUDITING)?"selected":"")+ ">\n" +
                                                            "           审核中\n" +
                                                            "    </option>\n" +
                                                            "    <option value=\""+Certification.PASS_CERTIFICATION+"\" "+(((int)conditionsOfApply.get(i)[2]==Certification.PASS_CERTIFICATION)?"selected":"")+">\n"+
                                                            "           审核通过\n" +
                                                            "    </option>\n" +
                                                            "    <option value=\""+Certification.NOT_PASS_CERTIFICATION+"\" "+(((int)conditionsOfApply.get(i)[2]==Certification.NOT_PASS_CERTIFICATION)?"selected":"")+">\n" +
                                                            "           审核不通过\n" +
                                                            "    </option>\n" +
                                                            " </select>" +
                                                            "                                       </th>\n" +
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
                                            当前第<%=pageNumberInCertifyDemand%>页
                                        </th>
                                    </tr>
                                </table>
                                <table width="868" height="18" class="d-xi-b">
                                    <tr>
                                        <th>
                                            <a href="javascript:pageChange(<%=pageNumberInCertifyDemand-1%>);">上一页</a>
                                        </th>
                                        <th>
                                            <a href="javascript:pageChange(1);">首页</a>
                                        </th>
                                        <th>
                                            <a href="javascript:pageChange(<%=pageNumberInCertifyDemand+1%>);">下一页</a>
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
                    var urlToDetailDemand="demand?method=detailDemand&demandId=<%=demand.getDemandId()%>";
                </script>
                <input type="hidden" name="method" value="changeConditionOfApply"/>
                <input type="submit" style="width: 80px" value="保存"/>
            </th>
        </tr>
    </table>
</form>
<table align="center" border="0" width="300" height="18" cellspacing="0">
    <tr>
        <th>
            <button onclick="returnTo(urlToDetailDemand)" style="width: 80px">返回</button>
        </th>
    </tr>
</table>
</body>
</html>
