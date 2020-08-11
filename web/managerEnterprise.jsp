<%@ page import="com.biang.www.po.Demand" %>
<%@ page import="java.util.List" %>
<%@ page import="com.biang.www.po.Enterprise" %>
<%@ page import="com.biang.www.service.IUserService" %>
<%@ page import="com.biang.www.service.impl.UserServiceImpl" %>
<%@ page import="com.biang.www.util.Certification" %>
<%@ page import="com.biang.www.controller.DemandServlet" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/8/11
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        IUserService userService=new UserServiceImpl();
        Enterprise enterprise= (Enterprise) session.getAttribute("enterprise");
        if(enterprise==null){
            response.sendRedirect(request.getContextPath() + "/main.jsp");
            return;
        }
        List<Demand> demands= (List<Demand>) session.getAttribute("demands");
        int pageNumberInManagerEnterprise;
        if(session.getAttribute("pageNumberInManagerEnterprise")==null){
            request.getRequestDispatcher("enterprise?method=managerEnterprise&enterpriseId="+enterprise.getEnterpriseId()).forward(request, response);
            return;
        }
        pageNumberInManagerEnterprise=Integer.parseInt((String)session.getAttribute("pageNumberInManagerEnterprise"));
    %>
    <title><%=enterprise.getEnterpriseName()%>-详情</title>
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
                    pageNumberName:"pageNumberInManagerEnterprise",
                    pageNumber:page,
                },
                function(){
                    window.location.href="enterprise?method=managerEnterprise";
                }
            );
        }
    </script>
</head>
<body>
    <form action="enterprise" method="post">
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
                                        <span class="lab_l">
                                            <select name="conditionsOfCertification" >
                                                  <option value="<%=Certification.CERTIFICATION_AUDITING%>" <%=(enterprise.getConditionsOfCertification()==Certification.CERTIFICATION_AUDITING)?"selected":""%>>
                                                        审核中
                                                  </option>
                                                  <option value="<%=Certification.PASS_CERTIFICATION%>" <%=(enterprise.getConditionsOfCertification()==Certification.PASS_CERTIFICATION)?"selected":""%>>
                                                        审核通过
                                                  </option>
                                                  <option value="<%=Certification.NOT_PASS_CERTIFICATION%>" <%=(enterprise.getConditionsOfCertification()==Certification.NOT_PASS_CERTIFICATION)?"selected":""%>>
                                                        审核不通过
                                                  </option>
                                            </select>
                                        </span> </li>
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
                                            <th>预算</th>
                                            <th>审核情况</th>
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
                                                                "                                        <th>\n" +
                                                                "<select name=\"conditionOfCertification"+demand.getDemandId()+"\" >\n" +
                                                                "    <option value=\""+Certification.CERTIFICATION_AUDITING+"\" "+((demand.getConditionOfCertification()==Certification.CERTIFICATION_AUDITING)?"selected":"")+ ">\n" +
                                                                "           审核中\n" +
                                                                "    </option>\n" +
                                                                "    <option value=\""+Certification.PASS_CERTIFICATION+"\" "+((demand.getConditionOfCertification()==Certification.PASS_CERTIFICATION)?"selected":"")+">\n"+
                                                                "           审核通过\n" +
                                                                "    </option>\n" +
                                                                "    <option value=\""+Certification.NOT_PASS_CERTIFICATION+"\" "+((demand.getConditionOfCertification()==Certification.NOT_PASS_CERTIFICATION)?"selected":"")+">\n" +
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
                                                当前第<%=pageNumberInManagerEnterprise%>页
                                            </th>
                                        </tr>
                                    </table>
                                    <table width="868" height="18" class="d-xi-b">
                                        <tr>
                                            <th>
                                                <a href="javascript:pageChange(<%=pageNumberInManagerEnterprise-1%>);">上一页</a>
                                            </th>
                                            <th>
                                                <a href="javascript:pageChange(1);">首页</a>
                                            </th>
                                            <th>
                                                <a href="javascript:pageChange(<%=pageNumberInManagerEnterprise+1%>);">下一页</a>
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
                        var urlToCertify="enterprise?method=certifyEnterprise";
                    </script>
                    <input type="hidden" name="method" value="changeEnterpriseCertification"/>
                    <input type="submit" style="width: 80px" value="保存"/>
                </th>
            </tr>
        </table>
    </form>
    <table align="center" border="0" width="300" height="18" cellspacing="0">
        <tr>
            <th>
                <button onclick="returnTo(urlToCertify)" style="width: 80px">返回</button>
            </th>
        </tr>
    </table>
</body>
</html>
