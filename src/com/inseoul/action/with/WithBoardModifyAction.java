package com.inseoul.action.with;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSeparatorUI;

import com.inseoul.action.Action;

import com.inseoul.svc.with.WithBoardModifyService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.WithBoardBean;

public class WithBoardModifyAction implements Action {
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		System.out.println("WithBoardModifyAction 도착! ");
		int wid=0;
		ActionForward forward = null;
		boolean isModifySuccess = false;
		wid = Integer.parseInt(request.getParameter("wid"));
		int ref = Integer.parseInt(request.getParameter("ref"));
		int nowPage = Integer.parseInt(request.getParameter("page"));
		
		String mojib_limitStr = request.getParameter("mojib_limit").toString();
		java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(mojib_limitStr);
		Date mojib_limitDate = new Date(utilDate.getTime());
		
		String trip_startStr = request.getParameter("trip_start").toString();
		java.util.Date tripS = new SimpleDateFormat("yyyy-MM-dd").parse(trip_startStr);
		Date trip_startDate = new Date(tripS.getTime());
		
		String trip_endStr = request.getParameter("trip_end").toString();
		java.util.Date tripE = new SimpleDateFormat("yyyy-MM-dd").parse(trip_endStr);
		Date trip_endDate = new Date(tripE.getTime());
		if( wid == 0)
			wid = 1;
		WithBoardBean article=new WithBoardBean();
		article.setWid(wid);
		article.setTitle(request.getParameter("title"));
		article.setContents(request.getParameter("contents"));
		article.setMojib_limit(mojib_limitDate);
		article.setTrip_start(trip_startDate);
		article.setTrip_end(trip_endDate);
		article.setDues(Integer.parseInt(request.getParameter("dues")));
		article.setMojib_person(request.getParameter("mojib_person"));
		WithBoardModifyService withBoardModifyService = new WithBoardModifyService();
		isModifySuccess = withBoardModifyService.modifyArticle(article);
		
			if(!isModifySuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('수정 실패');");
				out.println("history.back()");
				out.println("</script>");
			}
			else{
				forward = new ActionForward();
				forward.setRedirect(true); 
				forward.setPath("withDetail.with?wid="+article.getWid()+"&ref="+ref+"&page="+nowPage); 
			}
			System.out.println("WithBoardModifyAction 떠남! ");
		return forward;
	}
}
