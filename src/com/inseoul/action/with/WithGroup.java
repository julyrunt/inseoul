package com.inseoul.action.with;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inseoul.action.Action;
import com.inseoul.svc.album.AlbumDetailService;
import com.inseoul.svc.with.WithGroupService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.AlbumBean;

public class WithGroup implements Action{
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("WithGroup execute 도착~");
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("uid");
		String wid = (String) request.getParameter("wid");
		WithGroupService withGroupService = new WithGroupService();
		boolean isGroupSuccess = withGroupService.updateGroup(Integer.parseInt(uid), Integer.parseInt(wid));
		
		if(isGroupSuccess){
   			forward = new ActionForward();
   			int stateCount = withGroupService.stateGroup(Integer.parseInt(uid), Integer.parseInt(wid));
   			request.setAttribute("stateCount", stateCount);
   			forward.setPath("groupbutton.jsp");
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
