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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author BIANG
 */
@WebServlet("/enterprise")
public class EnterpriseServlet extends BaseServlet {
    IEnterpriseService enterpriseService=new EnterpriseServiceImpl();
    IDemandService demandUserService=new DemandServiceImpl();
    public void detailEnterprise(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session=request.getSession();
        User loginUser= (User) session.getAttribute("loginUser");
        if(loginUser==null){
            response.sendRedirect(request.getContextPath() + "/index.jsp");
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

        List<Demand> demands=demandUserService.getDemandByEnterprise(enterprise);
        session.setAttribute("enterprise",enterprise);
        session.setAttribute("demands",demands);
        response.sendRedirect(request.getContextPath() + "/detailEnterprise.jsp");
    }
    public void certifyEnterprise(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    }
}
