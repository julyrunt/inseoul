package com.inseoul.action.qna;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;
import com.inseoul.svc.qna.QnaDetailService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.QnABoardBean;

public class QnaDetailAction implements Action{
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
		   	
			int qid=Integer.parseInt(request.getParameter("qid"));
			int ref=Integer.parseInt(request.getParameter("ref"));
			String page = request.getParameter("page");
			ArrayList<QnABoardBean> replyList=new ArrayList<QnABoardBean>();
			QnaDetailService qnaDetailService = new QnaDetailService();
			QnABoardBean article = qnaDetailService.getArticle(qid, ref);
			replyList = qnaDetailService.getReply(ref);
			ActionForward forward = new ActionForward();
			request.setAttribute("page", page);
		   	request.setAttribute("article", article);
			request.setAttribute("replyList", replyList);
	   		forward.setPath("qna-detail.jsp");
	   		return forward;

		 }
}
