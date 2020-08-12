package com.biang.www.util;

import com.biang.www.po.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommonUtil {
    public static String getRandomNum()
    {
        int ran = (int)( Math.random()*9000) +1000 ;
        return String.valueOf(ran) ;
    }
    public static boolean isLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession tempSession = request.getSession();
        User loginUser = (User) tempSession.getAttribute("loginUser");
        if(loginUser==null){
            response.sendRedirect(request.getContextPath()+"/index.jsp");
            return true;
        }else {
            return false;
        }
    }

}
