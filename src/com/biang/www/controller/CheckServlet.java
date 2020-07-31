package com.biang.www.controller;

import com.biang.www.po.User;
import com.biang.www.service.IUserService;
import com.biang.www.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author dell
 */
@WebServlet("/check")
public class CheckServlet extends HttpServlet {
    IUserService userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        String rememberPassword=request.getParameter("rememberPassword");
        User user=new User();
        user.setUserName(userName);
        user.setPassword(password);
        try {
            if(userService.register(user)!=null) {
                HttpSession session=request.getSession();
                session.setAttribute(String.valueOf(user.getUserId()),user);
                session.setMaxInactiveInterval(300);
                Cookie[] cookies=request.getCookies();
                for(int i =0;i<cookies.length;i++){
                    cookies[i].setMaxAge(0);
                    response.addCookie(cookies[i]);
                }
                response.addCookie(new Cookie("id", String.valueOf(user.getUserId())));
                response.addCookie(new Cookie("userName", user.getUserName()));
                if("remember".equals(rememberPassword)) {
                    response.addCookie(new Cookie("password", user.getPassword()));
                }
                request.getRequestDispatcher("success.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
