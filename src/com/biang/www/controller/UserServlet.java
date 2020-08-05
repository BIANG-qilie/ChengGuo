package com.biang.www.controller;

import com.biang.www.po.User;
import com.biang.www.service.IUserService;
import com.biang.www.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author BIANG
 */
@WebServlet("/user")
public class UserServlet extends BaseServlet {
    IUserService userService=new UserServiceImpl();
    public void login(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
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
    public void register(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
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
}
