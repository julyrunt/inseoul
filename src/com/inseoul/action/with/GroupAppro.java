package com.inseoul.action.with;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;
import com.inseoul.svc.with.GroupUpdate;
import com.inseoul.svc.with.WithGroupManageService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.WithBoardBean;

public class GroupAppro implements Action{
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("GroupAppro 액션 도착");
		ActionForward forward = new ActionForward();
		ArrayList<WithBoardBean> groups = new ArrayList<WithBoardBean>();
		String uid = (String) request.getParameter("uid");
		String wid = (String) request.getParameter("wid");
		
		GroupUpdate groupUpdate = new GroupUpdate();
		boolean isApproSuccess = groupUpdate.groupAppro(Integer.parseInt(wid), Integer.parseInt(uid));
		
		if(isApproSuccess) {
   			groups = WithGroupManageService.getGroup(Integer.parseInt(wid));
   			request.setAttribute("groups", groups);
   			request.setAttribute("wid", wid);
   			System.out.println("쫌");
   			forward.setPath("group-manage.jsp");
   		}
   		else{
   			response.setContentType("text/html;charset=UTF-8");
   			PrintWriter out = response.getWriter();
   			out.println("<script>");
   			out.println("alert('GroupAppro fail')");
   			out.println("history.back()");
   			out.println("</script>");
   		}
		return forward;
	}
}
