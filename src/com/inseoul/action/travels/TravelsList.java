package com.inseoul.action.travels;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.travels.LocationIndexModel;
import com.inseoul.model.travels.TravelsListModel;
import com.inseoul.vo.PageInfo;
import com.inseoul.vo.Tourist_SpotBean;
import com.inseoul.vo.TravelsBean;

public class TravelsList implements CommandInter{
	
	static TravelsList impl = new TravelsList();

	public static TravelsList instance() {
		return impl;
	}

	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("TravelsList showData 도착~");
		TravelsListModel model = TravelsListModel.instance();
		ArrayList<TravelsBean> articlelist = new ArrayList<TravelsBean>();

		int page=1;
		int limit=5;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		int listCount= model.getListCount();
		articlelist = (ArrayList<TravelsBean>) model.selectAllTravels(page);

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
		request.setAttribute("articlelist", articlelist);
		
		return "travels-list.jsp";
	}
}
