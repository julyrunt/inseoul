package com.inseoul.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inseoul.action.Action;
import com.inseoul.action.qna.QnAlistAction;
import com.inseoul.action.qna.QnAwriteAction;
import com.inseoul.action.qna.QnaDeleteAction;
import com.inseoul.action.qna.QnaDetailAction;
import com.inseoul.action.qna.QnaModifyAction;
import com.inseoul.action.qna.QnaModifyFormAction;
import com.inseoul.action.qna.QnaReplyAction;
import com.inseoul.vo.ActionForward;

@WebServlet("*.qa")
public class QnaController extends javax.servlet.http.HttpServlet{ 
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		System.out.println();
		request.setCharacterEncoding("UTF-8");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		ActionForward forward=null;
		Action action=null;
		/* 글 작성 DB에 넣기  */
		if(command.equals("/board/qnaWritePro.qa")){
			System.out.println("qnaWritePro.qa");
			action  = new QnAwriteAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/* 글 리스트 보기 */
		else if(command.equals("/board/qnaList.qa")){
			System.out.println("qnaList.qa");
			action  = new QnAlistAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/* 글 자세히 보기 */
		else if(command.equals("/board/qnaDetail.qa")){
			System.out.println("qnaDetail.qa controll");
			action  = new QnaDetailAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/* 글 삭제 */
		else if(command.equals("/board/qnaDelete.qa")){
			action  = new QnaDeleteAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/* 글 수정 */
		else if(command.equals("/board/qnaModifyForm.qa")){;
			action  = new QnaModifyFormAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/* 글 수정 */
		else if(command.equals("/board/qnaModify.qa")){
			action  = new QnaModifyAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/* 댓글 */
		else if(command.equals("/board/qnaReply.qa")){
			action  = new QnaReplyAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
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
		doProcess(request,response);
	}  	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request,response);
	}   
}