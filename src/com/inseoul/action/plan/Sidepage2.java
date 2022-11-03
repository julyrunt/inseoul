package com.inseoul.action.plan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.controller.CommandInter;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.LocationBean;
import com.inseoul.vo.PageInfo;
import com.inseoul.vo.QnABoardBean;
import com.inseoul.model.plan.LocationModel;
import com.inseoul.svc.qna.QnAlistService;

public class Sidepage2 implements CommandInter{

  static Sidepage2 impl = new Sidepage2();

  public static Sidepage2 instance(){
    return impl;
  }

  @Override
  public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
	  
	  	LocationModel model = LocationModel.instance();
	  	int page=1;
		int limit=15;
	
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}

		ArrayList<LocationBean> list= (ArrayList<LocationBean>) model.getArticleList(page);
		ArrayList<LocationBean> dto = (ArrayList<LocationBean>) model.selectLocation();
		
		int listCount=model.getListCount();
		int maxPage=(int)((double)listCount/limit+0.95); 
		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
		int endPage = startPage+10-1;

		if (endPage> maxPage) endPage= maxPage;


		int day = 1;

		request.setAttribute("day", day);

   		PageInfo pageInfo = new PageInfo();
   		pageInfo.setEndPage(endPage);
   		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", list);
		request.setAttribute("location", dto);
		return "index.jsp";
  }
 }