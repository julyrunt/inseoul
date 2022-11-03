package com.inseoul.action.with;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;

import com.inseoul.svc.with.ReplyWriteService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.WithBoardBean;

public class WithBoardReplyAction implements Action{
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			 throws Exception{
		System.out.println("WithBoardReplyAction 도착! ");
		ActionForward forward = null;
	    String nowPage = request.getParameter("page");
	    WithBoardBean article = new WithBoardBean();
	    article.setWid(Integer.parseInt(request.getParameter("wid")));
	    article.setUid(Integer.parseInt(request.getParameter("uid")));
	    article.setContents(request.getParameter("reply"));
	    article.setRef(Integer.parseInt(request.getParameter("ref")));
	    article.setSeq(Integer.parseInt(request.getParameter("seq")));
	    article.setNick(request.getParameter("nick"));
	    ReplyWriteService withBoardReplyService = new ReplyWriteService();
	    boolean isReplySuccess = withBoardReplyService.replyArticle(article);
	    
	    if(isReplySuccess){
   			forward = new ActionForward();
   			forward.setRedirect(true);
   			forward.setPath("withDetail.with?page="+nowPage+"&wid="+article.getWid()+"&ref="+article.getRef());
   		}
   		else{
   			response.setContentType("text/html;charset=UTF-8");
   			PrintWriter out = response.getWriter();
   			out.println("<script>");
   			out.println("alert('WithBoardReplyAction fail')");
   			out.println("history.back()");
   			out.println("</script>");
   		}
	    System.out.println("WithBoardReplyAction 떠남! ");
	    return forward;
	}
}
