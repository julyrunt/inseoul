package com.inseoul.action.with;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inseoul.action.Action;
import com.inseoul.svc.with.WithGroupManageService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.WithBoardBean;

public class WithGroupManage implements Action{
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("WithGroupManage 도착!");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("uid");
		String wid = (String) request.getParameter("wid");
		
		ArrayList<WithBoardBean> groups = new ArrayList<WithBoardBean>();
		WithGroupManageService withGroupManageService = new WithGroupManageService();
		groups = withGroupManageService.getGroup(Integer.parseInt(wid));
		
		request.setAttribute("groups", groups);
		request.setAttribute("wid", wid);
		
		forward.setPath("group-management.jsp");
		return forward;
	}

}
