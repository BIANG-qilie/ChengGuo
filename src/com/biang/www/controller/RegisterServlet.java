package com.biang.www.controller;

import com.biang.www.po.User;
import com.biang.www.service.IUserService;
import com.biang.www.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BIANG
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    IUserService userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        User user=new User();
        user.setUserName(userName);
        user.setPassword(password);
        try {
            if(userService.register(user)) {
                //正常注册成功
                Cookie[] cookies=request.getCookies();
                for(Cookie cookie:cookies){
                    if("registerUserName".equals(cookie.getName())
                            ||"registerTime".equals(cookie.getName())){
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
                request.getRequestDispatcher("registerSuccess.jsp").forward(request, response);
                return;
            }else{//异常 用户名重复
                response.addCookie(new Cookie("registerUserName",userName));
                response.sendRedirect(request.getContextPath() + "/register.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
