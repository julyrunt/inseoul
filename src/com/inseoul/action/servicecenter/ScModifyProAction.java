package com.inseoul.action.servicecenter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;

import com.inseoul.svc.servicecenter.ScModifyProService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.Servicecenter;

public class ScModifyProAction implements Action {
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		System.out.println("1");
		int sid=0;
		ActionForward forward = null;
		boolean isModifySuccess = false;
		sid=Integer.parseInt(request.getParameter("sid"));
		System.out.println("ScModifyProAction 도착"+sid);
		String page = request.getParameter("page");
		System.out.println("ScModifyProAction 도착1"+page);
		if(  sid == 0)
			sid = 1;
		System.out.println("ScModifyProAction 도착2");
		Servicecenter article=new Servicecenter();
		article.setSid(sid);
		article.setTitle(request.getParameter("title"));
		article.setContents(request.getParameter("contents"));
		ScModifyProService scModifyProService = new ScModifyProService();
		isModifySuccess = scModifyProService.modifyArticle(article);
		System.out.println("ScModifyProAction 도착3");
		
		//이제 수정하기까지 됨 컨트롤로가서 다음꺼 ㄱㄱ

			if(!isModifySuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('��������');");
				out.println("history.back()");
				out.println("</script>");
			}
			else{
				forward = new ActionForward();
				forward.setRedirect(true);
				System.out.println("Detail넘겨줌");
				forward.setPath("ScList.sc?sid="+article.getSid()+"&page="+page); 
			}

		return forward;
	}
}
