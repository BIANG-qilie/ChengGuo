package com.biang.www.controller;

import com.biang.www.po.User;
import com.biang.www.service.IUserService;
import com.biang.www.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author dell
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    IUserService userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        String[] rememberPassword=request.getParameterValues("rememberPassword");
        User user=new User();
        user.setUserName(userName);
        user.setPassword(password);
        Cookie[] cookies=request.getCookies();
        try {
            User loginUser=null;
            loginUser=userService.login(user);
            if(loginUser!=null) {
                HttpSession session=request.getSession();
                session.setAttribute("loginUser",loginUser);
                session.setMaxInactiveInterval(1800);
                for(int i =0;i<cookies.length;i++){
                    cookies[i].setMaxAge(0);
                    response.addCookie(cookies[i]);
                }
                response.addCookie(new Cookie("userName", loginUser.getUserName()));
                if(rememberPassword!=null) {
                    response.addCookie(new Cookie("password", loginUser.getPassword()));
                }
                request.getRequestDispatcher("main.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i =0;i<cookies.length;i++){
            cookies[i].setMaxAge(0);
            response.addCookie(cookies[i]);
        }
        response.addCookie(new Cookie("errorUserName", user.getUserName()));
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
