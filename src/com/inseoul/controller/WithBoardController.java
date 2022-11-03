package com.inseoul.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inseoul.action.Action;
import com.inseoul.action.with.*;
import com.inseoul.vo.ActionForward;

@WebServlet("*.with")
public class WithBoardController extends javax.servlet.http.HttpServlet{ 
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("WithBoardController 왔다!");
		
		System.out.println();
		request.setCharacterEncoding("UTF-8");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		ActionForward forward=null;
		Action action=null;
		
		/* 글 작성 DB에 넣기  */
		if(command.equals("/board/WithBoardWriteAction.with")){
			action  = new WithBoardWriteAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/* 글 리스트 보기 작성날짜 역순 */
		else if(command.equals("/board/boardList.with")){
			System.out.println("boardList.with 실행좀!");
			action  = new BoardListAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/* 글 자세히 보기 */
		else if(command.equals("/board/withDetail.with")){
			System.out.println("withDetail.with 실행좀!");
			action  = new WithBoardDetailAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/* 댓글 작성 */
		else if(command.equals("/board/replyWrite.with")){
			System.out.println("replyWrite.with 실행!");
			action  = new ReplyWriteAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/* 댓글 리스트 보기 */
		else if(command.equals("/board/replyList.with")){
			System.out.println("replyList.with 실행좀!");
			action  = new ReplyListAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/* 글 삭제 */
		else if(command.equals("/board/withDelete.with")){
			System.out.println("withDelete.with 실행좀!");
			action  = new WithBoardDeleteAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/* 댓글 삭제 */
		else if(command.equals("/board/replyDelete.with")){
			System.out.println("replyDelete.with 실행좀!");
			action  = new ReplyDeleteAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/* 글 수정 */
		else if(command.equals("/board/withModifyForm.with")){
			System.out.println("withModifyForm.with 실행좀!");
			action  = new WithBoardModifyFormAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/* 글 수정 */
		else if(command.equals("/board/withModify.with")){
			System.out.println("withModify.with 실행좀!");
			action  = new WithBoardModifyAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/* 댓글 */
		else if(command.equals("/board/withReply.with")){
			System.out.println("withReply.with 실행좀!");
			action  = new WithBoardReplyAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/* 그룹 신청, 취소 */
		else if(command.equals("/board/withGroup.with")){
			System.out.println("withGroup.with 실행좀!");
			action  = new WithGroup();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/* 그룹 관리창 오픈 */
		else if(command.equals("/board/groupManage.with")){
			System.out.println("groupManage.with 실행좀!");
			action  = new WithGroupManage();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/* 그룹 승인 */
		else if(command.equals("/board/groupManageAppro.with")){
			System.out.println("groupManageAppro.with 실행좀!");
			action  = new GroupAppro();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/board/groupManagebanned.with")){
			System.out.println("groupManagebanned.with 실행좀!");
			action  = new GroupBanned();
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
