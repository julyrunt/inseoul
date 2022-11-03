package com.inseoul.action.qna;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;
import com.inseoul.svc.qna.QnaReplyService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.QnABoardBean;

public class QnaReplyAction implements Action{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			 throws Exception{
		ActionForward forward = null;
	    String nowPage = request.getParameter("page");
	    QnABoardBean article = new QnABoardBean();
	    article.setQid(Integer.parseInt(request.getParameter("qid")));
	    article.setUid(Integer.parseInt(request.getParameter("uid")));
	    article.setContents(request.getParameter("reply"));
	    article.setRef(Integer.parseInt(request.getParameter("ref")));
	    article.setSeq(Integer.parseInt(request.getParameter("seq")));
	    article.setNick(request.getParameter("nick"));
	    QnaReplyService qnaReplyService = new QnaReplyService();
	    boolean isReplySuccess = qnaReplyService.replyArticle(article);
	    
	    if(isReplySuccess){
  			forward = new ActionForward();
  			forward.setRedirect(true);
  			forward.setPath("qnaDetail.qa?page="+nowPage+"&qid="+article.getQid()+"&ref="+article.getRef());
  		}
  		else{
  			response.setContentType("text/html;charset=UTF-8");
  			PrintWriter out = response.getWriter();
  			out.println("<script>");
  			out.println("alert('WithBoardReplyAction fail')");
  			out.println("history.back()");
  			out.println("</script>");
  		}
	    return forward;
	}
}
