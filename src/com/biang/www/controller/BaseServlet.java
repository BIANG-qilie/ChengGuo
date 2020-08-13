package com.biang.www.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

public class BaseServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            String methodName = null;
            if (!isMultipart) {
                // 获取请求标识
                methodName= request.getParameter("method");
            }else {
                Iterator<FileItem> iter = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request).iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    String itemName = item.getFieldName();
                    if (item.isFormField()) {
                        if ("method".equals(itemName)) {
                            methodName = item.getString("UTF-8");
                        }
                    }
                }
            }
            // 获取指定类的字节码对象
            // 这里的this指的是继承BaseServlet对象
            Class<? extends BaseServlet> clazz = this.getClass();
            // 通过类的字节码对象获取方法的字节码对象
            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 让方法执行
            method.invoke(this, request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}