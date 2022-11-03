package com.inseoul.action.qna;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;
import com.inseoul.svc.qna.QnaModifyService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.QnABoardBean;

public class QnaModifyAction implements Action{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		int qid=0;
		ActionForward forward = null;
		boolean isModifySuccess = false;
		qid = Integer.parseInt(request.getParameter("qid"));
		int ref = Integer.parseInt(request.getParameter("ref"));
		int nowPage = Integer.parseInt(request.getParameter("page"));
		
		if(  qid == 0)
			qid = 1;
		QnABoardBean article=new QnABoardBean();
		article.setQid(qid);
		article.setTitle(request.getParameter("title"));
		article.setContents(request.getParameter("contents")); 
		QnaModifyService withBoardModifyService = new QnaModifyService();
		isModifySuccess = withBoardModifyService.modifyArticle(article);

			if(!isModifySuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('수정 실패');");
				out.println("history.back()");
				out.println("</script>");
			}
			else{
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("qnaDetail.qa?qid="+article.getQid()+"&ref="+ref+"&page="+nowPage); 
			}
		return forward;
	}
}
