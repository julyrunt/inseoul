package com.inseoul.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.festival.*;

public class FestivalController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		CommandInter inter = null;
		String viewName = "";
		
		try {
			/* 인덱스 전체 출력 */
			if(command.equals("/festival/festivalList.val")){
				System.out.println("festivalList.val 가자");
				inter = FestivalList.instance();
				viewName = inter.showData(request, response);
				viewName = "/festival/"+viewName;
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			/* 축제 상세보기 */
			else if(command.equals("/festival/festivalDetail.val")){
				System.out.println("festivalDetail.val ㄱㄱ");
				inter = FestivalDetail.instance();
				viewName = inter.showData(request, response); 
				viewName = "/festival/"+viewName; 
				request.getRequestDispatcher(viewName).forward(request, response);
			}
			
			else {
				viewName = "error.html";
				response.sendRedirect(viewName);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
