package com.inseoul.action.with;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;

import com.inseoul.svc.with.WithBoardDeleteService;
import com.inseoul.vo.ActionForward;

public class WithBoardDeleteAction implements Action {
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{	 
		ActionForward forward = null;
		int wid = Integer.parseInt(request.getParameter("wid"));
		int ref = Integer.parseInt(request.getParameter("ref"));
		String nowPage = request.getParameter("page");
		
		WithBoardDeleteService withBoardDeleteService = new WithBoardDeleteService();
		boolean isDeleteSuccess = withBoardDeleteService.removeArticle(wid);
		
		if(!isDeleteSuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 실패');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		else{
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("boardList.with?page=" + nowPage);
		}
		
	
		return forward;
	}
	}

