package com.biang.www.controller;

import com.biang.www.po.Demand;
import com.biang.www.po.Enterprise;
import com.biang.www.po.User;
import com.biang.www.service.IAnnexService;
import com.biang.www.service.IDemandService;
import com.biang.www.service.IDemandUserService;
import com.biang.www.service.IEnterpriseService;
import com.biang.www.service.impl.AnnexServiceImpl;
import com.biang.www.service.impl.DemandServiceImpl;
import com.biang.www.service.impl.DemandUserServiceImpl;
import com.biang.www.service.impl.EnterpriseServiceImpl;
import com.biang.www.util.CommonUtil;
import com.biang.www.util.EmailSender;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author BIANG
 */
@WebServlet("/demand")
public class DemandServlet extends BaseServlet {
    IDemandService demandService=new DemandServiceImpl();
    IDemandUserService demandUserService=new DemandUserServiceImpl();
    IEnterpriseService enterpriseService=new EnterpriseServiceImpl();
    IAnnexService annexService=new AnnexServiceImpl();
    public static final int MAX_NUMBER_OF_MESSAGES=5;
    public void reloadDemand(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session=request.getSession();
        User loginUser= (User) session.getAttribute("loginUser");
        int pageNumber=Integer.parseInt ((session.getAttribute("pageNumber")!=null)?((String)session.getAttribute("pageNumber")):"1");
        List<Demand> demands=new ArrayList<>() ;
        switch ((loginUser==null)?1:loginUser.getLevel()) {
            case User.MANAGER:
                //看得到所有需求
                demands.addAll(demandService.getPagingDemandFromAllDemand(pageNumber));
                break;
            case User.ENTERPRISE_USER:
                //看得到自己企业未通过以及审核中的需求
                demands.addAll(demandService.getPagingDemandByEnterpriseUser(loginUser,pageNumber));
                break;
            case User.COMMON_USER:
                //看得到所有通过的需求
                demands.addAll(demandService.getPassCertificationDemand(pageNumber));
                break;
            default:
                break;
        }
        session.setAttribute("demands",demands);
        session.setAttribute("pageNumber",String.valueOf(pageNumber));
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }
    public void detailDemand(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        HttpSession session=request.getSession();
        if(CommonUtil.isLogin(request, response)){
            return;
        }
        int demandId=Integer.parseInt(request.getParameter("demandId"));
        Demand demand=demandService.getDemandByDemandId(demandId);
        Enterprise enterprise=enterpriseService.getEnterpriseByEnterpriseId(demand.getEnterpriseId());
        List<Object[]> annexes=annexService.getAnnexesByDemandId(demand.getDemandId());
        session.setAttribute("annexes",annexes);
        session.setAttribute("demand",demand);
        session.setAttribute("enterprise",enterprise);
        request.getRequestDispatcher("detailDemand.jsp").forward(request, response);
    }
    public void pageChange(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String pageNumber =  request.getParameter("pageNumber");
        String pageNumberName = request.getParameter("pageNumberName");
        HttpSession session=request.getSession();
        session.setAttribute(pageNumberName,pageNumber);
        reloadDemand(request,response);
    }
    public void query(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String queryContent= request.getParameter("queryContent");
        if("".equals(queryContent.trim())){
            reloadDemand(request, response);
            return;
        }
        HttpSession session=request.getSession();
        User loginUser= (User) session.getAttribute("loginUser");
        if(CommonUtil.isLogin(request, response)){
            return;
        }
        List<Demand> demands=new ArrayList<>() ;
        int pageNumber=Integer.parseInt ((session.getAttribute("pageNumber")!=null)?((String)session.getAttribute("pageNumber")):"1");
        switch (loginUser.getLevel()) {
            case User.MANAGER:
                //看得到所有需求
                demands.addAll(demandService.queryFromAllDemand(queryContent,pageNumber));
                break;
            case User.ENTERPRISE_USER:
                //看得到自己企业未通过以及审核中的需求
                demands.addAll(demandService.queryFromEnterpriseUser(loginUser,queryContent,pageNumber));
                break;
            case User.COMMON_USER:
                //看得到所有通过的需求
                demands.addAll(demandService.queryFromPassCertificationDemand(queryContent,pageNumber));
                break;
            default:
                break;
        }
        session.setAttribute("demands",demands);
        session.setAttribute("pageNumber",String.valueOf(pageNumber));
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }
    public void addDemand(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String title=request.getParameter("title");
        String introduction=request.getParameter("introduction");
        String specificContent=request.getParameter("specificContent");
        String demandUnits=request.getParameter("demandUnits");
        String budget=request.getParameter("budget");
        String timeRequirement=request.getParameter("timeRequirement");
        boolean isHaveAnnex= Boolean.parseBoolean(request.getParameter("isHaveAnnex"));
        HttpSession session=request.getSession();
        session.removeAttribute("pageNumber");
        User loginUser= (User) session.getAttribute("loginUser");
        if(CommonUtil.isLogin(request, response)){
            return;
        }
        Enterprise enterprise= (Enterprise) session.getAttribute("enterprise");
        if(enterprise.getUserId()!=loginUser.getUserId()){
            response.sendRedirect(request.getContextPath()+"/main.jsp");
            return;
        }
        Demand demand=new Demand();
        demand.setTitle(title);
        demand.setIntroduction(introduction);
        demand.setSpecificContent(specificContent);
        demand.setDemandUnits(demandUnits);
        demand.setBudget(budget);
        demand.setTimeRequirement(timeRequirement);
        demand.setEnterpriseId(enterprise.getEnterpriseId());
        Demand insertDemand=null;
        if((insertDemand=demandService.addDemand(demand))!=null){
            //发布成功
            if(isHaveAnnex){
                session.setAttribute("newDemand", insertDemand);
                response.sendRedirect(request.getContextPath() + "/uploadAnnex.jsp");
            }else {
                response.sendRedirect(request.getContextPath() + "/enterprise?method=detailEnterprise");
            }
        }else{
            //发布失败
            EmailSender emailSender=new EmailSender();
            emailSender.errorReport("发布需求失败", demand);
        }
    }
    public void changeConditionOfDemand(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        int conditionOfDemand= Integer.parseInt(request.getParameter("conditionOfDemand"));
        int demandId=Integer.parseInt(request.getParameter("demandId"));
        HttpSession session=request.getSession();
        User loginUser= (User) session.getAttribute("loginUser");
        if(CommonUtil.isLogin(request, response)){
            return;
        }
        Enterprise enterprise= (Enterprise) session.getAttribute("enterprise");
        if(enterprise.getUserId()!=loginUser.getUserId()){
            response.sendRedirect(request.getContextPath() + "/main.jsp");
            return;
        }
        if(demandService.changeDemandConditionOfDemand(demandId,conditionOfDemand)){
            //改变需求状态成功
            response.sendRedirect(request.getContextPath() + "/demand?method=detailDemand&demandId="+demandId);
        }else{
            //改变需求状态异常
            EmailSender emailSender=new EmailSender();
            emailSender.errorReport("改变需求状态异常",demandId);
        }
    }
    public void certifyDemand(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        HttpSession session=request.getSession();
        Demand demand= (Demand) session.getAttribute("demand");
        User loginUser=(User)session.getAttribute("loginUser");
        if(CommonUtil.isLogin(request, response)){
            return;
        }
        Enterprise enterprise= (Enterprise) session.getAttribute("enterprise");
        if(enterprise.getUserId()!=loginUser.getUserId()){
            response.sendRedirect(request.getContextPath() + "/main.jsp");
            return;
        }
        List<Object[]> allConditionsOfApply= demandUserService.getConditionOfApplyByDemand(demand);
        int pageNumberInCertifyDemand;
        if(session.getAttribute("pageNumberInCertifyDemand")!=null) {
            pageNumberInCertifyDemand = Integer.parseInt((String) session.getAttribute("pageNumberInCertifyDemand"));
        }else{
            pageNumberInCertifyDemand=1;
        }
        List<Object[]> conditionsOfApply=new ArrayList<>() ;
        if(allConditionsOfApply!=null) {
            for (int i = DemandServlet.MAX_NUMBER_OF_MESSAGES * (pageNumberInCertifyDemand - 1); i < Integer.min(DemandServlet.MAX_NUMBER_OF_MESSAGES * pageNumberInCertifyDemand, allConditionsOfApply.size()); i++) {
                conditionsOfApply.add(allConditionsOfApply.get(i));
            }
        }
        session.setAttribute("conditionsOfApply", conditionsOfApply);
        session.setAttribute("pageNumberInCertifyDemand",String.valueOf(pageNumberInCertifyDemand));
        response.sendRedirect(request.getContextPath() + "/certifyDemand.jsp");
    }
}
