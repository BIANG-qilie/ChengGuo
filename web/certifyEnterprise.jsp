<%@ page import="java.util.Properties" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="com.biang.www.controller.DemandServlet" %>
<%@ page import="java.util.List" %>
<%@ page import="com.biang.www.po.Enterprise" %>
<%@ page import="com.biang.www.service.IEnterpriseService" %>
<%@ page import="com.biang.www.service.impl.EnterpriseServiceImpl" %>
<%@ page import="com.biang.www.service.IUserService" %>
<%@ page import="com.biang.www.service.impl.UserServiceImpl" %>
<%@ page import="com.biang.www.service.IDemandService" %>
<%@ page import="com.biang.www.service.impl.DemandServiceImpl" %>
<%@ page import="com.biang.www.util.Certification" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/8/11
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>企业审核</title>
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
                    pageNumberName:"pageNumberInCertifyEnterprise",
                    pageNumber:page,
                },
                function(){
                    window.location.href="enterprise?method=certifyEnterprise";
                }
            );

        }
    </script>
</head>
<%
    IDemandService demandService=new DemandServiceImpl();
    IUserService userService=new UserServiceImpl();
    InputStream input = null;
    Properties prop = new Properties();
    String propFileName = "version.properties";
    input = getClass().getClassLoader().getResourceAsStream(propFileName);
    String version = null,author = null,report=null;
    if(input!=null){
        prop.load(input);
        version=prop.getProperty("version");
        author=prop.getProperty("author");
        report=prop.getProperty("report");
    }
    List<Enterprise> enterprises= (List<Enterprise>) session.getAttribute("enterprises");

    int pageNumberInCertifyEnterprise;
    if(session.getAttribute("pageNumberInCertifyEnterprise")==null){
        request.getRequestDispatcher("enterprise?method=certifyEnterprise").forward(request, response);
        return;
    }
    pageNumberInCertifyEnterprise=Integer.parseInt((String)session.getAttribute("pageNumberInCertifyEnterprise"));
%>
<body>
    <div class="content-buy w1190">
        <div class="c-left fl">
            <div class="goods-box bj-box">
                <div class="bj-top">
                    <div class="btm-dash" id="title">企业审核</div>
                    <div class="btm-dash btm-dash1">
                        <div class="fl bj-num">
                            <ul>
                                <li>
                                    <label >版本号</label>
                                    <span class="lab_l"><%=version%></span> </li>
                                <li>
                                    <label >制作人</label>
                                    <span class="lab_l"><%=author%></span> </li>
                                <li>
                                    <label >更新报告</label>
                                    <span class="lab_l"><%=report%></span> </li>
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
                                        <th>企业名</th>
                                        <th>企业信息</th>
                                        <th>联系人</th>
                                        <th>需处理信息</th>
                                        <th>注册人</th>
                                    </tr>
                                    <%
                                        for(int i = 0; i< DemandServlet.MAX_NUMBER_OF_MESSAGES; i++) {
                                            boolean isEmpty=!(i<enterprises.size());
                                            if(isEmpty){
                                                out.print("<tr class=\"d-xi-b\">\n" +
                                                        "                                        <th>&nbsp;</th>\n" +
                                                        "                                        <th>&nbsp;</th>\n" +
                                                        "                                        <th>&nbsp;</th>\n" +
                                                        "                                        <th>&nbsp;</th>\n" +
                                                        "                                        <th>&nbsp;</th>\n" +
                                                        "                                    </tr>");
                                            }else {
                                                Enterprise enterprise = enterprises.get(i);


                                                try {
                                                    out.print("<tr class=\"d-xi-b\">\n" +
                                                            "                                        <th><a href=\"enterprise?method=managerEnterprise&enterpriseId="+enterprise.getEnterpriseId()+"\">" + enterprise.getEnterpriseName()+ "</a></th>\n" +
                                                            "                                        <th>" + enterprise.getInformation()+ "</th>\n" +
                                                            "                                        <th>" + enterprise.getContactPerson()+ "</th>\n" +
                                                            "                                        <th>" + ((enterprise.getConditionsOfCertification()== Certification.CERTIFICATION_AUDITING)?"1":(demandService.getNotYetPassedDemandByEnterprise(enterprise)==null)?"0":demandService.getNotYetPassedDemandByEnterprise(enterprise).size() )+ "</th>\n" +
                                                            "                                        <th>" + userService.getUserByUserId(enterprise.getUserId()).getUserName() + "</th>\n" +
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
                                            当前第<%=pageNumberInCertifyEnterprise%>页
                                        </th>
                                    </tr>
                                </table>
                                <table width="868" height="18" class="d-xi-b">
                                    <tr>
                                        <th>
                                            <a href="javascript:pageChange(<%=pageNumberInCertifyEnterprise-1%>);">上一页</a>
                                        </th>
                                        <th>
                                            <a href="javascript:pageChange(1);">首页</a>
                                        </th>
                                        <th>
                                            <a href="javascript:pageChange(<%=pageNumberInCertifyEnterprise+1%>);">下一页</a>
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
                    var urlToMain="main.jsp";
                </script>
                <button onclick="returnTo(urlToMain)" style="width: 80px">返回</button>
            </th>
        </tr>
    </table>
</body>
</html>
