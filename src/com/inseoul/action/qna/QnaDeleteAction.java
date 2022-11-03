package com.inseoul.action.qna;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;
import com.inseoul.svc.qna.QnaDeleteService;
import com.inseoul.vo.ActionForward;

public class QnaDeleteAction implements Action{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{	 
		ActionForward forward = null;
		int qid = Integer.parseInt(request.getParameter("qid"));
		String nowPage = request.getParameter("page");
		QnaDeleteService qnaDeleteService = new QnaDeleteService();
		boolean isDeleteSuccess = qnaDeleteService.removeArticle(qid);

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
			forward.setPath("qnaList.qa?page=" + nowPage);
		}


		return forward;
	}
}
