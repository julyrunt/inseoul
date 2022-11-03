package com.inseoul.action.servicecenter;

import static com.inseoul.db.JdbcUtil.close;
import static com.inseoul.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;
import com.inseoul.dao.ScDAO;
import com.inseoul.svc.servicecenter.ScDetailService;
import com.inseoul.svc.servicecenter.ScListService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.PageInfo;
import com.inseoul.vo.Servicecenter;
import com.inseoul.vo.UserBean;

public class ScListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Servicecenter> articleList=new ArrayList<Servicecenter>();
		ArrayList<Servicecenter> replyList = new ArrayList<Servicecenter>();
	  	int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}

		
		ScListService scListService = new ScListService();
		int listCount = scListService.getListCount();
		articleList = scListService.getArticleList(page,limit);
		replyList = scListService.getReply();
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
		request.setAttribute("replyList", replyList);
		ActionForward forward= new ActionForward();
   		forward.setPath("/servicecenter/index.jsp");
   		return forward;
	}

}
