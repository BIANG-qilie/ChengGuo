package com.biang.www.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author BIANG
 */
@WebServlet("/checkCode")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean resultTip = false;
        String checkcodeClient =  request.getParameter("checkcode");
        String checkcodeServer = (String) request.getSession().getAttribute("CKECKCODE");
        if(checkcodeServer.equals(checkcodeClient)){
           resultTip = true;
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(String.valueOf(resultTip));
        out.flush();
        out.close();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    }
}
