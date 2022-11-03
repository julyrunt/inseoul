package com.inseoul.action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;
import com.inseoul.svc.qna.QnaDetailService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.QnABoardBean;


public class QnaModifyFormAction implements Action {
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
	 	ActionForward forward = new ActionForward();
		int wid=Integer.parseInt(request.getParameter("qid"));
		int ref=Integer.parseInt(request.getParameter("ref"));
		int page = Integer.parseInt(request.getParameter("page"));
		QnaDetailService qnaDetailService = new QnaDetailService();	
		QnABoardBean article =qnaDetailService.getArticle(wid, ref);
	   	request.setAttribute("article", article);
	   	
	   	
   		forward.setPath("qna-update.jsp?page="+page);
   		return forward;
   		
 }
}
