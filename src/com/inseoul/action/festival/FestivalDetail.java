package com.inseoul.action.festival;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.festival.FestivalDetailModel;
import com.inseoul.vo.FestivalBean;

public class FestivalDetail implements CommandInter{

	static FestivalDetail impl = new FestivalDetail();

	public static FestivalDetail instance() {
		return impl;
	}
	
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FestivalDetail showData 옴~");
		FestivalDetailModel model = FestivalDetailModel.instance();
		FestivalBean article = new FestivalBean();
		
		String fid = (String) request.getParameter("fid");
		
		article = model.selectOne(Integer.parseInt(fid));
		request.setAttribute("article", article);
		
		return "festival-detail.jsp";
	}
}
