package com.inseoul.action.qna;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;
import com.inseoul.svc.qna.QnAlistService;
import com.inseoul.vo.QnABoardBean;

import com.inseoul.vo.ActionForward;
import com.inseoul.vo.PageInfo;

public class QnAlistAction implements Action{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		ArrayList<QnABoardBean> articleList=new ArrayList<QnABoardBean>();
	  	int page=1;
		int limit=25;
		
		if(request.getParameter("page")!=null){
			System.out.println("#33"+request.getParameter("page") );
			page=Integer.parseInt(request.getParameter("page"));
		}

		
		QnAlistService boardListService = new QnAlistService();
		int listCount=boardListService.getListCount();
		articleList = boardListService.getArticleList(page,limit);
   		int maxPage=(int)((double)listCount/limit+0.95); 
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   	    int endPage = startPage+10-1;

   		if (endPage> maxPage) endPage= maxPage;

   		PageInfo pageInfo = new PageInfo();
   		pageInfo.setEndPage(endPage);
   		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		ActionForward forward= new ActionForward();
   		forward.setPath("qna.jsp");
   		return forward;
   		
	 }
}
