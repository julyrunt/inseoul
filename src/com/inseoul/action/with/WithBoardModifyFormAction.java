package com.inseoul.action.with;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;

import com.inseoul.svc.with.WithBoardDetailService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.WithBoardBean;;

public class WithBoardModifyFormAction implements Action{
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("WithBoardModifyFormAction 도착!");
	 	ActionForward forward = new ActionForward();
		int wid=Integer.parseInt(request.getParameter("wid"));
		int ref=Integer.parseInt(request.getParameter("ref"));
		int page = Integer.parseInt(request.getParameter("page"));
		System.out.println("WithBoardModifyFormAction 페이지: "+ request.getParameter("page"));
		WithBoardDetailService withBoardDetailService = new WithBoardDetailService();	
		WithBoardBean article =withBoardDetailService.getArticle(wid, ref);
	   	request.setAttribute("article", article);
	   	
	   	
   		forward.setPath("with_update.jsp?page="+page);
   		return forward;
   		
 }
}
