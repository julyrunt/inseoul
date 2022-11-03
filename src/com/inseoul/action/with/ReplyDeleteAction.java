package com.inseoul.action.with;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;
import com.inseoul.svc.with.ReplyDeleteService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.WithBoardBean;

public class ReplyDeleteAction implements Action {
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{	 
		ActionForward forward = null;
		int wid = Integer.parseInt(request.getParameter("wid"));
		int ref = Integer.parseInt(request.getParameter("ref"));
		ReplyDeleteService replyDeleteService = new ReplyDeleteService();
		boolean isDeleteSuccess = replyDeleteService.removeArticle(wid);
		WithBoardBean article = null;
		
		if(!isDeleteSuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('댓글삭제 실패');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		else{
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("replyList.with?ref="+ref);
		}
		
		return forward;
	}
	}

