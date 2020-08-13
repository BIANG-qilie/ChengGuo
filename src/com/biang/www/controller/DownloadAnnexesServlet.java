package com.biang.www.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/downloadAnnexes")
public class DownloadAnnexesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String fileName = request.getParameter("filename") ;
		response.addHeader("content-Type","application/octet-stream" );
		response.addHeader("content-Disposition","attachement;filename="+fileName );

		InputStream in = getServletContext().getResourceAsStream("/annexes/"+fileName) ;

		ServletOutputStream out = response.getOutputStream() ;
		byte[] bs = new byte[10];
		int len=-1 ;
		while(  (len=in.read(bs)) != -1) {
			out.write(bs,0,len);
		}
		out.close();
		in.close();
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
