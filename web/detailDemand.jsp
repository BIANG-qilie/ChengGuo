<%@ page import="com.biang.www.po.Demand" %>
<%@ page import="com.biang.www.po.Enterprise" %>
<%@ page import="com.biang.www.po.User" %><%--
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
        Demand demand= (Demand) session.getAttribute("demand");
        Enterprise enterprise= (Enterprise) session.getAttribute("enterprise");
        User loginUser=(User)session.getAttribute("loginUser");
        if(demand==null||enterprise==null){
            request.getRequestDispatcher("main.jsp").forward(request, response);
            return;
        }
    %>
    <title><%=demand.getTitle()%>-详情</title>
    <script type="text/javascript" src="js/returnTo.js"></script>
    <script type="text/javascript">
        var isApplied=true;
        var demandId=<%=demand.getDemandId()%>;
        var loginUserId=<%=loginUser.getUserId()%>;

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
    <script type="text/javascript">
        window.onload=function (){
            $.get("demandUser",
                {
                    method:"checkApplied",
                    demandId: demandId,
                    loginUserId:loginUserId
                },
                function(result){
                    isApplied=result;
                    alert(isApplied);
                });
        }

    </script>
    <link rel="stylesheet" type="text/css" href="css/detailDemand.css"/>
</head>
<body>
    <div class="content-buy w1190">
        <div class="c-left fl">
            <div class="goods-box bj-box">
                <div class="bj-top">
                    <div class="btm-dash" id="title"><%=demand.getTitle()%></div>
                    <div class="btm-dash btm-dash1">
                        <div class="fl bj-num">
                            <ul>
                                <li>
                                    <label >预计</label>
                                    <span class="lab_l"><i><%=demand.getBudget()%></i></span> </li>
                                <li>
                                    <label >简介</label>
                                    <span class="lab_l"><%=demand.getIntroduction()%></span> </li>
                                <li>
                                    <label >需求单位</label>
                                    <span class="lab_l"><%=demand.getDemandUnits()%></span> </li>
                                <li>
                                    <label >时间要求</label>
                                    <span class="lab_l"><%=demand.getTimeRequirement()%></span></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="goods-box product-inform-box mt10">
                <div class="pro-info-detail">
                    <div id="content" style="display:block" class="pdetail tab_content_event_class">
                        <div class="dvop-title">
                            <h4>详细说明</h4>
                        <div class="d-xi-b">
                            <div style="overflow:hidden;">
                                需求内容：<%=demand.getSpecificContent()%>&nbsp;
                                <div>企业名称：<%=enterprise.getEnterpriseName()%>&nbsp;</div>
                                <div>企业联系人：<%=enterprise.getContactPerson()%>&nbsp;</div>
                                <div>企业介绍：<%=enterprise.getInformation()%></div><br/>
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
                <form action="demandUser" method="post">
                    <input type="hidden" name="method" value="applyDemand"/>
                    <input type="hidden" name="demandId" value="<%=demand.getDemandId()%>"/>
                    <input  type="submit" onclick="return checkIsMyEnterprise()" style="width: 80px" value="报名"/>
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
</html>
