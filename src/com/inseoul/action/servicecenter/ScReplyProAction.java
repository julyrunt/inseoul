package com.inseoul.action.servicecenter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;

import com.inseoul.svc.servicecenter.ScReplyProService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.Servicecenter;

public class ScReplyProAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			 throws Exception{
				 
				 	ActionForward forward = null;
				    String nowPage = request.getParameter("page");
				 	Servicecenter article = new Servicecenter();  		
				 	article.setSid(Integer.parseInt(request.getParameter("sid")));
				 	article.setUid(Integer.parseInt(request.getParameter("uid")));
				 	article.setNick(request.getParameter("nick"));
				 	article.setTitle(request.getParameter("title"));
				 	article.setContents(request.getParameter("contents"));
				 	article.setRef(Integer.parseInt(request.getParameter("ref")));
				 	article.setSeq(Integer.parseInt(request.getParameter("seq")));	   		
				 	ScReplyProService scReplyProService = new ScReplyProService();
				 	boolean isReplySuccess = scReplyProService.replyArticle(article);
				 	
			   		if(isReplySuccess){
			   			forward = new ActionForward();
			   			forward.setRedirect(true);
			   			forward.setPath("ScList.sc");
			   		}
			   		else{
			   			response.setContentType("text/html;charset=UTF-8");
			   			PrintWriter out = response.getWriter();
			   			out.println("<script>");
			   			out.println("alert('fail')");
			   			out.println("history.back()");
			   			out.println("</script>");
			   		}
			   		
			   		return forward;
			   		
			}  	
}
