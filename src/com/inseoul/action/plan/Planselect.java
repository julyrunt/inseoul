package com.inseoul.action.plan;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.plan.LocationModel;
import com.inseoul.model.plan.SelectplanModel;
import com.inseoul.vo.LocationBean;
import com.inseoul.vo.PageInfo;
import com.inseoul.vo.Routemaps;

public class Planselect implements CommandInter{
	static Planselect impl = new Planselect();
	 
	 public static Planselect instance(){
		    return impl;
	 }
	 @Override
	  public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  
		  	SelectplanModel model = SelectplanModel.instance();
		  	int page=1;
			int limit=5;
		
			if(request.getParameter("page")!=null){
				page=Integer.parseInt(request.getParameter("page"));
			}

			ArrayList<Routemaps> lidlist= (ArrayList<Routemaps>) model.selectname(page);
			
			
			
			
			int listCount=model.getListCount();
			System.out.println("5");
			System.out.println(listCount);
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
	
			request.setAttribute("lids", lidlist);
			return "../board/index.jsp";
	  }
}
