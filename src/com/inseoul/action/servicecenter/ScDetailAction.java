package com.inseoul.action.servicecenter;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;
import com.inseoul.svc.servicecenter.ScDetailService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.Servicecenter;

public class ScDetailAction implements Action {
public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
	    ArrayList<Servicecenter> reply=new ArrayList<Servicecenter>();
		int sid=Integer.parseInt(request.getParameter("sid"));
		int ref=Integer.parseInt(request.getParameter("ref"));
		String page = request.getParameter("page");
		ScDetailService scDetailService = new ScDetailService();
		Servicecenter article = scDetailService.getArticle(sid);
//		reply = scDetailService.getReply(ref);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
	   	request.setAttribute("article", article);
	   	request.setAttribute("reply", reply);
   		forward.setPath("/servicecenter/sc-reply.jsp");
   		return forward;
	 }
}
