package com.inseoul.action.servicecenter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;

import com.inseoul.svc.servicecenter.ScDeleteProService;
import com.inseoul.vo.ActionForward;

public class ScDeleteProAction implements Action {
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{	 

		ActionForward forward = null;
		int sid=Integer.parseInt(request.getParameter("sid"));
		String page = request.getParameter("page");
		ScDeleteProService scDeleteProService = new ScDeleteProService();


			boolean isDeleteSuccess = scDeleteProService.removeArticle(sid);

			if(!isDeleteSuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('��������');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}
			else{
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("ScList.sc?page=" + page);
			}
		


		return forward;
	}
}
