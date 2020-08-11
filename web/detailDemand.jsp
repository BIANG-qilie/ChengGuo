<%@ page import="com.biang.www.po.Demand" %>
<%@ page import="com.biang.www.po.Enterprise" %>
<%@ page import="com.biang.www.po.User" %>
<%@ page import="com.biang.www.dao.IDemandUserDao" %>
<%@ page import="com.biang.www.service.IDemandUserService" %>
<%@ page import="com.biang.www.service.impl.DemandUserServiceImpl" %>
<%@ page import="com.biang.www.util.Certification" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/8/8
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        IDemandUserService demandUserService=new DemandUserServiceImpl();
        Demand demand= (Demand) session.getAttribute("demand");
        Enterprise enterprise= (Enterprise) session.getAttribute("enterprise");
        User loginUser=(User)session.getAttribute("loginUser");
        if(loginUser == null){
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
        if(demand==null||enterprise==null){
            request.getRequestDispatcher("main.jsp").forward(request, response);
            return;
        }
    %>
    <title><%=demand.getTitle()%>-详情</title>
    <script type="text/javascript" src="js/returnTo.js"></script>
    <script type="text/javascript">
        var isApplied=<%=demandUserService.isApplied(String.valueOf(loginUser.getUserId()), String.valueOf(demand.getDemandId()))%>;
        function checkIsMyEnterprise() {
            if(<%=(loginUser.getUserId()==enterprise.getUserId())%>){
                alert("不能报名自己发布的需求");
                return false;
            }else {
                if(isApplied){
                    alert("您已经报名了");
                    return false;
                }else {
                    alert("报名成功");
                    return true;
                }
            }
        }
    </script>
    <link rel="stylesheet" type="text/css" href="css/detailDemand.css"/>
</head>
<body>
    <form action="demand<%=(loginUser.getUserId()==enterprise.getUserId())?"":"User"%>" method="post">
        <input type="hidden" name="demandId" value="<%=demand.getDemandId()%>"/>
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
                                    <%
                                        if(loginUser.getUserId()==enterprise.getUserId()) {
                                            //自家企业
                                            out.write("                                <li>\n" +
                                                            "                                    <label >需求情况</label>\n" +
                                                            "                                    <span class=\"lab_l\">"+
                                                    "<select name=\"conditionOfDemand\" >\n" +
                                                    "    <option value=\""+Certification.CERTIFICATION_AUDITING+"\" "+((demand.getConditionOfDemand()==Certification.CERTIFICATION_AUDITING)?"selected":"")+ ">\n" +
                                                    "           需求中\n" +
                                                    "    </option>\n" +
                                                    "    <option value=\""+Certification.PASS_CERTIFICATION+"\" "+((demand.getConditionOfDemand()==Certification.PASS_CERTIFICATION)?"selected":"")+">\n"+
                                                    "           需求已满\n" +
                                                    "    </option>\n" +
                                                    "    <option value=\""+Certification.NOT_PASS_CERTIFICATION+"\" "+((demand.getConditionOfDemand()==Certification.NOT_PASS_CERTIFICATION)?"selected":"")+">\n" +
                                                    "           需求已取消\n" +
                                                    "    </option>\n" +
                                                    " </select>\n" +
                                                    "<a href=\"demand?method=certifyDemand\">审核报名需求</a>"+
                                                    "</span>\n" +
                                                    "                                </li>\n");
                                        }else{
                                            //别人的企业
                                            out.write("                                <li>\n" +
                                                    "                                    <label >是否已报名</label>\n" +
                                                    "                                    <span class=\"lab_l\">" +((demandUserService.isApplied(String.valueOf(loginUser.getUserId()), String.valueOf(demand.getDemandId())))?"是":"否")+"</span>\n" +
                                                    "                                </li>\n");
                                        }

                                    %>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="goods-box product-inform-box mt10">
                    <div class="pro-info-detail">
                        <div id="content" style="display:block" class="pdetail tab_content_event_class">
                            <div class="drop-title">
                                <h4>详细说明</h4>
                            <div class="d-xi-b">
                                <div style="overflow:hidden;">
                                    需求内容：<%=demand.getSpecificContent()%>&nbsp;
                                    <div>企业名称：<%=enterprise.getEnterpriseName()%>&nbsp;</div>
                                    <div>企业联系人：<%=enterprise.getContactPerson()%>&nbsp;</div>
                                    <div>企业信息：<%=enterprise.getInformation()%></div><br/>
                                </div>
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
                    <%
                        if(loginUser.getUserId()!=enterprise.getUserId()) {
                            out.write("                <input type=\"hidden\" name=\"method\" value=\"applyDemand\"/>\n" +
                                    "                <input type=\"submit\" onclick=\"return checkIsMyEnterprise()\" style=\"width: 80px\" value=\"报名\"/>\n" );
                        }else{
                            out.write("                <input type=\"hidden\" name=\"method\" value=\"changeConditionOfDemand\"/>\n" +
                                    "                <input type=\"submit\" value=\"保存\" style=\"width: 80px\"/>\n");
                        }
                    %>
                </th>
            </tr>
        </table>
    </form>
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

