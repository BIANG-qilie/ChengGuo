package com.biang.www.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CheckCode")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      boolean resultTip = false;
        //获取用户输入验证码
       String checkcodeClient =  request.getParameter("checkcode");
       //真实的验证码值
       String checkcodeServer = (String) request.getSession().getAttribute("CKECKCODE");
       if(checkcodeServer.equals(checkcodeClient)){
           resultTip = true;
       }
       response.setContentType("text/html;charset=UTF-8");
        //输出流
        PrintWriter out = response.getWriter();
        out.write(String.valueOf(resultTip));
        out.flush();
        out.close();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
