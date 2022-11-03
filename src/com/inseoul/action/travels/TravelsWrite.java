package com.inseoul.action.travels;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.travels.LocationDetailModel;
import com.inseoul.model.travels.TravelsModel;
import com.inseoul.vo.Tourist_SpotBean;

public class TravelsWrite implements CommandInter{
	static TravelsWrite impl = new TravelsWrite();

	public static TravelsWrite instance() {
		return impl;
	}
	
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("TravelsWrite showData 옴~");
		TravelsModel model = TravelsModel.instance();
		Tourist_SpotBean article = new Tourist_SpotBean();
		
		String lid = (String) request.getParameter("lid");
		
		article = model.getArticle(Integer.parseInt(lid));
		
		request.setAttribute("article", article);
		
		return "travels-write.jsp";
	}
}
