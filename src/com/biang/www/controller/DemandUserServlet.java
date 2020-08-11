package com.biang.www.controller;

import com.biang.www.po.Enterprise;
import com.biang.www.po.User;
import com.biang.www.service.IDemandUserService;
import com.biang.www.service.impl.DemandUserServiceImpl;
import com.biang.www.util.EmailSender;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

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
            emailSender.errorReport("报名异常", loginUser);
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
    public void changeConditionOfApply(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        HttpSession session=request.getSession();
        Enterprise enterprise= (Enterprise) session.getAttribute("enterprise");
        User loginUser=(User)session.getAttribute("loginUser");
        if(loginUser==null){
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        if(enterprise.getUserId()!=loginUser.getUserId()){
            response.sendRedirect(request.getContextPath() + "/main.jsp");
        }
        List<Object[]> conditionsOfApply= (List<Object[]>) session.getAttribute("conditionsOfApply");
        if(conditionsOfApply!=null){
            for (int i=0;i< conditionsOfApply.size();i++){
                int conditionOfApply= Integer.parseInt(request.getParameter("conditionOfApply"+conditionsOfApply.get(i)[1]));
                if(demandUserService.changeConditionOfApplyByUserIdAndDemandId((int)conditionsOfApply.get(i)[1],(int)conditionsOfApply.get(i)[0],conditionOfApply)){
                    //审核更新成功
                }else {
                    //审核更新失败
                    EmailSender emailSender=new EmailSender();
                    emailSender.errorReport("审核更新失败",conditionOfApply+" "+ conditionsOfApply.get(i)[1] +" "+ conditionsOfApply.get(i)[0]);
                    break;
                }
                response.sendRedirect(request.getContextPath() + "/demand?method=certifyDemand");
            }
        }

    }
}
