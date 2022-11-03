package com.inseoul.action.travels;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.travels.LocationIndexModel;
import com.inseoul.svc.with.BoardListService;
import com.inseoul.vo.PageInfo;
import com.inseoul.vo.Tourist_SpotBean;

public class LocationIndex implements CommandInter {

	static LocationIndex impl = new LocationIndex();

	public static LocationIndex instance() {
		return impl;
	}

	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LocationIndex showData 도착~");
		LocationIndexModel model = LocationIndexModel.instance();
		ArrayList<Tourist_SpotBean> articlelist = new ArrayList<Tourist_SpotBean>();

		int page=1;
		int limit=8;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		int listCount= model.getListCount();
		articlelist = (ArrayList<Tourist_SpotBean>) model.selectAllSpot(page);

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
