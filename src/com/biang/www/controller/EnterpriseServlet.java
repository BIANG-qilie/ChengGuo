package com.biang.www.controller;

import com.biang.www.po.Demand;
import com.biang.www.po.Enterprise;
import com.biang.www.po.User;
import com.biang.www.service.IDemandService;
import com.biang.www.service.IEnterpriseService;
import com.biang.www.service.impl.DemandServiceImpl;
import com.biang.www.service.impl.EnterpriseServiceImpl;
import com.biang.www.util.CommonUtil;
import com.biang.www.util.EmailSender;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BIANG
 */
@WebServlet("/enterprise")
public class EnterpriseServlet extends BaseServlet {
    IEnterpriseService enterpriseService=new EnterpriseServiceImpl();
    IDemandService demandUserService=new DemandServiceImpl();
    IDemandService demandService=new DemandServiceImpl();
    public void detailEnterprise(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session=request.getSession();
        User loginUser= (User) session.getAttribute("loginUser");
        if(CommonUtil.isLogin(request, response)){
            return;
        }
        Enterprise enterprise=enterpriseService.getEnterpriseByUser(loginUser);
        if(enterprise==null&&loginUser.getLevel()!=User.MANAGER){
            response.sendRedirect(request.getContextPath() + "/user?method=upgrade");
            return;
        }else {
            if(loginUser.getLevel()==User.MANAGER){
                //管理员没有自己企业，直接回到主页
                response.sendRedirect(request.getContextPath() + "/main.jsp");
            }
        }

        List<Demand> allDemands=demandUserService.getDemandByEnterprise(enterprise);
        int pageNumberInDetailEnterprise=Integer.parseInt ((session.getAttribute("pageNumberInDetailEnterprise")!=null)?((String)session.getAttribute("pageNumberInDetailEnterprise")):"1");
        List<Demand> demands=new ArrayList<>() ;
        for(int i = DemandServlet.MAX_NUMBER_OF_MESSAGES*(pageNumberInDetailEnterprise-1); i< Integer.min(DemandServlet.MAX_NUMBER_OF_MESSAGES*pageNumberInDetailEnterprise,allDemands.size()); i++){
            demands.add(allDemands.get(i));
        }
        session.setAttribute("pageNumberInDetailEnterprise",String.valueOf(pageNumberInDetailEnterprise));
        session.setAttribute("enterprise",enterprise);
        session.setAttribute("demands",demands);
        response.sendRedirect(request.getContextPath() + "/detailEnterprise.jsp");
    }
    public void certifyEnterprise(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session=request.getSession();
        User loginUser= (User) session.getAttribute("loginUser");
        if(CommonUtil.isLogin(request, response)){
            return;
        }
        if(loginUser.getLevel()!=User.MANAGER){
            response.sendRedirect(request.getContextPath() + "/main.jsp");
            return;
        }
        List<Enterprise> allEnterprises=enterpriseService.getAllEnterprise();
        int pageNumberInCertifyEnterprise=Integer.parseInt ((session.getAttribute("pageNumberInCertifyEnterprise")!=null)?((String)session.getAttribute("pageNumberInCertifyEnterprise")):"1");
        List<Enterprise> enterprises=new ArrayList<>() ;
        for(int i = DemandServlet.MAX_NUMBER_OF_MESSAGES*(pageNumberInCertifyEnterprise-1); i< Integer.min(DemandServlet.MAX_NUMBER_OF_MESSAGES*pageNumberInCertifyEnterprise,allEnterprises.size()); i++){
            enterprises.add(allEnterprises.get(i));
        }
        session.setAttribute("pageNumberInCertifyEnterprise",String.valueOf(pageNumberInCertifyEnterprise));
        session.setAttribute("enterprises",enterprises);
        response.sendRedirect(request.getContextPath() + "/certifyEnterprise.jsp");
    }
    public void managerEnterprise(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String enterpriseId=request.getParameter("enterpriseId");
        HttpSession session=request.getSession();
        User loginUser= (User) session.getAttribute("loginUser");
        if(CommonUtil.isLogin(request, response)){
            return;
        }
        if(loginUser.getLevel()!=User.MANAGER){
            response.sendRedirect(request.getContextPath() + "/main.jsp");
            return;
        }
        Enterprise enterprise=enterpriseService.getEnterpriseByEnterpriseId(Integer.parseInt(enterpriseId));
        List<Demand> allDemands=demandUserService.getDemandByEnterprise(enterprise);
        int pageNumberInManagerEnterprise=Integer.parseInt ((session.getAttribute("pageNumberInManagerEnterprise")!=null)?((String)session.getAttribute("pageNumberInManagerEnterprise")):"1");
        List<Demand> demands=new ArrayList<>() ;
        for(int i = DemandServlet.MAX_NUMBER_OF_MESSAGES*(pageNumberInManagerEnterprise-1); i< Integer.min(DemandServlet.MAX_NUMBER_OF_MESSAGES*pageNumberInManagerEnterprise,allDemands.size()); i++){
            demands.add(allDemands.get(i));
        }
        session.setAttribute("pageNumberInManagerEnterprise",String.valueOf(pageNumberInManagerEnterprise));
        session.setAttribute("enterprise",enterprise);
        session.setAttribute("demands",demands);
        response.sendRedirect(request.getContextPath() + "/managerEnterprise.jsp");
    }
    public void changeEnterpriseCertification(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session=request.getSession();
        User loginUser= (User) session.getAttribute("loginUser");
        Enterprise enterprise= (Enterprise) session.getAttribute("enterprise");
        if(CommonUtil.isLogin(request, response)){
            return;
        }
        if(loginUser.getLevel()!=User.MANAGER){
            response.sendRedirect(request.getContextPath() + "/main.jsp");
            return;
        }
        if(enterprise==null){
            response.sendRedirect(request.getContextPath() + "/main.jsp");
            return;
        }
        int conditionsOfCertification= Integer.parseInt(request.getParameter("conditionsOfCertification"));
        List<Demand>demands= (List<Demand>) session.getAttribute("demands");
        List<Integer> conditionOfCertificationOfDemand=new ArrayList<>();
        if(demands!=null) {
            for (Demand demand : demands) {
                conditionOfCertificationOfDemand.add(Integer.valueOf(request.getParameter("conditionOfCertification" + demand.getDemandId())));
            }
        }
        if(enterpriseService.changeEnterpriseCertification(enterprise,conditionsOfCertification)) {
            //更改审核成功
            if(demands!=null) {
                for (int i = 0; i < demands.size(); i++) {
                    Demand demand = demands.get(i);
                    if (demandService.changeDemandCertification(demand, conditionOfCertificationOfDemand.get(i))) {
                        //更改需求审核成功
                    } else {
                        //更改需求失败
                        EmailSender emailSender = new EmailSender();
                        emailSender.errorReport("更改需求失败", demand);
                        break;
                    }
                }
            }
            response.sendRedirect(request.getContextPath() + "/enterprise?method=managerEnterprise&enterpriseId="+enterprise.getEnterpriseId());
        }else{
            //更改审核异常
            EmailSender emailSender=new EmailSender();
            emailSender.errorReport("更改审核异常", enterprise);
        }
    }
}
