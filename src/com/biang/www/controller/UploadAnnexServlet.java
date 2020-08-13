package com.biang.www.controller;

import com.biang.www.po.Demand;
import com.biang.www.po.User;
import com.biang.www.service.IAnnexService;
import com.biang.www.service.IUserService;
import com.biang.www.service.impl.AnnexServiceImpl;
import com.biang.www.service.impl.UserServiceImpl;
import com.biang.www.util.EmailSender;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet("/uploadAnnex")

//不用 BaseServlet 是因为会提前使用迭代器，使指针指空
public class UploadAnnexServlet extends HttpServlet {
    IAnnexService annexService=new AnnexServiceImpl();
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session=request.getSession();
        List<String> annexes= (List<String>) session.getAttribute("annexes");
        if(annexes==null){
            annexes=new ArrayList<>();
        }
        User loginUser=(User)session.getAttribute("loginUser");
        Demand newDemand=(Demand) session.getAttribute("newDemand");
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
                        if(!"txt".equals(ext)) {
                            System.out.println(4);
                            tip="附件类型有误！格式只能是 txt";
                            session.setAttribute("tip", tip);
                            response.sendRedirect(request.getContextPath()+"/uploadAnnex.jsp");
                            return ;//终止
                        }
                        String path = "D:\\Gitfile\\ChengGuo\\web\\annexes";
                        File file = new File(path, newDemand.getDemandId()+" "+fileName);
                        if(annexService.addAnnex(newDemand.getDemandId(),fileName)){
                            item.write(file);
                            annexes.add(newDemand.getDemandId()+" "+fileName);
                            session.setAttribute("annexes",annexes);
                            tip="上传成功！";
                        }else {
                            EmailSender emailSender=new EmailSender();
                            emailSender.errorReport("附件修改数据库出错","数据库修改出错！");
                            return;
                        }
                        session.setAttribute("tip", tip);
                        response.sendRedirect(request.getContextPath()+"/uploadAnnex.jsp");
                        return;
                    }
                }
                tip="没有选择文件";
                session.setAttribute("tip", tip);
                response.sendRedirect(request.getContextPath()+"/uploadAnnex.jsp");
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
