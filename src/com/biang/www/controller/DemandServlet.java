package com.biang.www.controller;

import com.biang.www.dao.IEnterpriseDao;
import com.biang.www.dao.impl.EnterpriseDaoImpl;
import com.biang.www.po.Demand;
import com.biang.www.po.Enterprise;
import com.biang.www.po.User;
import com.biang.www.service.IDemandService;
import com.biang.www.service.IEnterpriseService;
import com.biang.www.service.IUserService;
import com.biang.www.service.impl.DemandServiceImpl;
import com.biang.www.service.impl.EnterpriseServiceImpl;
import com.biang.www.service.impl.UserServiceImpl;
import com.biang.www.util.EmailSender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author dell
 */
@WebServlet("/demand")
public class DemandServlet extends BaseServlet {
    IDemandService demandService=new DemandServiceImpl();
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
        HttpSession session=request.getSession();
        session.setAttribute("pageNumber",pageNumber);
        reloadDemand(request,response);
    }
    public void query(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session=request.getSession();
        User loginUser= (User) session.getAttribute("loginUser");
        List<Demand> allDemands=new ArrayList<>() ;
        String queryContent= request.getParameter("queryContent");
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
}
