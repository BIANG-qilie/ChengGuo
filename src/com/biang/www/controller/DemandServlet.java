package com.biang.www.controller;

import com.biang.www.po.Demand;
import com.biang.www.po.Enterprise;
import com.biang.www.po.User;
import com.biang.www.service.IDemandService;
import com.biang.www.service.IDemandUserService;
import com.biang.www.service.IEnterpriseService;
import com.biang.www.service.impl.DemandServiceImpl;
import com.biang.www.service.impl.DemandUserServiceImpl;
import com.biang.www.service.impl.EnterpriseServiceImpl;
import com.biang.www.util.EmailSender;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author dell
 */
@WebServlet("/demand")
public class DemandServlet extends BaseServlet {
    IDemandService demandService=new DemandServiceImpl();
    IDemandUserService demandUserService=new DemandUserServiceImpl();
    IEnterpriseService enterpriseService=new EnterpriseServiceImpl();
    public static final int MAX_NUMBER_OF_MESSAGES=5;
    public void reloadDemand(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session=request.getSession();
        User loginUser= (User) session.getAttribute("loginUser");
        List<Demand> allDemands=new ArrayList<>() ;
        switch ((loginUser==null)?1:loginUser.getLevel()) {
            case User.MANAGER:
                //看得到所有需求
                allDemands.addAll(demandService.getAllDemand());
                break;
            case User.ENTERPRISE_USER:
                //看得到自己企业未通过以及审核中的需求
                allDemands.addAll(demandService.getDemandByEnterpriseUser(loginUser));
                break;
            case User.COMMON_USER:
                //看得到所有通过的需求
                allDemands.addAll(demandService.getPassCertificationDemand());
                break;
            default:
                break;
        }
        int pageNumber=Integer.parseInt ((session.getAttribute("pageNumber")!=null)?((String)session.getAttribute("pageNumber")):"1");
        List<Demand> demands=new ArrayList<>() ;
        for(int i = MAX_NUMBER_OF_MESSAGES*(pageNumber-1); i< Integer.min(MAX_NUMBER_OF_MESSAGES*pageNumber,allDemands.size()); i++){
            demands.add(allDemands.get(i));
        }
        session.setAttribute("demands",demands);
        session.setAttribute("sizeOfDemands",allDemands.size());
        session.setAttribute("pageNumber",String.valueOf(pageNumber));
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }
    public void detailDemand(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        HttpSession session=request.getSession();
        int demandId=Integer.parseInt(request.getParameter("demandId"));
        Demand demand=demandService.getDemandByDemandId(demandId);
        Enterprise enterprise=enterpriseService.getEnterpriseByEnterpriseId(demand.getEnterpriseId());
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
        if("".equals(queryContent)){
            reloadDemand(request, response);
            return;
        }
        HttpSession session=request.getSession();
        User loginUser= (User) session.getAttribute("loginUser");
        List<Demand> allDemands=new ArrayList<>() ;
        switch ((loginUser==null)?-10086:loginUser.getLevel()) {
            case User.MANAGER:
                //看得到所有需求
                allDemands.addAll(demandService.queryFromAllDemand(queryContent));
                break;
            case User.ENTERPRISE_USER:
                //看得到自己企业未通过以及审核中的需求
                allDemands.addAll(demandService.queryFromEnterpriseUser(loginUser,queryContent));
                break;
            case User.COMMON_USER:
                //看得到所有通过的需求
                allDemands.addAll(demandService.queryFromPassCertificationDemand(queryContent));
                break;
            default:
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
        }
        int pageNumber=Integer.parseInt ((session.getAttribute("pageNumber")!=null)?((String)session.getAttribute("pageNumber")):"1");
        List<Demand> demands=new ArrayList<>() ;
        for(int i = MAX_NUMBER_OF_MESSAGES*(pageNumber-1); i< Integer.min(MAX_NUMBER_OF_MESSAGES*pageNumber,allDemands.size()); i++){
            demands.add(allDemands.get(i));
        }
        session.setAttribute("demands",demands);
        session.setAttribute("sizeOfDemands",allDemands.size());
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
        HttpSession session=request.getSession();
        session.removeAttribute("pageNumber");
        Enterprise enterprise= (Enterprise) session.getAttribute("enterprise");
        Demand demand=new Demand();
        demand.setTitle(title);
        demand.setIntroduction(introduction);
        demand.setSpecificContent(specificContent);
        demand.setDemandUnits(demandUnits);
        demand.setBudget(budget);
        demand.setTimeRequirement(timeRequirement);
        demand.setEnterpriseId(enterprise.getEnterpriseId());
        if(demandService.addDemand(demand)){
            //发布成功
            response.sendRedirect(request.getContextPath() + "/enterprise?method=detailEnterprise");
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
        Enterprise enterprise= (Enterprise) session.getAttribute("enterprise");
        User loginUser=(User)session.getAttribute("loginUser");
        if(loginUser==null){
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        if(enterprise.getUserId()!=loginUser.getUserId()){
            response.sendRedirect(request.getContextPath() + "/main.jsp");
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
        Enterprise enterprise= (Enterprise) session.getAttribute("enterprise");
        User loginUser=(User)session.getAttribute("loginUser");
        if(loginUser==null){
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        if(enterprise.getUserId()!=loginUser.getUserId()){
            response.sendRedirect(request.getContextPath() + "/main.jsp");
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
            if (allConditionsOfApply != null) {
                for (int i = DemandServlet.MAX_NUMBER_OF_MESSAGES * (pageNumberInCertifyDemand - 1); i < Integer.min(DemandServlet.MAX_NUMBER_OF_MESSAGES * pageNumberInCertifyDemand, allConditionsOfApply.size()); i++) {
                    conditionsOfApply.add(allConditionsOfApply.get(i));
                }
            }
        }
        session.setAttribute("conditionsOfApply", conditionsOfApply);
        session.setAttribute("pageNumberInCertifyDemand",String.valueOf(pageNumberInCertifyDemand));
        response.sendRedirect(request.getContextPath() + "/certifyDemand.jsp");
    }
}
