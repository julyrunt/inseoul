package com.inseoul.action.with;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inseoul.action.Action;

import com.inseoul.svc.with.ReplyWriteService;

import com.inseoul.vo.ActionForward;
import com.inseoul.vo.WithBoardBean;

public class ReplyWriteAction implements Action{
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		System.out.println("ReplyWriteAction 도착");
		ActionForward forward=null;
		WithBoardBean withBoardBean = null;
		
		request.setCharacterEncoding("UTF-8");
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("uid");
		String nick = (String) session.getAttribute("nick");
		int ref = Integer.parseInt(request.getParameter("ref"));
		withBoardBean = new WithBoardBean();
		withBoardBean.setUid(Integer.parseInt(uid));
		withBoardBean.setNick(nick);
		withBoardBean.setContents(request.getParameter("contents"));
		withBoardBean.setRef(ref);
		
		ReplyWriteService replyWriteService = new ReplyWriteService();
		boolean isWriteSuccess = replyWriteService.replyArticle(withBoardBean);
		System.out.println(isWriteSuccess);
		
		if(!isWriteSuccess){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글쓰기 fail')");
			out.println("history.back();");
			out.println("</script>");
		}else{
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("replyList.with?ref="+ref);
		}
		
		return forward;
	}
}
