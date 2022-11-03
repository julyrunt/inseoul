package com.inseoul.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;
import com.inseoul.action.servicecenter.ScDeleteProAction;
import com.inseoul.action.servicecenter.ScDetailAction;
import com.inseoul.action.servicecenter.ScListAction;
import com.inseoul.action.servicecenter.ScModifyFormAction;
import com.inseoul.action.servicecenter.ScModifyProAction;
import com.inseoul.action.servicecenter.ScReplyFormAction;
import com.inseoul.action.servicecenter.ScReplyProAction;
import com.inseoul.action.servicecenter.ScWriteProAction;
import com.inseoul.vo.ActionForward;

@WebServlet("*.sc")
public class ServiceController  extends javax.servlet.http.HttpServlet  {
	protected void scProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		ActionForward forward=null;
		Action action=null;
		

		 if(command.equals("/servicecenter/ScList.sc")) {
			 action = new ScListAction();
			 try {
					forward=action.execute(request, response );
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
		 else if(command.equals("/servicecenter/ScWritePro.sc")){
			 action = new ScWriteProAction();
			 try {
					forward=action.execute(request, response );
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
//		 else if(command.equals("/servicecenter/ScList2.sc")) {
//			 System.out.println("!23");
//			 action = new ScListAction2();
//			 System.out.println("!23");
//			 try {
//					forward=action.execute(request, response );
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//		 }
		 else if(command.equals("/servicecenter/ScDetail.sc")){
				action = new ScDetailAction();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		 else if(command.equals("/servicecenter/ScReplyForm.sc")){
				action = new ScReplyFormAction();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			else if(command.equals("/servicecenter/ScReplyPro.sc")){
				action = new ScReplyProAction();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			else if(command.equals("/servicecenter/ScModifyForm.sc")){
				action = new ScModifyFormAction();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			}else if(command.equals("/servicecenter/ScModifyPro.sc")){
				System.out.println("Pro.sc 도착");
				action = new ScModifyProAction();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			}else if(command.equals("/servicecenter/ScDeleteForm.sc")){
				String nowPage = request.getParameter("page");
				request.setAttribute("page", nowPage);
				int sid=Integer.parseInt(request.getParameter("sid"));
				request.setAttribute("sid",sid);
				forward=new ActionForward();
				forward.setPath("/삭제.jsp");
			}
			else if(command.equals("/servicecenter/ScDeletePro.sc")){
				System.out.println("Delete.sc 도착");
				action = new ScDeleteProAction();
				try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		 if(forward != null){
				
				if(forward.isRedirect()){
					response.sendRedirect(forward.getPath());
				}else{
					RequestDispatcher dispatcher=
							request.getRequestDispatcher(forward.getPath());
					dispatcher.forward(request, response);
				}
				
			}
 }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		scProcess(request,response);
	}  	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		scProcess(request,response);
	}   
}
