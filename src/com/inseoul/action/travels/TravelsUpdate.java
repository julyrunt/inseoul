package com.inseoul.action.travels;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.travels.TravelsModel;
import com.inseoul.vo.Tourist_SpotBean;
import com.inseoul.vo.TravelsBean;

public class TravelsUpdate implements CommandInter{
	
	static TravelsUpdate impl = new TravelsUpdate();

	public static TravelsUpdate instance() {
		return impl;
	}
	
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("TravelsUpdate showData 옴~");
		TravelsBean article = null;
		Tourist_SpotBean spot = null;
		TravelsModel model = new TravelsModel();
		String tid = (String) request.getParameter("tid");
		String lid = (String) request.getParameter("lid");
		
		article = model.updateArticle(Integer.parseInt(tid));
		spot = model.getArticle(Integer.parseInt(lid));
		
		request.setAttribute("tid", Integer.parseInt(tid));
		request.setAttribute("article", article);
		request.setAttribute("spot", spot);
		
		return "travels-update.jsp";
	}
}
