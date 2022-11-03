package com.inseoul.action.servicecenter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;

import com.inseoul.svc.servicecenter.ScDetailService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.Servicecenter;

public class ScModifyFormAction implements Action {
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
	 	ActionForward forward = new ActionForward();
		int sid=Integer.parseInt(request.getParameter("sid"));
		ScDetailService scDetailService = new ScDetailService();	
	   	Servicecenter article =scDetailService.getArticle(sid);
	   	request.setAttribute("article", article);
   		forward.setPath("/servicecenter/sc-modify.jsp");
   		return forward;
   		
 }
}
