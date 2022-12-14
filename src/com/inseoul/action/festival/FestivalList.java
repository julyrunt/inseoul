package com.inseoul.action.festival;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.festival.FestivalListModel;
import com.inseoul.vo.FestivalBean;
import com.inseoul.vo.PageInfo;

public class FestivalList implements CommandInter{

	static FestivalList impl = new FestivalList();

	public static FestivalList instance() {
		return impl;
	}
	
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FestivalList showData 도착~");
		FestivalListModel model = FestivalListModel.instance();
		ArrayList<FestivalBean> articlelist = new ArrayList<FestivalBean>();

		int page=1;
		int limit=8;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		int listCount= model.getListCount();
		articlelist = (ArrayList<FestivalBean>) model.selectAll(page);

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
		
		return "./";
	}
}
