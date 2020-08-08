package com.biang.www.controller;

import com.biang.www.po.User;
import com.biang.www.service.IUserService;
import com.biang.www.service.impl.UserServiceImpl;
import com.biang.www.util.CommonUtil;
import com.biang.www.util.EmailSender;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.GeneralSecurityException;

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
            User loginUser;
            loginUser=userService.login(user);
            if(loginUser!=null) {
                HttpSession session=request.getSession();
                session.setAttribute("loginUser",loginUser);
                session.setMaxInactiveInterval(1800);
                for (Cookie value : cookies) {
                    value.setMaxAge(0);
                    response.addCookie(value);
                }
                response.addCookie(new Cookie("userName", loginUser.getUserName()));
                if(rememberPassword!=null) {
                    response.addCookie(new Cookie("password", loginUser.getPassword()));
                }
                Cookie cookie=new Cookie("JSESSIONID",session.getId());
                cookie.setMaxAge(1800);
                response.addCookie(cookie);
                response.sendRedirect(request.getContextPath() + "/main.jsp");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
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
        String email=request.getParameter("email");
        User user=new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
        try {
            if(userService.isUserNameExist(user)==null) {
                //用户名不重复
                if(userService.isEmailExist(user)==null){
                    //邮箱不重复
                    if(userService.register(user)) {
                        Cookie[] cookies = request.getCookies();
                        for (Cookie cookie : cookies) {
                            if ("registerUserName".equals(cookie.getName())
                                    || "registerTime".equals(cookie.getName())) {
                                cookie.setMaxAge(0);
                                response.addCookie(cookie);
                            }
                        }
                        request.getRequestDispatcher("registerSuccess.jsp").forward(request, response);
                    }else{
                        //未知的异常
                        request.getSession().setAttribute("registerError",user);
                        EmailSender emailSender=new EmailSender();
                        emailSender.ErrorReport("用户注册异常",  user);
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                    }
                }else{//异常 邮箱重复
                    response.addCookie(new Cookie("registerEmail",userName));
                    response.sendRedirect(request.getContextPath() + "/register.jsp");
                }
            }else{//异常 用户名重复
                response.addCookie(new Cookie("registerUserName",userName));
                response.sendRedirect(request.getContextPath() + "/register.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void forgetPassword(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String userName=request.getParameter("userName");
        User user=new User();
        user.setUserName(userName);
        if(userService.isUserNameExist(user)!=null){
            response.addCookie(new Cookie("forgetPasswordUserName",userName));
            response.sendRedirect(request.getContextPath() + "/forgetPasswordEmail.jsp");
        }else{
            response.addCookie(new Cookie("errorForgetPasswordUserName",userName));
            response.sendRedirect(request.getContextPath() + "/forgetPassword.jsp");
        }

    }
    public void forgetPasswordEmail(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Cookie[] cookies=request.getCookies();
        String userName=null;
        for(Cookie cookie:cookies){
            if("forgetPasswordUserName".equals(cookie.getName())){
                userName=cookie.getValue();
            }
        }
        String email=request.getParameter("email");
        User user=new User();
        user.setUserName(userName);
        user.setEmail(email);
        User forgetPasswordUser= null;
        try {
            forgetPasswordUser = userService.verifyEmail(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(forgetPasswordUser!=null){
            String checkCode= CommonUtil.getRandomNum();
            HttpSession session=request.getSession();
            session.setAttribute("CKECKCODE", checkCode);
            session.setAttribute("forgetPasswordUser",forgetPasswordUser);
            //发送邮件
            EmailSender emailSender=new EmailSender();
            emailSender.Initialization(forgetPasswordUser,checkCode);
            try {
                emailSender.send();
            } catch (MessagingException | GeneralSecurityException e) {
                e.printStackTrace();
            }
            try {
                request.getRequestDispatcher("forgetPasswordCheckCode.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }else{
            response.addCookie(new Cookie("errorForgetPasswordEmail",email));
            response.sendRedirect(request.getContextPath() + "/forgetPasswordEmail.jsp");
        }

    }
    public void changePassword(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String newPassword=request.getParameter("newPassword");
        HttpSession session=request.getSession();
        User forgetPasswordUser= (User) session.getAttribute("forgetPasswordUser");
        if(userService.changePassword(forgetPasswordUser,newPassword)){
            //修改成功
            response.sendRedirect(request.getContextPath() + "/changePasswordSuccess.jsp");
        }else {
            //修改失败
            session.setAttribute("changePasswordError",forgetPasswordUser);
            EmailSender emailSender=new EmailSender();
            emailSender.ErrorReport("密码修改异常",  forgetPasswordUser);
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
        }

    }

}
