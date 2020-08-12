package com.biang.www.controller;

import com.biang.www.po.Enterprise;
import com.biang.www.po.User;
import com.biang.www.service.IDemandUserService;
import com.biang.www.service.impl.DemandUserServiceImpl;
import com.biang.www.util.CommonUtil;
import com.biang.www.util.EmailSender;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        if(CommonUtil.isLogin(request, response)){
            return;
        }
        if(demandUserService.applyDemand(loginUser,demandId)){
            //报名成功
        }else{
            //报名异常
            EmailSender emailSender=new EmailSender();
            emailSender.errorReport("报名异常", loginUser);
        }
        request.getRequestDispatcher("detailDemand.jsp").forward(request, response);
    }

    public void changeConditionOfApply(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        HttpSession session=request.getSession();
        User loginUser=(User)session.getAttribute("loginUser");
        if(CommonUtil.isLogin(request, response)){
            return;
        }
        Enterprise enterprise= (Enterprise) session.getAttribute("enterprise");
        if(enterprise.getUserId()!=loginUser.getUserId()){
            response.sendRedirect(request.getContextPath() + "/main.jsp");
            return;
        }
        List<Object[]> conditionsOfApply= (List<Object[]>) session.getAttribute("conditionsOfApply");
        if(conditionsOfApply!=null){
            for (Object[] objects : conditionsOfApply) {
                int conditionOfApply = Integer.parseInt(request.getParameter("conditionOfApply" + objects[1]));
                if (demandUserService.changeConditionOfApplyByUserIdAndDemandId((int) objects[1], (int) objects[0], conditionOfApply)) {
                    //审核更新成功
                } else {
                    //审核更新失败
                    EmailSender emailSender = new EmailSender();
                    emailSender.errorReport("审核更新失败", conditionOfApply + " " + objects[1] + " " + objects[0]);
                    break;
                }
                response.sendRedirect(request.getContextPath() + "/demand?method=certifyDemand");
            }
        }

    }
}
