package com.inseoul.action.travels;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.travels.TravelsModel;
import com.inseoul.model.travels.TravelsReplyModel;

public class TravelsDelete implements CommandInter{
	
	static TravelsDelete impl = new TravelsDelete();

	public static TravelsDelete instance() {
		return impl;
	}
	
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("TravelsDelete showData 옴~");
		TravelsModel model = TravelsModel.instance();
		
		String tid = (String) request.getParameter("tid");
		
		model.deleteArticle(Integer.parseInt(tid));
		
		return "travelsList.tv";
	}
}
