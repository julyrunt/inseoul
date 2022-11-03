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

public class Sidepage implements CommandInter{

  static Sidepage impl = new Sidepage();

  public static Sidepage instance(){
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
		System.out.println("5");
		int maxPage=(int)((double)listCount/limit+0.95); 
		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
		int endPage = startPage+10-1;

		if (endPage> maxPage) endPage= maxPage;


//		String startStr = request.getParameter("start_date").toString();
//		java.util.Date startD = new SimpleDateFormat("yyyy-MM-dd").parse(startStr);
//
//
//		String endStr = request.getParameter("end_date").toString();
//		java.util.Date endD = new SimpleDateFormat("yyyy-MM-dd").parse(endStr);
//
//
//
//		long calcul = endD.getTime() - startD.getTime();
//		int day = (int) (calcul / ( 24*60*60*1000)+1);
//		System.out.println("紐� 諛�? :"+day);
//
//		request.setAttribute("start", startD);
//		request.setAttribute("end", endD);
//		request.setAttribute("day", day);

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