package com.biang.www.controller;

import com.biang.www.dao.impl.DemandUserDaoImpl;
import com.biang.www.dao.IDemandUserDao;
import com.biang.www.po.User;
import com.biang.www.service.IDemandUserService;
import com.biang.www.service.impl.DemandUserServiceImpl;
import com.biang.www.util.EmailSender;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @author BIANG
 */
@WebServlet("/demandUser")
public class DemandUserServlet extends BaseServlet {
    IDemandUserService demandUserService=new DemandUserServiceImpl();
    public void applyDemand(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String demandId=request.getParameter("demandId");
        HttpSession session=request.getSession();
        User loginUser=(User) session.getAttribute("loginUser");
        if(demandUserService.applyDemand(loginUser,demandId)){
            //报名成功
        }else{
            //报名异常
            EmailSender emailSender=new EmailSender();
            emailSender.ErrorReport("报名异常", loginUser);
        }
        request.getRequestDispatcher("detailDemand.jsp").forward(request, response);
    }
    public void checkApplied(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String demandId=request.getParameter("demandId");
        String loginUserId=request.getParameter("loginUserId");
        System.out.println("1"+demandId);
        System.out.println("2"+loginUserId);
        PrintWriter out=response.getWriter();
        if(demandUserService.isApplied(loginUserId,demandId)){
            out.write("true");
        }else{
            out.write("false");
        }
        out.flush();
        out.close();
    }
}
