package com.inseoul.action.with;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inseoul.action.Action;

import com.inseoul.svc.with.BoardListService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.WithBoardBean;
import com.inseoul.vo.PageInfo;

public class BoardListAction implements Action {
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("BoardListAction 도착!");
		ArrayList<WithBoardBean> articleList=new ArrayList<WithBoardBean>();
		
	  	int page=1;
		int limit=5;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		BoardListService boardListService = new BoardListService();
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
		
		ArrayList<Integer> d_Day = new ArrayList<Integer>();
		for(int i=0; i < articleList.size(); i++) {
			String mojibs = articleList.get(i).getMojib_limit().toString();
			String todayFM = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			Date mojib_limit = new Date(dateFormat.parse(mojibs).getTime()); //String값을 Date값으로 변환
			Date today = new Date(dateFormat.parse(todayFM).getTime());
			
			long calcul = mojib_limit.getTime() - today.getTime(); 	// e.getTime()은 1970년 00:00:00 부터 몇초가 흘렀는지 계산, 
																	// 디데이 기준이 되는 시간 - 현재시간 = 계산
			int d_days = (int) (calcul / ( 24*60*60*1000));			// 24*60*60*1000 값으로 위에 초를 나눠서 일수로 계산
			d_Day.add(d_days); 										// 일수로 계산된 값 
		}
		System.out.println(d_Day);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		request.setAttribute("d_Day", d_Day);
		
		ActionForward forward= new ActionForward();
   		forward.setPath("with.jsp");
   		return forward;
   		
	 }

}
