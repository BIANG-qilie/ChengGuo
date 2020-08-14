package com.biang.www.controller;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.biang.www.po.User;
import com.biang.www.service.IUserService;
import com.biang.www.service.impl.UserServiceImpl;
import com.biang.www.util.EmailSender;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author BIANG
 * 不用 BaseServlet 是因为会提前使用迭代器，使指针指空
 */
@WebServlet("/uploadHeadImage")


public class UploadHeadImageServlet extends HttpServlet {
    IUserService userService=new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws  IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session=request.getSession();
        User loginUser=(User)session.getAttribute("loginUser");
        String tip = null;
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                Iterator<FileItem> iter = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request).iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    if (!item.isFormField()){
                        String fileName = item.getName();
                        String ext = fileName.substring(  fileName.indexOf(".")+1 );
                        if(!"jpg".equals(ext)) {
                            System.out.println(4);
                            tip="图片类型有误！格式只能是jpg";
                            session.setAttribute("tip", tip);
                            response.sendRedirect(request.getContextPath()+"/uploadHeadImage.jsp");
                            return ;//终止
                        }
                        String path = "D:\\Gitfile\\ChengGuo\\web\\images";
                        File file = new File(path, fileName);
                        if(userService.changeHeadImage(loginUser,fileName)){
                            if(loginUser.getHeadImage()!=null){
                                File oldFile=new File("D:\\Gitfile\\ChengGuo\\web\\images\\"+loginUser.getHeadImage());
                                oldFile.delete();
                            }
                            loginUser.setHeadImage(loginUser.getUserId()+".jpg");
                            session.setAttribute("loginUser", loginUser);
                            File renameFile=new File("D:\\Gitfile\\ChengGuo\\web\\images\\"+loginUser.getUserId()+".jpg");
                            item.write(file);
                            file.renameTo(renameFile);
                            tip="上传成功！";
                        }else {
                            EmailSender emailSender=new EmailSender();
                            emailSender.errorReport("头像修改数据库出错","数据库修改出错！");
                            return;
                        }
                        session.setAttribute("tip", tip);
                        response.sendRedirect(request.getContextPath()+"/uploadHeadImage.jsp");
                        return;
                    }
                }
                tip="没有选择文件";
                session.setAttribute("tip", tip);
                response.sendRedirect(request.getContextPath()+"/uploadHeadImage.jsp");
                return;
            }else {
                EmailSender emailSender=new EmailSender();
                emailSender.errorReport("头像上传前端出错","isNotMultipart");
                return;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
